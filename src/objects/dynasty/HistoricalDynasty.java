package objects.dynasty;

import java.util.ArrayList;

public class HistoricalDynasty {
		private String nienDai;
		private ArrayList<String> nhanVat = new ArrayList<String>();
		public HistoricalDynasty(String nienDai) {
			this.nienDai = nienDai;
		}
		
		public String getNienDai() {
			return this.nienDai;
		}
		
		public ArrayList<String> getNhanVat(){
			return this.nhanVat;
		}
		public void setNhanVat(String nhanVat) {
			this.nhanVat.add(nhanVat);
		}
}
