package lpnu.service.impl;

import lpnu.config.DTOConvertor;
import lpnu.dto.ItemDTO;
import lpnu.entity.Item;
import lpnu.repository.ItemRepository;
import lpnu.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public static ItemDTO toDTO(final Item item){
        return DTOConvertor.convertToDto(item, ItemDTO.class);
    }

    @Override
    public ItemDTO getItemBy(final Long itemId) {
        return toDTO(itemRepository.getItemById(itemId));
    }

    @Override
    public ItemDTO createItem(final Item item) {
        return toDTO(itemRepository.createItem(item));
    }

    @Override
    public ItemDTO removeItem(final Long itemId){
        return toDTO(itemRepository.removeItem(itemId));
    }

    @Override
    public ItemDTO updateItemName(final Item item) {
        return toDTO(itemRepository.updateItem(item));
    }
}
