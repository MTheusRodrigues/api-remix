package br.com.remix.api.course.model;

import java.util.UUID;

import br.com.remix.api.course.dto.CourseDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "courses")
@Entity(name = "Course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    private String imageUrl;

    public Course(CourseDto courseDto) {
        id = courseDto.getId();
        name = courseDto.getName();
        description = courseDto.getDescription();
        imageUrl = courseDto.getImageUrl();
    }
}
