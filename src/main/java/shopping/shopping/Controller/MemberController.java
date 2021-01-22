package shopping.shopping.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shopping.shopping.Entity.Member;
import shopping.shopping.Entity.Orders;
import shopping.shopping.Entity.Product;
import shopping.shopping.Repository.MemberRepository;
import shopping.shopping.Service.MemberService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.server.ExportException;
import java.util.List;
import java.util.Optional;

@RestController
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    public MemberController(MemberService memberService, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    /** 내 정보 */
    @PostMapping("/user/me")
    public Member me(
            @RequestHeader(value = "cookie") String cookie,
            HttpServletResponse response
    ) throws IOException {
        Long userId = memberService.parserCookie(cookie);
            Optional<Member> findUser = memberRepository.findById(userId);
            if(findUser.isEmpty()){
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "존재하지않는 유저");
            }
            response.setStatus(HttpServletResponse.SC_OK);
            return findUser.get();

    }

    /** 유저 상세보기  */
    @PostMapping("/user/{id}/detail")
    public Member userDetail(
            @RequestParam(value = "id") String id,
            HttpServletResponse response
    ) throws IOException {
        Long userId = Long.parseLong(id);
        Optional<Member> findUser = memberRepository.findById(userId);
        if(findUser.isEmpty()){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "존재하지않는 유저");
        }
        response.setStatus(HttpServletResponse.SC_OK);
        Member member = findUser.get();
        return member;
    }

    /** 내 주문 정보  */
    @PostMapping("/user/me/order_list")
    public List<Orders> userOrderList(
            @RequestHeader(value = "cookie") String cookie,
            HttpServletResponse response
    ) throws IOException {
        Long userId = memberService.parserCookie(cookie);
        Optional<Member> findUser = memberRepository.findById(userId);
        if(findUser.isEmpty()){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "존재하지않는 유저");
        }
        response.setStatus(HttpServletResponse.SC_OK);
        return findUser.get().getOrders();
    }

    /** 유저 상세보기  */
    @PostMapping("/user/me/product_list")
    public List<Product> userProductList(
            @RequestHeader(value = "cookie") String cookie,
            HttpServletResponse response
    ) throws IOException {
        Long userId = memberService.parserCookie(cookie);
        Optional<Member> findUser = memberRepository.findById(userId);
        if(findUser.isEmpty()){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "존재하지않는 유저");
        }
        response.setStatus(HttpServletResponse.SC_OK);
        return findUser.get().getProducts();
    }



}
