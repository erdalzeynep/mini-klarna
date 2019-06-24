package dal.zeynep.miniklarna.dto;

public class PaymentDto {
    private boolean isPaid;
    private Integer price;

    public PaymentDto(boolean isPaid, Integer price) {
        this.isPaid = isPaid;
        this.price = price;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
