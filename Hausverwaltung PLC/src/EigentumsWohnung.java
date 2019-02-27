/**
 * @author <Borislav Milijic>
 * Matrikelnummer: 01249658
 */
import java.text.DecimalFormat;

public class EigentumsWohnung extends Wohnung {

	public EigentumsWohnung(int Id, double Flaeche, int Zimmer, int Stock, int Baujahr, int PLZ, String Strasse,
			int Hausnummer, int Top, double Betriebskosten, double Reparatur_beitrag) {
		super(Id, Flaeche, Zimmer, Stock, Baujahr, PLZ, Strasse, Hausnummer, Top);
		this.Betriebskosten = Betriebskosten;
		this.Reparatur_beitrag = Reparatur_beitrag;
	}
	private static final long serialVersionUID = 1L;
	public double Betriebskosten;
	public double Reparatur_beitrag;
	public double eigentum_zuschlag = zuschlag_rechner_eigentum();
	
	public double zuschlag_rechner_eigentum () {
		if (getStock() == 0) {
			return 0;
		}
		return (double)getStock()*0.02;	
	} 
	
	public double kvadratura() {
		return ((Reparatur_beitrag + Betriebskosten)*getFlaeche());
	}
	
	public double gesamtKosten() {
		return kvadratura()+(kvadratura()*eigentum_zuschlag);
	}
	@Override
	public String toString() {
		DecimalFormat df = Wohnung.getDecimalFormat();
		return 	"Typ:            EW" + '\n'
				+"Id:             " + getId() + '\n' 
				+"Flaeche:        " + df.format(getFlaeche()) + '\n' 
				+"Zimmer:         " + getZimmer() + '\n' 
				+"Stock:          " + getStock() + '\n' 
				+"Baujahr:        " + getBaujahr() + '\n' 
				+"PLZ:            " + getPLZ() + '\n' 
				+"Strasse:        " + getStrasse() + '\n'
				+"Hausnummer:     " + getHausnummer() + '\n'
				+"Top:            " + getTop() + '\n'
				+"Betriebskosten: " + df.format(Betriebskosten) + '\n' 
				+"Ruecklage:      " + df.format(Reparatur_beitrag) +"";
	}
}