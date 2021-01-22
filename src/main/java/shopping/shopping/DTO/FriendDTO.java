package shopping.shopping.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import shopping.shopping.Repository.FriendRepository;

@Data
@Getter @Setter
public class FriendDTO {

    @Autowired
    private FriendRepository friendRepository;

    private Long id;
    private Long fid;
    private String email;
    private String name;
    private String avatar_url;

    public FriendDTO(Long fid, Long id, String email, String name, String avatar_url){
        this.id = id;
        this.fid = fid;
        this.email = email;
        this.name = name;
        this.avatar_url = avatar_url;
    }

}
