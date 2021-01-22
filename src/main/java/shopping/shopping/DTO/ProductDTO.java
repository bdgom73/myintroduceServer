package shopping.shopping.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import shopping.shopping.Entity.Member;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Getter @Setter
public class ProductDTO {

    private Long id;
    private String name;
    private String explain;
    private String photo = "https://lh3.googleusercontent.com/proxy/Lun0nfPAFLBOZvyudEj_b-viOxFqJfMS8VVfpsmC89pVPMB1bWV1KdIpMbcYIGSvhSwWML9g8FsV3R3IHebLGMOLpA3UoEqdspKIpUcRkzDSfFeKaSgR2HCbZ-at6f15";
    private String kind;
    private Long price;
    private Long quantity;
    private String member_name;
    private Long member_id;
    private String member_avatar;

    public ProductDTO(
            Long id, String name, String explain, String photo, String kind, Long price, Long quantity, Member member
    ){
        this.id = id;
        this.name = name;
        this.explain = explain;
        this.photo = photo;
        this.kind = kind;
        this.price = price;
        this.quantity = quantity;
        this.member_id = member.getId();
        this.member_name = member.getName();
        this.member_avatar = member.getAvatar_url();
    }
}
