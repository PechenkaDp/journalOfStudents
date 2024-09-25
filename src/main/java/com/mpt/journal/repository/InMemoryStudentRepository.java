package com.mpt.journal.repository;

import com.mpt.journal.model.StudentModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryStudentRepository {
    private List<StudentModel> students = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger(1);

    public StudentModel addStudent(StudentModel student) {
        student.setId(idCounter.getAndIncrement());
        students.add(student);
        return student;
    }

    public StudentModel updateStudent(StudentModel student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == student.getId()) {
                students.set(i, student);
                return student;
            }
        }
        return null;
    }

    public void deleteStudent(int id) {
        students.removeIf(student -> student.getId() == id);
    }

    public List<StudentModel> findAllStudents() {
        return students.stream().filter(student -> !student.isDeleted()).collect(Collectors.toList());
    }

    public StudentModel findStudentById(int id) {
        return students.stream().filter(student -> student.getId() == id && !student.isDeleted()).findFirst().orElse(null);
    }

    public List<StudentModel> searchStudents(String searchTerm) {
        return students.stream()
                .filter(student -> (student.getName().contains(searchTerm) || student.getLastName().contains(searchTerm)) && !student.isDeleted())
                .collect(Collectors.toList());
    }

    public List<StudentModel> filterStudents(String name, String lastName, String firstName) {
        return students.stream()
                .filter(student -> (name == null || student.getName().contains(name)) &&
                        (lastName == null || student.getLastName().contains(lastName)) &&
                        (firstName == null || student.getFirstName().contains(firstName)) && !student.isDeleted())
                .collect(Collectors.toList());
    }

    public void deleteMultipleStudents(List<Integer> ids) {
        students.removeIf(student -> ids.contains(student.getId()));
    }

    public void softDeleteStudent(int id) {
        students.stream().filter(student -> student.getId() == id).findFirst().ifPresent(student -> student.setDeleted(true));
    }
}
