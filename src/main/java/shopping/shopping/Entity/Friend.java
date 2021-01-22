package shopping.shopping.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Friend {

    @Id @GeneratedValue
    @Column(name = "FRIEND_LIST_ID")
    private Long id;

    private Long fid;
    private String email;
    private String name;
    private String avatar_url;

    public Friend(Long fid,String email,String name,String avatar_url){
        this.fid = fid;
        this.email = email;
        this.name = name;
        this.avatar_url = avatar_url;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public void setMember(Member member){
        this.member = member;
        member.getFriends().add(this);
    }
}
