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
	ArrayList<Fait> litteraux;
	Fait classTrue;
	
	public Parser(String filename)
	{
		this.attributes = new ArrayList<Attribute>();
		this.data = new ArrayList<Data>();
		this.pos = new ArrayList<Data>();
		this.neg = new ArrayList<Data>();
		this.litteraux = new ArrayList<Fait>();
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
			    	 
			    	 String[] values = valuesString.split(",");
			    	 // On trim
			    	 for(int i = 0; i < values.length; i++)
			    	 {
			    		 values[i] = values[i].trim();
			    	 }
			    		 
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
		    	 if(words[words.length - 1].equals("yes"))
		    	 {
		    		 pos.add(d);
		    		 //System.out.println("Yes = " + words[words.length - 1]);
		    	 }
		    	 else
		    	 {
		    		 //System.out.println("No = " + words[words.length - 1]);
		    		 neg.add(d);
		    	 }

		     }
		 }

		 // Création de la liste des litteraux
		 for(int i = 0; i < this.attributes.size() - 1; i++)
		 {
			 Attribute attribute = this.attributes.get(i);
			 for(int j = 0; j < attribute.getValues().size(); j++)
			 {
	    		 this.litteraux.add(new Fait(attribute.getName(), attribute.getValues().get(j)));
			 }
		 }
		 
		 // On récupère le fait qui sera a validé exemple: "play = yes"
		 classTrue = getClassOfAttributes();
	}
	
	public Fait getClassOfAttributes()
	{
		String condition = this.attributes.get(this.attributes.size()-1).getName();
		String valeur = this.attributes.get(this.attributes.size()-1).getValues().get(this.attributes.get(this.attributes.size()-1).getValues().size()-1);
		
		Fait fait = new Fait(condition, valeur);
		return fait;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Parser p = new Parser("data/weather.nominal.arff");
	}

}
