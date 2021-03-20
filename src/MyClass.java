import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MyClass {

	public int somma(int a, int b) {
		return a + b;
	}

	public int sum(String c, int a, String d, int b) {
		return a + b;
	}

	public String pappagallo(String parola) {
		return parola;
	}

	public static void main(String[] args) {
		MyClass mc = new MyClass();
		ExtractParameterForTest extract = new ExtractParameterForTest();

		/*
		 * for(int i =0; i < 50; i++) { Random rnd = new Random();
		 * mc.somma(rnd.nextInt(100), rnd.nextInt(75)); }
		 */
		mc.pappagallo("pappagallo");

		mc.sum("aaa", 10, "ppp", 17);

		HashMap<String, MaxMinTest[]> map = extract
				.extractParameterForTest(Paths.get(".\\src\\dataForMethod\\").toString());
		List<String> keyValues = new ArrayList<String>(map.keySet());

		HashMap<String, int[]> valuesBetweenMaxMinMap = new HashMap<String, int[]>();

		for (int i = 0; i < keyValues.size(); i++) {
			MaxMinTest[] mm = (map.get(keyValues.get(i)));
			System.out.println("\n" + keyValues.get(i));
			for (int j = 0; j < mm.length; j++) {
				System.out.print(mm[j] + ", ");
				valuesBetweenMaxMinMap.put(keyValues.get(i),
						extract.generateRandomValuesBetween(mm[j].getMin(), mm[j].getMax(), 50));
			}

		}
	}
}