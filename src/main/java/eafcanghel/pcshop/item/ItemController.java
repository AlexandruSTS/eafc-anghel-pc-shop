package eafcanghel.pcshop.item;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
            Page<Item> emptyPage = Page.empty();
            return ResponseEntity.badRequest().body(emptyPage);
        }
    }

    @PostMapping(value = "/add-item")
    public ResponseEntity<?> addItem(@RequestBody Item item, Authentication authentication) {
        if (!authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated.");
        }
        if (!hasPermission(authentication, "SCOPE_write:item")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User does not have the required permission.");
        }
        try {
            Item newItem = itemService.addItem(item);
            return ResponseEntity.status(HttpStatus.CREATED).body(newItem);
        } catch (ItemValidationException e) {
            LOGGER.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getErrors());
        }
    }

    private boolean hasPermission(Authentication authentication, String requiredPermission) {
        return authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(requiredPermission));
    }
}
