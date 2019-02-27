import java.io.*;
import java.util.List;
import java.util.ArrayList;

/**
 * @author <Borislav Milijic>
 * Matrikelnummer: 01249658
 */

public class HausverwaltungSerializationDAO implements HausverwaltungDAO {
	private static HausverwaltungSerializationDAO instance;
	
	public static HausverwaltungSerializationDAO getInstance() {
		if (instance == null) {
			instance = new HausverwaltungSerializationDAO(file_name);
		} return instance;
	} 
	
	public static String file_name;
	File file;
	List<Wohnung> list;
	
	public HausverwaltungSerializationDAO (String f_name) {
		file_name = f_name;
		file = new File(file_name);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			list = new ArrayList<Wohnung>();
			try { 
      	FileOutputStream writer = new FileOutputStream(file_name);
        ObjectOutputStream object_writer = new ObjectOutputStream(writer);
        object_writer.writeObject(list);
        writer.close();
        object_writer.close();
       } catch (FileNotFoundException e) {
           System.out.println("Error: File nicht gefunden.");
           System.exit(1);
       } catch (IOException e) {
           System.out.println("Error: Serialisierung.");
           System.exit(1);
       }
		} else {
			list = (List<Wohnung>) getWohnungen();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Wohnung> getWohnungen() {
		List<Wohnung> list = null;
		try {
			FileInputStream reader = new FileInputStream(file_name);
			ObjectInputStream object_reader = new ObjectInputStream(reader);
			list = (List<Wohnung>) object_reader.readObject();
			reader.close();
			object_reader.close();
		} catch (IOException e) {
			System.out.println("Fehler bei Deserialisierung: " + e.getMessage());
			System.exit(1);
		}  catch(ClassNotFoundException e) {
			System.out.println("Fehler bei Deserialisierung: " + e.getMessage());
			System.exit(1);
		}
		return list;
	}

	public Wohnung getWohnungbyId(int id) {
		for (int i=0; i<list.size(); i++) {
			if (list.get(i).getId() == id)
				return list.get(i);
		} 
		return null;
	}
	
	public void saveWohnung(Wohnung wohnung) {
		for (Wohnung new_wohnung: list) {
		    if (new_wohnung.getId() == wohnung.getId()) {
		        System.out.println("Error: Wohnung bereits vorhanden. (id=" + wohnung.getId() + ")");
		    }
		}
		try {
			list.add(wohnung);
			 FileOutputStream writer = new FileOutputStream(file_name); 
		     ObjectOutputStream object_writer = new ObjectOutputStream(writer); 
		     object_writer.writeObject(list);		      
		     object_writer.close();
		System.out.println("Info: Wohnung " + wohnung.getId() + " added.");
		} catch (NullPointerException e) {
			System.out.println("Error: Wohnung bereits vorhanden. (id=" + wohnung.getId() + ")");
		} catch (IOException e) {
			System.out.println("Error: Wohnung bereits vorhanden." + e + " (id=" + wohnung.getId() + ")");
			System.exit(1);
		} catch (IllegalArgumentException e) {
			System.out.println("Error: Wohnung bereits vorhanden. (id=" + wohnung.getId() + ")");
		}
	}

	public void deleteWohnung(int delete_id) {
		try {
			if (list.contains(getWohnungbyId(delete_id)) && getWohnungbyId(delete_id) != null) {
				list.remove(getWohnungbyId(delete_id));
				FileOutputStream writer = new FileOutputStream(file_name);
				ObjectOutputStream object_writer = new ObjectOutputStream(writer);
				object_writer.writeObject(list);
				object_writer.close();
				writer.close();
				System.out.println("Info: Wohnung " + delete_id + " deleted.");
			}	
		} catch (Exception e) {
			System.out.println("Error: Wohnung nicht vorhanden. (id=" + delete_id + ")");
			System.exit(1);
		}
	}
}