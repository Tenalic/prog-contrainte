package progcomp.utils;

import progcomp.object.CSP;

/**
 * 
 * @author Stephane
 *
 */
public class Fonction {

	/**
	 * 
	 * @param nbVariable        nombre de noeud exemple : 5
	 * @param tailleDesDonnee   valeur possible pour chaque noeud exemple : 3
	 *                          représente {1,2,3}
	 * @param d nombre d'arc / nombre de lien du csp si complé
	 *                          (nombre de lien qu'on vas supr) exemples : 0.5 (50%)
	 * @param e nombre de valeur qu'on vas supr. exemple : 0.5 (50%)
	 * @return
	 */
	public CSP creationCSP(int nbVariable, int tailleDesDonnee, double d, double e) {
		return new CSP(nbVariable, tailleDesDonnee, d, e);
	}

}
