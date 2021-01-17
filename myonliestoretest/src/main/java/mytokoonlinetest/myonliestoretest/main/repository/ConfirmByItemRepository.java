package mytokoonlinetest.myonliestoretest.main.repository;

import mytokoonlinetest.myonliestoretest.main.model.BuyItem;
import mytokoonlinetest.myonliestoretest.main.model.ConfirmBuyItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfirmByItemRepository extends JpaRepository<ConfirmBuyItem, Long> {

    Page<ConfirmBuyItem> findByBuyItemId(Long buyItemId, Pageable pageable);
    Optional<ConfirmBuyItem> findByIdAndBuyItemId(Long id, Long buyItemId);
}
