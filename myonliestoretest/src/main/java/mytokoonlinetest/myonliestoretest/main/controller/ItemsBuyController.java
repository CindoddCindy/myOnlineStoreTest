package mytokoonlinetest.myonliestoretest.main.controller;

import mytokoonlinetest.myonliestoretest.main.exception.ResourceNotFoundException;
import mytokoonlinetest.myonliestoretest.main.model.BuyItem;
import mytokoonlinetest.myonliestoretest.main.repository.ItemBuyRepository;
import mytokoonlinetest.myonliestoretest.main.repository.ItemRepositoryDua;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ItemsBuyController {
    @Autowired
    private ItemBuyRepository itemBuyRepository;

    @Autowired
    private ItemRepositoryDua itemRepositoryDua;

    @GetMapping("/item/{itemId}/buyitem")
    public Page<BuyItem> getAllBuyByItemId(@PathVariable (value = "itemId") Long itemId,
                                                Pageable pageable) {
        return itemBuyRepository.findByItemId(itemId, pageable);
    }

    @PostMapping("/item/{itemId}/buyitem")
    public BuyItem createBuy(@PathVariable (value = "itemId") Long itemId,
                                 @Valid @RequestBody BuyItem buyItem) {
        return itemRepositoryDua.findById(itemId).map(item -> {
            buyItem.setItem(item);
            return itemBuyRepository.save(buyItem);
        }).orElseThrow(() -> new ResourceNotFoundException("ItemId " + itemId + " not found"));
    }

    @PutMapping("/item/{itemId}/buyitem/{buyitemId}")
    public BuyItem updateBuyItems(@PathVariable (value = "itemId") Long itemId,
                                 @PathVariable (value = "buyitemId") Long buyitemId,
                                 @Valid @RequestBody BuyItem buyItemRequest) {
        if(!itemRepositoryDua.existsById(itemId)) {
            throw new ResourceNotFoundException("itemId " + itemId + " not found");
        }

        return itemBuyRepository.findById(buyitemId).map(buyItem -> {
            buyItem.setBuyername(buyItemRequest.getBuyername());
            return itemBuyRepository.save(buyItem);
        }).orElseThrow(() -> new ResourceNotFoundException("BuyItemId " + buyitemId + "not found"));
    }

    @DeleteMapping("/item/{itemId}/buyitem/{buyitemId}")
    public ResponseEntity<?> deleteBuyItem(@PathVariable (value = "itemId") Long itemId,
                                           @PathVariable (value = "buyitemId") Long buyitemId) {
        return itemBuyRepository.findByIdAndItemId(buyitemId, itemId).map(buyItem -> {
            itemBuyRepository.delete(buyItem);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + buyitemId + " and postId " + itemId));
    }
}
