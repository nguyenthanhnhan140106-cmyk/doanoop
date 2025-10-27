package doanoop;
import java.util.ArrayList;
public abstract class nhanVien extends conNguoi{
    public nhanVien(String id, String ten, String email, String sdt){
        super(id,ten,email,sdt);
    }
    public String toString() {
        return "MãNV: " + id + " || Tên: " + ten + " || Email: " + email + " || SĐT: " + sdt;
    }
    public static int countFullTime(ArrayList<nhanVien> list){
        int count=0;
        for (nhanVien nv:list){
            if (nv instanceof nvFulltime){
                count++;
            }
        }
        return count;
    }
    public static int countPartTime(ArrayList<nhanVien> list){
        int count=0;
        for (nhanVien nv:list){
            if (nv instanceof nvParttime){
                count++;
            }
        }
        return count;
    }
    public static void thongKeNV(ArrayList<nhanVien> list){
        System.out.println("\n=== THỐNG KÊ NHÂN VIÊN ===");
        System.out.println("Tổng số nhân viên: " + list.size());
        System.out.println("Nhân viên Full-time: " + countFullTime(list));
        System.out.println("Nhân viên Part-time: " + countPartTime(list));
        System.out.println("==========================\n");
    }
}
