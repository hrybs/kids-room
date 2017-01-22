package ua.softserveinc.tc.validator.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * This annotation checks "is unique managers" was entered.
 *
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = UniqueManagerValidationImpl.class)
public @interface UniqueManagerValidation {

    String message() default "Invalid value entered.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
