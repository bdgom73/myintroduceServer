package shopping.shopping.Entity;

import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;
import org.hibernate.criterion.Order;
import shopping.shopping.Domain.BaseTimeEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Product extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "PRODUCT_ID")
    private Long id;
    @Column(name = "PRODUCT_NAME")
    private String name;
    @Column(name = "PRODUCT_EXPLAIN")
    private String explain;
    @Column(name = "PRODUCT_IMG")
    private String photo;
    private String kind;
    private Long price;
    private Long quantity;

    public Product(String name,String explain,String  photo){
        this.name = name;
        this.explain = explain;
        this.photo = photo;
    }
    public Product(){
        this(null,null,null);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;



    @OneToMany(mappedBy = "product")
    List<Orders> orders = new ArrayList<>();

    public void setMember(Member member){
        this.member = member;
        member.getProducts().add(this);
    }
}
