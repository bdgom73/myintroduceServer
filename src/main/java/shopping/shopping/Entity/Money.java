package shopping.shopping.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Money {

    @Id
    private Long id;
    private Long money = 0L;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    private Member member;

    public void giveMoney(Long money){
        this.money = this.money + money;
    }
    public void receiveMoney(Long money){
        this.money = this.money - money;
    }

}
