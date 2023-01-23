package br.com.remix.api.course.controller;

import java.net.URI;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.remix.api.course.dto.CourseDto;
import br.com.remix.api.course.service.CourseService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/courses")
public class CourseController {

    final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CourseDto> save(@RequestBody @Valid CourseDto courseDto, UriComponentsBuilder u) {
        CourseDto course = courseService.save(courseDto);
        URI uri = u.path("/courses/{id}").buildAndExpand(course.getId()).toUri();
        return ResponseEntity.created(uri).body(course);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> findById(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.ok(courseService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<CourseDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(courseService.findAll(pageable));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<CourseDto> upadate(@RequestBody @Valid CourseDto courseDto) {
        return ResponseEntity.ok(courseService.update(courseDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Page<CourseDto>> delete(@PathVariable(value = "id") UUID id, Pageable pageable) {
        courseService.delete(id);
        return ResponseEntity.ok(courseService.findAll(pageable));
    }
}
