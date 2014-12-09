package admission.bean;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

public class SecondGroupsBean {
    interface Group1 {}
    @NotNull
    Object aaa;
    @NotNull(groups = { Group1.class, Default.class })
    Object bbb;
    @NotNull(groups = Group1.class)
    Object ccc;
}
