package com.infosys.infosysdemo.services;


import javax.websocket.server.PathParam;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.infosys.infosysdemo.entities.Student;
import java.util.List;

@Service
public class StudentService {
	
	private List<Student> students;
	
	public StudentService(List<Student> s) {
		this.students = s;
	}
	
	public Student addStudent(@RequestBody Student s) {
		this.students.add(s);
		return s;
	}
	
	public Student getOneStudent(@PathVariable int index) {
		return students.get(index);
	}
	
	public List<Student> getAllStudents() {
		return this.students;
	}
	
	public Student editStudent(@PathVariable int index, @RequestBody Student s) {
		this.students.set(index, s);
		return s;
	}
	
	public Student patchStudent(@PathVariable int index, @PathParam("name") String newName, @PathParam("id") int newId, @PathParam("grade") int newGrade, @PathParam("gpa") double newGpa) {
		Student toChange = this.students.get(index);
		
		newName = newName != null ? newName : toChange.getName();
		newId = newId != 0 ? newId : toChange.getId();
		newGrade = newGrade != 0 ? newGrade : toChange.getGrade();
		newGpa = newGpa != 0.0 ? newGpa : toChange.getGpa();
		
		toChange.setName(newName);
		toChange.setId(newId);
		toChange.setGrade(newGrade);
		toChange.setGpa(newGpa);
		
		this.students.set(index, toChange);
		
		return toChange;
		
	}
	
	public boolean deleteStudent(@PathVariable int index) {
		this.students.remove(index);
		return true;
	}
	

}
