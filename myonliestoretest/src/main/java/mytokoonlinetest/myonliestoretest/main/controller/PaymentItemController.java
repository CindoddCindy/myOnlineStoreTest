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

    @GetMapping("/confirmbuyitem/{confirmBuyItemId}/paymentitem")
    public Page<PaymentItem> getAllPaymentItemByConfirmId(@PathVariable (value = "confirmBuyItem") Long confirmBuyItemId,
                                                    Pageable pageable) {
        return paymentItemRepository.findByConfirmBuyItemId(confirmBuyItemId, pageable);
    }

    @PostMapping("/confirmbuyitem/{confirmBuyItemId}/paymentitem")
    public PaymentItem createPamentItem(@PathVariable (value = "confirmBuyItemId") Long confirmBuyItemId,
                                 @Valid @RequestBody PaymentItem paymentItem) {
        return confirmByItemRepository.findById(confirmBuyItemId).map(confirmBuyItem -> {
            paymentItem.setConfirmBuyItem(confirmBuyItem);
            return paymentItemRepository.save(paymentItem);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + confirmBuyItemId + " not found"));
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
