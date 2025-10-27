package doanoop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.Normalizer; // using for vietnamese conversion (chuyen doi dau cau)
import java.util.ArrayList;
import java.util.Scanner;

public class DanhSachNhanVien{
    private ArrayList<nhanVien> a= new ArrayList<nhanVien>();

    public void docFile(String tenFile) {
        try {
            BufferedReader input = new BufferedReader(new FileReader(tenFile, java.nio.charset.StandardCharsets.UTF_8));
            String line;

            while ((line = input.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] arr = line.split("\\|\\|");

                String id = arr[0].split(":")[1].trim();
                String ten = arr[1].split(":")[1].trim();
                String email = arr[2].split(":")[1].trim();
                String sdt=arr[3].split(":")[1].trim();
                nhanVien nv;
                if (id.toUpperCase().startsWith("PT"))
                    nv = new nvParttime(id, ten, email , sdt);
                else
                    nv = new nvFulltime(id, ten, email , sdt);
                a.add(nv);
                
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void ghiFile(String tenFile) {
        try {
            BufferedWriter fw = new BufferedWriter(new FileWriter(tenFile, java.nio.charset.StandardCharsets.UTF_8));
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
        String id="";
        while (true){
            System.out.println("Nếu là nhân viên parttime thì mã nhân viên bắt đầu là PT còn nhân viên fulltime bắt đầu là FT! ");
            System.out.println("Nhập mã nhân viên: ");
            id=sc.nextLine().trim();
            if (id.isEmpty()){
                System.out.println("Mã nhân viên không được để trống!");
                continue;
            }
            String manv = id.toUpperCase().trim();
            if (!manv.startsWith("PT") && !manv.startsWith("FT")) {
                System.out.println("Lưu ý: Mã nhân viên phải bắt đầu bằng PT (Part Time), FT (Full Time)");
                continue;
            }
            boolean exists=false;
            for (nhanVien nv:a){
                if (nv.getid().equalsIgnoreCase(id)){
                    exists=true;
                    break;
                }
            }
            if (exists){
                System.out.println("Tồn tại mã nhân viên "+id+", Vui lòng nhập lại!");
                continue;
            }
            break;
        }
        String ten="";
        while (true){
            System.out.println("Nhập tên nhân viên: ");
            ten=sc.nextLine().trim();
            if (ten.isEmpty()){
                System.out.println("Tên nhân viên không được để trống!");
                continue;
            }
            if (ten.matches(".*\\d.*")) {
                System.out.println("Tên nhân viên không được chứa số! Vui lòng nhập lại");
                continue;
            }
            if (ten.matches(".*\\s{2,}.*")) {
                System.out.println("Tên nhân viên không được chứa nhiều khoảng trắng liên tiếp!");
                continue;
            }
            break;
        }
        
        String email="";
        while(true){
            System.out.println("Nhập email: ");
            email=sc.nextLine().trim();
            if (email.isEmpty()){
                System.out.println("Email không được để trống!");
                continue;
            }
            if (!email.matches("^[A-Za-z0-9_]+@[A-Za-z0-9.]+\\.[A-Za-z]{2,}$")) {
                System.out.println("Email không hợp lệ!");
                System.out.println("Ví dụ: example@gmail.com");
                continue;
            }
            break;
        }

        String sdt="";
        while(true){
            System.out.println("Nhập số điện thoại liên hệ: ");
            sdt=sc.nextLine().trim();
            if (sdt.isEmpty()){
                System.out.println("Số điện thoại không được trống ");
            }
            if (!sdt.matches("0\\d{9}")) {
                System.out.println("SDT phải đúng hình thức (10 số, bắt đầu bằng 0)!");
                continue;
            }
            break;
        }


        nhanVien nv;
        if (id.toUpperCase().startsWith("PT"))
            nv = new nvParttime(id, ten, email, sdt);
        else
            nv = new nvFulltime(id, ten, email, sdt);

        a.add(nv);
        System.out.println("Đã thêm nhân viên mới!");
    }
    public void suaDSNhanVien() {
        Scanner sc = new Scanner(System.in);
        String id="";
        while (true){
            System.out.println("Nhập mã nhân viên: ");
            id = sc.nextLine().trim();
            if (id.isEmpty() || id == null){
                System.out.println("Mã nhân viên không được để trống!");
                continue;
            }

            String manv = id.toUpperCase().trim();
            if (!manv.startsWith("PT") && !manv.startsWith("FT")) {
                System.out.println("Lưu ý: Mã nhân viên phải bắt đầu bằng PT (Làm việc Part Time), FT (Làm việc Full Time)");
                continue;
            }
            break;
        }

        nhanVien nv = null;
        for (nhanVien X : a) {
            if (X.getid().equals(id)) {
                nv = X;
                break;
            }
        }
        if (nv == null){
            System.out.println("Mã nhân viên không tồn tại.");
            return;
        }
        System.out.println("\nThông tin nhân viên hiện tại:");
        System.out.println(nv);
        int chon;
        do {
            System.out.println("\n========== MENU CHỈNH SỬA ==========");
            System.out.println("1. Sửa Tên nhân viên");
            System.out.println("2. Sửa Email");
            System.out.println("3. Sửa Số điện thoại");
            System.out.println("4. Sửa Tất cả thông tin");
            System.out.println("0. Lưu và thoát");
            System.out.println("====================================");
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
                    String ten="";
                    while (true){
                        System.out.println("Nhập tên nhân viên: ");
                        ten = sc.nextLine().trim();
                        if (ten.isEmpty()){
                            System.out.println("Tên nhân viên không được để trống!");
                            continue;
                        }
                        if (ten.matches(".*\\d.*")) {
                            System.out.println("Tên nhân viên không được chứa số! Vui lòng nhập lại");
                            continue;
                        }

                        if (ten.matches(".*\\s{2,}.*")) {
                            System.out.println("Tên nhân viên không được chứa nhiều khoảng trắng liên tiếp!");
                            continue;
                        }break;
                    }
                    nv.setten(ten);
                    System.out.println("Đã cập nhật tên sản phẩm!");
                    break;
                case 2:
                    String email="";
                    while (true){
                        System.out.println("Nhập email của nhân viên: ");
                        email = sc.nextLine().trim();
                        if (email.isEmpty()){
                            System.out.println("Email không được để trống!");
                            continue;
                        }
                        if (!email.matches("^[A-Za-z0-9_]+@[A-Za-z0-9.]+\\.[A-Za-z]{2,}$")) {
                            System.out.println("Email không hợp lệ!");
                            System.out.println("Ví dụ: example@gmail.com");
                            continue;
                        }
                        break;
                    }
                    nv.setemail(email);
                    System.out.println("Đã cập nhật email!");
                    break;
                case 3:
                    String sdt="";
                    while (true){
                        System.out.println("Nhập số điện thoại nhân viên: ");
                        sdt = sc.nextLine().trim();
                        if (sdt.isEmpty()){
                            System.out.println("Số điện thoại không được để trống!");
                            continue;
                        }

                        if (!sdt.matches("0\\d{9}")) {
                            System.out.println("SDT phải đúng hình thức (10 số, bắt đầu bằng 0)!");
                            continue;
                        }
                        break;
                    }
                    nv.setsdt(sdt);
                    System.out.println("Đã cập nhật số điện thoại!");
                    break;
                case 4:
                    System.out.println("--- Nhập tất cả thông tin mới ---");
                    ten="";
                    while (true){
                        System.out.println("Nhập tên nhân viên: ");
                        ten = sc.nextLine().trim();
                        if (ten.isEmpty()){
                            System.out.println("Tên nhân viên không được để trống!");
                            continue;
                        }
                        if (ten.matches(".*\\d.*")) {
                            System.out.println("Tên nhân viên không được chứa số! Vui lòng nhập lại");
                            continue;
                        }

                        if (ten.matches(".*\\s{2,}.*")) {
                            System.out.println("Tên nhân viên không được chứa nhiều khoảng trắng liên tiếp!");
                            continue;
                        }break;
                    }
                    email="";
                    while (true){
                        System.out.println("Nhập email của nhân viên: ");
                        email = sc.nextLine().trim();
                        if (email.isEmpty()){
                            System.out.println("Email không được để trống!");
                            continue;
                        }
                        if (!email.matches("^[A-Za-z0-9_]+@[A-Za-z0-9.]+\\.[A-Za-z]{2,}$")) {
                            System.out.println("Email không hợp lệ!");
                            System.out.println("Ví dụ: example@gmail.com");
                            continue;
                        }
                        break;
                    }
                    sdt="";
                    while (true){
                        System.out.println("Nhập số điện thoại nhân viên: ");
                        sdt = sc.nextLine().trim();
                        if (sdt.isEmpty()){
                            System.out.println("Số điện thoại không được để trống!");
                            continue;
                        }

                        if (!sdt.matches("0\\d{9}")) {
                            System.out.println("SDT phải đúng hình thức (10 số, bắt đầu bằng 0)!");
                            continue;
                        }
                        break;
                    }
                    nv.setten(ten);
                    nv.setemail(email);
                    nv.setsdt(sdt);

                    System.out.println("Đã cập nhật tất cả thông tin!");
                    break;
                case 0:
                    System.out.println("Đã lưu các thay đổi!");
                    System.out.println("\nThông tin sản phẩm sau khi sửa:");
                    System.out.println(nv);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
        while(chon!=0);          
    }
    public nhanVien timNhanVien(String ma){
        for (nhanVien nv :a){
            if (nv.getid().equalsIgnoreCase(ma)){
                return nv;
            }
        }
        return null;
    }
    private String removeDauTen(String str) {
        if (str == null) return "";
    
        String normalized = Normalizer.normalize(str, Normalizer.Form.NFD);
        normalized = normalized.replaceAll("\\p{M}", "");
    
    
        normalized = normalized.replace("Đ", "D").replace("đ", "d");
    
        return normalized.toLowerCase();
    }

    public void timKiemNhanVien() {
        Scanner sc = new Scanner(System.in);
        int chon;

        do {
            System.out.println("\n========== MENU TÌM KIẾM ==========");
            System.out.println("1. Tìm kiếm theo mã nhân viên");
            System.out.println("2. Tìm kiếm theo tên nhân viên");
            System.out.println("3. Tìm kiếm theo email");
            System.out.println("4. Tìm kiếm theo số điện thoại");
            System.out.println("5. Tìm kiếm theo loại nhân viên (PT/FT)");
            System.out.println("0. Quay lại");
            System.out.println("====================================");
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

            ArrayList<nhanVien> ketQuaTimKiem = new ArrayList<>();
            String tuKhoa = "";

            switch (chon) {
                case 1:
                    while (true){
                        System.out.println("Nhập mã nhân viên: ");
                        tuKhoa = sc.nextLine().trim();
                        if (tuKhoa.isEmpty()){
                            System.out.println("Mã nhân viên không được để trống!");
                            continue;
                        }

                        String manv = tuKhoa.toUpperCase().trim();
                        if (!manv.startsWith("PT") && !manv.startsWith("FT")) {
                            System.out.println("Lưu ý: Mã nhân viên phải bắt đầu bằng PT (Làm việc Part Time), FT (Làm việc Full Time)");
                            continue;
                        }
                        break;
                    }

                    nhanVien nv = null;

                    for (nhanVien X : a) {
                        if (X.getid().equals(tuKhoa)) {
                            nv = X;
                            break;
                        }
                    }

                    if (nv == null){
                        System.out.println("Mã nhân viên không tồn tại. Vui lòng nhập lại!");
                        return;
                    }
                    for (nhanVien nv2 : a) {
                        if (nv2.getid().equalsIgnoreCase(tuKhoa)) {
                            ketQuaTimKiem.add(nv2);
                        }
                    }
                    break;
                case 2:
                    while (true){
                        System.out.println("Nhập tên nhân viên: ");
                        tuKhoa = sc.nextLine().trim();
                        if (tuKhoa.isEmpty()){
                            System.out.println("Vui lòng không được để trống!");
                            continue;
                        }

                        if (tuKhoa.matches(".*\\d.*")) {
                            System.out.println("Tên sản phẩm không được chứa số! Vui lòng nhập lại");
                            continue;
                        }

                        if (tuKhoa.matches(".*\\s{2,}.*")) {
                            System.out.println("Tên nhân viên không được chứa nhiều khoảng trắng liên tiếp!");
                            continue;
                        }
                        break;
                    }
                    String tuKhoaNoAccent = removeDauTen(tuKhoa);
                        for (nhanVien X : a) {
                            String tenNoAccent = removeDauTen(X.getten());
                            if (tenNoAccent.contains(tuKhoaNoAccent)) ketQuaTimKiem.add(X);
                        }
                    break;
                case 3:
                    while (true){
                        System.out.println("Nhập email của nhân viên: ");
                        tuKhoa = sc.nextLine().trim();
                        if (tuKhoa.isEmpty()){
                            System.out.println("Email không được để trống!");
                            continue;
                        }
                        if (!tuKhoa.matches("^[A-Za-z0-9_]+@[A-Za-z0-9.]+\\.[A-Za-z]{2,}$")) {
                            System.out.println("Email không hợp lệ!");
                            System.out.println("Ví dụ: example@gmail.com");
                            continue;
                        }
                        break;
                    }  
                    for (nhanVien X : a) {
                        if (X.getemail().toLowerCase().contains(tuKhoa.toLowerCase())) {
                            ketQuaTimKiem.add(X);
                        }
                    }
                    break;
                case 4:
                    while (true){
                        System.out.println("Nhập SDT của nhân viên: ");
                        tuKhoa = sc.nextLine().trim();
                        if (tuKhoa.isEmpty()){
                            System.out.println("Số điện thoại không được trống ");
                        }
                        if (!tuKhoa.matches("0\\d{9}")) {
                            System.out.println("SDT phải đúng hình thức (10 số, bắt đầu bằng 0)!");
                            continue;
                        }
                        break;
                    }
                    for (nhanVien X : a) {
                        if (X.getsdt().contains(tuKhoa)) {
                            ketQuaTimKiem.add(X);
                        }
                    }
                    break;
                case 5:
                    System.out.print("Nhập loại nhân viên (PT - Part Time / FT - Full Time): ");
                    tuKhoa = sc.nextLine().trim().toUpperCase();

                    if (!tuKhoa.equals("PT") && !tuKhoa.equals("FT")) {
                        System.out.println("Loại nhân viên không hợp lệ! Chỉ nhập PT hoặc FT.");
                        continue;
                    }

                    for (nhanVien X : a) {
                        if (X.getid().toUpperCase().startsWith(tuKhoa)) {
                            ketQuaTimKiem.add(X);
                        }
                    }
                    break;
                case 0:
                    System.out.println("Thoát tìm kiếm.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
                    continue;
            }
            if (chon >= 1 && chon <= 5) {
                if (ketQuaTimKiem.isEmpty()) {
                    System.out.println("Không tìm thấy nhân viên nào phù hợp!");
                } else {
                    System.out.println("\n=== KẾT QUẢ TÌM KIẾM ===");
                    System.out.println("Tìm thấy " + ketQuaTimKiem.size() + " nhân viên:");
                    for (int i = 0; i < ketQuaTimKiem.size(); i++) {
                        System.out.println((i + 1) + ". " + ketQuaTimKiem.get(i));
                        System.out.println("--------------------------------------");
                    }
                }

                System.out.print("\nNhấn Enter để tiếp tục...");
                sc.nextLine();
            }
        } while (chon != 0);
    }
    public void xoaNhanVien() {
        Scanner sc = new Scanner(System.in);
        int chon;

        do {
            System.out.println("\n========== MENU XÓA ==========");
            System.out.println("1. Xóa theo mã nhân viên");
            System.out.println("2. Xóa theo tên nhân viên");
            System.out.println("3. Xóa theo email");
            System.out.println("4. Xóa theo số điện thoại");
            System.out.println("0. Quay lại");
            System.out.println("==========================================");
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

            ArrayList<nhanVien> ketQuaTimKiem = new ArrayList<>();
            String tuKhoa = "";

            switch (chon) {
                case 1:
                    System.out.print("Nhập mã nhân viên cần xóa: ");
                    tuKhoa = sc.nextLine().trim();
                    for (nhanVien nv : a) {
                        if (nv.getid().equalsIgnoreCase(tuKhoa)) {
                            ketQuaTimKiem.add(nv);
                        }
                    }
                    break;

                case 2:
                    System.out.print("Nhập tên nhân viên cần xóa: ");
                    tuKhoa = sc.nextLine().trim();
                    for (nhanVien nv : a) {
                        if (nv.getten().toLowerCase().contains(tuKhoa.toLowerCase())) {
                            ketQuaTimKiem.add(nv);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Nhập email nhân viên cần xóa: ");
                    tuKhoa = sc.nextLine().trim();
                    for (nhanVien nv : a) {
                        if (nv.getemail().toLowerCase().contains(tuKhoa.toLowerCase())) {
                            ketQuaTimKiem.add(nv);
                        }
                    }
                    break;

                case 4:
                    System.out.print("Nhập số điện thoại nhân viên cần xóa: ");
                    tuKhoa = sc.nextLine().trim();
                    for (nhanVien nv : a) {
                        if (nv.getsdt().contains(tuKhoa)) {
                            ketQuaTimKiem.add(nv);
                        }
                    }
                    break;

                case 0:
                    System.out.println("Đã hủy thao tác xóa.");
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
                    continue;
            }

            if (chon >= 1 && chon <= 4) {
                if (ketQuaTimKiem.isEmpty()) {
                    System.out.println("Không tìm thấy nhân viên nào phù hợp!");
                } else {
                    System.out.println("\n=== KẾT QUẢ TÌM KIẾM ===");
                    for (int i = 0; i < ketQuaTimKiem.size(); i++) {
                        System.out.println((i + 1) + ". " + ketQuaTimKiem.get(i));
                    }
                }

                System.out.print("\nNhập số thứ tự nhân viên cần xóa (0 để hủy): ");
                int luaChonXoa;
                try {
                    luaChonXoa = sc.nextInt();
                    sc.nextLine();
                } catch (Exception e) {
                    System.out.println("Lựa chọn không hợp lệ!");
                    sc.nextLine();
                    continue;
                }

                if (luaChonXoa == 0) {
                    System.out.println("Đã hủy xóa.");
                    continue;
                }

                if (luaChonXoa < 1 || luaChonXoa > ketQuaTimKiem.size()) {
                    System.out.println("Số thứ tự không hợp lệ!");
                    continue;
                }

                nhanVien nvCanXoa = ketQuaTimKiem.get(luaChonXoa - 1);
                System.out.println("\nThông tin nhân viên cần xóa:");
                System.out.println(nvCanXoa);

                System.out.print("Bạn có chắc muốn xóa nhân viên này không? (y/n): ");
                String confirm = sc.nextLine();
                if (confirm.equalsIgnoreCase("y")) {
                    a.remove(nvCanXoa);
                    System.out.println("Đã xóa nhân viên khỏi danh sách!");
                    return;
                } else {
                    System.out.println("Hủy xóa nhân viên.");
                }
            }
            } while (chon != 0);
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
    public void thongKe() {
        if (a.isEmpty()) {
            System.out.println("Danh sách nhân viên trống!");
            return;
        }
        nhanVien.thongKeNV(a);
    }
}
