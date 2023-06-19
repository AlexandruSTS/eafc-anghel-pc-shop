package eafcanghel.pcshop.item;

import jakarta.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ItemValidationException extends Exception {
    private final List<String> errors;

    public ItemValidationException(Set<ConstraintViolation<Object>> violations) {
        super("Item validation failed");
        this.errors = extractErrors(violations);
    }
    public List<String> getErrors() {
        return errors;
    }

    private List<String> extractErrors(Set<ConstraintViolation<Object>> violations) {
        List<String> errorList = new ArrayList<>();

        for (ConstraintViolation<Object> violation : violations) {
            String errorMessage = violation.getMessage();
            String propertyPath = violation.getPropertyPath().toString();

            String error = propertyPath +  " " + errorMessage ;
            errorList.add(error);
        }

        return errorList;
    }
}
