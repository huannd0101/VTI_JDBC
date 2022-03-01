import model.Student;
import model.StudentDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RunMain {
    private static AppController controller = new AppController();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choose = 0;
        do {
            controller.menu();

            System.out.print("Nhập lựa chọn: ");
            choose = sc.nextInt();
            sc.nextLine();

            switch (choose) {
                case 1:
                    viewListStudent();
                    break;
                case 2:
                    insertMark();
                    break;
                case 3:
                    findByName();
                    break;
                case 4:
                    findByMark();
                    break;
                case 5:
                    findByAvg();
                    break;
                case 6:
                    System.out.println("BYE!");
                    break;
                default:
                    System.out.println("Không có lựa chọn này!");
            }
        } while (choose != 6);
    }

    private static void findByAvg() {
        List<Student> students = controller.getStudentsByAVG(8);
        for(Student student : students) {
            System.out.println(student);
        }
    }

    private static void findByMark() {
        List<Student> students = controller.getStudentsByMark(7);
        for(Student student : students) {
            System.out.println(student);
        }
    }

    private static void findByName() {
        System.out.print("Nhập tên sinh viên: ");
        String name = sc.nextLine();

        List<StudentDTO> studentDTOS = controller.getStudents(name);
        printList(studentDTOS);
    }

    private static void insertMark() {
        System.out.print("Nhập student id: ");
        int id = sc.nextInt();

        if(!controller.studentExists(id)) {
            System.out.println("Không có sinh viên này!");
            return;
        }

        List<Double> vals = new ArrayList<>();
        double temp;
        do {
            System.out.print("Nhập điểm >= 0: ");
            temp = sc.nextDouble();
            if(temp >= 0) {
                vals.add(temp);
            }
        } while (temp >= 0);

        controller.insertMarkById(id, vals);
    }

    private static void viewListStudent() {
        List<StudentDTO> studentDTOS = controller.getStudents(null);
        printList(studentDTOS);
    }

    private static void printList(List<StudentDTO> studentDTOS) {
        for(StudentDTO dto : studentDTOS) {
            System.out.println(dto);
        }
    }


}
