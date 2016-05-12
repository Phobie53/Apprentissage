import java.util.HashMap;
import java.util.Map;


public class Regle {
	Map<String, String> conditions = new HashMap<>();
	private String resultat;
	
	public Regle() {
		this.conditions = null;
		this.resultat = null;
	}
	
	/***
	 * @param attribut String (ex : Température)
	 * @param valeur String (ex : Froide)
	 */
	public void addCondition(String attribut, String valeur) {
		// Si notre map de conditions contient déjà cette condition
		if (this.conditions.containsKey(attribut)) {
			// Alors on la supprime
			this.conditions.remove(attribut);
		}
		
		// Ajout de la nouvelle condition
		this.conditions.put(attribut, valeur);
	}
	
	/***
	 * @param attribut String (ex : Température)
	 * @return boolean (True si condition déjà enregistrée)
	 */
	public boolean isConditionExiste(String attribut) {
		if (this.conditions.containsKey(attribut)) return true;
		else return false;
	}
	
	/***
	 * @param attribut String (ex : Température)
	 * @return String Valeur correspondant à l'attribut pour cette règle
	 */
	public String getValeurFromAttribut(String attribut) {
		if (this.conditions.containsKey(attribut)) {
			return this.conditions.get(attribut);
		} else {
			return null;
		}
	}
	
	
	/*********************************
	 		 GETTERS & SETTERS
	 *********************************/
    public Map<String, String> getConditions() {
		return conditions;
	}
	public void setConditions(Map<String, String> conditions) {
		this.conditions = conditions;
	}
	public String getResultat() {
		return resultat;
	}
	public void setResultat(String resultat) {
		this.resultat = resultat;
	}
}
