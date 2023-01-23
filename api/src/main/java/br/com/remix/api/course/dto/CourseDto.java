package br.com.remix.api.course.dto;

import java.util.UUID;

import br.com.remix.api.course.model.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {

    private UUID id;
    private String name;
    private String description;
    private String imageUrl;

    public CourseDto(Course course) {
        id = course.getId();
        name = course.getName();
        description = course.getDescription();
        imageUrl = course.getImageUrl();
    }
}
