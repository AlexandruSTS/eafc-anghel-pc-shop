package eafcanghel.pcshop.item;

import eafcanghel.pcshop.category.Category;
import eafcanghel.pcshop.category.CategoryRepository;
import jakarta.validation.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    public Page<Item> getAllItems(Pageable pageable) {
        Page<Item> itemsPage = itemRepository.findAll(pageable);
        return itemsPage;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Item addItem(@Valid Item item) throws ItemValidationException {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Object>> violations = validator.validate(item);

        if (!violations.isEmpty()) {
            throw new ItemValidationException(violations);
        }
        Item savedItem = itemRepository.save(item);
        return savedItem;
    }

    public String calculateETag(List<Item> items) {
        StringBuilder sb = new StringBuilder();

        for (Item item : items) {
            sb.append(item.getVersion());
        }
        // Generate the ETag by hashing the concatenated identifier string
        String eTag = DigestUtils.md5DigestAsHex(sb.toString().getBytes());

        return eTag;
    }
}
