package admission.service;

import org.junit.Test;
import org.springframework.context.annotation.Bean;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserFormTest {

    @Test
    public void testName() throws Exception {


    }

    @Test
    public void 最初の一歩() throws Exception {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        UserForm userForm = new UserForm();
        userForm.setAge(1);
        Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);
        assertThat("aaaはtrueじゃないといけないので一つエラーがある", violations.size(), is(2));
        for (ConstraintViolation<UserForm> violation : violations) {
            System.out.println(violation.getMessage());
        }
    }
}
