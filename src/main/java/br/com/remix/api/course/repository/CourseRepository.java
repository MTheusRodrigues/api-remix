package br.com.remix.api.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import br.com.remix.api.course.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {

}
