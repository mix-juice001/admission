package admission.bean;

import org.apache.tomcat.util.net.SecureNioChannel;
import org.junit.Before;
import org.junit.Test;

import javax.validation.*;
import javax.validation.groups.Default;

import java.math.BigDecimal;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ValidationTest {

    private Validator validator;
    @Before
    public void setUp() throws Exception {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void validateTest() {
        BooleanBean bean = new BooleanBean();
        bean.setMustFalse(Boolean.TRUE);
        bean.setMustTrue(false);

        Set<ConstraintViolation<BooleanBean>> violations = validator.validate(bean);
        assertThat("2つエラーがある", violations.size(), is(2));

        for (ConstraintViolation<BooleanBean> violation : violations) {
            System.out.println(violation.getMessage());
        }
    }

    @Test
    public void decimalValidation() throws Exception {
        DecimalMaxMinBean bean = new DecimalMaxMinBean();
        bean.setStringValue("100"); // 10より大きいからNG
        bean.setIntValue(-9); // -10.0より大きいからOK
        bean.setBigDecimalValue(new BigDecimal("3.5")); // 範囲内だからOK
        assertThat(validator.validate(bean).size(), is(1));

        for (ConstraintViolation<DecimalMaxMinBean> violation : validator.validate(bean)) {
            System.out.println(violation.getMessage());
        }
    }

    @Test
    public void digitValidate() throws Exception {
        DigitBean bean = new DigitBean();
        bean.setStringValue("101.000"); // 少数以下が3桁超えてるのでNG
        bean.setIntValue(1000000000); // 整数部が4桁超えてるのでNG
        bean.setBigDecimalValue(new BigDecimal("1111.123")); // 桁数内なのでOK
        assertThat(validator.validate(bean).size(), is(2));

        for (ConstraintViolation<DigitBean> violation : validator.validate(bean)) {
            System.out.println(violation.getMessage());
        }
    }

    @Test
    public void validatePattern() throws Exception {
        PatternBean bean = new PatternBean();
        bean.setHoge("bar"); // hogeじゃないからNG
        bean.setBar("bar"); // barだからOK
        assertThat(validator.validate(bean).size(), is(1));

        for (ConstraintViolation<PatternBean> violation : validator.validate(bean)) {
            System.out.println(violation.getMessage());
        }
    }

    @Test
    public void validateSize() throws Exception {
        Set<ConstraintViolation<SizeBean>> violations = validator.validate(new SizeBean());
        for (ConstraintViolation<SizeBean> violation : violations) {
            System.err.println(violation.getMessage());
        }
    }

    @Test
    public void validateFirstGroupDefaultGroups() throws Exception {
        // 以下の二つは同じ意味
        // グループがDefaultのものだけ検証する
        // ここではaaaだけが検査対象となる
        assertThat(validator.validate(new FirstGroupsBean()).size(), is(1));
        assertThat(validator.validate(new FirstGroupsBean(), Default.class).size(), is(1));
    }
    @Test
    public void validateFirstGroupGroup1Groups() throws Exception {
        // グループがGroup1のものだけが検証する
        // ここではbbbだけが検査対象となる
        assertThat(validator.validate(new FirstGroupsBean(), FirstGroupsBean.Group1.class).size(), is(1));
    }

    @Test
    public void validateSecoundGroupDefaultGroups() throws Exception {
        // グループがDefaultのものだけ検証する
        // ここではaaa,bbbだけが検査対象となる
        assertThat(validator.validate(new SecondGroupsBean()).size(), is(2));
    }
    @Test
    public void validateSecoundGroupGroup1Groups() throws Exception {
        // グループがGroup1のものだけが検証する
        // ここではbbb, cccだけが検査対象となる
        assertThat(validator.validate(new SecondGroupsBean(), SecondGroupsBean.Group1.class).size(), is(2));
    }
    @Test
    public void validateSecoundGroupAllGroups() throws Exception {
        // すべてのグループを検証する
        // ここではaaa, bbb, cccすべてが対象となる
        assertThat(validator.validate(new SecondGroupsBean(), Default.class, SecondGroupsBean.Group1.class).size(), is(3));
    }

    @Test
    public void validateGroupSequence() throws Exception {

        GroupSequenceBean bean = new GroupSequenceBean();
        // 最初にDefaultが実施される
//        validate(bean, GroupSequenceBean.All.class);

        assertThat(validator.validate(bean, GroupSequenceBean.All.class).size(), is(2));

        bean.aaa = new Object();
        bean.bbb = new Object();
        // Defaultが問題なけれがGroup1が検証される
        assertThat(validator.validate(bean, GroupSequenceBean.All.class).size(), is(1));
    }

    @Test
    public void validatePatternList() throws Exception {
        PatternListBean bean = new PatternListBean();
        bean.str = "ccc";
        assertThat(validator.validate(bean).size(), is(2));
        bean.str = "aaa";
        assertThat(validator.validate(bean).size(), is(1));
        bean.str = "bbb";
        assertThat(validator.validate(bean).size(), is(1));
        bean.str = "aaabbb";
        assertThat(validator.validate(bean).size(), is(0));
    }

    @Test
    public void NormalUserでバリデーションしてみる() throws Exception {
        GroupsWithList gl = new GroupsWithList();
        gl.diskSize = 9;
        assertThat(validator.validate(gl, GroupsWithList.NormalUser.class).size(), is(0));

        gl.diskSize = 50;
        assertThat("一般ユーザなので10超えててNG", validator.validate(gl, GroupsWithList.NormalUser.class).size(), is(1));
    }

    @Test
    public void PremiumUserでバリデーションしてみる() throws Exception {
        GroupsWithList gl = new GroupsWithList();
        gl.diskSize = 9;
        assertThat(validator.validate(gl, GroupsWithList.PremiumUser.class).size(), is(0));

        gl.diskSize = 50;
        assertThat("プレミアムユーザなので100までOK", validator.validate(gl, GroupsWithList.PremiumUser.class).size(), is(0));
    }
}