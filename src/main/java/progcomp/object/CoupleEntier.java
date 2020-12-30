package progcomp.object;

public class CoupleEntier {

	private int a;

	private int b;

	public CoupleEntier(int a, int b) {
		this.setA(a);
		this.setB(b);
	}

	public CoupleEntier(CoupleEntier c) {
		this.setA(c.getA());
		this.setB(c.getB());
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

}
