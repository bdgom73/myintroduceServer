package shopping.shopping.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import shopping.shopping.DTO.OrderDTO;
import shopping.shopping.Entity.Orders;
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
public class OrderListController {

    @PersistenceContext
    private final EntityManager em;

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    public OrderListController(EntityManager em, MemberRepository memberRepository, MemberService memberService, ProductRepository productRepository, OrderRepository orderRepository) {
        this.em = em;
        this.memberRepository = memberRepository;
        this.memberService = memberService;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @PostMapping("/order/list")
    public List<OrderDTO> orderList(
            @RequestHeader("cookie") String cookie,
            HttpServletResponse response
    ){
        try{
            Long memberId = memberService.parserCookie(cookie);
            List<Orders> byMemberId = orderRepository.findByMemberId(memberId);
            List<OrderDTO> orderDTOS = new ArrayList<>();
            for (Orders orders : byMemberId) {
                orderDTOS.add(new OrderDTO(orders.getId(),orders.getState(),orders.getProduct()));
            }
            response.setStatus(HttpServletResponse.SC_OK);
            return orderDTOS;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
