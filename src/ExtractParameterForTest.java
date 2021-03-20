import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.BufferedReader;

public class ExtractParameterForTest {

	private List<String> listFile(String pathFolder) {
		// System.out.println(pathFolder);
		List<String> fileInFolder = new ArrayList<String>();
		File folder = new File(pathFolder);
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			if (file.isFile()) {
				// System.out.println(file.getName());
				fileInFolder.add(file.getName());
			}
		}
		return fileInFolder;
	}

	public HashMap<String, MaxMinTest[]> extractParameterForTest(String pathFolder) {
		List<String> fileInFolder = listFile(pathFolder);
		System.out.println(fileInFolder.size());

		HashMap<String, MaxMinTest[]> map = new HashMap<>();
		try {
			
			for (String nameFile : fileInFolder) {
				System.out.println(nameFile);
				File myFile = new File(pathFolder + "\\" + nameFile);
				if (!myFile.createNewFile()) {
					BufferedReader reader = new BufferedReader(new FileReader(myFile));
					String line = "";
					line = reader.readLine();
					System.out.println(line);
					if(line == null) continue;
					String[] parameterType = line.split(",");
					
					
					// controllo il numero di parametri interi nel metodo e salvo la posizione del parametro
					int numberOfIntParameter = 0;
					int[] positionOfIntParameter = new int[parameterType.length];

					for (int i = 0; i < parameterType.length; i++) {
						if (parameterType[i].contains("int")) {
							positionOfIntParameter[numberOfIntParameter++] = i;
						}
					}
					
					if (numberOfIntParameter == 0)
						continue;
					
					MaxMinTest[] maxMin = new MaxMinTest[numberOfIntParameter];
					for (int i = 0; i < numberOfIntParameter; i++) {
						maxMin[i] = new MaxMinTest();
					}
					map.put(nameFile, maxMin);

					// leggo tutte le righe e controllo se il parametro i-esimo è max o min, con
					// l'array positionOfIntParameter prendo solamente i parametri interi
					while ((line = reader.readLine()) != null) {
						String[] parameter = line.split(",");
						for (int i = 0; i < numberOfIntParameter; i++) {
							//System.out.println(parameter[positionOfIntParameter[i]] + " i = " + i);
							map.get(nameFile)[i].setMax(
									Integer.parseInt(parameter[positionOfIntParameter[i]]) > map.get(nameFile)[i]
											.getMax() ? Integer.parseInt(parameter[positionOfIntParameter[i]])
													: map.get(nameFile)[i].getMax());

							map.get(nameFile)[i].setMin(
									Integer.parseInt(parameter[positionOfIntParameter[i]]) < map.get(nameFile)[i]
											.getMin() ? Integer.parseInt(parameter[positionOfIntParameter[i]])
													: map.get(nameFile)[i].getMin());
						}
					}
					reader.close();
				}

			}
		} catch (

		Exception e) {
			System.out.println(e);
		}
		return map;

	}

	public int[] generateRandomValuesBetween(int min, int max, int numberOfValues) {
		int[] values = new int[numberOfValues];
		for (int i = 0; i < numberOfValues; i++)
			values[i] = (int) (Math.random() * (max - min + 1) + min + 1);
		return values;
	}
}
