package org.works;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Person {
    private int id;
    private String name;
    private String surname;
    private String lastname;

}
