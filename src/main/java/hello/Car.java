package hello;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Car {
    @NotNull
    @NotEmpty
    @Size(min = 1)
    private String color;
    @Size(min = 3, max = 20)
    private String model;
    @Min(4)
    @Max(4)
    private Integer numberOfTyres;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getNumberOfTyres() {
        return numberOfTyres;
    }

    public void setNumberOfTyres(Integer numberOfTyres) {
        this.numberOfTyres = numberOfTyres;
    }
}
