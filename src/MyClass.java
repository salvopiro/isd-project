import java.util.Random;

public class MyClass {
	public int somma(Numero numero, String commento) {
		return numero.getA() + numero.getB();
	}

	public int somma(int a, int b) {
		return a + b;
	}

	public String pappagallo(String parola) {
		return parola;
	}

	public static void main(String[] args) {
		MyClass mc = new MyClass();
		//Numero num = new Numero(14, 75);
		
		
		for(int i =0; i < 50; i++) {
			Random rnd = new Random();
			mc.somma(rnd.nextInt(100), rnd.nextInt(75));
		}
		
		mc.pappagallo("pappagallo");

	}
}