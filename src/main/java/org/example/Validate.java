package org.example;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Validate {

    // kiểm tra mã code sinh viên đã tô tại hay chưa
    public String checkCodeExit(String code){
        StudentDAO stdao = new StudentDAO(DatabaseConnection.getConnection());
        Scanner sc = new Scanner(System.in);
        while (true){
            Student sv= stdao.getByCode(code);
            if (sv != null){
                System.out.println("Mã bị trùng");
                System.out.println("Nhập mã code sinh vien: ");
                code = sc.nextLine();
            }else {
                return code;
            }
        }
    }

    // validate tên sinh viên bỏ kí tự đặc biệt
    public String validateName(String name ) {
        Scanner sc = new Scanner(System.in);
        Pattern pattern = Pattern.compile("^[a-zA-Z ]{1,25}$");
        while (true) {
            System.out.println("Nhập tên sinh viên:");
            name = sc.nextLine();
            if (pattern.matcher(name).find()) {
                break;
            } else {
                System.err.println("Ten khong co ki tu dac biet");
            }
        }
        return name;
    }

    // validate số điện thoại không được nhập chữ
    public String validatePhone(String phone) {
        Scanner sc = new Scanner(System.in);
        Pattern pattern = Pattern.compile("^[0-9]{8,10}$");
        while (true) {
            System.out.println("Nhập số điện thoại:");
            phone = sc.nextLine();
            if (pattern.matcher(phone).find()) {
                break;
            }else {
                System.err.println("Số điện thoại không bao gồm chữ cái ");
            }
        }
        return phone;
    }

    // validate tuổi ko đc nhập chữ và lớn hơn 0
    public int validateAge(int age) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Nhap tuoi sinh vien");
                age = Integer.parseInt(sc.nextLine());
                if (age > 0) {
                    break;
                } else {
                    System.err.println("Tuoi phai la so duong");
                }
            } catch (Exception e) {
                System.err.println("Tuoi sinh vien phai la so");
            }
        }
        return age;
    }
}
