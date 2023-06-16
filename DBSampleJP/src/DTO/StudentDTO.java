package DTO;

import java.util.ArrayList;
import java.util.List;

import Bean.StudentBean;

public class StudentDTO {
	private List<StudentBean> student;
	
	public StudentDTO() {
		super();
		this.student = new ArrayList<StudentBean>();
	}

	public List<StudentBean> getStudent() {
		return student;
	}

	public void setStudent(List<StudentBean> student) {
		this.student = student;
	}
	public int add(StudentBean bean) {
		student.add(bean);
		return 1;
	}
	public int size() {
		return this.student.size();
	}
	public StudentBean get(int i) {
		return this.student.get(i);
	}
}
