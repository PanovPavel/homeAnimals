package org.works;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class TypePet {
    private int id;
    private String type;
}
