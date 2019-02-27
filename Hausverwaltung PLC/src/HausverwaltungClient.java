/**
 * @author <Borislav Milijic>
 * Matrikelnummer: 01249658
 */

public class HausverwaltungClient {
	
	public static void main (String[] args) {

	if (args.length == 0) throw new IllegalArgumentException("Error: Parameter ungueltig.");	
			
		Hausverwaltung hv = new Hausverwaltung(args[0]);
			try {
				if (args[1].equals("list")) {
					if (args.length==2) System.out.println(hv.get_all_Wohnungen());
					if (args.length==3) System.out.println(hv.get_one_Wohnung(Integer.parseInt(args[2])));
					if (args.length>3) throw new IllegalArgumentException("Error: Parameter ungueltig.");
				}
				else if (args[1].equals("add")) {
					if (args[2].equals("EW")) {
						if (args.length!=14) throw new IllegalArgumentException("Error: Parameter ungueltig.");
            if (Integer.parseInt(args[7])>2018)
							throw new Exception ("Error: Baujahr ungueltig.123");
						else 	hv.add_ew(	Integer.parseInt(args[3]),
								Double.parseDouble(args[4]),
								Integer.parseInt(args[5]),
								Integer.parseInt(args[6]),
								Integer.parseInt(args[7]),
								Integer.parseInt(args[8]),
								args[9],
								Integer.parseInt(args[10]),
								Integer.parseInt(args[11]),
								Double.parseDouble(args[12]),
								Double.parseDouble(args[13])
								);
					}
					else if (args[2].equals("MW")) {
						if (args.length!=14) throw new IllegalArgumentException("Error: Parameter ungueltig.");
            if (Integer.parseInt(args[7])>2018)
							throw new Exception ("Error: Baujahr ungueltig.123");
						else hv.add_mw(	Integer.parseInt(args[3]),
										Double.parseDouble(args[4]),
										Integer.parseInt(args[5]),
										Integer.parseInt(args[6]),
										Integer.parseInt(args[7]),
										Integer.parseInt(args[8]),
										args[9],
										Integer.parseInt(args[10]),
										Integer.parseInt(args[11]),
										Double.parseDouble(args[12]),
										Integer.parseInt(args[13])
										);
					} else throw new IllegalArgumentException("Error: Parameter ungueltig.");
				} else if (args[1].equals("delete")) {
					if (args.length!=3) 
						throw new IllegalArgumentException("Error: Parameter ungueltig.");
					else hv.delete_Wohnung(Integer.parseInt(args[2]));
				} else if (args[1].equals("count")) {
					if (args.length==2) 
						System.out.println(hv.count_Wohnung());
					else if (args.length==3) {
						if (args[2].equals("EW")) 
							System.out.println(hv.count_ew());
						else if (args[2].equals("MW")) 
							System.out.println(hv.count_mw());
						else throw new IllegalArgumentException("Error: Parameter ungueltig.");
					}
				} else if (args[1].equals("meancosts")) 
					System.out.println(hv.durchschnitt_kosten());
				else if (args[1].equals("oldest")) 
					System.out.print(hv.get_oldest());
				else throw new IllegalArgumentException("Error: Parameter ungueltig.");
			} catch (IllegalArgumentException e) {
				System.out.println("Error: Parameter ungueltig.");
			} catch (Exception e) {
				System.out.println("Error: Baujahr ungueltig.");
			}
	}
}