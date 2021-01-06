package mytokoonlinetest.myonliestoretest.main.repository;

import mytokoonlinetest.myonliestoretest.auth.model.User;
import mytokoonlinetest.myonliestoretest.main.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item,Long> {

    Page<Item> findByUserId(Long userId, Pageable pageable);
    Optional<Item> findByIdAndUserId(Long id, Long userId);

}
