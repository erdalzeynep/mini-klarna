package dal.zeynep.miniklarna.dto;

public class OrderDto {
    private int orderId;
    private boolean isPaid;
    private boolean isSuccessful;

    public OrderDto(int orderId, boolean isPaid, boolean isSuccessful) {
        this.orderId = orderId;
        this.isPaid = isPaid;
        this.isSuccessful = isSuccessful;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }
}
