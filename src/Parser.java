import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class Parser 
{

	ArrayList<Attribute> attributes;
	ArrayList<Data> data;
	ArrayList<Data> pos;
	ArrayList<Data> neg;
	
	public Parser(String filename)
	{
		this.attributes = new ArrayList<Attribute>();
		this.data = new ArrayList<Data>();
		this.pos = new ArrayList<Data>();
		this.neg = new ArrayList<Data>();
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
			    	 Attribute attr = new Attribute(name, values);
			    	 this.attributes.add(attr);		    	 
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
		    	 Data d = new Data(this.attributes, words);
		    	 data.add(d);
		    	 if(words[words.length - 1] == "yes")
		    		 pos.add(d);
		    	 else
		    		 neg.add(d);
		     }
		 }

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Parser p = new Parser("data/weather.nominal.arff");
	}

}
