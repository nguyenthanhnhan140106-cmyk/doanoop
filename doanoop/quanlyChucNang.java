package doanoop;

import java.util.Scanner;

public class quanlyChucNang implements chucNang{
    Scanner sc = new Scanner(System.in);
    DanhSachHangHoa a=new DanhSachHangHoa();
    DanhSachNhanVien b=new DanhSachNhanVien();
    muaHang c=new muaHang();
    @Override
    public void menu(){
        a.docFile("doanoop/danhsachsanpham.txt");
        b.docFile("doanoop/danhsachnhanvien.txt");

        
        int chon;

        do {
            System.out.println("\n========== MENU CHÍNH ==========");
            System.out.println("1. Danh sách hàng hóa");
            System.out.println("2. Danh sách nhân viên");
            System.out.println("3. Mua hàng");
            System.out.println("4. Xem hóa đơn");
            System.out.println("0. Thoát");
            System.out.print("Nhập lựa chọn: ");
            
            try {
                chon = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Vui lòng nhập số!");
                sc.nextLine(); 
                chon = -1; 
                continue;
            }

            switch (chon) {
                case 1:
                    menuHangHoa();
                    break;
                case 2:
                    menuNhanVien();
                    break;
                case 3:
                    muaHang();
                    break;
                case 4:
                    xemHoaDon();
                    break;
                case 0:
                    System.out.println("Thoát chương trình...");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
                    break;
            }
        } while (chon != 0);
    }
    
    public void menuHangHoa(){
        int lc1;
        do {
            System.out.println("\n--- DANH SÁCH HÀNG HÓA ---");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Xóa sản phẩm");
            System.out.println("3. Sửa sản phẩm");
            System.out.println("4. Tìm kiếm sản phẩm");
            System.out.println("5. Xem danh sách sản phẩm");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            
            try {
                lc1 = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Vui lòng nhập số!");
                sc.nextLine();
                lc1 = -1;
                continue;
            }
            
            switch (lc1) {
                case 1:
                    themDShanghoa();
                    a.ghiFile("doanoop/danhsachsanpham.txt");
                    break;
                case 2:
                    xoaSanPham();
                    a.ghiFile("doanoop/danhsachsanpham.txt");
                    break;
                case 3:
                    suaDSsanpham();
                    a.ghiFile("doanoop/danhsachsanpham.txt");
                    break;
                case 4:
                    timKiemSanPham();
                    break;
                case 5:
                    menuPrintHangHoa();
                    break;
                case 0:
                    System.out.println("Quay lại menu chính...");
                    break;
                default:
                    System.out.println("Sai lựa chọn!");
                    break;
            }
        } while (lc1 != 0);
    }
    
    public void menuNhanVien(){
        int lc2;
        do {
            System.out.println("\n--- DANH SÁCH NHÂN VIÊN ---");
            System.out.println("1. Thêm nhân viên");
            System.out.println("2. Xóa nhân viên");
            System.out.println("3. Sửa nhân viên");
            System.out.println("4. Tìm kiếm nhân viên");
            System.out.println("5. Xem danh sách nhân viên");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            
            try {
                lc2 = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Vui lòng nhập số!");
                sc.nextLine();
                lc2 = -1;
                continue;
            }
            
            switch (lc2) {
                case 1:
                    themNhanVien();
                    b.ghiFile("doanoop/danhsachnhanvien.txt");
                    break;
                case 2:
                    xoaNhanVien();
                    b.ghiFile("doanoop/danhsachnhanvien.txt");
                    break;
                case 3:
                    suaDSNhanVien();
                    b.ghiFile("doanoop/danhsachnhanvien.txt");
                    break;
                case 4:
                    timKiemNhanVien();
                    break;
                case 5:
                    menuPrintNhanVien();
                    break;
                case 0:
                    System.out.println("Quay lại menu chính...");
                    break;
                default:
                    System.out.println("Sai lựa chọn!");
                    break;
            }
        } while (lc2 != 0);
    }
    
    public void menuPrintHangHoa(){
        int xem;
        do {
            System.out.println("\n--- XEM DANH SÁCH ---");
            System.out.println("1. Điện thoại");
            System.out.println("2. Laptop");
            System.out.println("3. Loa");
            System.out.println("4. Tất cả sản phẩm");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            
            try {
                xem = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Vui lòng nhập số!");
                sc.nextLine();
                xem = -1;
                continue;
            }
            
            switch (xem) {
                case 1:
                    a.xuatDanhSachDienThoai();
                    break;
                case 2:
                    a.xuatDanhSachLapTop();
                    break;
                case 3:
                    a.xuatDanhSachLoa();
                    break;
                case 4:
                    a.xuatTatCa();
                    break;
                case 0:
                    System.out.println("Quay lại...");
                    break;
                default:
                    System.out.println("Sai lựa chọn!");
                    break;
            }
        } while (xem != 0);
    }
    
    public void menuPrintNhanVien(){
        int xemNV;
        do {
            System.out.println("\n--- XEM DANH SÁCH NHÂN VIÊN ---");
            System.out.println("1. Nhân viên Part-time");
            System.out.println("2. Nhân viên Full-time");
            System.out.println("3. Tất Cả Nhân viên");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            
            try {
                xemNV = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Vui lòng nhập số!");
                sc.nextLine();
                xemNV = -1;
                continue;
            }
            
            switch (xemNV) {
                case 1:
                    b.xuatDanhSachNhanVienParttime();
                    break;
                case 2:
                    b.xuatDanhSachNhanVienFullTime();
                    break;
                case 3:
                    b.xuatTatCa();
                    break;
                case 0:
                    System.out.println("Quay lại...");
                    break;
                default:
                    System.out.println("Sai lựa chọn!");
                    break;
            }
        } while (xemNV != 0);
    }
    @Override
    public void subMenu(){
        
    }
    @Override
    public void themDShanghoa() {
        a.themDSHangHoa();
    }

    @Override
    public void suaDSsanpham() {
        a.suaDSsanpham();
    }

    @Override
    public void timKiemSanPham() {
        a.timKiemSanPham();
    }

    @Override
    public void xoaSanPham() {
        a.xoaSanPham();
    }

    @Override
    public void themNhanVien() {
        b.themDSNhanVien();
    }

    @Override
    public void suaDSNhanVien() {
        b.suaDSNhanVien();
    }

    @Override
    public void timKiemNhanVien() {
        b.timKiemNhanVien();
    }

    @Override
    public void xoaNhanVien() {
        b.xoaNhanVien();
    }
    @Override
    public void muaHang() {
        c.muaHang();
    }

    @Override
    public void xemHoaDon() {
        c.xuatHoaDon();
    }

}
