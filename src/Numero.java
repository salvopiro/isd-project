public class Numero {
	private int a;
	private int b;

	public Numero(int a, int b) {
		this.a = a;
		this.b = b;
	}

	public int getA() {
		return a;
	}

	public int getB() {
		return b;
	}

	@Override
	public String toString() {

		return this.a + "," + this.b;

	}
}
