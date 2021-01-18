package mytokoonlinetest.myonliestoretest.main.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "paymentdone")
public class PaymentDone extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String sellerconf;

    @NotNull
    private String namepay;

    @NotNull
    private String payqty;

    @NotNull
    private String notepay;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_item_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PaymentItem paymentItem;

    public PaymentDone() {
    }

    public PaymentDone(@NotNull String sellerconf, @NotNull String namepay, @NotNull String payqty, @NotNull String notepay) {
        this.sellerconf = sellerconf;
        this.namepay = namepay;
        this.payqty = payqty;
        this.notepay = notepay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSellerconf() {
        return sellerconf;
    }

    public void setSellerconf(String sellerconf) {
        this.sellerconf = sellerconf;
    }

    public String getNamepay() {
        return namepay;
    }

    public void setNamepay(String namepay) {
        this.namepay = namepay;
    }

    public String getPayqty() {
        return payqty;
    }

    public void setPayqty(String payqty) {
        this.payqty = payqty;
    }

    public String getNotepay() {
        return notepay;
    }

    public void setNotepay(String notepay) {
        this.notepay = notepay;
    }

    public PaymentItem getPaymentItem() {
        return paymentItem;
    }

    public void setPaymentItem(PaymentItem paymentItem) {
        this.paymentItem = paymentItem;
    }
}
