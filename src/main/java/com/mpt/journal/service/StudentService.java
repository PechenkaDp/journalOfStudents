package com.mpt.journal.service;

import com.mpt.journal.model.StudentModel;
import java.util.List;

public interface StudentService {
    StudentModel addStudent(StudentModel student);
    StudentModel updateStudent(StudentModel student);
    void deleteStudent(int id);
    List<StudentModel> findAllStudents();
    StudentModel findStudentById(int id);
    List<StudentModel> searchStudents(String searchTerm);
    List<StudentModel> filterStudents(String name, String lastName, String firstName);
    void deleteMultipleStudents(List<Integer> ids);
    void softDeleteStudent(int id);
}
