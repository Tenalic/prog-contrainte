package progcomp.object;

/**
 * 
 * @author Stephane
 *
 */
public class CSP {

	/**
	 * nombre de noeud
	 */
	private int taille;

	/**
	 * lien entre les noeud
	 */
	private int graph[][];

	/**
	 * valeur possible pour chaque noeud (peut être remplacer par tableau de noeud
	 * par la suite si tout les noeud non pas les même valeur, ou l'indice
	 * représente le numero du noeud)
	 */
	private int tailleDonnes;

	/**
	 * ensemble des valeur possible pour chaque valeur du domaine.
	 */
	private CoupleEntier ensemble[][];

	/**
	 * Crée un tableau de taille _taille des liens entres tout les noeud crée le
	 * tableau des valeur possible en fonction de la _tailleDonnes
	 * 
	 * @param _taille
	 * @param _tailleDonnees
	 */
	public CSP(int _taille, int _tailleDonnees) {
		this.taille = _taille;
		this.graph = new int[_taille][_taille];
		initGraoh();
		this.tailleDonnes = _tailleDonnees;
		this.ensemble = new CoupleEntier[_tailleDonnees][_tailleDonnees];
		initEnsemble();
	}

	/**
	 * Crée un tableau de taille _taille des liens entres tout les noeud et suprime
	 * un nombre de noeud en fonction de _densiteContrainte crée le tableau des
	 * valeur possible en fonction de la _tailleDonnes
	 * 
	 * @param _taille
	 * @param _tailleDonnees
	 * @param _densiteContrainte
	 */
	public CSP(int _taille, int _tailleDonnees, double _densiteContrainte) {
		this.taille = _taille;
		this.graph = new int[_taille][_taille];
		initGraoh();
		this.tailleDonnes = _tailleDonnees;
		this.ensemble = new CoupleEntier[_tailleDonnees][_tailleDonnees];
		suprLien(_densiteContrainte);
		initEnsemble();
	}

	/**
	 * Crée un tableau de taille _taille des liens entres tout les noeud et suprime
	 * un nombre de noeud en fonction de _densiteContrainte et crée le tableau des
	 * valeur possible en fonction de la _tailleDonnes et supprime des valeur en
	 * fonction de _dureteContraintes
	 * 
	 * @param _taille
	 * @param _tailleDonnees
	 * @param _densiteContrainte
	 */
	public CSP(int _taille, int _tailleDonnees, double _densiteContrainte, double _dureteContraintes) {
		this.taille = _taille;
		this.graph = new int[_taille][_taille];
		initGraoh();
		this.tailleDonnes = _tailleDonnees;
		suprLien(_densiteContrainte);
		this.ensemble = new CoupleEntier[_tailleDonnees][_tailleDonnees];
		initEnsemble();
		suprDomaine(_dureteContraintes);
	}

	public int[][] getGraph() {
		return graph;
	}

	public int getTailleDonnes() {
		return tailleDonnes;
	}

	public int getTaille() {
		return taille;
	}

	public CoupleEntier[][] getEnsemble() {
		return ensemble;
	}

	private void initGraoh() {
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				graph[i][j] = 1;
			}
			graph[i][i] = 0; // pas de lien avec sois même
		}
	}

	private void initEnsemble() {
		for (int i = 0; i < tailleDonnes; i++) {
			for (int j = 0; j < tailleDonnes; j++) {
				ensemble[i][j] = new CoupleEntier(i, j);
			}
		}
	}

	private void suprLien(double _densiteContrainte) {
		// nombre de lien * % de lien a supr
		int nombreLienSupr = (int) ((taille * taille - taille) * (1 - _densiteContrainte));
		int compt = 0;
		int i, j;
		while (compt < nombreLienSupr) {
			i = 0 + (int) (Math.random() * taille);
			j = 0 + (int) (Math.random() * taille);
			if (graph[i][j] != 0) {
				graph[i][j] = 0;
				compt++;
			}
		}
	}

	private void suprDomaine(double _dureteContraintes) {
		// nombre de valeur * % de lien a supr
		int nombreDomaineSupr = (int) ((tailleDonnes * tailleDonnes) * (1 - _dureteContraintes));
		int compt = 0;
		int i, j;
		while (compt < nombreDomaineSupr) {
			i = 0 + (int) (Math.random() * tailleDonnes);
			j = 0 + (int) (Math.random() * tailleDonnes);
			if (ensemble[i][j] != null) {
				ensemble[i][j] = null;
				compt++;
			}
		}
	}

}
