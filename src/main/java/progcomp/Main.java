package progcomp;

import java.util.ArrayList;

import progcomp.object.CSP;
import progcomp.utils.Fonction;

public class Main {

	static Fonction fonction;

	public static void main(String[] args) {
		fonction = new Fonction();
		CSP csp = fonction.creationCSP(5, 6, 0.5, 0.5);

		ArrayList<Integer> solution = new ArrayList<Integer>();
		if (csp != null) {
			solution = fonction.backTrackingChronologique(csp);
			if (solution != null) {
				for (int v : solution) {
					System.out.println(v);
				}
			} else {
				System.out.println("Aucune solution de trouvé");
			}
		}
	}

}
