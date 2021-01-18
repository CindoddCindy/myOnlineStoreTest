package mytokoonlinetest.myonliestoretest.main.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "payment")
public class Payment extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String payconf;

    @NotNull
    private String paynote;



    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "confirm_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ConfirmBuyItem confirmBuyItem;

    public Payment(@NotNull String payconf, @NotNull String paynote) {
        this.payconf = payconf;
        this.paynote = paynote;
    }

    public Payment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPayconf() {
        return payconf;
    }

    public void setPayconf(String payconf) {
        this.payconf = payconf;
    }

    public String getPaynote() {
        return paynote;
    }

    public void setPaynote(String paynote) {
        this.paynote = paynote;
    }

    public ConfirmBuyItem getConfirmBuyItem() {
        return confirmBuyItem;
    }

    public void setConfirmBuyItem(ConfirmBuyItem confirmBuyItem) {
        this.confirmBuyItem = confirmBuyItem;
    }
}
