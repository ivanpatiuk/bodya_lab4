package lpnu.service;

import lpnu.dto.ItemDTO;
import lpnu.entity.Item;

public interface ItemService {
    ItemDTO getItemBy(final Long itemId);
    ItemDTO createItem(final Item item);
    ItemDTO removeItem(final Long itemId);
    ItemDTO updateItemName(final Item item);
}
