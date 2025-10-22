package doanoop;

public class nvParttime extends nhanVien{
    public nvParttime(String id, String ten, String email, String sdt, int soGioLam){
        super(id,ten,email,sdt,soGioLam);
    }
    @Override
    public double tinhLuong(){
        return 25*soGioLam;
    }
}
