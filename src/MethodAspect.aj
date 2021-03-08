import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public aspect MethodAspect {
	pointcut p1() : 
		execution (* *.*(..)) && !within(MethodAspect);

	before() : p1() {
		Object[] args = thisJoinPoint.getArgs();
		String initialSignature = thisJoinPoint.getSignature().toString();

		if (args.length != 0 && !initialSignature.contains("main(String[])")) {
			String pathFile = createFile(initialSignature);
			insertDataInCsv(pathFile, args);
		}
	}

	private String createFile(String signature) {
		String methodSignature = signature.replaceAll("\\s+", "");

		methodSignature = methodSignature.replaceAll("[^\\w]", "_");
		String[] parameter = methodSignature.split("_");
		String pathFile = Paths.get(
				".\\src\\dataForMethod\\" + methodSignature + ".csv")
				.toString();

		try {
			File myFile = new File(pathFile);
			if (myFile.createNewFile()) {
				FileWriter myWriter = new FileWriter(pathFile, true);

				for (int i = 2; i < parameter.length; i++) {
					myWriter.append(parameter[i] + (i < parameter.length - 1 ? "," : "\n"));

					//second commit 
				}
				myWriter.flush();
				myWriter.close();
			}
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return pathFile;
	}

	private void insertDataInCsv(String pathFile, Object[] args) {

		try {
			FileWriter myWriter = new FileWriter(pathFile, true);
			for (int i = 0; i < args.length; i++)
				myWriter.append(args[i].toString() + (i != args.length - 1 ? "," : "\n"));

			myWriter.flush();
			myWriter.close();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
