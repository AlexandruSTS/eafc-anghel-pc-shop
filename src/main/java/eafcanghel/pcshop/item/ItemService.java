package eafcanghel.pcshop.item;

import eafcanghel.pcshop.category.Category;
import eafcanghel.pcshop.category.CategoryRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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


    public void handleValidationErrors(Set<ConstraintViolation<Item>> violations) throws ItemValidationException {
        if (!violations.isEmpty()) {
            // Create a StringBuilder to collect the error messages
            StringBuilder errorMessageBuilder = new StringBuilder();

            // Append each validation error message to the StringBuilder
            for (ConstraintViolation<Item> violation : violations) {
                String fieldName = violation.getPropertyPath().toString();
                String errorMessage = violation.getMessage();
                errorMessageBuilder.append(fieldName).append(" - ").append(errorMessage).append("; ");
            }

            // Throw a custom exception with the error message
            throw new ItemValidationException(errorMessageBuilder.toString());
        }
    }

    public Item addItem(Item item) throws ItemValidationException {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        // Validate the item object
        Set<ConstraintViolation<Item>> violations = validator.validate(item);

        // Handle validation errors
        handleValidationErrors(violations);

        // Save the item to the database
        Item savedItem = itemRepository.save(item);

        // Return the saved item
        return savedItem;
    }



}
