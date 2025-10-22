package doanoop;

public class quanlyChucNang implements chucNang{

    DanhSachHangHoa a=new DanhSachHangHoa();
    DanhSachNhanVien b=new DanhSachNhanVien();
    muaHang c=new muaHang();
    @Override
    public void themDShanghoa() {
        a.themDSHangHoa();
    }

    @Override
    public void suaDSsanpham() {
        a.suaDSsanpham();
    }

    @Override
    public void timKiemSanPham() {
        a.timKiemSanPham();
    }

    @Override
    public void xoaSanPham() {
        a.xoaSanPham();
    }

    @Override
    public void themNhanVien() {
        b.themDSNhanVien();
    }

    @Override
    public void suaDSNhanVien() {
        b.suaDSNhanVien();
    }

    @Override
    public void timKiemNhanVien() {
        b.timKiemNhanVien();
    }

    @Override
    public void xoaNhanVien() {
        b.xoaNhanVien();
    }
    @Override
    public void muaHang() {
        c.muaHang();
    }

    @Override
    public void xemHoaDon() {
        c.xuatHoaDon();
    }

}
