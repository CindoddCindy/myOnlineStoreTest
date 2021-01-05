package mytokoonlinetest.myonliestoretest.auth.repository;


import java.util.Optional;

import mytokoonlinetest.myonliestoretest.auth.model.Role;
import mytokoonlinetest.myonliestoretest.auth.model.RoleAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long>  {
    Optional<Role> findByName(RoleAccess roleName);


}
