package org.example.repository;

import org.example.model.Student;

import java.sql.*;
import java.util.ArrayList;

public class StudentRepository {
    String url = "jdbc:mysql://localhost:3306/student_db";
    String username = "root";
    String password = "SQL@9100";

    public void createStudent(Student student){
            String sql = """
                    INSERT INTO students(name,age,course)
                    VALUES(?,?,?)
                    """;
        try(
            Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){
            preparedStatement.setString(1,student.getName());
            preparedStatement.setInt(2,student.getAge());
            preparedStatement.setString(3,student.getCourse());
            int res = preparedStatement.executeUpdate();
            if(res!=0){
                System.out.println("User created successfully");
            }else{
                System.out.println("User not created");
            }
        }catch(SQLException e){
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }

    public void updateCourse(long id,String course){
        String sql = """
                UPDATE students SET course=?
                WHERE id=?
                """;

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setString(1,course);
            preparedStatement.setLong(2,id);

            int res = preparedStatement.executeUpdate();
            if(res!=0){
                System.out.println("Updated "+res+" Records");
            }else{
                System.out.println("Updation failed");
            }
        }catch (SQLException e){
            System.out.println("Error at updating a student");
            e.printStackTrace();
        }
    }

    public void deleteStudent(long id){
        String sql = """
                DELETE FROM students
                WHERE id=?
                """;

        try(
                Connection connection = DriverManager.getConnection(url,username,password);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){
            preparedStatement.setLong(1,id);

            int res = preparedStatement.executeUpdate();
            if(res!=0){
                System.out.println("Deleted "+res+" rows");
            }else{
                System.out.println("Deletion failed");
            }

        }catch (SQLException e){
            System.out.println("Error at deleting a student");
            e.printStackTrace();
        }
    }

    public Student getStudentById(long id){
        String sql = """
                SELECT id,name,age,course FROM students
                WHERE id=?
                """;
        Student std = new Student();
        try(
                Connection connection = DriverManager.getConnection(url,username,password);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){
            preparedStatement.setLong(1,id);

            ResultSet res = preparedStatement.executeQuery();
//            res.next();
            std.setId(res.getLong(1));
            std.setName(res.getString(2));
            std.setAge(res.getInt(3));
            std.setCourse(res.getString(4));
        }catch (SQLException e){
            System.out.println("Failed at fetch a student : "+e.getMessage());
        }
        return std;
    }

    public ArrayList<Student> getAllStuddents(){
        ArrayList<Student> stds = new ArrayList<>();

        String sql = """
                SELECT id,name,age,course FROM students
                """;
        try(
                Connection connection = DriverManager.getConnection(url,username,password);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){

            ResultSet res = preparedStatement.executeQuery();
//            res.next();
            while(res.next()){
                Student std = new Student();
                std.setId(res.getLong(1));
                std.setName(res.getString(2));
                std.setAge(res.getInt(3));
                std.setCourse(res.getString(4));

                stds.add(std);
            }

        }catch (SQLException e){
            System.out.println("Fetching students failed "+e.getMessage());
        }

        return stds;
    }
}
