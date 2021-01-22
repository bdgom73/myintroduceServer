package shopping.shopping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import shopping.shopping.Domain.ROUTES;

@Configuration
public class BeanConfig {

    @Bean
    public ROUTES routes(){
        return new ROUTES();
    }


}
