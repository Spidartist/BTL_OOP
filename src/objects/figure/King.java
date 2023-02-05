package objects.figure;


public class King extends HistoricalFigure {
	private String paperURL;
	private String mieuHieu;
	private String thuyHieu;
	private String nienHieu;
	private String tenHuy;
	private String theThu;
	private String namTriVi;

	public King(String ten, String namSinh, String namMat, String paperURL, String mieuHieu, String thuyHieu,
			String nienHieu, String tenHuy, String theThu, String namTriVi) {
		super(ten, namSinh, namMat);
		this.paperURL = paperURL;
		this.mieuHieu = mieuHieu;
		this.thuyHieu = thuyHieu;
		this.nienHieu = nienHieu;
		this.tenHuy = tenHuy;
		this.theThu = theThu;
		this.namTriVi = namTriVi;
	}

	public King(String paperURL, String mieuHieu, String thuyHieu,
			String nienHieu, String tenHuy, String theThu, String namTriVi, String ten) {
		super(ten);
		this.paperURL = paperURL;
		this.mieuHieu = mieuHieu;
		this.thuyHieu = thuyHieu;
		this.nienHieu = nienHieu;
		this.tenHuy = tenHuy;
		this.theThu = theThu;
		this.namTriVi = namTriVi;
	}

	public King(String ten) {
		super(ten);
	}

	public King() {
	}

	public String getPaperURL() {
		return paperURL;
	}

	public void setPaperURL(String paperURL) {
		this.paperURL = paperURL;
	}

	public String getMieuHieu() {
		return mieuHieu;
	}

	public void setMieuHieu(String mieuHieu) {
		this.mieuHieu = mieuHieu;
	}

	public String getThuyHieu() {
		return thuyHieu;
	}

	public void setThuyHieu(String thuyHieu) {
		this.thuyHieu = thuyHieu;
	}

	public String getNienHieu() {
		return nienHieu;
	}

	public void setNienHieu(String nienHieu) {
		this.nienHieu = nienHieu;
	}

	public String getTenHuy() {
		return tenHuy;
	}

	public void setTenHuy(String tenHuy) {
		this.tenHuy = tenHuy;
	}

	public String getTheThu() {
		return theThu;
	}

	public void setTheThu(String theThu) {
		this.theThu = theThu;
	}

	public String getNamTriVi() {
		return namTriVi;
	}

	public void setNamTriVi(String namTriVi) {
		this.namTriVi = namTriVi;
	}

}
