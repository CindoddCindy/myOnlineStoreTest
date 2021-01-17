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

}
