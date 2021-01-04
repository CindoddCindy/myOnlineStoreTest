package mytokoonlinetest.myonliestoretest.auth.repository;

import java.util.Optional;

import mytokoonlinetest.myonliestoretest.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>  {
    Optional<User> findByName(String name);
    Boolean existsByName(String name);
    Boolean existsByEmail(String email);
}
