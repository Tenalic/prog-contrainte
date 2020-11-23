package progcomp;

import progcomp.object.CSP;
import progcomp.utils.Fonction;

public class Main {
	
	static Fonction fonction;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		fonction =  new Fonction();
		CSP scp = fonction.creationCSP(5, 3, 0.5, 0.5);
		if(scp != null) {
			System.out.print("pas nul");
		}
	}

}
