package shopping.shopping.Service;

import org.springframework.stereotype.Service;
import shopping.shopping.DTO.RegisterDTO;
import shopping.shopping.Entity.*;
import shopping.shopping.Repository.FriendRepository;
import shopping.shopping.Repository.MemberRepository;
import shopping.shopping.Repository.MoneyRepository;
import shopping.shopping.Repository.OrderRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @PersistenceContext
    private EntityManager em;

    private final FriendRepository friendRepository;
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final MoneyRepository moneyRepository;

    public MemberService(FriendRepository friendRepository, OrderRepository orderRepository, MemberRepository memberRepository, MoneyRepository moneyRepository) {
        this.friendRepository = friendRepository;
        this.orderRepository = orderRepository;
        this.memberRepository = memberRepository;
        this.moneyRepository = moneyRepository;
    }

    /*
    * 회원가입 및 로그인
    * *****
    * */
    public void memberJoin(RegisterDTO registerDTO) throws Exception {
        String email = registerDTO.getEmail();
        Optional<Member> findEmail = memberRepository.findByEmail(email);
        if(findEmail.isEmpty()){
            if(registerDTO.getPassword1().equals(registerDTO.getPassword2())){
                Member member = new Member(registerDTO);
                Member save = memberRepository.save(member);
                Money money = new Money();
                money.setMember(member);
                money.giveMoney(100L);
                save.setMoney(money);
                moneyRepository.save(money);
            }else{
                throw new Exception("비밀번호가 서로 다릅니다.");
            }
        }else{
            throw new Exception("이미 존재하는 계정입니다.");
        }
    }

    public Long memberLogin(String email,String password) throws Exception {
        Optional<Member> findEmail = memberRepository.findByEmail(email);
        if(findEmail.isEmpty()){
            throw new Exception("존재하지 않는 계정입니다.");
        }else if(findEmail.get().getPassword().equals(password)){
            return findEmail.get().getId();
        }else{
            throw new Exception("error code : 40004 알 수 없는 오류");
        }
    }

    /*
     * 쿠키분석
     * *****
     * */
    public Long parserCookie(String cookie){
        return Long.parseLong(cookie);
    }

    /*
     * 친구 등록 및 삭제
     * *****
     * */
    public void addFriend(Member myInfo, Member friendInfo) {
        try{
            List<Friend> byMemberId = friendRepository.findByMemberId(myInfo.getId());
            List<Friend> byMemberId1 = friendRepository.findByMemberId(friendInfo.getId());

            if(byMemberId.isEmpty() || byMemberId1.isEmpty()){
                Friend me = new Friend(myInfo.getId(),myInfo.getEmail(),myInfo.getName(),myInfo.getAvatar_url());
                Friend friend = new Friend(friendInfo.getId(),friendInfo.getEmail(),friendInfo.getName(),friendInfo.getAvatar_url());
                me.setMember(friendInfo);
                friend.setMember(myInfo);
                em.persist(me);
                em.persist(friend);
            }else if(this.friendDuplicate(byMemberId)){
                throw new Exception();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private Boolean friendDuplicate(List<Friend> friends){
        boolean check = false;
        for (Friend friend : friends) {
            check = friend.getFid().equals(friend.getMember().getId());
        }
        return check;
    }
    public void deleteFriend(Member myInfo, Member friendInfo){
        try{
            Optional<Friend> findMe = friendRepository.findByMemberIdAndFid(myInfo.getId(), friendInfo.getId());
            Optional<Friend> findFr = friendRepository.findByMemberIdAndFid(friendInfo.getId(), myInfo.getId());
            if(findMe.isEmpty() || findFr.isEmpty()){
                throw new Exception();
            }
            friendRepository.delete(findMe.get());
            friendRepository.delete(findFr.get());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /*
     * 주문하기 및 주문 취소
     * *****
     * */
    public void placeAnOrder(Member member, Product product){
        Orders orders = new Orders();
        orders.setMember(member);
        member.getOrders().add(orders);
        orders.setProduct(product);
        product.getOrders().add(orders);
        orderRepository.save(orders);
    }

    public void cancelOrder(Orders orders) throws Exception {
        Optional<Orders> findOrder = orderRepository.findById(orders.getId());
        if(findOrder.isEmpty()){
            throw new Exception();
        }
        orderRepository.delete(findOrder.get());
    }


}
