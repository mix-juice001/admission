package admission.bean;

import javax.validation.constraints.Size;

public class SizeBean {
    @Size(min = 10)
    String defaultMessage = "hoge";
    @Size(min = 10, message = "サイズが{min}と{max}の間ではないよ")
    String directMessage = "hoge";
    @Size(min = 10, message = "{mykey}")
    String fromPropMessage = "hoge";

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public String getDirectMessage() {
        return directMessage;
    }

    public void setDirectMessage(String directMessage) {
        this.directMessage = directMessage;
    }

    public String getFromPropMessage() {
        return fromPropMessage;
    }

    public void setFromPropMessage(String fromPropMessage) {
        this.fromPropMessage = fromPropMessage;
    }
}
