package mytokoonlinetest.myonliestoretest.main.repository;

import mytokoonlinetest.myonliestoretest.main.model.BuyItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemBuyRepository  extends JpaRepository<BuyItem, Long> {
    Page<BuyItem> findByItemId(Long itemId, Pageable pageable);
    Optional<BuyItem> findByIdAndItemId(Long id, Long itemId);

}
