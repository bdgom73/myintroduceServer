package shopping.shopping.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shopping.shopping.Entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
