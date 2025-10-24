package doanoop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class DanhSachHangHoa{
    private ArrayList<hangHoa> a= new ArrayList<hangHoa>();

    public void docFile(String tenFile) {
        try {

            BufferedReader input = new BufferedReader(new FileReader(tenFile));
            String line;
            while ((line = input.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] arr = line.split("\\|\\|");

                String ma = arr[0].split(":")[1].trim();
                String tensp = arr[1].split(":")[1].trim();
                String ngay = arr[2].split(":")[1].trim();
                double dg = Double.parseDouble(arr[3].split(":")[1].trim());
                double sl = Double.parseDouble(arr[4].split(":")[1].trim());
                String nsx = arr[5].split(":")[1].trim();
                hangHoa hh;
                if (ma.toUpperCase().startsWith("DT"))
                    hh = new dienThoai(ma, tensp, ngay, dg, sl, nsx);
                else if (ma.toUpperCase().startsWith("LT"))
                    hh = new lapTop(ma, tensp, ngay, dg, sl, nsx);
                else
                    hh = new Loa(ma, tensp, ngay, dg, sl, nsx);
                a.add(hh);
                
            }
            input.close();
        } catch (Exception e) {
            System.out.println("File không tồn tại");   
            e.printStackTrace();
        }
    }


    public void ghiFile(String tenFile) {
        try {
            BufferedWriter fw = new BufferedWriter(new FileWriter(tenFile));
            for (hangHoa x : a) {
                fw.write(x.toString());
                fw.write("\n");
            }
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void themDSHangHoa(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Nếu là Điện Thoại thì mã sản phẩm bắt đầu là DT, Laptop là LT, Loa là LO! ");
        System.out.println("Nhập mã sản phẩm: ");
        String maHang=sc.nextLine();

        System.out.println("Nhập tên sản phẩm: ");
        String tenSp=sc.nextLine();

        System.out.println("Nhập ngày nhập sản phẩm: ");
        String ngayNhap=sc.nextLine();

        System.out.println("Nhập giá tiền: ");
        double donGia=sc.nextInt();

        System.out.println("Nhập số lượng sản phẩm: ");
        int soluong=sc.nextInt();

        sc.nextLine();
        System.out.println("Nhập nơi sản xuất: ");
        String nsx=sc.nextLine();

        hangHoa hh;
        if (maHang.toUpperCase().startsWith("DT"))
            hh = new dienThoai(maHang,tenSp,ngayNhap, donGia, soluong,nsx);
        else if (maHang.toUpperCase().startsWith("LT"))
            hh = new lapTop(maHang,tenSp,ngayNhap, donGia, soluong,nsx);
        else
            hh=new Loa(maHang,tenSp,ngayNhap, donGia, soluong,nsx);

        a.add( hh );
        System.out.println("Đã thêm sản phẩm mới!");
    }
    public void suaDSsanpham() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập mã sản phẩm cần sửa: ");
        String maHang = sc.nextLine();
        hangHoa hh = null;

        for (hangHoa X : a) {
            if (X.getmaHang().equals(maHang)) {
                hh=X;
                break;
            }
        }
        if (hh == null)
            System.out.println("Mã sản phẩm không tồn tại.");
        else
            System.out.println(hh);
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).getmaHang().equals(maHang)) {
                System.out.println("Nhập tên sản phẩm: ");
                String tenSp = sc.nextLine();

                System.out.println("Nhập ngày nhập sản phẩm: ");
                String ngayNhap = sc.nextLine();

                System.out.println("Nhập giá tiền: ");
                double donGia = sc.nextInt();

                System.out.println("Nhập số lượng sản phẩm: ");
                int soluong = sc.nextInt();
                sc.nextLine();

                System.out.println("Nhập nơi sản xuất: ");
                String nsx = sc.nextLine();

                a.get(i).settenSP(tenSp);
                a.get(i).setngayNhap(ngayNhap);
                a.get(i).setdonGia(donGia);
                a.get(i).setsoLuong(soluong);
                a.get(i).setnsx(nsx);

                System.out.println(" Đã sửa thông tin sản phẩm! ");
                break;
            }
        }
    }
    public void timKiemSanPham() {
            Scanner sc = new Scanner(System.in);
            System.out.println("Nhập mã sản phẩm muốn tìm: ");
            String maHang = sc.nextLine();
            hangHoa hh = null;

            for (hangHoa X : a) {
                if (X.getmaHang().equals(maHang)) {
                    hh=X;
                    break;
                }
            }
            if (hh == null)
                System.out.println("Mã sản phẩm không tồn tại.Vui lòng nhập lại: ");
            else
                System.out.println(hh);
    }
    public void xoaSanPham() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã sản phẩm cần xóa: ");
        String maHang = sc.nextLine();

        boolean found = false;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).getmaHang().equals(maHang)) {
                System.out.println("Thông tin sản phẩm cần xóa:");
                System.out.println(a.get(i));

                System.out.print("Bạn có chắc muốn xóa sản phẩm này không? (y/n): ");
                String confirm = sc.nextLine();

                if (confirm.equalsIgnoreCase("y")) {
                    a.remove(i);
                    System.out.println(" Đã xóa sản phẩm khỏi danh sách!");
                } else
                    System.out.println(" Hủy xóa sản phẩm.");

                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println(" Không tìm thấy sản phẩm có mã: " + maHang);
        }
    }

    public void xuatDanhSachDienThoai() {
        System.out.println(" danh sách điện thoại: ");
        for (hangHoa X : a) {
            if (X instanceof dienThoai ) {
                System.out.println(X);
            }
        }
    }

    public void xuatDanhSachLapTop() {
        System.out.println(" danh sách laptop: ");
        for (hangHoa X : a) {
            if (X instanceof lapTop)
            System.out.println(X);
        }
    }

    public void xuatDanhSachLoa() {
        System.out.println(" danh sách loa: ");
        for (hangHoa X : a) {
            if (!(X instanceof dienThoai) && !(X instanceof lapTop))
                System.out.println(X);
        }
    }

    public void xuatTatCa() {
        for( hangHoa X:a){
            System.out.println(X);
        }
    }
}
