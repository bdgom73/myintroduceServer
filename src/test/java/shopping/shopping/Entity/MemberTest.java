package shopping.shopping.Entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.annotation.Rollback;
import shopping.shopping.DTO.RegisterDTO;
import shopping.shopping.Repository.FriendRepository;
import shopping.shopping.Repository.MemberRepository;
import shopping.shopping.Service.MemberService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    MemberService memberService;
    @Autowired
    FriendRepository friendRepository;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void member_Friend() throws Exception {
        Member member = new Member("mm","dd","aa");
        Member user = new Member("userA","11","uesrA");
        Member user2 = new Member("userB","11","uesrB");
        em.persist(user);
        em.persist(user2);
        em.persist(member);
        Product product = new Product("ProductA","ProductExplain","photo.jpg");
        product.setMember(member);
        em.persist(product);

        memberService.addFriend(member,user);
        memberService.addFriend(member,user2);
        memberService.addFriend(member,user);

    }

    @Test
    public void orderTable() throws Exception {
        RegisterDTO registerDTO1 = new RegisterDTO("userAEmail","userA","1111","1111");
        memberService.memberJoin(registerDTO1);
        Long aLong = memberService.memberLogin(registerDTO1.getEmail(), registerDTO1.getPassword1());
        Money money = em.find(Money.class, aLong);
        money.giveMoney(200000000L);
        money.receiveMoney(5L);
    }

}