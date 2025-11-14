package com.example.crud;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private List<Student> students = new ArrayList<>();
    private int nextId = 1; // to generate IDs automatically

    // Inner class for Student
    static class Student {
        public int id;
        public String name;
        public int marks;

        public Student() {} // default constructor

        public Student(int id, String name, int marks) {
            this.id = id;
            this.name = name;
            this.marks = marks;
        }
    }

    // GET all students
    @GetMapping
    public List<Student> getStudents() {
        return students;
    }

    // POST new student
    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        student.id = nextId++;
        students.add(student);
        return student;
    }

    // PATCH student by ID
    @PatchMapping("/{id}")
    public String updateStudent(@PathVariable int id, @RequestBody Student updated) {
        for (Student s : students) {
            if (s.id == id) {
                if (updated.name != null) s.name = updated.name;
                if (updated.marks != 0) s.marks = updated.marks;
                return "Updated student with ID " + id;
            }
        }
        return "Student not found";
    }

    // DELETE student by ID
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id) {
        for (Student s : students) {
            if (s.id == id) {
                students.remove(s);
                return "Deleted student with ID " + id;
            }
        }
        return "Student not found";
    }
}
