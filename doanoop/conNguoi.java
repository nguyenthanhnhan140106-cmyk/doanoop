package doanoop;

public class conNguoi {
    protected String id,ten,email,sdt;
    public conNguoi( String id, String ten, String email,String sdt){
        this.id=id;
        this.ten=ten;
        this.email=email;
        this.sdt=sdt;
    }

    public conNguoi() {
    }

    public String getid(){
        return id;
    }
    public void setid(String id){
        this.id=id;
    }
    public String getten(){
        return ten;
    }
    public void setten(String ten){
        this.ten=ten;
    }
    public String getemail(){
        return email;
    }
    public void setemail(String email){
        this.email=email;
    }
    public String getsdt(){
        return sdt;
    }
    public void setsdt(String sdt){
        this.sdt=sdt;
    }
}
