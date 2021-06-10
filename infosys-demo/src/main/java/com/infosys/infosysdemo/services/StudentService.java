package com.infosys.infosysdemo.services;




import org.springframework.stereotype.Service;

import com.infosys.infosysdemo.entities.Student;
import com.infosys.infosysdemo.repo.StudentRepo;



import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
	
	private StudentRepo repo;
	
	public StudentService(StudentRepo sr) {
		this.repo = sr;
	}
	
	
	public Student addStudent(Student s) {
		this.repo.save(s);
		return s;
	}
	
	public Student getOneStudent(Long id) {
		
		Optional<Student> c = this.repo.findById(id);
		return c.get(); 
	}
	
	public List<Student> getAllStudents() {
		return this.repo.findAll();
	}
	
	public Student editStudent(Long id, Student newStudent) {
		Student existing = this.getOneStudent(id);
		
		existing.setId(id);
		existing.setName(newStudent.getName());
		existing.setGrade(newStudent.getGrade());
		existing.setGpa(newStudent.getGpa());
		
		Student updated = this.repo.save(existing);
		
		return updated;
	}
	
	public Student patchStudent(Long id, Student newStudent) {
		Student toChange = this.repo.getById(id);
		
		String newName = newStudent.getName() != null ? newStudent.getName() : toChange.getName();
		Long newId = newStudent.getId() != null ? newStudent.getId() : toChange.getId();
		int newGrade = newStudent.getGrade() != 0 ? newStudent.getGrade() : toChange.getGrade();
		double newGpa = newStudent.getGpa() != 0.0 ? newStudent.getGpa() : toChange.getGpa();
		
		toChange.setName(newName);
		toChange.setId(newId);
		toChange.setGrade(newGrade);
		toChange.setGpa(newGpa);
		
		return this.repo.save(toChange);
		
		
		
	}
	
	public boolean deleteStudent(Long id) {
		this.repo.deleteById(id);
		return this.repo.existsById(id);
	}
	

}
