import java.lang.Math;
import java.util.ArrayList;

// TODO: J'ai mis des listes de String, mais à voir s'il ne faut pas plutôt des listes d'objets "Règle"
public class FOIL {

	// TODO: écrire le code correspondant aux commentaires orphelins
	public ArrayList<String> FOIL(ArrayList<String> pos, ArrayList<String> neg)
	{		
		ArrayList<String> regles = new ArrayList<String>();	// Règles <- vide	
		
		while(pos.size() != 0)	// Tant que Pos n'est pas vide
		{
			ArrayList<String> conditions_regle;	// Conditions_Règle <- vide
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
	
	public double gain(String L, ArrayList<String> pos, ArrayList<String> neg)
	{
		int P = pos.size();
		int N = neg.size();
		
		// TODO: il faut définir les bonnes valeurs pour p et n
		// p = nombre d'exemples dans Pos qui satisfont le literral L
		// n = nombre d'exemples dans Neg qui satisfont le literral L
		int p = 0;
		int n = 0;
		
		return (p*(Math.log(p/(p+n)) - Math.log(P/(P+N))));
	}
	
	public int nombreExemplesSatisfait(String litteral, ArrayList<String> exemples)
	{
		// TODO: il faut définir la bonne valeur pour le return
		return exemples.size();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
