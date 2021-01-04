package mytokoonlinetest.myonliestoretest.auth.repository;

import java.util.Optional;

import mytokoonlinetest.myonliestoretest.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>  {
    Optional<User> findByUsername(String name);
    Boolean existsByUsername(String name);
    Boolean existsByEmail(String email);
}
