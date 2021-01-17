package mytokoonlinetest.myonliestoretest.main.controller;

import mytokoonlinetest.myonliestoretest.main.exception.ResourceNotFoundException;
import mytokoonlinetest.myonliestoretest.main.model.BuyItem;
import mytokoonlinetest.myonliestoretest.main.repository.BuyItemRepository;
import mytokoonlinetest.myonliestoretest.main.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class BuyItemController {

    @Autowired
    private BuyItemRepository buyItemRepository;

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/api/auth/item/{itemId}/buyitem")
    public Page<BuyItem> getAllBuysByItemId(@PathVariable (value = "itemId") Long itemId,
                                                Pageable pageable) {
        return buyItemRepository.findByItemId(itemId, pageable);
    }

    @PostMapping("/api/auth/item/{itemId}/buyitem")
    public BuyItem createBuy(@PathVariable (value = "itemId") Long itemId,
                                 @Valid @RequestBody BuyItem buyItem) {
        return itemRepository.findById(itemId).map(item -> {
            buyItem.setItem(item);
            return buyItemRepository.save(buyItem);
        }).orElseThrow(() -> new ResourceNotFoundException("ItemId " + itemId + " not found"));
    }

    @PutMapping("/api/auth/item/{itemId}/buyitem/{buyitemId}")
    public BuyItem updateBuyItem(@PathVariable (value = "itemId") Long itemId,
                                 @PathVariable (value = "buyitemId") Long buyitemId,
                                 @Valid @RequestBody BuyItem buyItemRequest) {
        if(!itemRepository.existsById(itemId)) {
            throw new ResourceNotFoundException("ItemId " + itemId + " not found");
        }

        return buyItemRepository.findById(buyitemId).map(buyItem -> {
            buyItem.setBuyername(buyItemRequest.getBuyername());
            buyItem.setBuyerphone(buyItemRequest.getBuyerphone());
            buyItem.setBuyeraddress(buyItemRequest.getBuyeraddress());
            return buyItemRepository.save(buyItem);
        }).orElseThrow(() -> new ResourceNotFoundException("BuyItemId " + buyitemId + "not found"));
    }

    @DeleteMapping("/api/auth/item/{itemId}/buyitem/{buyitemId}")
    public ResponseEntity<?> deleteBuy(@PathVariable (value = "itemId") Long itemId,
                                           @PathVariable (value = "buyitemId") Long buyitemId) {
        return buyItemRepository.findByIdAndItemId(buyitemId, itemId).map(buyItem -> {
            buyItemRepository.delete(buyItem);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Buy Item not found with id " + buyitemId + " and postId " + itemId));
    }
}
