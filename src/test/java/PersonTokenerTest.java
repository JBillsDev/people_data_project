import Data.Person;
import Utility.PersonTokener;
import org.json.JSONObject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonTokenerTest extends PersonTestInheritable {
    
    private Person person;

    @BeforeEach
    protected void setup() {
        this.person = new Person();
        this.person.setName(NAME_FIRST, NAME_LAST);
        this.person.setBirthDate(20, 2, 1990);
        this.person.setPhone("Cell", "303", "321-4321");
        this.person.setEmailAddress("joe@bobmail.com");
        this.person.setRegisteredForUpdates(true);
    }

    @Test
    public void testPersonToJSONAndBack() {
        JSONObject jsonObjectPerson = PersonTokener.personToJson(this.person);
        var returnedPerson = PersonTokener.personFromJson(jsonObjectPerson.toString());

        assertEquals(returnedPerson.getNameFirst(), NAME_FIRST);
        assertEquals(returnedPerson.getNameLast(), NAME_LAST);

        assertEquals(returnedPerson.getBirthDay(), BIRTH_DAY);
        assertEquals(returnedPerson.getBirthMonth(), BIRTH_MONTH);
        assertEquals(returnedPerson.getBirthYear(), BIRTH_YEAR);

        assertEquals(returnedPerson.getPhoneType(), PHONE_TYPE);
        assertEquals(returnedPerson.getPhoneAreaCode(), PHONE_AREA_CODE);
        assertEquals(returnedPerson.getPhoneNumber(), PHONE_NUMBER);

        assertEquals(returnedPerson.getEmailAddress(), EMAIL_ADDRESS);
        assertEquals(returnedPerson.isRegisteredForUpdates(), REGISTERED_FOR_UPDATES);
    }

}
