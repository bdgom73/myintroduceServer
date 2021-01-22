package shopping.shopping.Entity;

import lombok.Getter;
import lombok.Setter;
import shopping.shopping.Domain.BaseTimeEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Orders extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;
    private Integer state;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;



}
