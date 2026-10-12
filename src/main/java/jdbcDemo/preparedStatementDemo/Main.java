package jdbcDemo.preparedStatementDemo;

import ch.qos.logback.core.spi.ScanException;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean turn = true;
        while(turn){
            System.out.println("1.Insert Students\n2.update Student\n3.Delete student\n4.Get Students\n5.Get Student By Id\n6.Exit");
            System.out.print("Select an option : ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 1->{
                    insertMany();
                }case 2->{
                    update();;
                }case 3->{
                    delete();
                }case 4->{
                    getStudent();
                }case 5->{
                    getStudentById();
                }
                case 6->{
                    turn = false;
                }
            }
        }
    }
    public static void insertMany(){
        Scanner sc = new Scanner(System.in);
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded and registered");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc?user=root&&password=SQL@9100");
            System.out.println("Connection established successfully");
            System.out.print("Enter how many record you want to insert : ");
            int n = sc.nextInt();
            sc.nextLine();
            String sql = """
                    INSERT INTO students(std_id,std_name,std_rollNo,std_subject) VALUES 
                    (?, ?, ?, ?)
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for(int i=1;i<=n;i++){
                System.out.print("Enter id for student "+i+" : ");
                int id = sc.nextInt();
                sc.nextLine();
                preparedStatement.setInt(1,id);
                System.out.print("Enter name for student "+i+" : ");
                String name = sc.nextLine();
                preparedStatement.setString(2,name);
                System.out.print("Enter rollNo for student "+i+" : ");
                String rollNo = sc.nextLine();
                preparedStatement.setString(3,rollNo);
                System.out.print("Enter subject for student "+i+" : ");
                String subject = sc.nextLine();
                preparedStatement.setString(4,subject);

                int res = preparedStatement.executeUpdate();
                System.out.println(res+" rows effected");
            }

        }catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException error){
            error.printStackTrace();
        }
    }
    public static void update(){
        Scanner sc = new Scanner(System.in);
        String sql = """
                UPDATE students SET std_name = ? WHERE std_id=?
                """;
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc?user=root&&password=SQL@9100");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            System.out.print("Enter student id : ");
            int id = sc.nextInt();
            sc.nextLine();
            preparedStatement.setInt(2,id);
            System.out.print("Enter new name : ");
            String name = sc.nextLine();
            preparedStatement.setString(1,name);

            int res = preparedStatement.executeUpdate();
            System.out.println(res+" rows effected");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void delete(){
        Scanner sc = new Scanner(System.in);
        String sql = """
                DELETE FROM students WHERE std_id=?
                """;
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc?user=root&&password=SQL@9100");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            System.out.print("Enter student id : ");
            int id = sc.nextInt();
            preparedStatement.setInt(1,id);
            int res = preparedStatement.executeUpdate();
            System.out.println(res+" rows effected");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void getStudent(){
        String sql = """
                SELECT * FROM students 
                """;
        try(
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc?user=root&&password=SQL@9100");
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                System.out.println("{ id : "+resultSet.getInt("std_id")+" name : "+resultSet.getString("std_name")+" RollNo : "+resultSet.getString("std_rollNo")+" Subject : "+resultSet.getString("std_subject"));
            }

        }catch (SQLException e){
            System.out.println("Failed at get student by id : "+e.getMessage());
        }
    }

    public static void getStudentById(){
        String sql = """
                SELECT * FROM students WHERE std_id=?
                """;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter id of the student : ");
        int id = sc.nextInt();
        try(
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc?user=root&&password=SQL@9100");
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                System.out.println("{ id : " + resultSet.getInt("std_id") + " name : " + resultSet.getString("std_name") + " RollNo : " + resultSet.getString("std_rollNo") + " Subject : " + resultSet.getString("std_subject"));
            }else{
                System.out.println("No Such Student Found !");
            }
        }catch (SQLException e){
            System.out.println("Failed at get student by id : "+e.getMessage());
        }
    }
}
