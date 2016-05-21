
public class Fait {

	String condition;
	String valeur;
	
	public Fait(String condition, String valeur)
	{
		this.condition = condition;
		this.valeur = valeur;
	}
	
	public String toString()
	{
		String fait = this.condition + " = " + this.valeur;
		return fait;
	}
	
	public String getCondition()
	{
		return this.condition;
	}
	
	public String getValeur()
	{
		return this.valeur;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
