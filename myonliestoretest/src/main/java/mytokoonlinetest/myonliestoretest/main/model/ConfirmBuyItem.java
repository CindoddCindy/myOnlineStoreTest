package mytokoonlinetest.myonliestoretest.main.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "confirmbuyitem")
public class ConfirmBuyItem extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String conf;

    @NotNull
    private String note;




    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "buyitem_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private BuyItem buyItem;


    public ConfirmBuyItem(@NotNull String conf, @NotNull String note) {
        this.conf = conf;
        this.note = note;
    }

    public ConfirmBuyItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConf() {
        return conf;
    }

    public void setConf(String conf) {
        this.conf = conf;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public BuyItem getBuyItem() {
        return buyItem;
    }

    public void setBuyItem(BuyItem buyItem) {
        this.buyItem = buyItem;
    }
}
