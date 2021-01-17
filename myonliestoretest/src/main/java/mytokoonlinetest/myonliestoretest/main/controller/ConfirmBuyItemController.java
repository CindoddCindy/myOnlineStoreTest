package mytokoonlinetest.myonliestoretest.main.controller;


import mytokoonlinetest.myonliestoretest.main.exception.ResourceNotFoundException;
import mytokoonlinetest.myonliestoretest.main.model.BuyItem;
import mytokoonlinetest.myonliestoretest.main.model.ConfirmBuyItem;
import mytokoonlinetest.myonliestoretest.main.repository.BuyItemRepository;
import mytokoonlinetest.myonliestoretest.main.repository.ConfirmByItemRepository;
import mytokoonlinetest.myonliestoretest.main.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ConfirmBuyItemController {

    @Autowired
    private ConfirmByItemRepository confirmByItemRepository;

    @Autowired
    private BuyItemRepository buyItemRepository;

    @GetMapping("/api/auth/buyitem/{buyItemId}/confirmbuyitem")
    public Page<ConfirmBuyItem> getAllConfirmByItemId(@PathVariable (value = "buyItemId") Long buyItemId,
                                                   Pageable pageable) {
        return confirmByItemRepository.findByBuyItemId(buyItemId, pageable);
    }

    @PostMapping("/api/auth/buyitem/{buyItemId}/confirmbuyitem")
    public ConfirmBuyItem createConfirm(@PathVariable (value = "buyItemId") Long buyItemId,
                             @Valid @RequestBody ConfirmBuyItem confirmBuyItem) {
        return buyItemRepository.findById(buyItemId).map(buyItem -> {
            confirmBuyItem.setBuyItem(buyItem);
            return confirmByItemRepository.save(confirmBuyItem);
        }).orElseThrow(() -> new ResourceNotFoundException("buyItemId " + buyItemId + " not found"));
    }


    @PutMapping("/api/auth/buyitem/{buyItemId}/confirmbuyitem/{confirmBuyItemId}")
    public ConfirmBuyItem updateConfirmBuyItem(@PathVariable (value = "buyItemId") Long buyItemId,
                                 @PathVariable (value = "confirmBuyItemId") Long confirmBuyItemId,
                                 @Valid @RequestBody ConfirmBuyItem confirmBuyItemRequest) {
        if(!buyItemRepository.existsById(buyItemId)) {
            throw new ResourceNotFoundException("BuyItemId " + buyItemId + " not found");
        }

        return confirmByItemRepository.findById(confirmBuyItemId).map(confirmBuyItem -> {
            confirmBuyItem.setConf(confirmBuyItemRequest.getConf());
            confirmBuyItem.setNote(confirmBuyItemRequest.getNote());
            return confirmByItemRepository.save(confirmBuyItem);
        }).orElseThrow(() -> new ResourceNotFoundException("BuyItemId " + confirmBuyItemId + "not found"));
    }

    @DeleteMapping("/api/auth/buyitem/{buyItemId}/confirmbuyitem/{confirmBuyItemId}")
    public ResponseEntity<?> deleteConfirmBuy(@PathVariable (value = "buyItemId") Long buyItemId,
                                       @PathVariable (value = "confirmBuyItemId") Long confirmBuyItemId) {
        return confirmByItemRepository.findByIdAndBuyItemId(confirmBuyItemId, buyItemId).map(confirmBuyItem -> {
            confirmByItemRepository.delete(confirmBuyItem);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Buy Item not found with id " + confirmBuyItemId + " and postId " + buyItemId));
    }


}
