package shopping.shopping.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Getter @Setter
public class RegisterDTO {

    // 기본정보 내보내기
    private String email;
    private String name;
    private String password1;
    private String password2;
    private String avatar_url;

    public RegisterDTO(
           String email,String name, String password1,String password2,String avatar_url
           ){
        this.email = email;
        this.name = name;
        this.password1 = password1;
        this.password2 = password2;
        this.avatar_url = avatar_url;
    }

    public RegisterDTO(
           String email,String name, String password1,String password2
           ){
        this(email, name, password1, password2, null);
    }

    public RegisterDTO(){
        this(null,null,null,null,null);
    }


}
