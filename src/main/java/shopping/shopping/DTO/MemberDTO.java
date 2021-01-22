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
public class MemberDTO {

    // 기본정보 내보내기
    private Long id;
    private String email;
    private String name;
    private String created;
    private String updated;
    private String avatar_url;
    private Long money;

    public MemberDTO(
            Long id, String email,String name, String created,
            String updated, String avatar_url, Long money
    ){
        this.id = id;
        this.email = email;
        this.name = name;
        this.created = created;
        this.updated = updated;
        this.avatar_url = avatar_url;
        this.money = money;
    }

}
