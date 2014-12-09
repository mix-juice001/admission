package admission.bean;

import javax.validation.constraints.Pattern;

public class PatternListBean {
    @Pattern.List({
            @Pattern(regexp = "^aaa.*"),
            @Pattern(regexp = ".*bbb$")
    })
    String str;

}
