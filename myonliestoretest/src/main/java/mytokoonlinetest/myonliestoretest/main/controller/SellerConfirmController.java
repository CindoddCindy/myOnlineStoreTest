package mytokoonlinetest.myonliestoretest.main.controller;

import mytokoonlinetest.myonliestoretest.main.exception.ResourceNotFoundException;
import mytokoonlinetest.myonliestoretest.main.model.SellerConfrim;
import mytokoonlinetest.myonliestoretest.main.repository.BuyItemRepository;
import mytokoonlinetest.myonliestoretest.main.repository.SellerConfrimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class SellerConfirmController {

    @Autowired
    private SellerConfrimRepository  sellerConfrimRepository;

    @Autowired
    private BuyItemRepository buyItemRepository;

    @GetMapping("/buyitem/{buyitemId}/sellerconfirm")
    public Page<SellerConfrim> getAllConfirmByBuyId(@PathVariable (value = "buyitemId") Long buyitemId,
                                                      Pageable pageable) {
        return sellerConfrimRepository.findByBuyItemtId(buyitemId, pageable);
    }

    @PostMapping("/buyitem/{buyitemId}/sellerconfirm")
    public SellerConfrim createSellerConfirm(@PathVariable (value = "buyitemId") Long buyitemId,
                                 @Valid @RequestBody SellerConfrim sellerConfrim) {
        return buyItemRepository.findById(buyitemId).map(buyItem -> {
            sellerConfrim.setBuyItem(buyItem);
            return sellerConfrimRepository.save(sellerConfrim);
        }).orElseThrow(() -> new ResourceNotFoundException("SellerConfirmId " + buyitemId + " not found"));
    }

    @PutMapping("/buyitem/{buyitemId}/sellerconfirm/{sellerconfirmId}")
    public SellerConfrim updateSellerConfirm(@PathVariable (value = "buyitemId") Long buyitemId,
                                 @PathVariable (value = "sellerconfirmId") Long sellerconfirmId,
                                 @Valid @RequestBody SellerConfrim sellerConfrimRequest) {
        if(!buyItemRepository.existsById(buyitemId)) {
            throw new ResourceNotFoundException("buyitemId " + buyitemId + " not found");
        }

        return sellerConfrimRepository.findById(sellerconfirmId).map(sellerConfrim -> {
            sellerConfrim.setSellerconfirmation(sellerConfrimRequest.getSellerconfirmation());
            sellerConfrim.setSellernote(sellerConfrimRequest.getSellernote());
            return sellerConfrimRepository.save(sellerConfrim);
        }).orElseThrow(() -> new ResourceNotFoundException("sellerconfirmId " + sellerconfirmId + "not found"));
    }

    @DeleteMapping("/buyitem/{buyitemId}/sellerconfirm/{sellerconfirmId}")
    public ResponseEntity<?> deleteSellerConfrim(@PathVariable (value = "buyitemId") Long buyitemId,
                                           @PathVariable (value = "sellerconfirmId") Long sellerconfirmId) {
        return sellerConfrimRepository.findBySellerConfirmAndBuyId(sellerconfirmId, buyitemId).map(sellerConfrim -> {
            sellerConfrimRepository.delete(sellerConfrim);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Seller Confirm not found with id " + sellerconfirmId + " and postId " + buyitemId));
    }

}
