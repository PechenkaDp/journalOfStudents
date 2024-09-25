package com.mpt.journal.service.impl;

import com.mpt.journal.model.StudentModel;
import com.mpt.journal.repository.InMemoryStudentRepository;
import com.mpt.journal.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InMemoryStudentServiceImpl implements StudentService {
    private final InMemoryStudentRepository studentRepository;

    public InMemoryStudentServiceImpl(InMemoryStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentModel addStudent(StudentModel student) {
        return studentRepository.addStudent(student);
    }

    @Override
    public StudentModel updateStudent(StudentModel student) {
        return studentRepository.updateStudent(student);
    }

    @Override
    public void deleteStudent(int id) {
        studentRepository.deleteStudent(id);
    }

    @Override
    public List<StudentModel> findAllStudents() {
        return studentRepository.findAllStudents();
    }

    @Override
    public StudentModel findStudentById(int id) {
        return studentRepository.findStudentById(id);
    }

    @Override
    public List<StudentModel> searchStudents(String searchTerm) {
        return studentRepository.searchStudents(searchTerm);
    }

    @Override
    public List<StudentModel> filterStudents(String name, String lastName, String firstName) {
        return studentRepository.filterStudents(name, lastName, firstName);
    }

    @Override
    public void deleteMultipleStudents(List<Integer> ids) {
        studentRepository.deleteMultipleStudents(ids);
    }

    @Override
    public void softDeleteStudent(int id) {
        studentRepository.softDeleteStudent(id);
    }
}
