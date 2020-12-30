package progcomp.object;

import java.util.ArrayList;

/**
 * 
 * @author Stephane Donc je génère toutes les valeurs possible entre 2 sommet,
 *         puis j'en supprime x%, et sa forme mes contrainte. Donc dans mon algo
 *         je génère un couple qui est dans le domaine, et je regarde si se
 *         couple et dans les contrainte si il ne l'est pas backtracking
 * 
 */
public class CSP {

	/**
	 * nombre de noeud
	 */
	private int taille;

	/**
	 * lien entre les noeud 1 si lien 0 sinon
	 */
	private int graph[][];

	/**
	 * valeur possible pour chaque noeud
	 */
	private int tailleDonnes;

	/**
	 * liste pour chaque noeud des valeurs possibles (son domaine)
	 */
	private ArrayList<ArrayList<Integer>> domaines;

	/**
	 * matrice de liste des valeurs possibles pour chaque domaines :
	 * listeDesValeursDomaine.get(0).get(1) = optenir la liste des valeurs possible
	 * entre les varriables 1 et 2
	 */
	private ArrayList<ArrayList<ArrayList<CoupleEntier>>> listeDesValeursDomaine;

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
		initDomaine(_tailleDonnees, _taille);
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
		suprLien(_densiteContrainte);
		initDomaine(_tailleDonnees, _taille);
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
		suprLien(_densiteContrainte);
		this.tailleDonnes = _tailleDonnees;
		this.domaines = new ArrayList<ArrayList<Integer>>();
		initDomaine(_tailleDonnees, _taille);
		suprDomaine(_dureteContraintes);
		initListDesValeursDomaine();
	}

	/**
	 * fait une copie de la csp en param
	 * 
	 * @param csp : csp a compie
	 */
	public CSP(CSP csp) {
		this.setDomaines(csp.getDomaines());
		this.setGraph(csp.getGraph());
		this.setTaille(csp.getTaille());
		this.setTailleDonnes(csp.getTailleDonnes());
		this.setListeDesValeursDomaine(csp.getListeDesValeursDomaine());
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

	public ArrayList<ArrayList<Integer>> getDomaines() {
		return domaines;
	}

	public void setDomaines(ArrayList<ArrayList<Integer>> domaines) {
		this.domaines = domaines;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public void setGraph(int[][] graph) {
		this.graph = graph;
	}

	public void setTailleDonnes(int tailleDonnes) {
		this.tailleDonnes = tailleDonnes;
	}

	private void initGraoh() {
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				graph[i][j] = 1;
			}
			graph[i][i] = 0; // pas de lien avec sois même
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

	private void initDomaine(int tailleDomaine, int nombreVariable) {
		for (int i = 0; i < nombreVariable; i++) {
			this.domaines.add(new ArrayList<Integer>());
			for (int j = 1; j < tailleDomaine; j++) {
				this.domaines.get(i).add(j);
			}
		}
	}

	private void suprDomaineOld(double _dureteContraintes) {
		// nombre de valeur * % de lien a supr
		int nombreDomaineSupr = (int) ((tailleDonnes * tailleDonnes) * (1 - _dureteContraintes));
		int compt = 0;
		int i, valeurSupr, indice, indiceCourant;
		while (compt < nombreDomaineSupr) {
			indiceCourant = 0;
			indice = -1;
			i = 0 + (int) (Math.random() * taille);
			valeurSupr = (int) (Math.random() * tailleDonnes);
			if (domaines.get(i) != null) {
				for (Integer valeur : domaines.get(i)) {
					if (valeur == valeurSupr) {
						indice = indiceCourant;
					} else {
						indiceCourant++;
					}
				}
				if (indice != -1) {
					domaines.get(i).remove(indice);
					compt++;
				}
			}
		}
	}

	private void suprDomaine(double _dureteContraintes) {
		// nombre de valeur * % de lien a supr
		int nombreDomaineSupr = (int) (this.tailleDonnes * (1 - _dureteContraintes));
		int value;
		for (int i = 0; i < this.domaines.size(); i++) {
			for (int j = 0; j < nombreDomaineSupr; j++) {
				value = (int) (Math.random() * this.domaines.get(i).size());
				this.domaines.get(i).remove(value);
			}
		}
	}

	public void initListDesValeursDomaine() {
		this.listeDesValeursDomaine = new ArrayList<ArrayList<ArrayList<CoupleEntier>>>();
		ArrayList<CoupleEntier> liste = new ArrayList<CoupleEntier>();
		for (int i = 0; i < this.taille; i++) {
			this.listeDesValeursDomaine.add(new ArrayList<ArrayList<CoupleEntier>>());
			for (int j = 0; j < this.taille; j++) {
				if (graph[i][j] == 1) {
					liste = genererListDesCoupleEntier(domaines.get(i), domaines.get(j));
					this.listeDesValeursDomaine.get(i).add(liste);
				}
			}
		}
	}

	public ArrayList<CoupleEntier> genererListDesCoupleEntier(ArrayList<Integer> domaine1,
			ArrayList<Integer> domaine2) {
		ArrayList<CoupleEntier> reponse = new ArrayList<CoupleEntier>();
		for (int a : domaine1) {
			for (int b : domaine2) {
				reponse.add(new CoupleEntier(a, b));
			}
		}
		return reponse;
	}

	public ArrayList<ArrayList<ArrayList<CoupleEntier>>> getListeDesValeursDomaine() {
		return listeDesValeursDomaine;
	}

	public void setListeDesValeursDomaine(ArrayList<ArrayList<ArrayList<CoupleEntier>>> listeDesValeursDomaine) {
		this.listeDesValeursDomaine = listeDesValeursDomaine;
	}

	public boolean listeValueContaineCouple(CoupleEntier couple, int index, int value) {
		for (CoupleEntier c : this.listeDesValeursDomaine.get(index).get(value)) {
			if (c.getA() == couple.getA() && c.getB() == couple.getB()) {
				return true;
			}
		}
		return false;
	}

}
