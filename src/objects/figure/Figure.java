package objects.figure;

public class Figure extends HistoricalFigure {
	private String queQuan ;
	private String danToc;
	private String namNhapNgu;
	private String ghiChu;
	private String namDoTrangNguyen;
	private King doiVua;
	public String getNamDoTrangNguyen() {
		return namDoTrangNguyen;
	}
	public void setNamDoTrangNguyen(String namDoTrangNguyen) {
		this.namDoTrangNguyen = namDoTrangNguyen;
	}
	public King getDoiVua() {
		return doiVua;
	}
	public void setDoiVua(King doiVua) {
		this.doiVua = doiVua;
	}
	public Figure(String ten) {
		super(ten);
	}
	
	public Figure(String ten,String namSinh, String namMat) {
		super(ten,namSinh,namMat);
	}
	public String getQueQuan() {
		return queQuan;
	}
	public void setQueQuan(String queQuan) {
		this.queQuan = queQuan;
	}
	public String getDanToc() {
		return danToc;
	}
	public void setDanToc(String danToc) {
		this.danToc = danToc;
	}
	public String getNamNhapNgu() {
		return namNhapNgu;
	}
	public void setNamNhapNgu(String namPhongChuc) {
		this.namNhapNgu = namPhongChuc;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	
}
