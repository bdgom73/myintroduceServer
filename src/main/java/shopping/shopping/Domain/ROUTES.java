package shopping.shopping.Domain;

import lombok.Getter;

import java.util.*;

@Getter
public class ROUTES{

    public ROOT ROOT(){
        return new ROOT();
    }
    public USER USER(){
        return new USER();
    }
}
