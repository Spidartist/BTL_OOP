package objects.festival;

import objects.figure.HistoricalFigure;

public class Festival {
	private String tenLeHoi;
	private String thoigian;
	private String diaDiem;
	private String figure;
	private String noiDung;
	public Festival(String tenLehoi,String thoiGian, String diaDiem) {
		this.tenLeHoi = tenLehoi;
		this.thoigian = thoiGian;
		this.diaDiem = diaDiem;
	}
	public String getTenLeHoi() {
		return tenLeHoi;
	}
	public void setTenLeHoi(String tenLeHoi) {
		this.tenLeHoi = tenLeHoi;
	}
	public String getThoigian() {
		return thoigian;
	}
	public void setThoigian(String thoigian) {
		this.thoigian = thoigian;
	}
	public String getDiaDiem() {
		return diaDiem;
	}
	public void setDiaDiem(String diaDiem) {
		this.diaDiem = diaDiem;
	}
	public String getFigure() {
		return figure;
	}
	public void setFigure(String figure) {
		this.figure = figure;
	}
	public String getNoiDung() {
		return noiDung;
	}
	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}
	
}
