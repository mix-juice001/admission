package admission.service;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserForm {
    public interface GroupA{};
    public interface GroupB{};
    @NotNull
    @Size(min = 1, max = 20, groups = GroupB.class)
    private String name;
    @NotNull
    @Min(20)
    @Max(value=100, groups = GroupA.class)
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}