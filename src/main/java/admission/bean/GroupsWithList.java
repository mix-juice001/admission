package admission.bean;

import javax.validation.constraints.Max;

public class GroupsWithList {
    interface NormalUser {
    }

    interface PremiumUser{
    }

    @Max.List({
            @Max(value = 10, groups = NormalUser.class),
            @Max(value = 100, groups = PremiumUser.class)
    })
    int diskSize;

}
