package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        StudentDAO svdao = new StudentDAO(DatabaseConnection.getConnection());
        do {
            System.out.println("--------Menu---------");
            System.out.println("1. Danh sách sinh vien.");
            System.out.println("2. Thêm mới sinh vien. ");
            System.out.println("3. Sửa thông tin sinh viên.");
            System.out.println("4. Xoa sinh viên");
            System.out.println("5. Tìm kiếm sinh viên");
            System.out.println("6. Sắp xếp giảm dần theo tuổi");
            System.out.println("7. Thoat");
            System.out.println("+++++++++++++++++++++");
            System.out.println("Moi ban chon: ");
            Scanner sc = new Scanner(System.in);
            int chon = Integer.parseInt(sc.nextLine());

            switch (chon) {
                case 1:
                    System.out.println("Danh sách sinh viên");
                    students = svdao.getALl();
                    students.forEach(student -> student.display());
                    break;

                case 2:
                    Student sv = new Student();
                    sv.input();
                    svdao.insert(sv);
                    break;

                case 3:
                    System.out.println("nhap id sinh vien muon sua: ");
                    int id = Integer.parseInt(sc.nextLine());
                    Student sinhVien = svdao.getById(id);
                    if (sinhVien == null) {
                        System.out.println("Không sinh vien theo id vua nhap");
                    }
                    if (sinhVien.getId() > 0) {
                        sinhVien.input();
                        svdao.update(sinhVien);
                    }
                    break;

                case 4:
                    System.out.println("Nhap ma sinh vien muon xoa: ");
                    id = Integer.parseInt(sc.nextLine());
                    sinhVien = svdao.getById(id);
                    if (sinhVien == null) {
                        System.out.println("Không sinh vien theo id vua nhap");
                    }
                    if (sinhVien.getId() > 0) {
                        sinhVien.inputDelete();
                        svdao.softDelete(sinhVien);
                        System.out.println("Xoa thanh cong");
                    }
                    break;

                case 5:
                    System.out.println("Nhap ten hoặc địa chỉ sinh vien muon tim kiem: ");
                    String search = sc.nextLine();
                    List<Student> rs = svdao.getByName(search);
                    if (rs.size() > 0) {
                        System.out.println("Sinh vien tim thay: ");
                        for (Student st : rs) {
                            st.display();
                        }
                    } else {
                        System.out.println("khong tim thay sinh vien ");
                    }
                    break;

                case 6:
                    System.out.println("Danh sach sau khi sap xep giam dan theo tuoi: ");
                    students = svdao.sortByAge();
                    students.forEach(student -> student.display());
                    break;


                case 7:
                    System.out.println("Ban da thoat chuong trinh");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Moi ban chon tu 1 den 7");
                    break;
            }
        } while (true);
    }
}