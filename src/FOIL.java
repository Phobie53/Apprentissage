import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;

// TODO: J'ai mis des listes de String, mais à voir s'il ne faut pas plutôt des listes d'objets "Règle"
public class FOIL 
{
	ArrayList<Regle> regles;

	// TODO: écrire le code correspondant aux commentaires orphelins
	public FOIL(ArrayList<Data> data, ArrayList<Fait> litterauxToCopy, Fait trueClass)
	{				
		// Calcul de pos et neg
		String nomDuFait = trueClass.getCondition();
		String valeurDuFait = trueClass.getValeur();
		ArrayList<Data> pos = new ArrayList<Data>();
		ArrayList<Data> neg = new ArrayList<Data>();
		for(int i = 0; i < data.size(); i++)
		{
			for(int j = 0; j < data.get(i).getValues().size(); j++)
			{
				if(data.get(i).getAttributeNameByIndex(j).equals(nomDuFait))
				{
					if(data.get(i).getValues().get(j).equals(valeurDuFait))
						pos.add(data.get(i));
					else
						neg.add(data.get(i));
				}
			}
		}
		
		ArrayList<Regle> regles = new ArrayList<Regle>();	// Règles <- vide	
		int indiceBoucle = 1;
		while(pos.size() != 0)	// Tant que Pos n'est pas vide
		{
			ArrayList<Fait> conditions_regle = new ArrayList<Fait>();	// Conditions_Règle <- vide
			ArrayList<Data> neg2 = new ArrayList<Data>(neg);		// Neg2 <- Neg
			ArrayList<Data> pos2 = new ArrayList<Data>(pos);		// Pos2 <- Pos
			ArrayList<Fait> litteraux = new ArrayList<Fait>(litterauxToCopy);		// Pos2 <- Pos
			
			Fait litteralMax = null;
			while(neg2.size() != 0)	// Tant que Neg2 n'est pas vide
			{
				litteralMax = litteralMax(litteraux, pos2, neg2);	// Choisir le littéral L qui maximise Gain(L, Pos2, Neg2)
				litteraux.remove(litteralMax); // Si le litteral est windy, retirer tout les fait windy =...
				//System.out.println("Litteral max => " + litteralMax.toString());
				conditions_regle.add(litteralMax);	// Ajouter L à Conditions_Règle
				// Retirer de Neg2 tous les exemples qui ne satisfont pas L
				neg2 = retirerExemplesNonSatisfaisant(neg2, litteralMax);
				// Retirer de Pos2 tous les exemples qui ne satisfont pas L
				pos2 = retirerExemplesNonSatisfaisant(pos2, litteralMax);
			}	// Fin tant que
			// Ajouter à Règles la règle (C <- Conditions_Règle)
			Regle newRegle = new Regle(conditions_regle, trueClass);				
			// Retirer de Pos tous les exemples qui satisfont Conditions_Règle
			int taillePosDebut = pos.size();
			pos = retirerExemplesSatisfaisant(pos, conditions_regle);
			int taillePosFin = pos.size();
			int nombreExemplesCouverts = taillePosDebut-taillePosFin;
			if(nombreExemplesCouverts == 0)
			{
				pos = new ArrayList<Data>();
				neg2 = new ArrayList<Data>();
			}
			else
			{
				newRegle.setNombreExemplesCouverts(nombreExemplesCouverts);
				regles.add(newRegle);
				System.out.println("Règle N°" + indiceBoucle++ + ": "+ newRegle.toString());	
			}
		} 	// Fin tant que		

		this.regles = regles;	//Retourner l'ensemble Règles	
	}

	public ArrayList<Data> retirerExemplesSatisfaisant(ArrayList<Data> exemples, ArrayList<Fait> litteral)
	{
		ArrayList<Data> exemplesNonSatisfaisants = new ArrayList<Data>();
		for(int i = 0; i < exemples.size(); i++)
		{				
			boolean toAdd = false;
			// Si l'exemple satisfait le litteral, on ajoute l'exemple au nouvel ensemble
			for(int j = 0; j < exemples.get(i).getValues().size(); j++)
			{

				for(int k=0; k < litteral.size(); k++)
				{
					if(litteral.get(k).condition.equals(exemples.get(i).getAttributes().get(j).getName()))
					{
						if(!litteral.get(k).valeur.equals(exemples.get(i).getValues().get(j)))
							toAdd = true;													
					}
				}
			}
			if(toAdd)
				exemplesNonSatisfaisants.add(exemples.get(i));
		}
		return exemplesNonSatisfaisants;
	}
	
	public ArrayList<Data> retirerExemplesNonSatisfaisant(ArrayList<Data> exemples, Fait litteral)
	{
		ArrayList<Data> exemplesSatisfaisants = new ArrayList<Data>();
		for(int i = 0; i < exemples.size(); i++)
		{
			// Si l'exemple satisfait le litteral, on ajoute l'exemple au nouvel ensemble
			for(int j = 0; j < exemples.get(i).getValues().size(); j++)
			{
				if(litteral.condition.equals(exemples.get(i).getAttributes().get(j).getName()))
				{
					if(litteral.valeur.equals(exemples.get(i).getValues().get(j)))
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
		
		if(p == 0)
			return -999999999;
		
		return p * (Math.log((double)p / (double)(p + n)) - Math.log((double)P / (double)(P + N)));
		
	}
	
	private static int nombreExemplesSatisfait(Fait litteral, ArrayList<Data> exemples)
	{
		int nombre = 0;
		for(int i = 0; i < exemples.size(); i++)
		{
			for(int j = 0; j < exemples.get(i).getValues().size(); j++)
			{
				if(litteral.getValeur().equals(exemples.get(i).getValues().get(j)))
				{
					nombre++;	
					//System.out.println(litteral.getValeur() + " = " + exemples.get(i).getValues().get(j));
				}
				//else
					//System.out.println("Pas égal: " + litteral.getValeur() + " = " + exemples.get(i).getValues().get(j));
								
			}			
		}	
		return nombre;
	}
	
	public Fait litteralMax(ArrayList<Fait> L, ArrayList<Data> pos, ArrayList<Data> neg)
	{
		int max = 0;
		double gainMax = -99999999;
		double gainTemp = -99999999;
		for(int i = 0; i < L.size(); i++)
		{
			gainTemp = gain(L.get(i), pos, neg);
			if(gainMax <= gainTemp)
			{
				gainMax = gainTemp;
				max = i;
				//System.out.println("Cond = " + L.get(i).toString() + " | Gain = " +  gainTemp);
			}
		}
		return L.get(max);
	}
	
	public static void main(String[] args) {

	}
	
	public ArrayList<Regle> getRegles() {
		return regles;
	}


}
