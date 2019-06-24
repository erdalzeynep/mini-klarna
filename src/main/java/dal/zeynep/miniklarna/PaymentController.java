package dal.zeynep.miniklarna;

        import dal.zeynep.miniklarna.dto.OrderDto;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;
        import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private final PaymentService paymentService = new PaymentService();

    @RequestMapping(value = "/purchase/{userEmail}/{price}", method = RequestMethod.POST)
    public OrderDto purchase(@PathVariable("userEmail") String userEmail,
                             @PathVariable("price") Integer price) {
        return paymentService.purchase(userEmail, price);
    }
}
