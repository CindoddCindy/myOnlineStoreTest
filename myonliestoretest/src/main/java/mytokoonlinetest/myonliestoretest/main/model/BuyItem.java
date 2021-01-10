package mytokoonlinetest.myonliestoretest.main.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "buyitem")
public class BuyItem extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String buyername;

    @NotNull
    private String buyerphone;

    @NotNull
    private String buyeraddress;

    @NotNull
    private String message;

    @NotNull
    private String buysellername;

    @NotNull
    private String buysellerphone;

    @NotNull
    private String buyitemname;

    @NotNull
    private String buyitemimage;

    @NotNull
    private String buyitemprice;

    @NotNull
    private String buyitemaddress;

    @NotNull
    private String buyitemqty;

    @NotNull
    private String buyitemdesc;

    public BuyItem() {
    }


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "items_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Item item;


    public BuyItem(@NotNull String buyername, @NotNull String buyerphone, @NotNull String buyeraddress, @NotNull String message, @NotNull String buysellername, @NotNull String buysellerphone, @NotNull String buyitemname, @NotNull String buyitemimage, @NotNull String buyitemprice, @NotNull String buyitemaddress, @NotNull String buyitemqty, @NotNull String buyitemdesc) {
        this.buyername = buyername;
        this.buyerphone = buyerphone;
        this.buyeraddress = buyeraddress;
        this.message = message;
        this.buysellername = buysellername;
        this.buysellerphone = buysellerphone;
        this.buyitemname = buyitemname;
        this.buyitemimage = buyitemimage;
        this.buyitemprice = buyitemprice;
        this.buyitemaddress = buyitemaddress;
        this.buyitemqty = buyitemqty;
        this.buyitemdesc = buyitemdesc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuyername() {
        return buyername;
    }

    public void setBuyername(String buyername) {
        this.buyername = buyername;
    }

    public String getBuyerphone() {
        return buyerphone;
    }

    public void setBuyerphone(String buyerphone) {
        this.buyerphone = buyerphone;
    }

    public String getBuyeraddress() {
        return buyeraddress;
    }

    public void setBuyeraddress(String buyeraddress) {
        this.buyeraddress = buyeraddress;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBuysellername() {
        return buysellername;
    }

    public void setBuysellername(String buysellername) {
        this.buysellername = buysellername;
    }

    public String getBuysellerphone() {
        return buysellerphone;
    }

    public void setBuysellerphone(String buysellerphone) {
        this.buysellerphone = buysellerphone;
    }

    public String getBuyitemname() {
        return buyitemname;
    }

    public void setBuyitemname(String buyitemname) {
        this.buyitemname = buyitemname;
    }

    public String getBuyitemimage() {
        return buyitemimage;
    }

    public void setBuyitemimage(String buyitemimage) {
        this.buyitemimage = buyitemimage;
    }

    public String getBuyitemprice() {
        return buyitemprice;
    }

    public void setBuyitemprice(String buyitemprice) {
        this.buyitemprice = buyitemprice;
    }

    public String getBuyitemaddress() {
        return buyitemaddress;
    }

    public void setBuyitemaddress(String buyitemaddress) {
        this.buyitemaddress = buyitemaddress;
    }

    public String getBuyitemqty() {
        return buyitemqty;
    }

    public void setBuyitemqty(String buyitemqty) {
        this.buyitemqty = buyitemqty;
    }

    public String getBuyitemdesc() {
        return buyitemdesc;
    }

    public void setBuyitemdesc(String buyitemdesc) {
        this.buyitemdesc = buyitemdesc;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
