package mytokoonlinetest.myonliestoretest.main.repository;

import mytokoonlinetest.myonliestoretest.main.model.SellerConfrim;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerConfrimRepository  extends JpaRepository<SellerConfrim, Long> {
    Page<SellerConfrim> findByBuyItemtId(Long buyId, Pageable pageable);
    Optional<SellerConfrim> findByIdAndBuyId(Long id, Long buyId);
}
