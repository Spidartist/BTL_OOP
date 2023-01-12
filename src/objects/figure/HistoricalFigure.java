package objects.figure;

public abstract class HistoricalFigure {
	protected String namSinh;
	protected String namMat;
	protected String ten;
	protected HistoricalFigure(String ten, String namSinh, String namMat) {
		this.namMat = namMat;
		this.namSinh = namSinh;
		this.ten = ten;
	}
	
	public HistoricalFigure(String ten) {
		this.ten = ten;
	}
	public String getNamSinh() {
		return namSinh;
	}
	public void setNamSinh(String namSinh) {
		this.namSinh = namSinh;
	}
	public String getNamMat() {
		return namMat;
	}
	public void setNamMat(String namMat) {
		this.namMat = namMat;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	
	
}

