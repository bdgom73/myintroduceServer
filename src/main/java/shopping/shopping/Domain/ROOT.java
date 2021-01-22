package shopping.shopping.Domain;

import lombok.Getter;

public class ROOT {

    private final String HOME = "/";
    private final String JOIN = "/";
    private final String LOGIN = "/";

    public String HOME(){
        return HOME;
    }
    public String JOIN() {
        return JOIN;
    }
    public String LOGIN() {
        return LOGIN;
    }
}
