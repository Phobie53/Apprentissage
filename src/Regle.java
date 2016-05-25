import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Regle 
{
	ArrayList<Fait> conditions = new ArrayList<Fait>();
	private Fait resultat;
	
	public Regle(ArrayList<Fait> conditions, Fait resultat) 
	{
		this.conditions = conditions;
		this.resultat = resultat;
	}
	
	public String toString()
	{
		String regle = "SI ";
		for(int i = 0; i < this.conditions.size(); i++)
		{
			if( (i != 0) && (i != this.conditions.size()))
				regle += " ET ";
			regle += this.conditions.get(i).toString();
		}
		regle += " ALORS ";
		regle += resultat.toString();
		return regle;
	}
	
	/***
	 * @param attribut String (ex : Température)
	 * @param valeur String (ex : Froide)
	 */
	/*public void addCondition(String attribut, String valeur) {
		// Si notre map de conditions contient déjà cette condition
		if (this.conditions.containsKey(attribut)) {
			// Alors on la supprime
			this.conditions.remove(attribut);
		}
		
		// Ajout de la nouvelle condition
		this.conditions.put(attribut, valeur);
	}*/
	
	/***
	 * @param attribut String (ex : Température)
	 * @return boolean (True si condition déjà enregistrée)
	 */
	/*public boolean isConditionExiste(String attribut) {
		if (this.conditions.containsKey(attribut)) return true;
		else return false;
	}*/
	
	/***
	 * @param attribut String (ex : Température)
	 * @return String Valeur correspondant à l'attribut pour cette règle
	 */
	/*public String getValeurFromAttribut(Fait attribut) {
		if (this.conditions.containsKey(attribut)) {
			return this.conditions.get(attribut);
		} else {
			return null;
		}
	}*/
	
	
	/*********************************
	 		 GETTERS & SETTERS
	 *********************************/
    public ArrayList<Fait> getConditions() {
		return conditions;
	}
	public void setConditions(ArrayList<Fait> conditions) {
		this.conditions = conditions;
	}
	public Fait getResultat() {
		return resultat;
	}
	public void setResultat(Fait resultat) {
		this.resultat = resultat;
	}
	

}
