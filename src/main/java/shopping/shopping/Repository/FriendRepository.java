package shopping.shopping.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shopping.shopping.Entity.Friend;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend,Long> {

    List<Friend> findByMemberId(Long member_id);

    Optional<Friend> findByMemberIdAndFid(Long member_id, Long friend_id);

}
