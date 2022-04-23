package Data;

import java.util.ArrayList;
import java.util.List;

public class LiveData {

    private final static LiveData INSTANCE = new LiveData();

    private final List<Person> personArray = new ArrayList<>();

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

    public static void removePersonAt(int index) {
        if (index >= INSTANCE.personArray.size()) return;

        INSTANCE.personArray.remove(index);
    }

    public static Person getPersonAt(int index) {
        return (index < INSTANCE.personArray.size()) ? INSTANCE.personArray.get(index) : null;
    }

}
