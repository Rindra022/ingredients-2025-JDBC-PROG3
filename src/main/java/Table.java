import java.time.Instant;
import java.util.List;

public class Table {
    private Integer id;
    private Integer number;
    private List<Order> orders;

    public boolean isAvailableAt(Instant time) {
        for (Order order : orders) {
            if (order.getCreationDatetime().equals(time)) {
                return false;
            }
        }
        return true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Table{" +
                "id=" + id +
                ", number=" + number +
                ", orders=" + orders +
                '}';
    }
}
