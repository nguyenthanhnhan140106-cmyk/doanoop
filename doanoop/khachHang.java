package doanoop;

public class khachHang extends conNguoi{
    protected String ngayMuaHang;
    public khachHang(String id,  String ten, String email, String sdt){
        super(id,ten,email,sdt);
    }
    @Override
    public String toString() {
        return "Khách hàng: " + ten + " (" + sdt + "||" +email + ")";
    }
}
