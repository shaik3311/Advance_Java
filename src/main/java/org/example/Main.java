package org.example;

import org.example.model.Student;
import org.example.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentRepository studentRepository = new StudentRepository();

        boolean turn = true;

        while (turn) {

            System.out.println("\n======= STUDENT MANAGER =======");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Get a student Detils");
            System.out.println("5. Get all students Detils");
            System.out.println("6. Exit");

            System.out.print("Select an operation : ");
            int n = sc.nextInt();
            sc.nextLine();

            switch (n) {

                case 1 -> {
                    System.out.println("\n====== Welcome To Student Creation Page ======");

                    System.out.print("Enter name of the student   : ");
                    String name = sc.nextLine();

                    System.out.print("Enter age of the student    : ");
                    int age = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter course of the student : ");
                    String course = sc.nextLine();

                    studentRepository.createStudent(
                            new Student(name, age, course)
                    );
                }

                case 2 -> {
                    System.out.println("\n====== Welcome To Student Course Updation Page ======");

                    System.out.print("Enter id of the student : ");
                    long id = sc.nextLong();
                    sc.nextLine();

                    System.out.print("Enter new course : ");
                    String newCourse = sc.nextLine();

                    studentRepository.updateCourse(id, newCourse);
                }

                case 3 -> {
                    System.out.println("\n====== Welcome To Student Deletion Page ======");

                    System.out.print("Enter student id : ");
                    long id = sc.nextLong();
                    sc.nextLine();

                    studentRepository.deleteStudent(id);
                }
                case 4 ->{
                    System.out.println("==== Get A student details ====");
                    System.out.print("Enter student id : ");
                    long id = sc.nextLong();
                    sc.nextLine();

                    System.out.println(studentRepository.getStudentById(id));
                }

                case 5 ->{
                    System.out.println("===== Get All Students Details =====");
                    ArrayList<Student> res = studentRepository.getAllStuddents();

                    System.out.println(res.get(0)+"\n"+res.get(1));
                }

                case 6 -> {
                    System.out.println("====== Thank you ======");
                    turn = false;
                }

                default -> System.out.println("Invalid operation!");
            }
        }

        sc.close();
    }
}