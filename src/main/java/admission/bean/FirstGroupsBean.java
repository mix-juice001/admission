package admission.bean;

import javax.validation.constraints.NotNull;

public class FirstGroupsBean {
    @NotNull
    // 何も指定しないと暗黙的に@NotNull(groups = Default.class
    Object aaa;

    @NotNull(groups = Group1.class)
    Object bbb;

    interface Group1 {
    }

    interface Group2 {
    }
}
