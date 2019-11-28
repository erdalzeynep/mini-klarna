package dal.zeynep.miniklarna.dto;

public class OrderDto {
    private long orderId;
    private boolean isPaid;
    private boolean isSuccessful;
    private Integer price;

    public OrderDto(long orderId, boolean isPaid, boolean isSuccessful, Integer price) {
        this.orderId = orderId;
        this.isPaid = isPaid;
        this.isSuccessful = isSuccessful;
        this.price = price;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
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
