package Data;

import java.util.ArrayList;

public class LiveData {

    private final static LiveData INSTANCE = new LiveData();

    private final ArrayList<Person> personArray = new ArrayList<>();

    private LiveData() {}

    public static void addPerson(Person person) {
        INSTANCE.personArray.add(person);
    }

    public static Person getPersonAt(int index) {
        return (index < INSTANCE.personArray.size()) ? INSTANCE.personArray.get(index) : null;
    }

    public static int getPersonCount() { return INSTANCE.personArray.size(); }

}
