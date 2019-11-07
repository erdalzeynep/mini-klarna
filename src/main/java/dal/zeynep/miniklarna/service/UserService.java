package dal.zeynep.miniklarna.service;

import dal.zeynep.miniklarna.HibernateUtil;
import dal.zeynep.miniklarna.model.User;
import dal.zeynep.miniklarna.repository.UserRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Component
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User getUserDetail(String userEmail) {
        return repository.findUserByEmail(userEmail);
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
        this.saveOrUpdateUser(user);
        return user;
    }

    public int getUserDebt(String userEmail) {
        User user = getUserDetail(userEmail);
        return user.getTotalDebt();
    }

    public void saveOrUpdateUser(User user) {
        repository.save(user);
    }
}
