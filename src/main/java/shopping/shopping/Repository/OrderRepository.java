package shopping.shopping.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shopping.shopping.Entity.Member;
import shopping.shopping.Entity.Orders;
import shopping.shopping.Entity.Product;

import javax.persistence.criteria.Order;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders,Long> {

    List<Orders> findByMemberId(Long member_id);
}
