/**
 * @author <Borislav Milijic>
 * Matrikelnummer: 01249658
 */
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public abstract class Wohnung implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static DecimalFormat getDecimalFormat() {
		DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance();
		dfs.setDecimalSeparator('.');
		return new DecimalFormat("0.00", dfs);
	}
	
	private int Id;
	private double Flaeche;
	private int Zimmer;
	private int Stock;
	private int Baujahr;
	private int PLZ;
	private String Strasse;
	private int Hausnummer;
	private int Top;
	
	public Wohnung (int Id, double Flaeche, int Zimmer, int Stock, int Baujahr, int PLZ, String Strasse, int Hausnummer, int Top) {
		this.Id = Id;
		this.Flaeche = Flaeche;
		this.Zimmer = Zimmer;
		this.Stock = Stock;
		this.Baujahr = Baujahr;
		this.PLZ = PLZ;
		this.Strasse = Strasse;
		this.Hausnummer = Hausnummer;
		this.Top = Top;
	}

	public int alter() {
		return 2018 - Baujahr;
	}
	
	abstract public double gesamtKosten();
	
	public int getId() {
		return Id;
	}

	public double getFlaeche() {
		return Flaeche;
	}

	public int getZimmer() {
		return Zimmer;
	}

	public int getStock() {
		return Stock;
	}

	public int getBaujahr() {
		return Baujahr;
	}

	public int getPLZ() {
		return PLZ;
	}

	public String getStrasse() {
		return Strasse;
	}

	public int getHausnummer() {
		return Hausnummer;
	}

	public int getTop() {
		return Top;
	}

}