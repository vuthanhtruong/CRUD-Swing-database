package topica.edu.vn;

public abstract class sinhvien implements TinhDiem{
	public String Id;
	public String Ten;
	public String GioiTinh;
	public float DiemToan;
	public float DiemVan;
	public float DiemAnh;
	public String getTen() {
		return Ten;
	}
	public void setTen(String ten) {
		Ten = ten;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getGioiTinh() {
		return GioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		GioiTinh = gioiTinh;
	}
	public float getDiemToan() {
		return DiemToan;
	}
	public void setDiemToan(float diemToan) {
		DiemToan = diemToan;
	}
	public float getDiemVan() {
		return DiemVan;
	}
	public void setDiemVan(float diemVan) {
		DiemVan = diemVan;
	}
	public float getDiemAnh() {
		return DiemAnh;
	}
	public void setDiemAnh(float diemAnh) {
		DiemAnh = diemAnh;
	}

	public sinhvien(String id, String ten, String gioiTinh, float diemToan, float diemVan, float diemAnh) {
		super();
		Id = id;
		Ten = ten;
		GioiTinh = gioiTinh;
		DiemToan = diemToan;
		DiemVan = diemVan;
		DiemAnh = diemAnh;
	}
	public sinhvien() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ID: " + Id + ", Ten: " + Ten + ", GioiTinh: " + GioiTinh +
                ", DiemToan: " + DiemToan + ", DiemVan: " + DiemVan + ", DiemAnh: " + DiemAnh + "\n";
	}
	@Override
	public float DiemTrungBinh() {
		// TODO Auto-generated method stub
		return this.DiemToan+this.DiemVan+this.DiemAnh;
	}
	public abstract String XepLoai();

}
