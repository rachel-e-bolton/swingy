package swingy;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

public class Test {
    @Size(min=3,max=10)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
