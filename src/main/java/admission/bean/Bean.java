package admission.bean;

import javax.validation.constraints.AssertTrue;

public class Bean {
    @AssertTrue
    private boolean aaa;
    public boolean isAaa() {
        return aaa;
    }
    public void setAaa(boolean aaa) {
        this.aaa = aaa;
    }
}