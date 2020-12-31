package progcomp;

import java.util.ArrayList;

import progcomp.object.CSP;
import progcomp.utils.Fonction;

public class Main {

	static Fonction fonction;

	public static void main(String[] args) {
		fonction = new Fonction();
		for (int i = 0; i < 100; i++) {

			CSP csp = fonction.creationCSP(10, 15, 0.1, 0.1);

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

}
