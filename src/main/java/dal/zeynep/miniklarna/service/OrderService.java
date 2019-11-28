package dal.zeynep.miniklarna.service;

import dal.zeynep.miniklarna.HibernateUtil;
import dal.zeynep.miniklarna.model.OrderModel;
import dal.zeynep.miniklarna.repository.OrderRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrderService {

    private final OrderRepository repository;

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public List<OrderModel> getUserOrders(String userEmail) {

        return repository.findOrdersByUserEmail(userEmail);
    }

    public OrderModel getOrderDetail(long orderId) {
        Optional order = repository.findById(orderId);
        return (order.isPresent()) ? (OrderModel) order.get() : null;
    }

    public void saveOrUpdateOrder(OrderModel order) {
        repository.save(order);
    }
}
