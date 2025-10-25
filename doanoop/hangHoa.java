package doanoop;

public class hangHoa {
    protected String maHang, tenSP ,ngayNhap;
    protected double donGia, soLuong;
    protected String nsx;
    public hangHoa( String maHang, String tenSP, String ngayNhap, double donGia, double soLuong, String nsx){
        this.maHang=maHang;
        this.tenSP=tenSP;
        this.ngayNhap=ngayNhap;
        this.donGia=donGia;
        this.soLuong=soLuong;
        this.nsx=nsx;
    }

    public hangHoa() {
    }

    public String getmaHang(){
        return maHang;
    }
    public void setmaHang(String maHang){
        this.maHang=maHang;
    }
    public String getTenSP(){
        return tenSP;
    }
    public void settenSP(String tenSP){
        this.tenSP=tenSP;
    }
    public String getngayNhap(){
        return ngayNhap;
    }
    public void setngayNhap(String ngayNhap){
        this.ngayNhap=ngayNhap;
    }
    public double getdonGia(){

        return donGia;
    }
    public void setdonGia(double donGia){
        this.donGia=donGia;
    }
    public double getsoLuong(){
        return soLuong;
    }
    public void setsoLuong( double soLuong){
        this.soLuong=soLuong;
    }
    public String getnsx(){
        return nsx;
    }
    public void setnsx( String nsx){
        this.nsx=nsx;
    }
    @Override
    public String toString(){
        return "Mã Hàng: "+maHang+"|| Tên SP: "+tenSP+"|| Ngày Nhập: "+ngayNhap+"|| Đơn giá: "+donGia+"|| Số Lượng: "+soLuong+ "|| Nơi sản xuất: "+nsx;
    }
}
