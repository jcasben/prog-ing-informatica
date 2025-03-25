import jakarta.json.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("https://data.statistics.sk/api/v2/dataset/as1001rs/all,2020/UKAZ04/TOTAL?lang=en");
        try (InputStream is = url.openStream()) {
            JsonReader jsonReader = Json.createReader(is);
            JsonObject file = jsonReader.readObject();

            String label = file.getString("label");
            String note = file.getJsonObject("dimension")
                    .getJsonObject("as1001rs_ukaz")
                    .getJsonObject("category")
                    .getJsonObject("label")
                    .getString("UKAZ04");

            JsonObject index = file.getJsonObject("dimension")
                    .getJsonObject("as1001rs_rok")
                    .getJsonObject("category")
                    .getJsonObject("index");

            Map<Integer, String> reversedIndex = new LinkedHashMap<>();
            for (String key : index.keySet()) {
                int value = index.getInt(key);
                reversedIndex.put(value, key);
            }

            JsonArray values = file.getJsonArray("value");

            System.out.println(label + " - " + note);

            int i = 0;
            for (JsonValue value : values) {
                int chartValue = Integer.parseInt(value.toString()) / 100000;
                System.out.print(reversedIndex.get(i) + ": " + value + "\t");
                for (int j = 0; j < chartValue; j++) System.out.print("*");
                System.out.println();
                i++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
