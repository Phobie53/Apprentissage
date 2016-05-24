
public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Parser p = new Parser("data/weather.nominal.arff");
		
		FOIL f = new FOIL(p.pos, p.neg, p.litteraux);
		for(int i = 0; i < f.regles.size(); i++)
		{
			System.out.println("Règle N°" + i + " = "+ f.regles.get(i).toString());
		}
		//TODO : Il reste à trouver comment définir proprement pos et neg
		//FOIL foil = new FOIL();
	}
	
	//TODO : Créer un objet pour les enregistrements
	
	// En développement
//	public void algoFOIL(List<Enregistrement> Pos, List<Enregistrement> Neg) {
//		Regle mesRegles = new Regle();
//		while (Pos.)
//	}
}
