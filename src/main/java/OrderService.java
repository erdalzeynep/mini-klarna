import model.OrderModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class OrderService {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public List<OrderModel> getUserOrders(String userEmail) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<OrderModel> query = builder.createQuery(OrderModel.class);

        Root<OrderModel> root = query.from(OrderModel.class);
        query.select(root).where(builder.equal(root.get("userEmail"), userEmail));
        List<OrderModel> orders = session.createQuery(query).getResultList();

        session.close();
        return orders;
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
