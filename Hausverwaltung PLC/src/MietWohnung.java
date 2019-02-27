import java.text.DecimalFormat;

/**
 * @author <Borislav Milijic>
 * Matrikelnummer: 01249658
 */

public class MietWohnung extends Wohnung {
	
	public MietWohnung(int Id, double Flaeche, int Zimmer, int Stock, int Baujahr, int PLZ, String Strasse,
		int Hausnummer, int Top, double Mietkosten, int Anzahl_mieter) {
		super(Id, Flaeche, Zimmer, Stock, Baujahr, PLZ, Strasse, Hausnummer, Top);
		this.Anzahl_mieter = Anzahl_mieter;
		this.Mietkosten = Mietkosten;
	}
	private static final long serialVersionUID = 1L;
	
	public double Mietkosten;
	public int Anzahl_mieter;
	public double miet_zuschlag = zuschlag_rechner();
	 
	public double zuschlag_rechner() {
		if (Anzahl_mieter <= 1) {			
			return miet_zuschlag = 0;
		} else if (Anzahl_mieter > 4) {
			return miet_zuschlag = 0.1;
		} else if (Anzahl_mieter == 2) {
			return miet_zuschlag = 0.025;
		} else if (Anzahl_mieter == 3) {
			return miet_zuschlag = 0.050;
		} else if (Anzahl_mieter == 4) {
			return miet_zuschlag = 0.075;
		}
		return miet_zuschlag;
	}	

	public double gesamtKosten() {
		return (Mietkosten*getFlaeche()) + ((Mietkosten*getFlaeche())*miet_zuschlag);
	}
	
	@Override
	public String toString() {
		DecimalFormat df = Wohnung.getDecimalFormat();
		return 	 "Typ:            MW" + '\n' 
				+"Id:             " + getId() + '\n' 
				+"Flaeche:        " + df.format(getFlaeche()) + '\n' 
				+"Zimmer:         " + getZimmer() + '\n' 
				+"Stock:          " + getStock() + '\n' 
				+"Baujahr:        " + getBaujahr() + '\n' 
				+"PLZ:            " + getPLZ() + '\n' 
				+"Strasse:        " + getStrasse() + '\n'
				+"Hausnummer:     " + getHausnummer() + '\n'
				+"Top:            " + getTop() + '\n'
				+"Miete/m2:       " + df.format(Mietkosten) + '\n' 
				+"Anzahl Mieter:  " + Anzahl_mieter +"";
	}
	
	public double getMietkosten() {
		return Mietkosten;
	}
	public int getAnzahl_mieter() {
		return Anzahl_mieter;
	}
	public double getMiet_zuschlag() {
		return miet_zuschlag;
	}
	public void setMietkosten(double mietkosten) {
		Mietkosten = mietkosten;
	}
	public void setAnzahl_mieter(int anzahl_mieter) {
		Anzahl_mieter = anzahl_mieter;
	}
	public void setMiet_zuschlag(double miet_zuschlag) {
		this.miet_zuschlag = miet_zuschlag;
	}
}