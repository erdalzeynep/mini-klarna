package integrationtest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dal.zeynep.miniklarna.Application;
import dal.zeynep.miniklarna.dto.OrderDto;
import dal.zeynep.miniklarna.model.OrderModel;
import dal.zeynep.miniklarna.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
public class OrderControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @MockBean
    private OrderService orderService;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @WithMockUser(value = "spring")
    @Test
    public void shouldReturnAuthenticatedUsersOrderList() throws Exception {
        List<OrderModel> orderList = new ArrayList<>();
        OrderModel unpaidOrder = new OrderModel("spring", 30, true);
        OrderModel paidOrder = new OrderModel("spring", 40, false);
        paidOrder.setPaid(true);
        orderList.add(paidOrder);
        orderList.add(unpaidOrder);

        given(orderService.getUserOrders("spring")).willReturn(orderList);

        ObjectMapper mapper = new ObjectMapper();
        OrderDto paidOrderDto = new OrderDto(paidOrder.getOrderId(), paidOrder.isPaid(), paidOrder.getIsSuccessful(), paidOrder.getPrice());
        OrderDto unpaidOrderDto = new OrderDto(unpaidOrder.getOrderId(), unpaidOrder.isPaid(), unpaidOrder.getIsSuccessful(), unpaidOrder.getPrice());

        Map<String, Object> expectedPaidOrder = mapper.convertValue(
                paidOrderDto,
                new TypeReference<Map<String, Object>>() {
                });

        Map<String, Object> expectedUnpaidOrder = mapper.convertValue(
                unpaidOrderDto,
                new TypeReference<Map<String, Object>>() {
                });

        mvc.perform(get("/getOrders")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orders", hasSize(2)))
                .andExpect(jsonPath("$.orders[*]", hasItem(expectedPaidOrder)))
                .andExpect(jsonPath("$.orders[*]", hasItem(expectedUnpaidOrder)));
    }
}
