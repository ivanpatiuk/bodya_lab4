package lpnu.controller;

import lpnu.dto.ItemDTO;
import lpnu.entity.Item;
import lpnu.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/item/")
@RestController
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("get-item-by-id/{id}")
    public ItemDTO getItemById(@PathVariable final Long id) { return itemService.getItemBy(id); }

   @PostMapping ("create-item")
   public ItemDTO createItem(@RequestBody final Item item){
        return itemService.createItem(item);
   }

   @PutMapping("update-item-name")
    public ItemDTO updateItemName(@RequestBody final Item item){
        return itemService.updateItemName(item);
   }

   @DeleteMapping("remove-item/{id}")
    public ItemDTO removeItem(@PathVariable final Long id){
        return itemService.removeItem(id);
   }
}
