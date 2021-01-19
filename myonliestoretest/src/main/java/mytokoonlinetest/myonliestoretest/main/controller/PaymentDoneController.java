package mytokoonlinetest.myonliestoretest.main.controller;


import mytokoonlinetest.myonliestoretest.main.exception.ResourceNotFoundException;
import mytokoonlinetest.myonliestoretest.main.model.PaymentDone;
import mytokoonlinetest.myonliestoretest.main.repository.PaymentDoneRepository;
import mytokoonlinetest.myonliestoretest.main.repository.PaymentItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PaymentDoneController {

    @Autowired
    private PaymentDoneRepository paymentDoneRepository;

    @Autowired
    private PaymentItemRepository paymentItemRepository;

    @GetMapping("/api/auth/paymentitem/{paymentItemId}/paymentdone")
    public Page<PaymentDone> getAllPaymentDoneByPaymentItemId(@PathVariable (value = "paymentItemId") Long paymentItemId,
                                                    Pageable pageable) {
        return paymentDoneRepository.findByPaymentItemId(paymentItemId, pageable);
    }

    @PostMapping("/api/auth/paymentitem/{paymentItemId}/paymentdone")
    public PaymentDone createPaymentDone(@PathVariable (value = "paymentItemId") Long paymentItemId,
                                 @Valid @RequestBody PaymentDone paymentDone) {
        return paymentItemRepository.findById(paymentItemId).map(paymentItem -> {
            paymentDone.setPaymentItem(paymentItem);
            return paymentDoneRepository.save(paymentDone);
        }).orElseThrow(() -> new ResourceNotFoundException("PaymentItemId " + paymentItemId + " not found"));
    }

    @PutMapping("/api/auth/paymentitem/{paymentItemId}/paymentdone/{paymentDoneId}")
    public PaymentDone updatePaymentDone(@PathVariable (value = "paymentItemId") Long paymentItemId,
                                 @PathVariable (value = "paymentDoneId") Long paymentDoneId,
                                 @Valid @RequestBody PaymentDone paymentDoneRequest) {
        if(!paymentItemRepository.existsById(paymentItemId)) {
            throw new ResourceNotFoundException("PostId " + paymentItemId + " not found");
        }

        return paymentDoneRepository.findById(paymentDoneId).map(paymentDone -> {
            paymentDone.setSellerconf(paymentDoneRequest.getSellerconf());
            paymentDone.setNamepay(paymentDoneRequest.getNamepay());
            paymentDone.setPayqty(paymentDoneRequest.getPayqty());
            paymentDone.setNotepay(paymentDone.getNotepay());
            return paymentDoneRepository.save(paymentDone);
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + paymentDoneId + "not found"));
    }

    @DeleteMapping("/api/auth/paymentitem/{paymentItemId}/paymentdone/{paymentDoneId}")
    public ResponseEntity<?> deletePaymentDone(@PathVariable (value = "paymentItemId") Long paymentItemId,
                                           @PathVariable (value = "paymentDoneId") Long paymentDoneId) {
        return paymentDoneRepository.findByIdAndPaymentItemId(paymentDoneId, paymentItemId).map(paymentDone -> {
            paymentDoneRepository.delete(paymentDone);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + paymentDoneId + " and postId " + paymentItemId));
    }


}
