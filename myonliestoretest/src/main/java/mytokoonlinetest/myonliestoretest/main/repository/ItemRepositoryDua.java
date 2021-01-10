package mytokoonlinetest.myonliestoretest.main.repository;

import mytokoonlinetest.myonliestoretest.main.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepositoryDua extends JpaRepository<Item, Long> {
}
