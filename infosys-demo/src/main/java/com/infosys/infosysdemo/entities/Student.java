package com.infosys.infosysdemo.entities;


public class Student {

	private String name;
	private int id;
	private int grade;
	private double gpa;
	
	public Student(String name, int id, int grade, double gpa) {
		this.setName(name);
		this.setId(id);
		this.setGrade(grade);
		this.setGpa(gpa);
	}
	
	public Student() {
		this.setName("???");
		this.setId(-1);
		this.setGrade(-1);
		this.setGpa(-1);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public double getGpa() {
		return gpa;
	}
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	
	
}
