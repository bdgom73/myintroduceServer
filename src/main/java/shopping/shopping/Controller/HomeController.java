package shopping.shopping.Controller;

import org.springframework.web.bind.annotation.*;
import shopping.shopping.DTO.MemberDTO;
import shopping.shopping.DTO.RegisterDTO;
import shopping.shopping.Entity.Member;
import shopping.shopping.Repository.MemberRepository;
import shopping.shopping.Service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class HomeController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    public HomeController(MemberService memberService, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    @PostMapping("/join")
    public void join(
            @RequestBody Map<String,String> joinInfo,
            HttpServletResponse response
            ) throws IOException {
        System.out.println("joinInfo = " + joinInfo);
        RegisterDTO registerDTO = new RegisterDTO(joinInfo.get("email"),joinInfo.get("name"),joinInfo.get("password1"),joinInfo.get("password2"));
        try{
            memberService.memberJoin(registerDTO);
            response.setStatus(HttpServletResponse.SC_OK);
        }catch (Exception e){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,e.getMessage());
        }

    }
    @PostMapping("/login")
    public Long login(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password,
            HttpServletResponse response
    ) throws IOException {
        try{
            return memberService.memberLogin(email, password);
        }catch (Exception e){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,e.getMessage());
            return null;
        }
    }

    @GetMapping("/api/test")
    public List<MemberDTO> apiTest(
        HttpServletRequest request
    ) throws Exception {
        System.out.println("request = " + request);
        List<Member> all = memberRepository.findAll();
        List<MemberDTO> memberDTOS = new ArrayList<>();
        for(int i =0 ; i<all.size() ; i++ ){
            memberDTOS.add(new MemberDTO(
                    all.get(i).getId(),
                    all.get(i).getEmail(),
                    all.get(i).getName(),
                    null,
                    null,
                    all.get(i).getAvatar_url(),
                    null
                    ));
        }
        return memberDTOS;

    }
}
