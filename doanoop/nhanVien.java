package doanoop;

public abstract class nhanVien extends conNguoi{
    public nhanVien(String id, String ten, String email, String sdt){
        super(id,ten,email,sdt);
    }
    public String toString() {
        return "MãNV: " + id + "|| Tên: " + ten + "|| Email: " + email + "|| SĐT: " + sdt;
    }
}
