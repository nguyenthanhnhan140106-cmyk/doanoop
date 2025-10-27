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
    private boolean leapYear(int year){
        return (year % 4 ==0 && year % 100 != 0) || (year % 400 ==0);
    }
    private boolean checkDate(String date){
        if (!date.matches("\\d{4}-\\d{2}-\\d{2}")){
            return false;
        }
        try{
            String[] parts= date.split("-");
            int year= Integer.parseInt(parts[0]);
            int month= Integer.parseInt(parts[1]);
            int day= Integer.parseInt(parts[2]);
            if (year <1) return false;
            // 1-12 in month
            if (month <1 || month >12){
                return false;
            }
            int[] daysInMonth={31,28,31,30,31,30,31,31,30,31,30,31};
            if (leapYear(year)){
                daysInMonth[1]=29;
            }
            // 1-31, if Feb then 1-28 (leap year is 1-29)
            if (day <1 || day> daysInMonth[month-1]){
                return false;
            }

            return true;
        } catch (Exception e){
            return false;
        }
    }

    public void themDSHangHoa(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Nếu là Điện Thoại thì mã sản phẩm bắt đầu là DT, Laptop là LT, Loa là LO! ");
        String maHang="";
        while (true){
            System.out.println("Nhập mã sản phẩm: ");
            maHang = sc.nextLine().toUpperCase().trim();
            if (maHang.isEmpty() || maHang == null){
                System.out.println("Mã sản phẩm không được để trống!");
                continue;
            }
            if (!maHang.matches("[A-Z0-9]+")){
                System.out.println("Mã sản phẩm không được có kí tự đặc biệt!");
                continue;
            }
            
            if (!maHang.startsWith("DT") && !maHang.startsWith("LT") && !maHang.startsWith("LO")){
                System.out.println("Mã phải là DT, LT hoặc LO!");
                continue;
            }
            boolean exists=false;
            for (hangHoa hh:a){
                if (hh.getmaHang().equalsIgnoreCase(maHang)){
                    exists=true;
                    break;
                }
            }
            if (exists){
                System.out.println("Tồn tại mã sản phẩm "+maHang+", Vui lòng nhập lại!");
                continue;
            }
            break;
        }

        String tenSp="";
        while(true){
            System.out.println("Nhập tên sản phẩm: ");
            tenSp=sc.nextLine().trim();
            if (tenSp.isEmpty()){
                System.out.println("San pham khong duoc de trong");
                continue;
            }
            break;
        }

        String ngayNhap="";
        while (true){
            System.out.println("Nhập ngày nhập sản phẩm (YYYY-MM-DD): ");
            ngayNhap=sc.nextLine().trim();
            if (ngayNhap.isEmpty()){
                System.out.println("Ngày nhập không được trống!");
                continue;
            }
            if (checkDate(ngayNhap)){
                break;
            }
            else{
                System.out.println("Ngày nhập không đúng định dạng!");
            }
            
        }
        

        double donGia = 0;
        while (true) {
            try {
                System.out.print("Nhập giá tiền: ");
                String input = sc.nextLine().trim();
                if (input == null){
                    System.out.println("Giá tiền không được trống!");
                    continue;
                }
                donGia = Double.parseDouble(input);
                
                if (donGia <= 0) {
                    System.out.println("Giá tiền phải lớn hơn 0!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Giá tiền phải là số! Vui lòng nhập lại.");
            }
        }

        
        double soluong = 0;
        while (true) {
            try {
                System.out.print("Nhập số lượng sản phẩm: ");
                String input = sc.nextLine().trim();
                soluong = Double.parseDouble(input);
                if (input.isEmpty()){
                    System.out.println("Số lượng không được trống");
                    continue;
                }
                
                
                if (soluong <= 0) {
                    System.out.println("Số lượng phải lớn hơn 0!");
                    continue;
                }
                break; 
            } catch (NumberFormatException e) {
                System.out.println("Số lượng phải là số! Vui lòng nhập lại.");
            }
        }
        String nsx="";
        while (true){
            System.out.println("Nhập quốc gia sản xuất: ");
            nsx=sc.nextLine().trim();
            if (nsx.isEmpty()){
                System.out.println("Không được trống nơi quốc gia sản xuất");
                continue;
            }
            if (!nsx.matches("[A-Za-z]+")){
                System.out.println("Quốc gia sản xuất chỉ sử dụng kí tự chữ");
                continue;
            }
            break;
        }

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
        String maHang="";
        while(true){
            System.out.println("Nhập mã sản phẩm cần sửa: ");
            maHang = sc.nextLine();
            if (maHang.isEmpty()){
                System.out.println("không được trống mã hàng");
                continue;
            }
            String ma = maHang.toUpperCase().trim();
            if (!ma.matches("[A-Z0-9]+")){
                System.out.println("Mã sản phẩm không được có kí tự đặc biệt!");
                continue;
            }
            if (!ma.startsWith("DT") && !ma.startsWith("LT") && !ma.startsWith("LO")) {
                System.out.println("Lưu ý: Mã hàng phải bắt đầu bằng DT (Điện thoại), LT (Laptop), hoặc LO (Loa)!");
                continue;
            }
            break;
        }
        hangHoa hh=null;
        for (hangHoa x:a){
            if (x.getmaHang().equalsIgnoreCase(maHang)){
                hh = x;
                break;
            }
        }
        if (hh == null){
            System.out.println("Không tồn tại mã sản phẩm "+maHang+", Vui lòng nhập lại!");
            return;
        }
        System.out.println("\nThông tin sản phẩm hiện tại:");
        System.out.println(hh);

        int chon;
        do {
            System.out.println("\n========== MENU CHỈNH SỬA ==========");
            System.out.println("1. Sửa Tên sản phẩm");
            System.out.println("2. Sửa Ngày nhập");
            System.out.println("3. Sửa Đơn giá");
            System.out.println("4. Sửa Số lượng");
            System.out.println("5. Sửa Nơi sản xuất");
            System.out.println("6. Sửa tất cả thông tin");
            System.out.println("0. Lưu và thoát");
            System.out.println("====================================");
            System.out.print("Nhập lựa chọn: ");

            try {
                chon = sc.nextInt();
                sc.nextLine();
            }catch (Exception e) {
                System.out.println("Vui lòng nhập số!");
                sc.nextLine();
                chon = -1;
                continue;
            }
            switch (chon){
                case 1:
                    String tenSp="";
                    while(true){
                        System.out.println("Nhập tên mới của sản phẩm: ");
                        tenSp = sc.nextLine().trim();
                            if (tenSp.isEmpty() || tenSp == null){
                            System.out.println("Tên sản phẩm không được để trống!");
                            continue;
                        }
                        break;
                    }
                    hh.settenSP(tenSp);
                    System.out.println("Đã sửa tên sp!");
                    break;
                case 2:
                    String ngayNhap="";
                        while (true){
                            System.out.println("Nhập ngày nhập sản phẩm (YYYY-MM-DD): ");
                            ngayNhap=sc.nextLine().trim();
                            if (ngayNhap.isEmpty()){
                                System.out.println("Ngày nhập không được trống");
                                continue;
                            }
                            if (checkDate(ngayNhap)) break;
                            else{
                                System.out.println("Ngày nhập không hợp lệ!");
                            }
                        }
                    hh.setngayNhap(ngayNhap);
                    System.out.println("Đã sửa ngày nhập!");
                    break;
                case 3:
                    double donGia=0;
                    while (true){
                        try{
                            System.out.println("Nhập đơn giá sản phẩm: ");
                            String input = sc.nextLine().trim();
                            donGia = Double.parseDouble(input);

                            if (input.isEmpty() || input == null){
                                System.out.println("Đơn giá sản phẩm không được để trống!");
                                continue;
                            }

                            donGia = Double.parseDouble(input);

                            if (donGia <= 0) {
                                System.out.println("Đơn giá phải lớn hơn 0!");
                                continue;
                            }
                            break;

                        } catch(NumberFormatException e){
                            System.out.println("Đơn giá không hợp lệ! Vui lòng nhập lại.");
                        }
                    }
                    hh.setdonGia(donGia);
                    System.out.println("Đã cập nhật đơn giá!");
                    break;
                case 4:
                double soLuong = 0;
                    while (true) {
                        try {
                            System.out.println("Nhập số lượng sản phẩm: ");
                            String input = sc.nextLine().trim();
                            soLuong = Double.parseDouble(input);

                            if (input.isEmpty() || input == null){
                                System.out.println("Đơn giá sản phẩm không được để trống!");
                                continue;
                            }

                            soLuong = Double.parseDouble(input);

                            if (soLuong <= 0) {
                                System.out.println("Số lượng phải lớn hơn 0!");
                                continue;
                            }
                            break;

                        } catch (NumberFormatException e) {
                            System.out.println("Số lượng không hợp lệ! Vui lòng nhập lại.");
                        }
                    }
                    hh.setsoLuong(soLuong);
                    System.out.println("Đã cập nhật số lượng!");
                    break;

                case 5:
                    String nsx="";
                    while (true){
                        System.out.println("Nhập nơi sản xuất sản phẩm: ");
                        nsx = sc.nextLine().trim();
                        if (nsx.isEmpty() || nsx == null){
                            System.out.println("Nơi sản xuất không được để trống! Vui lòng nhập lại.");
                            continue;
                        }
                        break;
                    }
                    hh.setnsx(nsx);
                    System.out.println("Đã cập nhật nơi sản xuất!");
                    break;

                case 6:
                    System.out.println("--- Nhập tất cả thông tin mới ---");

                    tenSp="";
                    while (true){
                        System.out.println("Nhập tên sản phẩm: ");
                        tenSp = sc.nextLine().trim();
                            if (tenSp.isEmpty() || tenSp == null){
                            System.out.println("Tên sản phẩm không được để trống!");
                            continue;
                        }
                        break;
                    }

                    ngayNhap="";
                    while (true){
                            System.out.println("Nhập ngày nhập sản phẩm (YYYY-MM-DD): ");
                            ngayNhap=sc.nextLine().trim();
                            if (ngayNhap.isEmpty()){
                                System.out.println("Ngày nhập không được trống");
                                continue;
                            }
                            if (checkDate(ngayNhap)) break;
                            else{
                                System.out.println("Ngày nhập không hợp lệ!");
                            }
                        }
                    

                    donGia=0;
                    while (true) {
                        try{
                            System.out.println("Nhập đơn giá sản phẩm: ");
                            String input = sc.nextLine().trim();
                            donGia = Double.parseDouble(input);

                            if (input.isEmpty() || input == null){
                                System.out.println("Đơn giá sản phẩm không được để trống!");
                                continue;
                            }

                            donGia = Double.parseDouble(input);

                            if (donGia <= 0) {
                                System.out.println("Đơn giá phải lớn hơn 0!");
                                continue;
                            }
                            break;

                        } catch(NumberFormatException e){
                            System.out.println("Đơn giá không hợp lệ! Vui lòng nhập lại.");
                        }
                    }

                    soLuong = 0;
                    while (true) {
                        try {
                            System.out.println("Nhập số lượng sản phẩm: ");
                            String input = sc.nextLine().trim();
                            soLuong = Double.parseDouble(input);

                            if (input.isEmpty() || input == null){
                                System.out.println("Đơn giá sản phẩm không được để trống!");
                                continue;
                            }

                            soLuong = Double.parseDouble(input);

                            if (soLuong <= 0) {
                                System.out.println("Số lượng phải lớn hơn 0!");
                                continue;
                            }
                            break;

                        } catch (NumberFormatException e) {
                            System.out.println("Số lượng không hợp lệ! Vui lòng nhập lại.");
                        }
                    }

                    nsx="";
                    while (true){
                        System.out.println("Nhập nơi sản xuất sản phẩm: ");
                        nsx = sc.nextLine().trim();
                        if (nsx.isEmpty() || nsx == null){
                            System.out.println("Nơi sản xuất không được để trống! Vui lòng nhập lại.");
                            continue;
                        }
                        break;
                    }

                    hh.settenSP(tenSp);
                    hh.setngayNhap(ngayNhap);
                    hh.setdonGia(donGia);
                    hh.setsoLuong(soLuong);
                    hh.setnsx(nsx);

                    System.out.println("Đã cập nhật tất cả thông tin!");
                    break;

                case 0:
                    System.out.println("Đã lưu các thay đổi!");
                    System.out.println("\nThông tin sản phẩm sau khi sửa:");
                    System.out.println(hh);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (chon != 0);
    }   
    public hangHoa timKiemMa(String ma){
        for (hangHoa x :a ){
            if (x.getmaHang().equalsIgnoreCase(ma)){
                return x;
            }
        } return null;
    }
    public void timKiemSanPham() {
        Scanner sc = new Scanner(System.in);
        int chon;
        do{
            System.out.println("\n========== MENU TÌM KIẾM ==========");
            System.out.println("1. Tìm kiếm theo mã sản phẩm");
            System.out.println("2. Tìm kiếm theo tên sản phẩm");
            System.out.println("3. Tìm kiếm theo ngày nhập");
            System.out.println("4. Tìm kiếm theo đơn giá");
            System.out.println("5. Tìm kiếm theo số lượng");
            System.out.println("6. Tìm kiếm theo nơi sản xuất");
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
            ArrayList<hangHoa> ketQuaTimKiem = new ArrayList<>();
            String tuKhoa = "";
            switch (chon) {
                case 1:
                    while (true){
                        System.out.println("Nhập mã sản phẩm: ");
                        tuKhoa = sc.nextLine().trim();
                        if (tuKhoa.isEmpty() || tuKhoa == null){
                            System.out.println("Mã sản phẩm không được để trống!");
                            continue;
                        }

                        String ma = tuKhoa.toUpperCase().trim();
                        if (!ma.matches("[A-Z0-9]+")){
                            System.out.println("Mã sản phẩm không được có kí tự đặc biệt!");
                            continue;
                        }
                        if (!ma.startsWith("DT") && !ma.startsWith("LT") && !ma.startsWith("LO")) {
                            System.out.println("Lưu ý: Mã hàng phải bắt đầu bằng DT (Điện thoại), LT (Laptop), hoặc LO (Loa)!");
                            continue;
                        }
                        break;
                    }

                    hangHoa hh = null;

                    for (hangHoa X : a) {
                        if (X.getmaHang().equals(tuKhoa)) {
                            hh=X;
                            break;
                        }
                    }

                    if (hh == null){
                        System.out.println("Mã sản phẩm không tồn tại! Vui lòng nhập lại ");
                        return;
                    }

                    for (hangHoa X : a) {
                        if (X.getmaHang().equalsIgnoreCase(tuKhoa)) {
                            ketQuaTimKiem.add(X);
                        }
                    }
                    break;
                case 2:
                        while (true){
                            System.out.println("Nhập tên sản phẩm: ");
                            tuKhoa = sc.nextLine().trim();
                            if (tuKhoa.isEmpty() || tuKhoa == null){
                                System.out.println("Tên sản phẩm không được để trống!");
                                continue;
                            }
                            break;
                        }

                        if (tuKhoa.matches(".*\\s{2,}.*")) {
                            System.out.println("Tên hàng hóa không được chứa nhiều khoảng trắng liên tiếp!");
                            continue;
                        }

                        for (hangHoa X : a) {
                            if (X.getTenSP().toLowerCase().contains(tuKhoa.toLowerCase())) {
                                ketQuaTimKiem.add(X);
                            }
                        }
                        break;

                case 3:
                    while (true){
                        System.out.println("Nhập ngày nhập sản phẩm (YYYY-MM-DD): ");
                        tuKhoa = sc.nextLine().trim();

                        if (tuKhoa.isEmpty()){
                            System.out.println("Ngày nhập không được để trống!");
                            continue;
                        }
                        if (checkDate(tuKhoa)){
                            break;
                        }
                        else{
                            System.out.println("Ngày nhập không hợp lệ! Vui lòng nhập lại.");
                        }
                    }
                    for (hangHoa X : a) {
                            if (X.getngayNhap().equals(tuKhoa)) {
                            ketQuaTimKiem.add(X);
                            }
                        }
                    break;

                        case 4:
                            double donGiaTimKiem = 0;
                            while (true) {
                                try {
                                    System.out.print("Nhập đơn giá cần tìm (VNĐ): ");
                                    String input = sc.nextLine().trim();
                                    
                                    // Ràng buộc: Không được để trống
                                    if (input == null || input.isEmpty()) {
                                        System.out.println("Đơn giá không được để trống!");
                                        continue;
                                    }
                                    
                                    donGiaTimKiem = Double.parseDouble(input);
                                    
                                    // Ràng buộc: Giá phải > 0
                                    if (donGiaTimKiem <= 0) {
                                        System.out.println("Đơn giá phải lớn hơn 0!");
                                        continue;
                                    }
                                    break;
                                } catch (NumberFormatException e) {
                                    System.out.println("Đơn giá phải là số!");
                                }
                            }
                                for (hangHoa X : a) {
                                    if (X.getdonGia() == donGiaTimKiem) {
                                        ketQuaTimKiem.add(X);
                                    }
                                }
                                break;
                        case 5:
                            double soLuongTimKiem = 0;
                            while (true) {
                                try {
                                    System.out.print("Nhập số lượng cần tìm: ");
                                    String input = sc.nextLine().trim();
                                    
                                    // Ràng buộc: Không được để trống
                                    if (input == null || input.isEmpty()) {
                                        System.out.println("Số lượng không được để trống!");
                                        continue;
                                    }
                                    
                                    soLuongTimKiem = Double.parseDouble(input);
                                    
                                    // Ràng buộc: Số lượng phải > 0
                                    if (soLuongTimKiem <= 0) {
                                        System.out.println("Số lượng phải lớn hơn 0!");
                                        continue;
                                    }
                                    if (soLuongTimKiem != Math.floor(soLuongTimKiem)) {
                                        System.out.println("Số lượng phải là số nguyên!");
                                            continue;
                                    }
                                    break;
                                    } catch (NumberFormatException e) {
                                        System.out.println("Số lượng phải là số!");
                                    }
                                }
                    
                    
                                for (hangHoa X : a) {
                                    if (X.getsoLuong() == soLuongTimKiem) {
                                        ketQuaTimKiem.add(X);
                                    }
                                }
                            break;
                        case 6:
                            while (true){
                                System.out.println("Nhập nơi sản xuất sản phẩm: ");
                                tuKhoa = sc.nextLine().trim();

                                if (tuKhoa.matches(".*\\s{2,}.*")) {
                                    System.out.println("Nơi sản xuất không được chứa nhiều khoảng trắng liên tiếp!");
                                    continue;
                                }

                                if (tuKhoa.isEmpty() || tuKhoa == null){
                                    System.out.println("Nơi sản xuất không được để trống! Vui lòng nhập lại.");
                                    continue;
                                }

                                for (hangHoa X : a) {
                                    if (X.getnsx().toLowerCase().contains(tuKhoa.toLowerCase())) {
                                        ketQuaTimKiem.add(X);
                                    }
                                }
                                break;
                            }
                            break;
                        case 0:
                            System.out.println("Quay lại menu chính...");
                            return;

                        default:
                            System.out.println("Lựa chọn không hợp lệ!");
                            continue;
            }
        if (chon >= 1 && chon <= 6) {
                    if (ketQuaTimKiem.isEmpty()) {
                        System.out.println("Không tìm thấy hàng hóa nào phù hợp!");
                    } else {
                        System.out.println("\n=== KẾT QUẢ TÌM KIẾM ===");
                        System.out.println("Tìm thấy " + ketQuaTimKiem.size() + " hàng hóa:");
                        for (int i = 0; i < ketQuaTimKiem.size(); i++) {
                            System.out.println((i + 1) + ". " + ketQuaTimKiem.get(i));
                            System.out.println("--------------------------------------");
                        }
                    }
                }

        } while(chon!=0);
    }
    public void xoaSanPham() {
        Scanner sc = new Scanner(System.in);
        
        String maHang ="";
        while (true){
            System.out.println("Nhập mã sản phẩm: ");
            maHang = sc.nextLine().trim();
            if (maHang.isEmpty() || maHang == null){
                System.out.println("Mã sản phẩm không được để trống!");
                continue;
            }

            String ma = maHang.toUpperCase().trim();
            if (!ma.startsWith("DT") && !ma.startsWith("LT") && !ma.startsWith("LO")) {
                System.out.println("Lưu ý: Mã hàng phải bắt đầu bằng DT (Điện thoại), LT (Laptop), hoặc LO (Loa)!");
                continue;
            }

            hangHoa hh = null;
            for (hangHoa x:a){
                if (x.getmaHang().equalsIgnoreCase(maHang)){
                    hh = x;
                    break;
                }
            }

            if (hh == null){
                System.out.println("Không tồn tại mã sản phẩm "+maHang+", Vui lòng nhập lại!");
                return;          
            }
            break;
        }

        boolean found = false;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).getmaHang().equalsIgnoreCase(maHang)) {
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
