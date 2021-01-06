package mytokoonlinetest.myonliestoretest.main.controller;



import mytokoonlinetest.myonliestoretest.auth.repository.UserRepository;
import mytokoonlinetest.myonliestoretest.main.model.Item;
import mytokoonlinetest.myonliestoretest.main.exception.ResourceNotFoundException;
import mytokoonlinetest.myonliestoretest.main.repository.ItemRepository;
import mytokoonlinetest.myonliestoretest.main.repository.UserDuaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserDuaRepository userDuaRepository;

    @GetMapping("/user/{userId}/items")
    public Page<Item> getAllItemsByUserId(@PathVariable (value = "userId") Long userId,
                                             Pageable pageable) {
        return itemRepository.findByUserId(userId, pageable);
    }

    @PostMapping("/user/{userId}/item")
    public Item createItems(@PathVariable (value = "userId") Long userId,
                              @Valid @RequestBody Item item) {
        return userDuaRepository.findById(userId).map(user ->  {
            item.setUser(user);
            return itemRepository.save(item);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    }

    @PutMapping("/user/{userId}/item/{itemId}")
    public Item updateItem(@PathVariable (value = "userId") Long userId,
                                 @PathVariable (value = "itemId") Long itemId,
                                 @Valid @RequestBody Item itemRequest) {
        if(!userDuaRepository.existsById(userId)) {
            throw new ResourceNotFoundException("UserId " + userId + " not found");
        }

        return itemRepository.findById(itemId).map(item -> {
            item.setItemname(itemRequest.getItemname());
            return itemRepository.save(item);
        }).orElseThrow(() -> new ResourceNotFoundException("ItemId " + itemId + "not found"));
    }

    @DeleteMapping("/user/{userId}/item/{itemId}")
    public ResponseEntity<?> deleteItem(@PathVariable (value = "userId") Long userId,
                                           @PathVariable (value = "itemId") Long itemId) {
        return itemRepository.findByIdAndUserId(itemId, userId).map(item -> {
            itemRepository.delete(item);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + itemId + " and postId " + userId));
    }
}
