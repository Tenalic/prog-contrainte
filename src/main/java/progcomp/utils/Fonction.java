package progcomp.utils;

import java.util.ArrayList;

import progcomp.object.CSP;
import progcomp.object.CoupleEntier;

/**
 * 
 * @author Stephane
 *
 */
public class Fonction {

	/**
	 * 
	 * @param nbVariable      nombre de noeud exemple : 5
	 * @param tailleDesDonnee valeur possible pour chaque noeud exemple : 3
	 *                        représente {1,2,3}
	 * @param d               nombre d'arc / nombre de lien du csp si complé (nombre
	 *                        de lien qu'on vas supr) exemples : 0.5 (50%)
	 * @param e               nombre de valeur qu'on vas supr. exemple : 0.5 (50%)
	 * @return
	 */
	public CSP creationCSP(int nbVariable, int tailleDesDonnee, double d, double e) {
		return new CSP(nbVariable, tailleDesDonnee, d, e);
	}

	/**
	 * algorithme de backTrackingChronologique
	 * 
	 * @param csp
	 */
	public ArrayList<Integer> backTrackingChronologique(CSP csp) {
		int i = 0;
		int compt;
		boolean back = false;
		ArrayList<Integer> solution = new ArrayList<Integer>();
		Integer x = 0;
		boolean ok;
		boolean fini = false;
		ArrayList<Integer> domaine1;
		while (i < csp.getTaille() && !fini) {
			ok = false;
			back = false;
			domaine1 = csp.getDomaines().get(i);
			while (!ok && !domaine1.isEmpty()) {
				x = domaine1.get(0);
				domaine1.remove(0);
				solution.add(x);
				if (solution.size() > 1) {
					compt = 0;
					for (int valeur : solution) {
						if (!back && (compt < csp.getListeDesValeursDomaine().get(i).size()
								&& csp.listeValueContaineCouple(new CoupleEntier(x, valeur), i, compt))) {
							ok = true;
						} else {
							ok = false;
							back = true;
						}
						compt++;
					}
				} else {
					ok = true;
				}
				if (ok == false) {
					solution.remove(i);
					csp.getDomaines().get(i).remove(x);
					back = false;
				}
			}
			if (ok == false) {
				i--; // retour arrière
				if (i < 0) {
					fini = true;
				}
			} else {
				i++;
			}
		}
		if (solution.size() == csp.getTaille()) {
			return solution;
		}
		return null;
	}

	public ArrayList<Integer> backJumping(CSP csp) {
		return null;
	}

	public ArrayList<Integer> ForwardChecking(CSP csp) {
		return null;
	}
}
