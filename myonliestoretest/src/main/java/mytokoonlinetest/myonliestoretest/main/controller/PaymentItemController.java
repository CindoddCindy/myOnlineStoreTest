package mytokoonlinetest.myonliestoretest.main.controller;


import mytokoonlinetest.myonliestoretest.main.exception.ResourceNotFoundException;
import mytokoonlinetest.myonliestoretest.main.model.PaymentItem;
import mytokoonlinetest.myonliestoretest.main.repository.ConfirmByItemRepository;
import mytokoonlinetest.myonliestoretest.main.repository.PaymentItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PaymentItemController {

    @Autowired
    private PaymentItemRepository paymentItemRepository;

    @Autowired
    private ConfirmByItemRepository confirmByItemRepository;

    @GetMapping("/api/auth/confirmbuyitem/{confirmBuyItemId}/paymentitem")
    public Page<PaymentItem> getAllPaymentItemByConfirmId(@PathVariable (value = "confirmBuyItemId") Long confirmBuyItemId,
                                                    Pageable pageable) {
        return paymentItemRepository.findByConfirmBuyItemId(confirmBuyItemId, pageable);
    }

    @PostMapping("/api/auth/confirmbuyitem/{confirmBuyItemId}/paymentitem")
    public PaymentItem createPamentItem(@PathVariable (value = "confirmBuyItemId") Long confirmBuyItemId,
                                 @Valid @RequestBody PaymentItem paymentItem) {
        return confirmByItemRepository.findById(confirmBuyItemId).map(confirmBuyItem -> {
            paymentItem.setConfirmBuyItem(confirmBuyItem);
            return paymentItemRepository.save(paymentItem);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + confirmBuyItemId + " not found"));
    }


    @PutMapping("/api/auth/confirmbuyitem/{confirmBuyItemId}/paymentitem/{paymentItemId}")
    public PaymentItem updatePaymentItem(@PathVariable (value = "confirmBuyItemId") Long confirmBuyItemId,
                                 @PathVariable (value = "paymentItemId") Long paymentItemId,
                                 @Valid @RequestBody PaymentItem paymentItemRequest) {
        if(!confirmByItemRepository.existsById(confirmBuyItemId)) {
            throw new ResourceNotFoundException("Confirm Buy Item Id " + confirmBuyItemId + " not found");
        }

        return paymentItemRepository.findById(paymentItemId).map(paymentItem -> {
            paymentItem.setConfpay(paymentItemRequest.getConfpay());
            paymentItem.setNotepay(paymentItemRequest.getNotepay());
            return paymentItemRepository.save(paymentItem);
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + paymentItemId + "not found"));
    }

    @DeleteMapping("/api/auth/confirmbuyitem/{confirmBuyItemId}/paymentitem/{paymentItemId}")
    public ResponseEntity<?> deleteComment(@PathVariable (value = "confirmBuyItemId") Long confirmBuyItemId,
                                           @PathVariable (value = "paymentItemId") Long paymentItemId) {
        return paymentItemRepository.findByIdAndConfirmBuyItemId(paymentItemId, confirmBuyItemId).map(paymentItem -> {
            paymentItemRepository.delete(paymentItem);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + paymentItemId + " and postId " + confirmBuyItemId));
    }


}
