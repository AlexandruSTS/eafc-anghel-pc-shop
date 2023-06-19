package eafcanghel.pcshop.category;

import eafcanghel.pcshop.item.ItemService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    private final ItemService itemService;

    @GetMapping(value = "/all-categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        try {
            List<Category> categories = itemService.getAllCategories();
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        }
    }

}
