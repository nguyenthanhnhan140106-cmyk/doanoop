package doanoop;

public abstract class nhanVien extends conNguoi{
    protected int soGioLam;
    public nhanVien(String id, String ten, String email, String sdt, int soGioLam ){
        super(id,ten,email,sdt);
        this.soGioLam=soGioLam;
    }
    public double getsoGioLam(){
        return soGioLam;
    }
    public void setsoGioLam(int soGioLam ){
        this.soGioLam=soGioLam;
    }
    public abstract double tinhLuong();
    public String toString() {
        return "MãNV: " + id + ", Tên: " + ten + ", Email: " + email + ", SĐT: " + sdt + ", SốGiờLàm: " + soGioLam + ", Lương: " + tinhLuong();
    }
}
