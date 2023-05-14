package com.project.rest.service;

import com.project.rest.model.Student;
import com.project.rest.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{


    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Override
    public Optional<Student> getStudent(Integer studentId) {
        return studentRepository.findById(studentId);
    }

    @Override
    public Student setStudent(Student student) {
        studentRepository.save(student);
        return student;
    }

    @Override
    @Transactional
    public void deleteStudent(Integer studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public Page<Student> getStudent(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }
    @Override
    public Optional<Student> searchByEmail (String email){
        return studentRepository.findByEmail(email);
    }
    @Override
    public Page<Student> searchByImie(String imie, Pageable pageable) {
        return studentRepository.findByNazwiskoStartsWithIgnoreCase(imie, pageable);
    }
}
