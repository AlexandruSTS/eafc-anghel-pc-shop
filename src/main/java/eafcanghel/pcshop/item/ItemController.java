package eafcanghel.pcshop.item;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public ResponseEntity getAllItems() {
        try {
            List<Item> itemsList = itemService.getAllItems();
            return ResponseEntity.accepted().body(itemsList);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
            return ResponseEntity.badRequest().body("Error retrieving all items");
        }
    }
}
