package br.com.remix.api.course.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.remix.api.course.dto.CourseDto;
import br.com.remix.api.course.model.Course;
import br.com.remix.api.course.repository.CourseRepository;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CourseDto save(CourseDto courseDto) {
        Course course = new Course(courseDto);
        return new CourseDto(courseRepository.save(course));
    }

    public CourseDto findById(UUID id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Course not found."));
        return new CourseDto(course);
    }

    public Page<CourseDto> findAll(Pageable pageable) {
        List<Course> coursers = courseRepository.findAll().stream().collect(Collectors.toList());
        List<CourseDto> courseDtos = coursers.stream().map(c -> new CourseDto(c)).collect(Collectors.toList());
        Page<CourseDto> page = new PageImpl<CourseDto>(courseDtos, pageable, courseDtos.size());
        return page;
    }

    public CourseDto update(CourseDto courseDto) {
        Course course = courseRepository.findById(courseDto.getId()).map(c -> {
            if (courseDto.getName() != null) {
                c.setName(courseDto.getName());
            }
            if (courseDto.getDescription() != null) {
                c.setDescription(courseDto.getDescription());
            }
            if (courseDto.getImageUrl() != null) {
                c.setImageUrl(courseDto.getImageUrl());
            }
            return courseRepository.save(c);
        }).orElseGet(() -> {
            return courseRepository.save(new Course(courseDto));
        });
        return new CourseDto(course);
    }

    public void delete(UUID id) {
        courseRepository.deleteById(id);
    }

}
