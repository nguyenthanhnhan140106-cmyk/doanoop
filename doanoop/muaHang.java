package doanoop;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class muaHang {
    private ArrayList<hoaDon> danhSachHoaDon = new ArrayList<>();
    private DanhSachHangHoa dshh;
    private DanhSachNhanVien dsnv;

    public muaHang() {
        this.dshh = new DanhSachHangHoa();
        this.dsnv = new DanhSachNhanVien();
    }
    public void setDanhSachHangHoa(DanhSachHangHoa dshh) {
        this.dshh=dshh;
    }
    public void setDanhSachNhanVien(DanhSachNhanVien dsnv){
        this.dsnv=dsnv;
    }
    public void muaHang() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n=== Thông tin khách hàng ===");

        System.out.print("Nhập mã khách hàng: ");
        String id = sc.nextLine().trim();

        System.out.print("Nhập tên khách hàng: ");
        String ten = sc.nextLine().trim();

        System.out.print("Nhập số điện thoại: ");
        String sdt = sc.nextLine().trim();

        System.out.print("Nhập email: ");
        String email = sc.nextLine().trim();

        khachHang kh = new khachHang(id, ten, email, sdt);

        System.out.print("Nhập Mã NV phục vụ:");
        String maNV= sc.nextLine().trim();
        nhanVien nv= dsnv.timNhanVien(maNV);
        if (nv == null) {
            System.out.println("Không tìm thấy nhân viên có mã: " + maNV);
            System.out.print("Tiếp tục mà không có nhân viên? (y or 0/n): ");
            String choice = sc.nextLine().trim();
            if (!choice.equalsIgnoreCase("y") || !choice.equalsIgnoreCase("0")) {
                System.out.println("Hủy giao dịch.");
                return;
            }
        }

 hoaDon hd = new hoaDon();
        hd.setKhachHang(kh);
        hd.setNhanVien(nv);
        
        System.out.println("\n--- MUA HÀNG ---");
        System.out.println("Nhập '0' để kết thúc mua hàng");
        
        while (true) {
            System.out.print("\nNhập mã sản phẩm: ");
            String maSP = sc.nextLine().trim();
            
            if (maSP.equals("0")) {
                break;
            }
            
            if (maSP.isEmpty()) {
                System.out.println("Mã sản phẩm không được để trống!");
                continue;
            }
            hangHoa hh = dshh.timKiemMa(maSP);
            
            if (hh == null) {
                System.out.println("Không tìm thấy sản phẩm có mã: " + maSP);
                continue;
            }
            if (hh.getsoLuong()<=0){
                System.out.println("Sản phẩm "+hh.getTenSP()+" đã hết hàng");
                System.out.println("Vui lòng nhập sản phẩm khác");
                continue;
            }
            System.out.println("Tìm thấy: " + hh.getTenSP());
            System.out.println("Giá: " + hh.getdonGia());
            
            
            double soLuongMua = 0;
            while (true) {
                System.out.print("Nhập số lượng mua: ");
                try {
                    String input = sc.nextLine().trim();
                    soLuongMua = Double.parseDouble(input);
                    
                    if (soLuongMua <= 0) {
                        System.out.println("Số lượng phải lớn hơn 0!");
                        continue;
                    }
                    
                    if (soLuongMua > hh.getsoLuong()) {
                        System.out.println("Không đủ hàng! Chỉ còn: " + hh.getsoLuong());
                        continue;
                    }
                    
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Số lượng phải là số!");
                }
            }
            
            hd.themSanPham(hh, soLuongMua);
            hh.setsoLuong(hh.getsoLuong() - soLuongMua);
            
            System.out.println("Đã thêm vào giỏ hàng!");
        }
        
        if (hd.getDanhSachSanPham().isEmpty()) {
            System.out.println("Không có sản phẩm nào được mua!");
            return;
        }
        
        System.out.println("\n" + hd);
        
        System.out.print("\nXác nhận thanh toán? (y/n): ");
        String confirm = sc.nextLine().trim();
        
        if (confirm.equalsIgnoreCase("y")) {
            danhSachHoaDon.add(hd);
            ghiHoaDon(hd);
            System.out.println("Thanh toán thành công!");
        } else {
            for (chiTietHoaDon ct : hd.getDanhSachSanPham()) {
                hangHoa hh = ct.getSanPham();
                hh.setsoLuong(hh.getsoLuong() + ct.getSoLuong());
            }
            System.out.println("Hủy giao dịch.");
        }
    }
    
    private void ghiHoaDon(hoaDon hd) {
        try {
            BufferedWriter fw = new BufferedWriter(new FileWriter("doanoop/hoadon.txt", true));
            fw.write(hd.toString());
            fw.write("\n\n");
            fw.close();
        } catch (Exception e) {
            System.out.println("Lỗi khi ghi hóa đơn!");
            e.printStackTrace();
        }
    }
    
    public void xemLichSuHoaDon() {
        try {
            BufferedReader input = new BufferedReader(new FileReader("doanoop/hoadon.txt"));
            String line;
            System.out.println("\n=== LỊCH SỬ HÓA ĐƠN ===\n");
            
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            input.close();
        } catch (Exception e) {
            System.out.println("Chưa có hóa đơn nào!");
        }
    }
    
    public void xuatHoaDon() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n--- XEM HÓA ĐƠN ---");
        System.out.println("1. Hóa đơn trong phiên làm việc này");
        System.out.println("2. Lịch sử tất cả hóa đơn");
        System.out.print("Chọn: ");
        
        try {
            int chon = sc.nextInt();
            sc.nextLine();
            
            if (chon == 1) {
                if (danhSachHoaDon.isEmpty()) {
                    System.out.println("Chưa có hóa đơn nào trong phiên này!");
                    return;
                }
                
                System.out.println("\n--- DANH SÁCH HÓA ĐƠN PHIÊN NÀY ---");
                for (int i = 0; i < danhSachHoaDon.size(); i++) {
                    System.out.println("\nHóa đơn " + (i + 1) + ":");
                    System.out.println(danhSachHoaDon.get(i));
                }
            } else if (chon == 2) {
                xemLichSuHoaDon();
            } else {
                System.out.println("Lựa chọn không hợp lệ!");
            }
        } catch (Exception e) {
            System.out.println("Vui lòng nhập số!");
        }
    }
}

class chiTietHoaDon {
    private hangHoa sanPham;
    private double soLuong;
    
    public chiTietHoaDon(hangHoa sanPham, double soLuong) {
        this.sanPham = sanPham;
        this.soLuong = soLuong;
    }
    
    public hangHoa getSanPham() {
        return sanPham;
    }
    
    public double getSoLuong() {
        return soLuong;
    }
    
    public double tinhTien() {
        if (sanPham instanceof thanhToan) {
            double soLuongGoc = sanPham.getsoLuong();
            sanPham.setsoLuong(soLuong);
            double thanhTien = ((thanhToan) sanPham).tinhThanhTien();
            sanPham.setsoLuong(soLuongGoc);
            return thanhTien;
        }
        return sanPham.getdonGia() * soLuong;
    }
    
    @Override
    public String toString() {
        return String.format("%s - %s: %.0f x %.0f = %.0f VND", 
            sanPham.getmaHang(), 
            sanPham.getTenSP(), 
            soLuong, 
            sanPham.getdonGia(), 
            tinhTien());
    }
}

class hoaDon {
    private String maHD;
    private khachHang khachHang;
    private nhanVien nhanVien;
    private ArrayList<chiTietHoaDon> danhSachSanPham;
    private String ngayMua;
    
    public hoaDon() {
        this.maHD = "HD" + System.currentTimeMillis();
        this.danhSachSanPham = new ArrayList<>();
    }
    
    public void setKhachHang(khachHang kh) {
        this.khachHang = kh;
    }
    
    public void setNhanVien(nhanVien nv) {
        this.nhanVien = nv;
    }
    
    public void themSanPham(hangHoa hh, double soLuong) {
        danhSachSanPham.add(new chiTietHoaDon(hh, soLuong));
    }
    
    public ArrayList<chiTietHoaDon> getDanhSachSanPham() {
        return danhSachSanPham;
    }
    
    public double tinhTongTien() {
        double tong = 0;
        for (chiTietHoaDon ct : danhSachSanPham) {
            tong += ct.tinhTien();
        }
        return tong;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("----HÓA ĐƠN----\n");
        sb.append("Mã hóa đơn: ").append(maHD).append("\n");
        sb.append("Ngày: ").append(ngayMua).append("\n");
        sb.append("Khách hàng: ").append(khachHang.toString()).append("\n");
        
        if (nhanVien != null) {
            sb.append("Nhân viên: ").append(nhanVien.getid()).append(" - ").append(nhanVien.getten()).append("\n");
        } else {
            sb.append("Nhân viên: (Không có)\n");
        }
        
        sb.append("\nChi tiết:\n");
        for (chiTietHoaDon ct : danhSachSanPham) {
            sb.append("  ").append(ct.toString()).append("\n");
        }
        
        sb.append("\nTổng cộng: ").append(String.format("%.0f", tinhTongTien())).append(" VND");
        sb.append("\n---------------");
        
        return sb.toString();
    }
}