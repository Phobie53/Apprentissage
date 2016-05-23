import java.util.ArrayList;

public class Attribute {

	static int instanceCounter = 0;
	int counter = 0;
	
	private String name;
	private ArrayList<String> values;
	
	public Attribute(String name, String[] values)
	{
		
		this.counter = instanceCounter++;
		this.name = name;
		this.values = new ArrayList<String>();
		for(int i = 0; i < values.length; i++)
		{
			this.values.add(values[i]);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Attribute a = new Attribute("outlook", new String[]{"sunny","overcast","rainy"});
		for(int i = 0; i < a.values.size(); i++)
		{
			System.out.println(a.name + " = " + a.values.get(i));
		}
		System.out.println("Instance numéro = " + a.counter);
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String toString() {
		return this.name;
	}

}
