package shopping.shopping.Domain;

public class USER {

    private final String USER_DETAIL = "/user/detail";
    private final String USER_UPDATE = "/user/update";
    private final String USER_DELETE = "/user/delete";

    public String USER_DETAIL(){
        return USER_DETAIL;
    }
    public String USER_UPDATE() {
        return USER_UPDATE;
    }
    public String USER_DELETE() {
        return USER_DELETE;
    }
}
