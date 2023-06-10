package eafcanghel.pcshop.item;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Page<Item> getAllItems(Pageable pageable) {
        Page<Item> itemsPage = itemRepository.findAll(pageable);
        return itemsPage;
    }
}
