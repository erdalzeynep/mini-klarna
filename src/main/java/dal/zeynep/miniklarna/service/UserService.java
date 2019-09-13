package dal.zeynep.miniklarna.service;

import dal.zeynep.miniklarna.HibernateUtil;
import dal.zeynep.miniklarna.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Component
public class UserService {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public User getUserDetail(String userEmail) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);

        Root<User> root = query.from(User.class);
        query.select(root).where(builder.equal(root.get("email"), userEmail));
        User user = session.createQuery(query).uniqueResult();
        session.close();
        return user;
    }

    public User authenticate(String userEmail, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);

        Root<User> root = query.from(User.class);

        query.select(root).where(
                builder.equal(root.get("email"), userEmail),
                builder.equal(root.get("password"), password)
        );
        User user = session.createQuery(query).uniqueResult();
        session.close();
        return user;
    }

    public User newUser(String email, String password) {
        User user = new User(email, password);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
        return user;
    }

    public int getUserDebt(String userEmail) {
        User user = getUserDetail(userEmail);
        return user.getTotalDebt();
    }

    public void saveOrUpdateUser(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(user);
        session.getTransaction().commit();
        session.close();
    }
}
