import java.util.ArrayList;
import java.text.DecimalFormat;

/**
 * @author <Borislav Milijic>
 * Matrikelnummer: 01249658
 */

public class Hausverwaltung {
	private HausverwaltungDAO hvDAO;
	
	public Hausverwaltung (String file_name) {
		hvDAO = new HausverwaltungSerializationDAO(file_name);
	}
	
	public String get_all_Wohnungen() {
		ArrayList<Wohnung> list = (ArrayList<Wohnung>) hvDAO.getWohnungen();
		String all_wohnungen = "";
		for (int i=0; i<list.size(); i++) {
			all_wohnungen += list.get(i).toString() + "\n";
		}
		return all_wohnungen;
	}
	
	public String get_one_Wohnung(int n) {
		try {
			if (hvDAO.getWohnungbyId(n) == null)
				throw new IllegalArgumentException("Error: Wohnung nicht vorhanden." );
		} catch (Exception e) {
			System.out.println("Error: Wohnung nicht vorhanden. (id =" + n + ")");
		}
		return hvDAO.getWohnungbyId(n) + "\n";
	}
	
	public void add_mw(int Id, double Flaeche, int Zimmer, int Stock, int Baujahr, int PLZ, String Strasse, int Hausnummer, int Top, double Mietkosten, int Anzahl_mieter) {
		hvDAO.saveWohnung(new MietWohnung(Id, Flaeche, Zimmer, Stock, Baujahr, PLZ, Strasse, Hausnummer, Top, Mietkosten, Anzahl_mieter));
	}
	
	public void add_ew(int Id, double Flaeche, int Zimmer, int Stock, int Baujahr, int PLZ, String Strasse, int Hausnummer, int Top, double Betriebskosten, double Reparatur_beitrag) {
		hvDAO.saveWohnung(new EigentumsWohnung(Id, Flaeche, Zimmer, Stock, Baujahr, PLZ, Strasse, Hausnummer, Top, Betriebskosten, Reparatur_beitrag));
	}
	
	public void delete_Wohnung(int id) {
		hvDAO.deleteWohnung(id);
	}
	
	public int count_Wohnung() {
		return hvDAO.getWohnungen().size();
	}
	
	public String durchschnitt_kosten() {
		double durch_kosten = 0;
		DecimalFormat df = Wohnung.getDecimalFormat();
		for (int i=0; i<count_Wohnung();i++) {
			durch_kosten += hvDAO.getWohnungen().get(i).gesamtKosten();
		}
		return df.format(durch_kosten/(double)count_Wohnung());	
	}
	
	public int count_mw() {
		int n = 0;
		for (int i=0; i<hvDAO.getWohnungen().size(); i++) {
			if (hvDAO.getWohnungen().get(i) instanceof MietWohnung)
				n++;
		}
		return n;
	}
	
	public int count_ew() {
		int n = 0;
		for (int i=0; i<hvDAO.getWohnungen().size(); i++) {
			if (hvDAO.getWohnungen().get(i) instanceof EigentumsWohnung)
				n++;
		}
		return n;
	}

	public String get_oldest() {
		String oldest = "";
		int compare = hvDAO.getWohnungen().get(0).alter();	
		for (int i = 1; i < count_Wohnung(); i++) {
			if (hvDAO.getWohnungen().get(i).alter() > compare) {
				compare = hvDAO.getWohnungen().get(i).alter();
			}
		}
		for (int i = 0; i < count_Wohnung(); i++) {
			if (hvDAO.getWohnungen().get(i).alter() == compare)
				oldest +="Id: " + hvDAO.getWohnungen().get(i).getId() + "\n";
		}
		return oldest;
	}
}