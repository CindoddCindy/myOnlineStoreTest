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

    @GetMapping("/paymentitem/{paymentItemId}/paymentdone")
    public Page<PaymentDone> getAllPaymentDoneByPaymentItemId(@PathVariable (value = "paymentItemId") Long paymentItemId,
                                                    Pageable pageable) {
        return paymentDoneRepository.findByPaymentItemId(paymentItemId, pageable);
    }

    @PostMapping("/paymentitem/{paymentItemId}/paymentdone")
    public PaymentDone createPaymentDone(@PathVariable (value = "paymentItemId") Long paymentItemId,
                                 @Valid @RequestBody PaymentDone paymentDone) {
        return paymentItemRepository.findById(paymentItemId).map(paymentItem -> {
            paymentDone.setPaymentItem(paymentItem);
            return paymentDoneRepository.save(paymentDone);
        }).orElseThrow(() -> new ResourceNotFoundException("PaymentItemId " + paymentItemId + " not found"));
    }
/*
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public Comment updateComment(@PathVariable (value = "postId") Long postId,
                                 @PathVariable (value = "commentId") Long commentId,
                                 @Valid @RequestBody Comment commentRequest) {
        if(!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }

        return commentRepository.findById(commentId).map(comment -> {
            comment.setText(commentRequest.getText());
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + "not found"));
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable (value = "postId") Long postId,
                                           @PathVariable (value = "commentId") Long commentId) {
        return commentRepository.findByIdAndPostId(commentId, postId).map(comment -> {
            commentRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + commentId + " and postId " + postId));
    }

 */
}
