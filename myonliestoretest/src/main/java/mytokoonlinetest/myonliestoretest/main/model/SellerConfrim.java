package mytokoonlinetest.myonliestoretest.main.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "sellerconfirm")
public class SellerConfrim extends AuditModel  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String sellerconfirmation;

    @NotNull
    private String sellernote;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "buy_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private BuyItem buyItem;

    public SellerConfrim() {
    }

    public SellerConfrim(@NotNull String sellerconfirmation, @NotNull String sellernote) {
        this.sellerconfirmation = sellerconfirmation;
        this.sellernote = sellernote;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSellerconfirmation() {
        return sellerconfirmation;
    }

    public void setSellerconfirmation(String sellerconfirmation) {
        this.sellerconfirmation = sellerconfirmation;
    }

    public String getSellernote() {
        return sellernote;
    }

    public void setSellernote(String sellernote) {
        this.sellernote = sellernote;
    }

    public BuyItem getBuyItem() {
        return buyItem;
    }

    public void setBuyItem(BuyItem buyItem) {
        this.buyItem = buyItem;
    }
}
