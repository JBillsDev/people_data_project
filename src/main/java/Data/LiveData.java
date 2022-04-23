package Data;

import Utility.PersonTokener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.tinylog.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

final public class LiveData {

    private final static LiveData INSTANCE = new LiveData();

    private final List<Person> personArray = new ArrayList<>();

    private static final String JSON_FILE_NAME = "personData.json";

    private LiveData() {}

    public static void addPerson(Person person) {
        final String newEntry = Person.createNameEntry(person);
        final int personArraySize = INSTANCE.personArray.size();
        for (int iter = 0; iter < personArraySize; ++iter) {
            if (Person.createNameEntry(INSTANCE.personArray.get(iter)).compareTo(newEntry) > 0) {
                INSTANCE.personArray.add(iter, person);
                return;
            }
        }

        INSTANCE.personArray.add(person);
    }

    public static void clearData() {
        INSTANCE.personArray.clear();
    }

    public static Person getPersonAt(int index) {
        return (index < INSTANCE.personArray.size()) ? INSTANCE.personArray.get(index) : null;
    }

    public static String[] getPersonListing() {
        final int personArraySize = INSTANCE.personArray.size();
        final String[] personListing = new String[personArraySize];

        for (int iter = 0; iter < personArraySize; ++iter) {
            personListing[iter] = Person.createNameEntry(INSTANCE.personArray.get(iter));
        }

        return personListing;
    }

    public static void load() {
        final File jsonFile = new File(JSON_FILE_NAME);
        if (!jsonFile.exists()) return;

        // Read JSON file and convert it to a single string to convert into JSON object
        final JSONTokener jsonTokenizer;
        try {
            final var bufferedReader = new BufferedReader(new FileReader(jsonFile));
            String bufferedReaderLineNext;
            StringBuilder builder = new StringBuilder();
            while ((bufferedReaderLineNext = bufferedReader.readLine()) != null) {
                builder.append(bufferedReaderLineNext);
            }

            jsonTokenizer = new JSONTokener(builder.toString());
            bufferedReader.close();
        } catch (IOException exception) {
            Logger.error(exception);
            return;
        }

        // Attempt to parse config file JSON
        try {
            final var jsonPersonArray = new JSONArray(jsonTokenizer);
            for (Object object : jsonPersonArray) {
                INSTANCE.personArray.add(PersonTokener.personFromJson((object).toString()));
            }
        } catch (JSONException exception) {
            Logger.warn("failed to parse config file");
            Logger.error(exception);
        }
    }

    public static void removePersonAt(int index) {
        if (index >= INSTANCE.personArray.size()) return;

        INSTANCE.personArray.remove(index);
    }

    public static void save() {
        final JSONArray jsonPersonArray = new JSONArray();

        for (Person person : INSTANCE.personArray) {
            jsonPersonArray.put(PersonTokener.personToJson(person));
        }

        try {
            final var jsonFile = new File(JSON_FILE_NAME);
            if (jsonFile.exists()) {
                if (!jsonFile.delete()) {
                    Logger.warn("failed to delete config");
                }
            }

            if (jsonFile.createNewFile()) {
                final var fileWriter = new FileWriter(jsonFile);
                fileWriter.write(jsonPersonArray.toString(3));
                fileWriter.close();
            }
            else {
                Logger.error("failed to create config file");
            }
        } catch (IOException exception) {
            Logger.error(exception);
        }
    }

    public static void updatePersonAt(int index, Person person) {
        if (index >= INSTANCE.personArray.size()) return;

        INSTANCE.personArray.remove(index);
        addPerson(person);
    }

}
