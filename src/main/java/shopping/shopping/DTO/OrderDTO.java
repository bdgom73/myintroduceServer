package shopping.shopping.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import shopping.shopping.Entity.Member;
import shopping.shopping.Entity.Product;

import javax.persistence.*;

@Data
@Getter @Setter
public class OrderDTO {

    private Long id;
    private Integer state;
    private Long product_id;
    private String product_name;
    private String product_explain;
    private String product_photo;
    private Long price;
    private Long quantity;
    private String kind;

    public OrderDTO(Long id, Integer state, Product product){
        this.id = id;
        this.state = state;
        this.product_id = product.getId();
        this.product_name = product.getName();
        this.product_explain = product.getExplain();
        this.product_photo = product.getPhoto();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.kind = product.getKind();
    }
}
