package dal.zeynep.miniklarna.model;

import javax.persistence.*;

@Entity
@Table
public class OrderModel {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;
    @Column
    private String userEmail;
    @Column
    private Integer price;
    @Column
    private boolean isPaid;
    @Column
    private boolean isSuccessful;

    public OrderModel() {
    }

    public OrderModel(String userEmail, Integer price, boolean isSuccessful) {
        this.userEmail = userEmail;
        this.price = price;
        this.isSuccessful = isSuccessful;
        this.isPaid = false;
    }

    public boolean getIsSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

}
