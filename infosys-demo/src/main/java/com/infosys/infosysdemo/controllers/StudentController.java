package com.infosys.infosysdemo.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.infosysdemo.entities.Student;
import com.infosys.infosysdemo.services.StudentService;


@RestController
public class StudentController {
	
	private StudentService service;
	
	public StudentController(StudentService s) {
		this.service = s;
	}
	
	@PostMapping("/add")
	public ResponseEntity addStudent(@RequestBody Student s) {
		return new ResponseEntity<>(this.service.addStudent(s), HttpStatus.CREATED);
	}
	
	@GetMapping("/getOne/{index}")
	public ResponseEntity<Student> getOneStudent(@PathVariable int index) {
		return new ResponseEntity<Student>(this.service.getOneStudent(index), HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Student>> getAllStudents() {
		return new ResponseEntity<List<Student>>(this.service.getAllStudents(), HttpStatus.OK);
	}
	
	@PutMapping("updateStudent/{index}")
	public ResponseEntity editStudent(@PathVariable int index, @RequestBody Student s) {
		return new ResponseEntity(this.service.editStudent(index, s), HttpStatus.ACCEPTED);
	}
	
	@PatchMapping("patchStudent/{index}")
	public ResponseEntity<Student> patchStudent(@PathVariable int index, @PathParam("name") String newName, @PathParam("id") int newId, @PathParam("grade") int newGrade, @PathParam("gpa") double newGpa) {
		Student toChange = this.service.getOneStudent(index);
		
		newName = newName != null ? newName : toChange.getName();
		newId = newId != 0 ? newId : toChange.getId();
		newGrade = newGrade != 0 ? newGrade : toChange.getGrade();
		newGpa = newGpa != 0.0 ? newGpa : toChange.getGpa();
		
		toChange.setName(newName);
		toChange.setId(newId);
		toChange.setGrade(newGrade);
		toChange.setGpa(newGpa);
		
		
		return new ResponseEntity<Student>(this.service.editStudent(index, toChange), HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("deleteStudent/{index}")
	public ResponseEntity deleteStudent(@PathVariable int index) {
		Student toReturn = this.service.getOneStudent(index);
		return new ResponseEntity(this.service.deleteStudent(index), HttpStatus.ACCEPTED);
	}
	
}
