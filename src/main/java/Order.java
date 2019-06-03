import java.util.UUID;

public class Order {
    private String orderId;
    private String userId;
    private Integer price;
    private boolean isPaid;
    private boolean isSuccessful;

    public Order(String userId, Integer price, boolean isSuccessful) {
        this.userId = userId;
        this.price = price;
        this.isSuccessful = isSuccessful;
        this.orderId = UUID.randomUUID().toString();
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
