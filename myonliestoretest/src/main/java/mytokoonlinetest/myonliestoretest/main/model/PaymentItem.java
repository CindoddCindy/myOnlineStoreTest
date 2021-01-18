package mytokoonlinetest.myonliestoretest.main.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "paymentitem")
public class PaymentItem extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String confpay;

    @NotNull
    private String notepay;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "confirm_buy_item_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ConfirmBuyItem confirmBuyItem;

    public PaymentItem() {
    }

    public PaymentItem(@NotNull String confpay, @NotNull String notepay) {
        this.confpay = confpay;
        this.notepay = notepay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConfpay() {
        return confpay;
    }

    public void setConfpay(String confpay) {
        this.confpay = confpay;
    }

    public String getNotepay() {
        return notepay;
    }

    public void setNotepay(String notepay) {
        this.notepay = notepay;
    }

    public ConfirmBuyItem getConfirmBuyItem() {
        return confirmBuyItem;
    }

    public void setConfirmBuyItem(ConfirmBuyItem confirmBuyItem) {
        this.confirmBuyItem = confirmBuyItem;
    }
}
