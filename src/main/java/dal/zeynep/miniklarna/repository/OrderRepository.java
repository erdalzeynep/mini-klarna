package dal.zeynep.miniklarna.repository;

import dal.zeynep.miniklarna.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {
    List<OrderModel> findOrdersByUserEmail(String userEmail);

}
