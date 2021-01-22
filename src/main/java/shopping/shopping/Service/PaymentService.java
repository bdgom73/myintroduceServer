package shopping.shopping.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shopping.shopping.Entity.Money;
import shopping.shopping.Repository.FriendRepository;
import shopping.shopping.Repository.MemberRepository;
import shopping.shopping.Repository.OrderRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class PaymentService {

    @PersistenceContext
    private EntityManager em;

    private final FriendRepository friendRepository;
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    public PaymentService(FriendRepository friendRepository, OrderRepository orderRepository, MemberRepository memberRepository) {
        this.friendRepository = friendRepository;
        this.orderRepository = orderRepository;
        this.memberRepository = memberRepository;
    }

    @PostMapping("/admin/coin/give")
    public Long giveCoin(
            @RequestParam("id") String id,
            @RequestParam("coin") String coin,
            HttpServletResponse response
    ) throws IOException {
        Long userId = Long.parseLong(id);
        Long setCoin;
        if(coin == null){
            setCoin = 0L;
        }else{
            setCoin = Long.parseLong(coin);
        }
        Money money = em.find(Money.class, userId);
        money.setMoney(money.getMoney()+ setCoin);
        return money.getMoney();
    }
}
