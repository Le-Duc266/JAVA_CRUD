package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private Connection connection;

    public StudentDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Student> getALl() {
        List<Student> result;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from students where deleted_at IS NULL");
            result= showInfor(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<Student> sortByAge() {
        List<Student> result;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from students ORDER BY age DESC ");
            result= showInfor(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public Student getById(int id){
        Student sv = null;
        try  {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from students where id = ? ");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                sv = new Student(resultSet.getInt("id"), resultSet.getString("code"), resultSet.getString("name"), resultSet.getString("phone"), resultSet.getString("address"), resultSet.getDate("created_at"), resultSet.getDate("updated_at"), resultSet.getInt("age"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sv;
    }

    public Student getByCode(String code){
        Student sv = null;
        try  {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from students where code = ? ");
            preparedStatement.setString(1,code);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                sv = new Student(resultSet.getInt("id"), resultSet.getString("code"), resultSet.getString("name"), resultSet.getString("phone"), resultSet.getString("address"), resultSet.getDate("created_at"), resultSet.getDate("updated_at"), resultSet.getInt("age"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sv;
    }

    public void insert(Student svien) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "insert into students(code,name,phone,address,created_at,updated_at,age) " +
                    "values(?,?,?,?,?,?,?)");
            preparedStatement.setString(1, svien.getCode());
            preparedStatement.setString(2, svien.getName());
            preparedStatement.setString(3, svien.getPhone());
            preparedStatement.setString(4, svien.getAddress());
            preparedStatement.setDate(5, new Date(svien.getCreated_at().getTime()));
            preparedStatement.setDate(6, new Date(svien.getUpdated_at().getTime()));
            preparedStatement.setInt(7, svien.getAge());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Student svien) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update students set code=?,name =?,phone=?,address=?,created_at=?,updated_at=?, age=? where id=?");
            preparedStatement.setString(1, svien.getCode());
            preparedStatement.setString(2, svien.getName());
            preparedStatement.setString(3, svien.getPhone());
            preparedStatement.setString(4, svien.getAddress());
            preparedStatement.setDate(5, new Date(svien.getCreated_at().getTime()));
            preparedStatement.setDate(6, new Date(svien.getUpdated_at().getTime()));
            preparedStatement.setInt(7, svien.getAge());
            preparedStatement.setInt(8, svien.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void softDelete(Student svien) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update students set code=?,name =?,phone=?,address=?,created_at=?,updated_at=?, age=?,deleted_at=? where id=?");
            preparedStatement.setString(1, svien.getCode());
            preparedStatement.setString(2, svien.getName());
            preparedStatement.setString(3, svien.getPhone());
            preparedStatement.setString(4, svien.getAddress());
            preparedStatement.setDate(5, new Date(svien.getCreated_at().getTime()));
            preparedStatement.setDate(6, new Date(svien.getUpdated_at().getTime()));
            preparedStatement.setInt(7, svien.getAge());
            preparedStatement.setDate(8, new Date(svien.getDeleted_at().getTime()));
            preparedStatement.setInt(9, svien.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from students where id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Student> getByName(String name) {
        List<Student> result;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from students where  name like ? or address like ?");
            preparedStatement.setString(1, "%" + name + "%");
            preparedStatement.setString(2, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            result= showInfor(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  result;
    }


    public List<Student> showInfor(ResultSet resultSet){
        List<Student> result = new ArrayList<Student>();
        try {
            while (resultSet.next()) {
                Student svien = new Student(resultSet.getInt("id"), resultSet.getString("code"), resultSet.getString("name"), resultSet.getString("phone"), resultSet.getString("address"), resultSet.getDate("created_at"), resultSet.getDate("updated_at"), resultSet.getInt("age"));
                result.add(svien);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


}
