package com.infosys.infosysdemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.infosysdemo.entities.Student;

public interface StudentRepo extends JpaRepository<Student,Long> {

}
