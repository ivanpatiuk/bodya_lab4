package lpnu.repository;

import lpnu.entity.Item;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.TreeMap;

@Repository
public class ItemRepository {
    private final Map<Long, Item> itemRepository = new TreeMap<>();
    private static Long freeId = 0L;

    public Item createItem(final Item item){
        item.setItemId(++freeId);
        itemRepository.put(item.getItemId(), item);
        return item;
    }

    public Item getItemById(final Long itemId){
        return itemRepository.get(itemId);
    }

    public Item updateItem(final Item item){
        itemRepository.put(item.getItemId(), item);
        return itemRepository.get(item.getItemId());
    }

    public Item removeItem(final Long itemId){
        return itemRepository.remove(itemId);
    }
}
