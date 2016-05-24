import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;

// TODO: J'ai mis des listes de String, mais à voir s'il ne faut pas plutôt des listes d'objets "Règle"
public class FOIL 
{
	ArrayList<Regle> regles;

	// TODO: écrire le code correspondant aux commentaires orphelins
	public FOIL(ArrayList<Data> pos, ArrayList<Data> neg, ArrayList<Fait> litteraux)
	{		
		//Fait litteral = pos.get(0).getLitteral();
		
		ArrayList<Regle> regles = new ArrayList<Regle>();	// Règles <- vide	
		
		while(pos.size() != 0)	// Tant que Pos n'est pas vide
		{
			ArrayList<Fait> conditions_regle = new ArrayList<Fait>();	// Conditions_Règle <- vide
			ArrayList<Data> neg2 = neg;		// Neg2 <- Neg
			ArrayList<Data> pos2 = pos;		// Pos2 <- Pos
			
			while(neg2.size() != 0)	// Tant que Neg2 n'est pas vide
			{
				Fait litteralMax = litteralMax(litteraux, pos2, neg2);	// Choisir le littéral L qui maximise Gain(L, Pos2, Neg2)
				System.out.println("Litteral max = " + litteralMax.toString());
				conditions_regle.add(litteralMax);	// Ajouter L à Conditions_Règle
				// Retirer de Neg2 tous les exemples qui ne satisfont pas L
				neg2 = retirerExemplesNonSatisfaisant(neg2, litteralMax);
				// Retirer de Pos2 tous les exemples qui ne satisfont pas L
				pos2 = retirerExemplesNonSatisfaisant(pos2, litteralMax);
				
			}	// Fin tant que
			
			// Ajouter à Règles la règle (C <- Conditions_Règle)
			Fait resultat = new Fait("play","yes");
			regles.add(new Regle(conditions_regle, resultat));
			// Retirer de Pos tous les exemples qui satisfont Conditions_Règle
			
		} 	// Fin tant que		

		this.regles = regles;	//Retourner l'ensemble Règles	
	}
	
	public ArrayList<Data> retirerExemplesNonSatisfaisant(ArrayList<Data> exemples, Fait litteral)
	{
		ArrayList<Data> exemplesSatisfaisants = new ArrayList<Data>();
		for(int i = 0; i < exemples.size(); i++)
		{
			// Si l'exemple satisfait le litteral, on ajoute l'exemple au nouvel ensemble
			for(int j = 0; j < exemples.get(i).getValues().size(); j++)
			{
				if(litteral.condition == exemples.get(i).getAttributes().get(i).getName())
				{
					if(litteral.valeur == exemples.get(i).getValues().get(i))
						exemplesSatisfaisants.add(exemples.get(i));						
				}
			}
		}
		return exemplesSatisfaisants;
	}
	
	public double gain(Fait L, ArrayList<Data> pos, ArrayList<Data> neg)
	{
		int P = pos.size();
		int N = neg.size();
		
		int p = nombreExemplesSatisfait(L, pos);	// p = nombre d'exemples dans Pos qui satisfont le literral L
		int n = nombreExemplesSatisfait(L, neg);	// n = nombre d'exemples dans Neg qui satisfont le literral L
		
		return (p*(Math.log(p/(p+n)) - Math.log(P/(P+N))));
	}
	
	private static int nombreExemplesSatisfait(Fait litteral, ArrayList<Data> exemples)
	{
		int nombre = 0;
		for(int i = 0; i < exemples.size(); i++)
		{
			for(int j = 0; j < exemples.get(i).getValues().size(); j++)
			{
				if(litteral.getValeur() == exemples.get(i).getValues().get(j))
					nombre++;				
			}			
		}	
		return nombre;
	}
	
	public Fait litteralMax(ArrayList<Fait> L, ArrayList<Data> pos, ArrayList<Data> neg)
	{
		int max = 0;
		double gainMax = 0;
		double gainTemp = 0;
		for(int i = 0; i < L.size(); i++)
		{
			gainTemp = gain(L.get(i), pos, neg);
			if(gainMax < gainTemp)
			{
				gainMax = gainTemp;
				max = i;
			}
		}
		return L.get(max);
	}
	
	public static void main(String[] args) {

	}

}
