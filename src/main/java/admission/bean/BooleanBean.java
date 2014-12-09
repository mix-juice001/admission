package admission.bean;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;

public class BooleanBean {

    @AssertTrue
    private boolean mustTrue;

    @AssertFalse
    private Boolean mustFalse;

    public Boolean getMustFalse() {
        return mustFalse;
    }

    public void setMustFalse(Boolean mustFalse) {
        this.mustFalse = mustFalse;
    }

    public boolean isMustTrue() {
        return mustTrue;
    }

    public void setMustTrue(boolean mustTrue) {
        this.mustTrue = mustTrue;
    }

}
