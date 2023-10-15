package parameters.Parameterization;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class JsonToHashMapExample {

    public static Map<String, Map<String, Map<String, Object>>> dataReader() {
        // Specify the path to your JSON file
        String pathToJson  = "C:\\Users\\fatim\\eclipse-workspace\\Parameterization\\src\\test\\java\\parameters\\Parameterization\\testJson.json";


        // Read JSON data from the file
        String jsonString = readFile(pathToJson);

        // Initialize Gson
        Gson gson = new Gson();

        // Define the Type for deserialization
        Type type = new TypeToken<Map<String, Map<String, Map<String, Object>>>>() {}.getType();

        // Parse JSON into a HashMap
        Map<String, Map<String, Map<String, Object>>> jsonMap = gson.fromJson(jsonString, type);

        // Access values from the HashMap
        Map<String, Object> testCase1Step1 = jsonMap.get("TestCase1").get("testStep1");
        String email = (String) testCase1Step1.get("email");
        String name = (String) testCase1Step1.get("name");
        Double age = (Double) testCase1Step1.get("age"); // Use Double for age

        // Print the values
        System.out.println("Email: " + email);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);

        return jsonMap;
    }

    // Helper method to read the contents of a file into a string
    private static String readFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

}
