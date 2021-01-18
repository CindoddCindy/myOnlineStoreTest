package mytokoonlinetest.myonliestoretest.main.repository;

import mytokoonlinetest.myonliestoretest.main.model.PaymentItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentItemRepository  extends JpaRepository<PaymentItem, Long> {
    Page<PaymentItem> findByConfirmBuyItemId(Long confirmBuyItemId, Pageable pageable);
    Optional<PaymentItem> findByIdAndConfirmBuyItemId(Long id, Long confirmBuyItemId);
}
