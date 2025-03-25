import jakarta.json.*;
import jakarta.json.stream.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws URISyntaxException, MalformedURLException {
        URL url = new URI("https://data.statistics.sk/api/v2/dataset/as1001rs/all,2020/UKAZ04/TOTAL?lang=en").toURL();
        parseJsonOMApi(url);
        System.out.println("\n----------------------------------------------------------------------------------\n");
        parseJsonStreamApi(url);
    }

    private static void parseJsonOMApi(URL url) {
        try (InputStream is = url.openStream()) {
            JsonReader jsonReader = Json.createReader(is);
            JsonObject file = jsonReader.readObject();

            String label = file.getString("label");
            String note = file.getJsonObject("dimension").getJsonObject("as1001rs_ukaz").getJsonObject("category").getJsonObject("label").getString("UKAZ04");

            JsonObject index = file.getJsonObject("dimension").getJsonObject("as1001rs_rok").getJsonObject("category").getJsonObject("index");

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

    private static void parseJsonStreamApi(URL url) {
        try (
                InputStream is = url.openStream();
                JsonParser parser = Json.createParser(is)
        ) {
            String currentKey = null;
            String label = "";
            String note = "";
            Map<Integer, String> reversedIndex = new LinkedHashMap<>();
            Map<Integer, Integer> values = new LinkedHashMap<>();
            boolean insideIndex = false;
            boolean insideValueArray = false;
            int valueIndex = 0;

            while (parser.hasNext()) {
                JsonParser.Event e = parser.next();

                switch (e) {
                    case KEY_NAME -> {
                        currentKey = parser.getString();
                        if ("index".equals(currentKey) && reversedIndex.isEmpty()) insideIndex = true;
                        else if ("value".equals(currentKey)) insideValueArray = true;
                    }
                    case VALUE_STRING -> {
                        if ("label".equals(currentKey) && label.isEmpty()) label = parser.getString();
                        else if ("UKAZ04".equals(currentKey) && note.isEmpty()) note = parser.getString();
                    }
                    case VALUE_NUMBER -> {
                        if (insideIndex) {
                            int indexValue = parser.getInt();
                            reversedIndex.put(indexValue, currentKey);
                        } else if (insideValueArray) values.put(valueIndex++, parser.getInt());
                    }
                    case END_OBJECT -> insideIndex = false;
                    case END_ARRAY -> insideValueArray = false;
                }
            }

            System.out.println(label + " - " + note);

            values.forEach((i, val) -> {
                int chartValue = val / 100000;
                System.out.print(reversedIndex.get(i) + ": " + val + "\t");
                for (int j = 0; j < chartValue; j++) {
                    System.out.print("*");
                }
                System.out.println();
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
