package mytokoonlinetest.myonliestoretest.main.repository;


import mytokoonlinetest.myonliestoretest.main.model.PaymentDone;
import mytokoonlinetest.myonliestoretest.main.model.PaymentItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentDoneRepository extends JpaRepository<PaymentDone, Long>{
    Page<PaymentDone> findByPaymentItemId(Long paymentItemId, Pageable pageable);
    Optional<PaymentDone> findByIdAndPaymentItemId(Long id, Long paymentItemId);
}
