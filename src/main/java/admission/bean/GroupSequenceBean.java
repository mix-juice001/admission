package admission.bean;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

public class GroupSequenceBean {
    @GroupSequence({ Default.class, Group1.class })
    interface All {
    }
    interface Group1 {
    }
    @NotNull
    Object aaa;
    @NotNull(groups = { Group1.class, Default.class })
    Object bbb;
    @NotNull(groups = Group1.class)
    Object ccc;
}
