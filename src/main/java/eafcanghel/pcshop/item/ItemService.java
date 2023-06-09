package eafcanghel.pcshop.item;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<Item> getAllItems() {
        List<Item> itemsList = itemRepository.findAll();
        return itemsList;
    }
}
