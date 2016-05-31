import java.util.ArrayList;

public class Data {

	static int instanceCounter = 0;
	int counter = 0;
	ArrayList<Attribute> attributes;
	private ArrayList<String> values;
	
	public Data(ArrayList<Attribute> attributes, String[] values)
	{
		
		this.counter = instanceCounter++;
		this.attributes = attributes;
		this.values = new ArrayList<String>();
		for(int i = 0; i < values.length; i++)
		{
			this.values.add(values[i]);
		}
	}
	
	public Fait getLitteral()
	{
		String condition = this.getAttributes().get(this.getAttributes().size()-1).getName();
		String valeur = this.values.get(this.values.size()-1);
		Fait fait = new Fait(condition, valeur);
		return fait;
	}
	
	public String getConditionsAt(int index)
	{
		return this.attributes.get(index) + " = "+ this.values.get(index);
	}
	
	public ArrayList<Attribute> getAttributes()
	{
		return this.attributes;
	}
	
	public String getAttributeNameByIndex(int index)
	{
		return this.attributes.get(index).getName();
	}
	
	public int getAttributeIndexByName(String name)
	{
		int index = 0;
		for(int i = 0; i < this.attributes.size(); i++)
		{
			if(this.attributes.get(i).getName().equals(name))
				index = i;
		}
		return index;
	}
	
	public ArrayList<String> getValues()
	{
		return this.values;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
