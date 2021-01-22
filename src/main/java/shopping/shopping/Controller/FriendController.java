package shopping.shopping.Controller;

import org.springframework.web.bind.annotation.*;
import shopping.shopping.DTO.FriendDTO;
import shopping.shopping.Entity.Friend;
import shopping.shopping.Repository.FriendRepository;
import shopping.shopping.Repository.MemberRepository;
import shopping.shopping.Repository.OrderRepository;
import shopping.shopping.Repository.ProductRepository;
import shopping.shopping.Service.MemberService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FriendController {

    @PersistenceContext
    private final EntityManager em;

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final FriendRepository friendRepository;
    public FriendController(EntityManager em, MemberRepository memberRepository, MemberService memberService, ProductRepository productRepository, OrderRepository orderRepository, FriendRepository friendRepository) {
        this.em = em;
        this.memberRepository = memberRepository;
        this.memberService = memberService;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.friendRepository = friendRepository;
    }

    @PostMapping("/{id}/friend")
    public List<FriendDTO> friendList(
            @RequestParam("id") String id,
            @RequestHeader("cookie") String cookie,
            HttpServletResponse response
    ){
        try{
            Long userId = memberService.parserCookie(cookie);
            List<Friend> Friends = friendRepository.findByMemberId(userId);
            List<FriendDTO> friendDTOS = new ArrayList<>();
            for (Friend friend : Friends) {
                friendDTOS.add(new FriendDTO(friend.getId(), friend.getFid(), friend.getEmail(), friend.getName(), friend.getAvatar_url()));
            }
            response.setStatus(HttpServletResponse.SC_OK);
            return friendDTOS;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @PostMapping("/test/hosting")
    public String hostingTest(
            @RequestBody String name
    ){
        System.out.println("name = " + name);
        return "test okay";
    }
}
