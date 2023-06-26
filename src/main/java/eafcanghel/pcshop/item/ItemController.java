package eafcanghel.pcshop.item;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    private final ItemService itemService;

    @GetMapping(value = "/all-items")
    public ResponseEntity<?> getAllItems(Pageable pageable, Authentication authentication,
                                         @RequestHeader(value = "If-None-Match", required = false) String ifNoneMatch) {
        if (!authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated.");
        }
        if (!hasPermission(authentication, "SCOPE_read:all-items")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User does not have the required permission.");
        }
        try {
            String requestEtag = "";

            if (ifNoneMatch != null) {
                requestEtag = this.removeFirstAndLastChar(ifNoneMatch);
            }

            Page<Item> itemsPage = itemService.getAllItems(pageable);

            // Generate the ETag value using the latest version of the items
            String currentETag = itemService.calculateETag(itemsPage.getContent());
            CacheControl cacheControl = CacheControl.maxAge(300, TimeUnit.SECONDS).cachePublic();

            // Compare the received ETag with the current ETag
            if (requestEtag != null && requestEtag.equals(currentETag)) {

                return ResponseEntity
                        .status(HttpStatus.NOT_MODIFIED)
                        .cacheControl(cacheControl)
                        .eTag(currentETag)
                        .build();
            }

            return ResponseEntity
                    .ok()
                    .cacheControl(cacheControl)
                    .eTag(currentETag)
                    .body(itemsPage);


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
        System.out.println(authentication.getAuthorities());
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

    @GetMapping(value = "/permissions")
    public ResponseEntity<?> getPermissions(Authentication authentication) {
        if (!authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated.");
        }
        try {
            List<String> permissions = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(permissions);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public static String removeFirstAndLastChar(String str) {
        if (str.length() <= 2) {
            return "";
        }

        return str.substring(1, str.length() - 1);
    }
}
