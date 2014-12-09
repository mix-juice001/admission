package admission.bean;

import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BeanTest {

    @Test
    public void 最初の一歩() throws Exception {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Bean bean = new Bean();
        bean.setAaa(false);
        Set<ConstraintViolation<Bean>> violations = validator.validate(bean);
        assertThat("aaaはtrueじゃないといけないので一つエラーがある", violations.size(), is(1));
        for (ConstraintViolation<Bean> violation : violations) {
            System.out.println(violation.getMessage());
        }
    }
}