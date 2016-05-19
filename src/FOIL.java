import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;

// TODO: J'ai mis des listes de String, mais à voir s'il ne faut pas plutôt des listes d'objets "Règle"
public class FOIL 
{

	// TODO: écrire le code correspondant aux commentaires orphelins
	public ArrayList<String> FOIL(ArrayList<String> pos, ArrayList<String> neg)
	{		
		ArrayList<String> regles = new ArrayList<String>();	// Règles <- vide	
		
		while(pos.size() != 0)	// Tant que Pos n'est pas vide
		{
			HashMap<String,String> conditions_regle;	// Conditions_Règle <- vide
			ArrayList<String> neg2 = neg;		// Neg2 <- Neg
			ArrayList<String> pos2 = pos;		// Pos2 <- Pos
			
			while(neg2.size() != 0)	// Tant que Neg2 n'est pas vide
			{
				// Choisir le littéral L qui maximise Gain(L, Pos2, Neg2)
				// Ajouter L à Conditions_Règle
				// Retirer de Neg2 tous les exemples qui ne satisfont pas L
				// Retirer de Pos2 tous les exemples qui ne satisfont pas L
				
			}	// Fin tant que
			
			// Ajouter à Règles la règle (C <- Conditions_Règle)
			// Retirer de Pos tous les exemples qui satisfont Conditions_Règle
			
		} 	// Fin tant que		

		return regles;	//Retourner l'ensemble Règles	
	}
	
	public double gain(HashMap<String,String> L, ArrayList<String> pos, ArrayList<String> neg)
	{
		int P = pos.size();
		int N = neg.size();
		
		int p = nombreExemplesSatisfait(L, pos);	// p = nombre d'exemples dans Pos qui satisfont le literral L
		int n = nombreExemplesSatisfait(L, neg);	// n = nombre d'exemples dans Neg qui satisfont le literral L
		
		return (p*(Math.log(p/(p+n)) - Math.log(P/(P+N))));
	}
	
	private static int nombreExemplesSatisfait(HashMap<String,String> litteral, ArrayList<String> exemples)
	{
		int nombre = 0;
		for(int i = 0; i < exemples.size(); i++)
		{
			if(litteral.values().toArray()[0] ==  exemples.get(i))
				nombre++;				
		}	
		return nombre;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FOIL f = new FOIL();
		HashMap<String,String> h = new HashMap<String,String>();
		h.put("temperature", "hot");
		ArrayList<String> dataArray = new  ArrayList<String>();
		dataArray.add("mild");
		dataArray.add("hot");
		dataArray.add("hot");
		 
		int valeurs = nombreExemplesSatisfait(h, dataArray);
		System.out.println(valeurs);
	}

}
