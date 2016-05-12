import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Parser {

	
	public Parser(String filename)
	{
		Scanner scanner;
		try {
			scanner = new Scanner(new FileReader(filename));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		 String line = null;
		 while (scanner.hasNextLine()) 
		 {
		     line = scanner.nextLine();
		     String[] words = line.split(" ");
		     //System.out.println("Ligne = " + line);
		     
		     if(line.startsWith("@attribute"))
		     {
		    	 String withoutPrefix = line.substring(11);
		    	 int endOfAttributeName = withoutPrefix.indexOf(' ');
		    	 
		    	 String name = withoutPrefix.substring(0, endOfAttributeName);
		    	 String valuesString = withoutPrefix.substring(endOfAttributeName + 1, withoutPrefix.length());
		    	 valuesString = valuesString.replace("{", "");
		    	 valuesString = valuesString.replace("}", "");
		    	 
		    	 String[] values = valuesString.split(", ");
		    	 for(int i =0; i < values.length; i++)
		    	 {
		    		 System.out.println("Valeur N°=" + i + " " + values[i]);
		    	 }
		    	 
		    	 System.out.println("Nom = " + name);
		    	 
		     }
		     else if(line.startsWith("@data"))
		     {
		    	 
		     }
		     
		     for(int i = 0; i < words.length ; i++)
		     {
			     //System.out.println("Mot N°" + i + " = " + words[i]);
		     }
		     //System.out.println();
		 }

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Parser p = new Parser("data/weather.nominal.arff");

	}

}
