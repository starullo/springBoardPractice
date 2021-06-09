package com.infosys.infosysdemo.controllers;

import java.util.ArrayList;

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


@RestController
public class StudentController {
	
	private ArrayList<Student> students = new ArrayList<Student>();
	
	@PostMapping("/create")
	public ResponseEntity addStudent(@RequestBody Student s) {
		this.students.add(s);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("/getOne/{index}")
	public ResponseEntity<Student> getOneStudent(@PathVariable int index) {
		return new ResponseEntity<Student>(students.get(index), HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<ArrayList<Student>> getAllStudents() {
		return new ResponseEntity<ArrayList<Student>>(this.students, HttpStatus.OK);
	}
	
	@PutMapping("updateStudent/{index}")
	public ResponseEntity editStudent(@PathVariable int index, @RequestBody Student s) {
		this.students.set(index, s);
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}
	
	@PatchMapping("patchStudent/{index}")
	public ResponseEntity<Student> patchStudent(@PathVariable int index, @PathParam("name") String newName, @PathParam("id") int newId, @PathParam("grade") int newGrade, @PathParam("gpa") double newGpa) {
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
		
		return new ResponseEntity<Student>(toChange, HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("deleteStudent/{index}")
	public ResponseEntity deleteStudent(@PathVariable int index) {
		Student toReturn = this.students.get(index);
		this.students.remove(index);
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}
	
}
