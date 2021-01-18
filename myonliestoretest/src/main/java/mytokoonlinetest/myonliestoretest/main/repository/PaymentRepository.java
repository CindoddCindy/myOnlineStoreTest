package mytokoonlinetest.myonliestoretest.main.repository;

import mytokoonlinetest.myonliestoretest.main.model.ConfirmBuyItem;
import mytokoonlinetest.myonliestoretest.main.model.Item;
import mytokoonlinetest.myonliestoretest.main.model.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Page<Payment> findByConfirmId(Long confirmId, Pageable pageable);
    Optional<Payment> findByPaymentIdAndConfirmId(Long id, Long confirmId);
}
