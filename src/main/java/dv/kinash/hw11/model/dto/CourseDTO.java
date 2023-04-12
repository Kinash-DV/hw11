package dv.kinash.hw11.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourseDTO {
    private Long id;
    private String name;

    public CourseDTO(String name) {
        this.name = name;
    }
}
