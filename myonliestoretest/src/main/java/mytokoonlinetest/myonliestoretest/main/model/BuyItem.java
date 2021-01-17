package mytokoonlinetest.myonliestoretest.main.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import mytokoonlinetest.myonliestoretest.auth.model.User;
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

    @NotNull String buyeraddress;

    @NotNull
    @Column(unique = true)
    private String itemname;

    @NotNull
    @Column(unique = true)
    private String itemimage;

    @NotNull
    @Column(unique = true)
    private String itemprice;

    @NotNull
    @Column(unique = true)
    private String itemaddress;

    @NotNull
    @Column(unique = true)
    private String itemqty;

    @NotNull

    @Column(unique = true)
    private String itemdesc;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "item_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Item item;


    public BuyItem(@NotNull String buyername, @NotNull String buyerphone, @NotNull String buyeraddress, @NotNull String itemname, @NotNull String itemimage, @NotNull String itemprice, @NotNull String itemaddress, @NotNull String itemqty, @NotNull String itemdesc) {
        this.buyername = buyername;
        this.buyerphone = buyerphone;
        this.buyeraddress = buyeraddress;
        this.itemname = itemname;
        this.itemimage = itemimage;
        this.itemprice = itemprice;
        this.itemaddress = itemaddress;
        this.itemqty = itemqty;
        this.itemdesc = itemdesc;
    }

    public BuyItem() {
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

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemimage() {
        return itemimage;
    }

    public void setItemimage(String itemimage) {
        this.itemimage = itemimage;
    }

    public String getItemprice() {
        return itemprice;
    }

    public void setItemprice(String itemprice) {
        this.itemprice = itemprice;
    }

    public String getItemaddress() {
        return itemaddress;
    }

    public void setItemaddress(String itemaddress) {
        this.itemaddress = itemaddress;
    }

    public String getItemqty() {
        return itemqty;
    }

    public void setItemqty(String itemqty) {
        this.itemqty = itemqty;
    }

    public String getItemdesc() {
        return itemdesc;
    }

    public void setItemdesc(String itemdesc) {
        this.itemdesc = itemdesc;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
