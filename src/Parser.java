import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class Parser 
{

	HashMap<String,String[]> attributes;
	ArrayList<String[]> data;
	
	public Parser(String filename)
	{
		this.attributes = new HashMap<String,String[]>();
		this.data = new ArrayList<String[]>();
		Scanner scanner;
		try 
		{
			scanner = new Scanner(new FileReader(filename));
		}
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		 boolean isDataSection = false;
		 String line = null;
		 
		 while (scanner.hasNextLine()) 
		 {
		     line = scanner.nextLine();
		     //System.out.println("Ligne = " + line);
		     
		     // Avant la section @data
		     if(!isDataSection)
		     {		    	
		    	 // Si c'est un attribut
			     if(line.startsWith("@attribute"))
			     {
				     String[] words = line.split(" ");
			    	 String withoutPrefix = line.substring(11);
			    	 int endOfAttributeName = withoutPrefix.indexOf(' ');
			    	 
			    	 String name = withoutPrefix.substring(0, endOfAttributeName);
			    	 System.out.println("Nom = " + name);
			    	 
			    	 String valuesString = withoutPrefix.substring(endOfAttributeName + 1, withoutPrefix.length());
			    	 valuesString = valuesString.replace("{", "");
			    	 valuesString = valuesString.replace("}", "");
			    	 
			    	 String[] values = valuesString.split(", ");
			    	 for(int i =0; i < values.length; i++)
			    	 {
			    		 System.out.println("Valeur N°" + i + " " + values[i]);
			    	 }
			    	 System.out.println();
			    	 attributes.put(name, values);			    	 
			     }
			     else if(line.startsWith("@data"))
			     {
			    	 isDataSection = true;
			    	 System.out.println();
			    	 System.out.println("Section Data");			    	 
			     }
		     }
		     // Après le @data
		     else
		     {
			     String[] words = line.split(",");
		    	 for(int i =0; i < words.length; i++)
		    	 {
		    		 System.out.println("Valeur N°" + i + " " + words[i]);
		    	 }
		    	 data.add(words);
		     }
		 }

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
