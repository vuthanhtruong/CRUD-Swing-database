package topica.edu.vn;

public class people extends sinhvien {

	public people() {
		super();
		// TODO Auto-generated constructor stub
	}

	public people(String id, String ten, String gioiTinh, float diemToan, float diemVan, float diemAnh) {
		super(id, ten, gioiTinh, diemToan, diemVan, diemAnh);
		// TODO Auto-generated constructor stub
	}
	public String XepLoai() {
		if(super.DiemTrungBinh()>25) {
		return "PASS";
		}
		return "NOT PASS";
	}
	

}
