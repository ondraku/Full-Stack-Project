package com.example.fullstackproject.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @AfterEach
    void tearDown() {
        studentRepository.deleteAll();
    }

    @Test
    void itShouldCheckWhenStudentEmailExists() {
        //given
        String email = "john@connor.com";
        Student student = new Student(
                "John",
                email,
                Gender.MALE
        );
        studentRepository.save(student);

        //when
        boolean expected = studentRepository.selectExistsEmail(email);

        //then
        assertThat(expected).isTrue();
    }

    @Test
    void itShouldCheckWhenStudentEmailDoesNotExist() {
        //given
        String email = "john@connor.com";

        //when
        boolean expected = studentRepository.selectExistsEmail(email);

        //then
        assertThat(expected).isFalse();
    }
}
