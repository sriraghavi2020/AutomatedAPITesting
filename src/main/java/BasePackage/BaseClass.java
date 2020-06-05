package BasePackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {

	public static int reponse_code_200 = 200;
	public static int reponse_code_201 = 201;
	public static int reponse_code_400 = 400;
	public static int reponse_code_401 = 401;
	public static int reponse_code_404 = 404;
	public static int reponse_code_500 = 500;

	public Properties propField;

	public static String readPropsFile(String readProp) {
		String propField = "";
		try {
			Properties prop = new Properties();
			FileInputStream file = new FileInputStream(
					"C:\\Users\\Kiriti\\eclipse-workspace\\APITesting\\src\\main\\java\\FilePackage\\config.properties");
			prop.load(file);

			propField = prop.getProperty(readProp);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return propField;

	}
}
