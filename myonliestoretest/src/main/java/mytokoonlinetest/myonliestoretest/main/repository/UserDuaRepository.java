package mytokoonlinetest.myonliestoretest.main.repository;

import mytokoonlinetest.myonliestoretest.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDuaRepository extends JpaRepository<User, Long> {
}
