package dal.zeynep.miniklarna.service;

import dal.zeynep.miniklarna.HibernateUtil;
import dal.zeynep.miniklarna.dto.OrderDto;
import dal.zeynep.miniklarna.model.OrderModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public List<OrderDto> getUserOrders(String userEmail) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<OrderModel> query = builder.createQuery(OrderModel.class);

        Root<OrderModel> root = query.from(OrderModel.class);
        query.select(root).where(builder.equal(root.get("userEmail"), userEmail));
        List<OrderModel> orders = session.createQuery(query).getResultList();
        List<OrderDto> orderDtos = new ArrayList<>();
        for (OrderModel orderModel : orders){
            orderDtos.add(new OrderDto(orderModel.getOrderId(), orderModel.isPaid(), orderModel.isSuccessful(), orderModel.getPrice()));
        }
        session.close();
        return orderDtos;
    }

    public OrderModel getOrderDetail(int orderId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<OrderModel> query = builder.createQuery(OrderModel.class);

        Root<OrderModel> root = query.from(OrderModel.class);
        query.select(root).where(builder.equal(root.get("orderId"), orderId));
        OrderModel order = session.createQuery(query).uniqueResult();
        session.close();
        return order;
    }

    public void saveOrUpdateOrder(OrderModel order) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(order);
        session.getTransaction().commit();
        session.close();
    }
}
