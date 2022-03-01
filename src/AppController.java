import SQLUtil.SQLConnection;
import model.Student;
import model.StudentDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppController {
    public void menu() {
        System.out.println("=======================================================");
        System.out.println("1. Xem danh sách sinh viên");
        System.out.println("2. Thêm điểm cho sinh viên");
        System.out.println("-. Tìm kiếm");
        System.out.println("3. Tìm kiếm sinh viên theo tên");
        System.out.println("4. Tìm kiếm sinh viên có điểm = 7");
        System.out.println("5. Tìm kiếm sinh viên có điểm trung bình > 8");
        System.out.println("6. Thoát");
        System.out.println("=======================================================");
    }

    private Connection conn;

    public AppController() {
         this.conn = SQLConnection.getConnection();
    }

    public boolean studentExists(Integer id) {
        String sql = "select count(*) from Student where id = " + id;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()) {
                return rs.getInt(1) == 1;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public List<Double> getAllMarkById(Integer id) {
        List<Double> res = new ArrayList<>();
        String sql = "select val from mark where student_id = " + id;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                res.add(Double.parseDouble(rs.getString(1)));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }

    public List<StudentDTO> getStudents(String name) {
        String sql = "select * from student";
        if(name != null) {
            sql = "SELECT * FROM Student where `name` LIKE '%" + name + "%'";
        }
        List<StudentDTO> res = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                res.add(
                        new StudentDTO(
                                rs.getInt(1),
                                rs.getString(2),
                                getAllMarkById(rs.getInt(1)
                                )
                        )
                );
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }

    public List<Student> getStudentsByMark(double mark) {
        String sql = "SELECT * FROM Student INNER JOIN Mark ON `Student`.`id` = `Mark`.`student_id` where val = " + mark;
        List<Student> students = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                students.add(new Student(rs.getInt(1), rs.getString(2)));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return students;
    }

    public List<Student> getStudentsByAVG(double avg) {
        String sql = "SELECT `Student`.`id`, name, AVG(val) as Diemtb FROM Student \n" +
                "INNER JOIN Mark ON `Student`.`id` = `Mark`.`student_id` \n" +
                "GROUP BY name\n" +
                "HAVING AVG(val) > " + avg;
        List<Student> students = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                students.add(new Student(rs.getInt(1), rs.getString(2)));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return students;
    }

    public int getMaxIdOffMarkTable() {
        String sql = "SELECT max(id) from Mark";
        int res = 0;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                res = rs.getInt(1);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public void insertMarkById(Integer id, List<Double> vals) {
        String sql = "INSERT INTO Mark VALUES (?, ?, ?)";
        int idMark = getMaxIdOffMarkTable();

        try {
            for(Double d : vals) {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, ++idMark);
                stmt.setString(2, String.valueOf(d));
                stmt.setInt(3, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
