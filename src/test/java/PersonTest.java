import Data.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonTest extends PersonTestInheritable {

    private static Person person;

    @BeforeEach
    protected void setup() {
        person = new Person();
    }

    @Test
    public void testName() {
        person.setName(NAME_FIRST, NAME_LAST);

        assertEquals(NAME_FIRST, person.getNameFirst());
        assertEquals(NAME_LAST, person.getNameLast());
    }

    @Test
    public void testBirthdate() {
        person.setBirthDate(BIRTH_DAY, BIRTH_MONTH, BIRTH_YEAR);

        assertEquals(BIRTH_DAY, person.getBirthDay());
        assertEquals(BIRTH_MONTH, person.getBirthMonth());
        assertEquals(BIRTH_YEAR, person.getBirthYear());
    }

    @Test
    public void testPhone() {
        person.setPhone(PHONE_TYPE, PHONE_AREA_CODE, PHONE_NUMBER);

        assertEquals(PHONE_TYPE, person.getPhoneType());
        assertEquals(PHONE_AREA_CODE, person.getPhoneAreaCode());
        assertEquals(PHONE_NUMBER, person.getPhoneNumber());
    }

    @Test
    public void testEmail() {
        person.setEmailAddress(EMAIL_ADDRESS);
        person.setRegisteredForUpdates(REGISTERED_FOR_UPDATES);

        assertEquals(EMAIL_ADDRESS, person.getEmailAddress());
        assertEquals(REGISTERED_FOR_UPDATES, person.isRegisteredForUpdates());
    }
}
