package doanoop;

import java.util.ArrayList;
import java.util.Scanner;

public class muaHang {
    private ArrayList<hangHoa> dsHangHoa;
    private ArrayList<khachHang> dsKhachHang;
    private khachHang khCuoi;
    private ArrayList<String> dsMuaCuoi;
    private double tongTienCuoi;

    public muaHang() {
        this.dsHangHoa = new ArrayList<>();
        this.dsKhachHang = new ArrayList<>();
    }

    public void muaHang() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n===== MUA SẢN PHẨM =====");

        // --- nhập thông tin khách hàng ---
        System.out.print("Nhập mã khách hàng: ");
        String id = sc.nextLine();

        System.out.print("Nhập tên khách hàng: ");
        String ten = sc.nextLine();

        System.out.print("Nhập số điện thoại: ");
        String sdt = sc.nextLine();

        System.out.print("Nhập email: ");
        String email = sc.nextLine();

        khachHang kh = new khachHang(id, ten, email, sdt);
        dsKhachHang.add(kh);

        ArrayList<String> dsMua = new ArrayList<>();
        double tongTien = 0;

        while (true) {
            System.out.print("\nNhập mã sản phẩm muốn mua (hoặc nhập '0' để kết thúc): ");
            String idSP = sc.nextLine();
            if (idSP.equalsIgnoreCase("0") || idSP.equalsIgnoreCase("xong")) break;

            hangHoa hh = null;
            for (hangHoa x : dsHangHoa) {
                if (x.getmaHang().equalsIgnoreCase(idSP)) {
                    hh = x;
                    break;
                }
            }

            if (hh == null) {
                System.out.println("Sản phẩm không tồn tại!");
                continue;
            }

            System.out.println("Sản phẩm: " + hh.getTenSP() + " | Giá: " + hh.getdonGia() + " | Tồn kho: " + hh.getsoLuong());
            System.out.print("Nhập số lượng muốn mua: ");
            double slMua = sc.nextDouble();
            sc.nextLine();

            if (slMua <= 0) {
                System.out.println("Số lượng không hợp lệ!");
                continue;
            }
            if (slMua > hh.getsoLuong()) {
                System.out.println("Không đủ hàng trong kho!");
                continue;
            }

            hh.setsoLuong(hh.getsoLuong() - slMua);
            double thanhTien = slMua * hh.getdonGia();
            tongTien += thanhTien;

            dsMua.add(hh.getTenSP() + " x" + slMua + " = " + thanhTien);
            System.out.println(" Đã thêm vào giỏ hàng: " + hh.getTenSP());
        }

        if (dsMua.isEmpty()) {
            System.out.println(" Bạn chưa mua sản phẩm nào!");
            return;
        }

        kh.ngayMuaHang = java.time.LocalDate.now().toString();
        System.out.println("\n Mua hàng thành công!");
        xuatHoaDon(kh, dsMua, tongTien);
    }

    public void xuatHoaDon(khachHang kh, ArrayList<String> dsMua, double tongTien) {
        khCuoi = kh;
        dsMuaCuoi = dsMua;
        tongTienCuoi = tongTien;
        System.out.println("===== HOA DON =====");
        System.out.println("Khach hang: " + kh.getten());
        System.out.println("Danh sach mua:");
        for (String sp : dsMua) {
            System.out.println(" - " + sp);
        }
        System.out.println("Tong tien: " + tongTien);
        System.out.println("===================");
    }

    public void xuatHoaDon() {
        if (khCuoi != null && dsMuaCuoi != null) {
            xuatHoaDon(khCuoi, dsMuaCuoi, tongTienCuoi);
        } else {
            System.out.println("Chua co hoa don nao de hien thi!");
        }
    }
}
