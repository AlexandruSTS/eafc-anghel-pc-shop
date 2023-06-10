package eafcanghel.pcshop.item;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    private final ItemService itemService;
    @GetMapping(value = "/all-items")
    public ResponseEntity <Page<Item>> getAllItems(Pageable pageable) {
        try {
            Page<Item> itemsPage = itemService.getAllItems(pageable);
            return ResponseEntity.ok(itemsPage);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
            Page<Item> emptyPage = Page.empty(); // Create an empty page
            return ResponseEntity.badRequest().body(emptyPage);
        }
    }
}
