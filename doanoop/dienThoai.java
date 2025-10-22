package doanoop;
public class dienThoai extends hangHoa implements thanhToan{
    private String noiSanXuat;
    public dienThoai( String maHang,String tenSP, String ngayNhap, double donGia, double soLuong, String nsx ){
        super(maHang, tenSP, ngayNhap, donGia, soLuong, nsx);
    }
    @Override
    public double tinhThanhTien(){
        if (nsx.equalsIgnoreCase("vietnam"))
        return soLuong*donGia*(1+VAT);
        else return soLuong*donGia*(1.2+VAT);
    }
}
