package mytokoonlinetest.myonliestoretest.main.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import mytokoonlinetest.myonliestoretest.auth.model.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "item")
public class Item extends AuditModel {

    //private String

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String sellername;

    @NotNull
    private String sellerphone;

    @NotNull
    private String itemname;

    @NotNull
    private String itemimage;

    @NotNull
    private String itemprice;

    @NotNull
    private String itemaddress;

    @NotNull
    private String itemqty;

    @NotNull
    private String itemdesc;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    public Item(@NotNull String itemname, @NotNull String itemimage, @NotNull String itemprice, @NotNull String itemaddress, @NotNull String itemqty, @NotNull String itemdesc) {
        this.itemname = itemname;
        this.itemimage = itemimage;
        this.itemprice = itemprice;
        this.itemaddress = itemaddress;
        this.itemqty = itemqty;
        this.itemdesc = itemdesc;
    }

    public Item() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
