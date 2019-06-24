package dal.zeynep.miniklarna;

import dal.zeynep.miniklarna.dto.UserDto;
import dal.zeynep.miniklarna.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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

    public UserDto getUserDebt(String userEmail){
        User user = getUserDetail(userEmail);
        return new UserDto(user.getTotalDebt());
    }

    public User getOrCreateUser(String userEmail) {

        User user = getUserDetail(userEmail);
        if (user == null) {
            user = new User(userEmail);
        }

        return user;
    }

    public void saveOrUpdateUser(User user){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(user);
        session.getTransaction().commit();
        session.close();
    }
}
