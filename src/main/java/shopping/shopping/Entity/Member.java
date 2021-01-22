package shopping.shopping.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import shopping.shopping.DTO.RegisterDTO;
import shopping.shopping.Domain.BaseTimeEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    private String email;
    private String password;
    private String name;
    private String avatar_url = "https://cdn.pixabay.com/photo/2016/08/31/11/54/user-1633249_960_720.png";
    private Boolean admin = false;

    public Member(String email,String password,String name){
        this.email = email;
        this.password = password;
        this.name = name;
    }
    public Member(RegisterDTO registerDTO){
        this(registerDTO.getEmail(),registerDTO.getPassword1(), registerDTO.getName());
    }
    public Member(){
        this(null,null,null);
    }
    @OneToMany(mappedBy = "member")
    List<Product> products = new ArrayList<>();
    @OneToMany(mappedBy = "member")
    List<Friend> friends = new ArrayList<>();
    @OneToMany(mappedBy = "member")
    List<Orders> orders = new ArrayList<>();
    @OneToOne(mappedBy = "member")
    private Money money;

}
