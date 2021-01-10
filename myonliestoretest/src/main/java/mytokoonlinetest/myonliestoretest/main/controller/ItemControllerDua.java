package mytokoonlinetest.myonliestoretest.main.controller;

import mytokoonlinetest.myonliestoretest.main.exception.ResourceNotFoundException;
import mytokoonlinetest.myonliestoretest.main.model.Item;
import mytokoonlinetest.myonliestoretest.main.repository.ItemRepositoryDua;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ItemControllerDua {

    @Autowired
    private ItemRepositoryDua itemRepositoryDua;

    @GetMapping("/item")
    public Page<Item> getAllItems(Pageable pageable) {
        return itemRepositoryDua.findAll(pageable);
    }

    @PostMapping("/item")
    public Item createItem(@Valid @RequestBody Item item) {
        return itemRepositoryDua.save(item);
    }

    @PutMapping("/item/{itemId}")
    public Item updateItem(@PathVariable Long itemId, @Valid @RequestBody Item itemRequest) {
        return itemRepositoryDua.findById(itemId).map(item -> {
            item.setItemname(itemRequest.getItemname());
            item.setItemimage(itemRequest.getItemimage());
            item.setItemprice(itemRequest.getItemprice());
            item.setItemaddress(itemRequest.getItemaddress());
            item.setItemqty(itemRequest.getItemqty());
            item.setItemdesc(itemRequest.getItemdesc());

            return itemRepositoryDua.save(item);
        }).orElseThrow(() -> new ResourceNotFoundException("ItemId " + itemId + " not found"));
    }


    @DeleteMapping("/item/{itemId}")
    public ResponseEntity<?> deleteItem(@PathVariable Long itemId) {
        return itemRepositoryDua.findById(itemId).map(item -> {
            itemRepositoryDua.delete(item);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + itemId + " not found"));
    }

}
