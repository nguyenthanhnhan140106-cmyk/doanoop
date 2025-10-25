package doanoop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class DanhSachNhanVien{
    private ArrayList<nhanVien> a= new ArrayList<nhanVien>();

    public void docFile(String tenFile) {
        try {
            BufferedReader input = new BufferedReader(new FileReader(tenFile));
            String line;

            while ((line = input.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] arr = line.split("\\|\\|");

                String id = arr[0].split(":")[1].trim();
                String ten = arr[1].split(":")[1].trim();
                String email = arr[2].split(":")[1].trim();
                String sdt=arr[3].split(":")[1].trim();
                int soGioLam =(int) Double.parseDouble(arr[4].split(":")[1].trim());
                nhanVien nv;
                if (id.toUpperCase().startsWith("PT"))
                    nv = new nvParttime(id, ten, email , sdt ,soGioLam);
                else
                    nv = new nvFulltime(id, ten, email , sdt ,soGioLam);
                a.add(nv);
                
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void ghiFile(String tenFile) {
        try {
            BufferedWriter fw = new BufferedWriter(new FileWriter(tenFile));
            for (nhanVien x : a) {
                fw.write(x.toString());
                fw.write("\n");
            }
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void themDSNhanVien(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Nếu là nhân viên parttime thì mã nhân viên bắt đầu là PT còn nhân viên fulltime bắt đầu là FT! ");
        System.out.println("Nhập mã nhân viên: ");
        String id=sc.nextLine();

        System.out.println("Nhập tên nhân viên: ");
        String ten=sc.nextLine();

        System.out.println("Nhập email: ");
        String email=sc.nextLine();

        System.out.println("Nhập số điện thoại liên hệ: ");
        String sdt=sc.nextLine();

        System.out.println("Nhập số giờ làm việc: ");
        int soGioLam=sc.nextInt();

        nhanVien nv;
        if (id.toUpperCase().startsWith("PT"))
            nv = new nvParttime(id, ten, email, sdt, soGioLam);
        else
            nv = new nvFulltime(id, ten, email, sdt, soGioLam);

        a.add(nv);
        System.out.println("Đã thêm nhân viên mới!");
    }
    public void suaDSNhanVien() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập mã nhân viên cần sửa: ");
        String id = sc.nextLine();

        nhanVien nv = null;
        for (nhanVien X : a) {
            if (X.getid().equals(id)) {
                nv = X;
                break;
            }
        }
        if (nv == null)
            System.out.println("Mã nhân viên không tồn tại.");
        else
            System.out.println(nv);
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).getid().equals(id)) {
                System.out.println("Nhập tên nhân viên: ");
                String ten=sc.nextLine();

                System.out.println("Nhập email: ");
                String email=sc.nextLine();

                System.out.println("Nhập số điện thoại liên hệ: ");
                String sdt=sc.nextLine();

                System.out.println("Nhập số giờ làm việc: ");
                int soGioLam=sc.nextInt();

                a.get(i).setten(ten);
                a.get(i).setemail(email);
                a.get(i).setsdt(sdt);
                a.get(i).setsoGioLam(soGioLam);

                System.out.println(" Đã sửa thông tin sản phẩm! ");
                break;
            }
        }
    }
    public nhanVien timNhanVien(String ma){
        for (nhanVien nv :a){
            if (nv.getid().equalsIgnoreCase(ma)){
                return nv;
            }
        }
        return null;
    }
    public void timKiemNhanVien() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập mã sản phẩm cần tiềm: ");
        String id = sc.nextLine();

        nhanVien nv = null;
        for (nhanVien X : a) {
            if (X.getid().equalsIgnoreCase(id)) {
                nv = X;
                break;
            }
        }
        if (nv == null)
            System.out.println("Mã nhân viên không tồn tại.");
        else
            System.out.println(nv);
    }
    public void xoaNhanVien() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã nhân viên cần xóa: ");
        String id = sc.nextLine();

        boolean found = false;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).getid().equals(id)) {
                System.out.println("Thông tin nhân viên cần xóa:");
                System.out.println(a.get(i));

                System.out.print("Bạn có chắc muốn xóa nhân viên này ra khỏi danh sách không? (y/n): ");
                String confirm = sc.nextLine();

                if (confirm.equalsIgnoreCase("y")) {
                    a.remove(i);
                    System.out.println(" Đã xóa nhân viên khỏi danh sách!");
                } else
                    System.out.println(" Hủy xóa nhân viên.");

                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println(" Không tìm thấy nhân viên có mã: " + id);
        }
    }

    public void xuatDanhSachNhanVienParttime() {
        System.out.println(" danh sách nhân viên parttime: ");
        for (nhanVien X : a) {
            if (X instanceof nvParttime ) {
                System.out.println(X);
            }
        }
    }

    public void xuatDanhSachNhanVienFullTime() {
        System.out.println(" danh sách nhân viên fulltime: ");
        for (nhanVien X : a) {
            if (X instanceof nvFulltime)
                System.out.println(X);
        }
    }
    public void xuatTatCa() {
        for( nhanVien X:a){
            System.out.println(X);
        }
    }
}
