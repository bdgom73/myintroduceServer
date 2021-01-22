package shopping.shopping.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shopping.shopping.Entity.Money;

public interface MoneyRepository extends JpaRepository<Money,Long> {
}
