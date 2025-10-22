package doanoop;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        quanlyChucNang m = new quanlyChucNang();
        DanhSachHangHoa a = new DanhSachHangHoa();
        m.a.docFile("D:\\java\\javacoban\\src\\doanoop\\danhsachsanpham.txt");
        DanhSachNhanVien b=new DanhSachNhanVien();
        m.b.docFile("D:\\java\\javacoban\\src\\doanoop\\danhsachnhanvien.txt");
        int chon;

        do {
            System.out.println("\n========== MENU CHÍNH ==========");
            System.out.println("1. Danh sách hàng hóa");
            System.out.println("2. Danh sách nhân viên");
            System.out.println("3. Mua hàng");
            System.out.println("4. Xem hóa đơn");
            System.out.println("0. Thoát");
            System.out.print("Nhập lựa chọn: ");
            chon = sc.nextInt();
            sc.nextLine();

            switch (chon) {
                case 1:
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
                        lc1 = sc.nextInt();
                        sc.nextLine();
                        switch (lc1) {
                            case 1:
                                m.themDShanghoa();
                                m.a.ghiFile("D:\\java\\javacoban\\src\\doanoop\\danhsachsanpham.txt");
                                break;
                            case 2:
                                m.xoaSanPham();
                                m.a.ghiFile("D:\\java\\javacoban\\src\\doanoop\\danhsachsanpham.txt");
                                break;
                            case 3:
                                m.suaDSsanpham();
                                m.a.ghiFile("D:\\java\\javacoban\\src\\doanoop\\danhsachsanpham.txt");
                                break;
                            case 4:
                                m.timKiemSanPham();
                                break;
                            case 5:
                                int xem;
                                do {
                                    System.out.println("\n--- XEM DANH SÁCH ---");
                                    System.out.println("1. Điện thoại");
                                    System.out.println("2. Laptop");
                                    System.out.println("3. Loa");
                                    System.out.println("4. Tất cả sản phẩm");
                                    System.out.println("0. Quay lại");
                                    System.out.print("Chọn: ");
                                    xem = sc.nextInt();
                                    sc.nextLine();
                                    switch (xem) {
                                        case 1:
                                            m.a.xuatDanhSachDienThoai();
                                            break;
                                        case 2:
                                            m.a.xuatDanhSachLapTop();
                                            break;
                                        case 3:
                                            m.a.xuatDanhSachLoa();
                                            break;
                                        case 4:
                                            m.a.xuatTatCa();
                                            break;
                                        case 0:
                                            System.out.println("Quay lại...");
                                            break;
                                        default:
                                            System.out.println("Sai lựa chọn!");
                                    }
                                } while (xem != 0);
                                break;
                            case 0:
                                System.out.println("Quay lại menu chính...");
                                break;
                            default:
                                System.out.println("Sai lựa chọn!");
                        }
                    } while (lc1 != 0);
                    break;

                case 2:
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
                        lc2 = sc.nextInt();
                        sc.nextLine();
                        switch (lc2) {
                            case 1:
                                m.themNhanVien();
                                m.b.ghiFile("D:\\java\\javacoban\\src\\doanoop\\danhsachnhanvien.txt");
                                break;
                            case 2:
                                m.xoaNhanVien();
                                m.b.ghiFile("D:\\java\\javacoban\\src\\doanoop\\danhsachnhanvien.txt");
                                break;
                            case 3:
                                m.suaDSNhanVien();
                                m.b.ghiFile("D:\\java\\javacoban\\src\\doanoop\\danhsachnhanvien.txt");
                                break;
                            case 4:
                                m.timKiemNhanVien();
                                break;
                            case 5:
                                int xemNV;
                                do {
                                    System.out.println("\n--- XEM DANH SÁCH NHÂN VIÊN ---");
                                    System.out.println("1. Nhân viên Part-time");
                                    System.out.println("2. Nhân viên Full-time");
                                    System.out.println("3. Tất Cả Nhân viên");
                                    System.out.println("0. Quay lại");
                                    System.out.print("Chọn: ");
                                    xemNV = sc.nextInt();
                                    sc.nextLine();
                                    switch (xemNV) {
                                        case 1:
                                            m.b.xuatDanhSachNhanVienParttime();
                                            break;
                                        case 2:
                                            m.b.xuatDanhSachNhanVienFullTime();
                                            break;
                                        case 3:
                                            m.b.xuatTatCa();
                                        case 0:
                                            System.out.println("Quay lại...");
                                            break;
                                        default:
                                            System.out.println("Sai lựa chọn!");
                                    }
                                } while (xemNV != 0);
                                break;
                            case 0:
                                System.out.println("Quay lại menu chính...");
                                break;
                            default:
                                System.out.println("Sai lựa chọn!");
                        }
                    } while (lc2 != 0);
                    break;

                case 3:
                    m.muaHang();
                    break;

                case 4:
                    m.xemHoaDon();
                    break;

                case 0:
                    System.out.println("Thoát chương trình...");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (chon != 0);

        sc.close();
    }
}
