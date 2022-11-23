package lpnu.init;

import lpnu.entity.Item;
import lpnu.entity.Warehouse;
import lpnu.entity.WarehouseItem;
import lpnu.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.TreeMap;

@Component
public class Initialization {

    private final DeliveryRepository deliveryRepository;
    private final OrderRepository orderRepository;
    private final WarehouseRepository warehouseRepository;
    private final ItemRepository itemRepository;


    @Autowired
    public Initialization(DeliveryRepository deliveryRepository, OrderRepository orderRepository, WarehouseRepository warehouseRepository, ItemRepository itemRepository) {
        this.deliveryRepository = deliveryRepository;
        this.orderRepository = orderRepository;
        this.warehouseRepository = warehouseRepository;
        this.itemRepository = itemRepository;
    }

    private void initItemRepository(){
        itemRepository.createItem(new Item("Pencil")); // id 1
        itemRepository.createItem(new Item("Geography Book")); //id 2
        itemRepository.createItem(new Item("Pen")); // id 3
        itemRepository.createItem(new Item("Copybook")); // id 4
        itemRepository.createItem(new Item("Pencil Sharpener")); // id 5
    }

    private void initWarehouseRepository(){
        Map<Long, WarehouseItem> map = new TreeMap<>();
        Item item1 = itemRepository.getItemById(1L);
        Item item2 = itemRepository.getItemById(2L);
        Item item3 = itemRepository.getItemById(3L);
        Item item4 = itemRepository.getItemById(4L);
        Item item5 = itemRepository.getItemById(5L);

        WarehouseItem warehouseItem1 = new WarehouseItem(item1, 30,  15.0);
        WarehouseItem warehouseItem2 = new WarehouseItem(item2, 50,  130.0);
        WarehouseItem warehouseItem3 = new WarehouseItem(item3, 60,  7.5);
        WarehouseItem warehouseItem4 = new WarehouseItem(item4, 70,  21.30);
        WarehouseItem warehouseItem5 = new WarehouseItem(item5, 35,  17.0);

        map.put(warehouseItem1.getItem().getItemId(), warehouseItem1);
        map.put(warehouseItem2.getItem().getItemId(), warehouseItem2);
        map.put(warehouseItem3.getItem().getItemId(), warehouseItem3);
        map.put(warehouseItem4.getItem().getItemId(), warehouseItem4);
        map.put(warehouseItem5.getItem().getItemId(), warehouseItem5);

        Warehouse warehouse1 = new Warehouse("Address 1, 29", map);
        warehouseRepository.createWarehouse(warehouse1);
    }

    @PostConstruct
    public void init(){
        initItemRepository();
        initWarehouseRepository();
    }

}
