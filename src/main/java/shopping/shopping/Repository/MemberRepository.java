package shopping.shopping.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shopping.shopping.Entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
}
