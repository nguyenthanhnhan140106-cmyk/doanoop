package doanoop;

public class nvFulltime extends nhanVien{
    public nvFulltime(String id, String ten, String email, String sdt, int soGioLam){
        super(id,ten,email,sdt,soGioLam);
    }
    @Override
    public double tinhLuong(){
        return 30*soGioLam;
    }
}