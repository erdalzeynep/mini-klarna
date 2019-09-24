package dal.zeynep.miniklarna.dto;

public class OrderDto {
    private int orderId;
    private boolean isPaid;
    private boolean isSuccessful;
    private Integer price;

    public OrderDto(int orderId, boolean isPaid, boolean isSuccessful, Integer price) {
        this.orderId = orderId;
        this.isPaid = isPaid;
        this.isSuccessful = isSuccessful;
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public boolean getIsPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public boolean getIsSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
