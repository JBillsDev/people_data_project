import Data.LiveData;
import Data.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LiveDataTest extends PersonTestInheritable {

    private Person person;

    @BeforeEach
    protected void setup() {
        person = createPerson();
    }

    @AfterEach
    protected void cleanUp() {
        LiveData.clearData();
    }

    @Test
    public void testPersonArrayAdd() {
        assertEquals(0, LiveData.getPersonArraySize());

        LiveData.addPersonLastNameAlphabetized(person);
        assertEquals(1, LiveData.getPersonArraySize());

        LiveData.addPersonLastNameAlphabetized(person);
        LiveData.addPersonLastNameAlphabetized(person);
        assertEquals(3, LiveData.getPersonArraySize());
    }

    @Test
    public void testPersonArrayGetPersonAt() {
        Person person1 = createPerson();
        person1.setName("Alan", "Abba");
        LiveData.addPersonLastNameAlphabetized(person);

        Person person2 = createPerson();
        person2.setName("Susan", "Summers");
        LiveData.addPersonLastNameAlphabetized(person2);

        Person person3 = createPerson();
        person3.setName("Jimmy", "John");
        LiveData.addPersonLastNameAlphabetized(person3);

        assertEquals(person, LiveData.getPersonAt(0));
        assertEquals(person2, LiveData.getPersonAt(2));
        assertEquals(person3, LiveData.getPersonAt(1));
    }

    @Test
    public void testPersonArrayRemoveAt() {
        Person person1 = createPerson();
        person1.setName("Alan", "Abba");
        LiveData.addPersonLastNameAlphabetized(person1);

        Person person2 = createPerson();
        person2.setName("Susan", "Summers");
        LiveData.addPersonLastNameAlphabetized(person2);

        Person person3 = createPerson();
        person3.setName("Jimmy", "John");
        LiveData.addPersonLastNameAlphabetized(person3);

        assertEquals(3, LiveData.getPersonArraySize());
        LiveData.removePersonAt(1);
        assertEquals(2, LiveData.getPersonArraySize());

        assertEquals(person1, LiveData.getPersonAt(0));
        assertEquals(person2, LiveData.getPersonAt(1));
        assertNull(LiveData.getPersonAt(2));
    }

    @Test
    public void updatePersonAt() {
        Person person1 = createPerson();
        person1.setName("Alan", "Abba");
        LiveData.addPersonLastNameAlphabetized(person1);

        Person person2 = createPerson();
        person2.setName("Susan", "Summers");
        LiveData.addPersonLastNameAlphabetized(person2);

        Person person3 = createPerson();
        person3.setName("Jimmy", "John");
        LiveData.addPersonLastNameAlphabetized(person3);

        person1.setName("Lyle", "Lemon");
        LiveData.updatePersonAt(0, person1);

        assertEquals(person3, LiveData.getPersonAt(0));
        assertEquals(person1, LiveData.getPersonAt(1));
        assertEquals(person2, LiveData.getPersonAt(2));
    }

}
