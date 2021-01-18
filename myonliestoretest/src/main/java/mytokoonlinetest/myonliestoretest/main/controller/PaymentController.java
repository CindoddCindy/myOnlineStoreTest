package mytokoonlinetest.myonliestoretest.main.controller;

import mytokoonlinetest.myonliestoretest.main.exception.ResourceNotFoundException;
import mytokoonlinetest.myonliestoretest.main.model.ConfirmBuyItem;
import mytokoonlinetest.myonliestoretest.main.model.Payment;
import mytokoonlinetest.myonliestoretest.main.repository.BuyItemRepository;
import mytokoonlinetest.myonliestoretest.main.repository.ConfirmByItemRepository;
import mytokoonlinetest.myonliestoretest.main.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PaymentController {

    @Autowired
    private ConfirmByItemRepository confirmByItemRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping("/api/auth/confirmbuyitem/{confirmId}/payment")
    public Page<Payment> getAllPaymentById(@PathVariable (value = "confirmId") Long confirmId,
                                               Pageable pageable) {
        return paymentRepository.findByConfirmId(confirmId, pageable);
    }

    @PostMapping("/api/auth/confirmbuyitem/{confirmId}/payment")
    public Payment createPayment(@PathVariable (value = "confirmId") Long confirmId,
                                        @Valid @RequestBody Payment payment) {
        return confirmByItemRepository.findById(confirmId).map(confirmBuyItem -> {
            payment.setConfirmBuyItem(confirmBuyItem);
            return paymentRepository.save(payment);
        }).orElseThrow(() -> new ResourceNotFoundException("ConfirmId " + confirmId + " not found"));
    }


    @PutMapping("/api/auth/confirmbuyitem/{confirmId}/payment/{paymentId}")
    public Payment updatePayment(@PathVariable (value = "confirmId") Long confirmId,
                                               @PathVariable (value = "paymentId") Long paymentId,
                                               @Valid @RequestBody Payment paymentRequest) {
        if(!confirmByItemRepository.existsById(confirmId)) {
            throw new ResourceNotFoundException("BuyItemId " + confirmId + " not found");
        }

        return paymentRepository.findById(paymentId).map(payment -> {
            payment.setPayconf(paymentRequest.getPayconf());
            payment.setPaynote(paymentRequest.getPaynote());
            return paymentRepository.save(payment);
        }).orElseThrow(() -> new ResourceNotFoundException("BuyItemId " + paymentId + "not found"));
    }

    @DeleteMapping("/api/auth/confirmbuyitem/{confirmId}/payment/{paymentId}")
    public ResponseEntity<?> deletePayment(@PathVariable (value = "confirmId") Long confirmId,
                                              @PathVariable (value = "paymentId") Long paymentId) {
        return paymentRepository.findByPaymentIdAndConfirmId(paymentId, confirmId).map(payment -> {
            paymentRepository.delete(payment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Buy payment not found with id " + paymentId + " and ConfirmId " + confirmId));
    }


}
