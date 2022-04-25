import Data.Person;

public class PersonTestInheritable {

    protected static final String NAME_FIRST = "Joe";
    protected static final String NAME_LAST = "Bob";
    protected static final String PHONE_TYPE = "Cell";
    protected static final String PHONE_AREA_CODE = "303";
    protected static final String PHONE_NUMBER = "321-4321";
    protected static final String EMAIL_ADDRESS = "joe@bobmail.com";

    protected static final int BIRTH_DAY = 20;
    protected static final int BIRTH_MONTH = 2;
    protected static final int BIRTH_YEAR = 1990;

    protected static final boolean REGISTERED_FOR_UPDATES = true;

    protected Person createPerson() {
        var person = new Person();
        person.setName(NAME_FIRST, NAME_LAST);
        person.setBirthDate(BIRTH_DAY, BIRTH_MONTH, BIRTH_YEAR);
        person.setPhone(PHONE_TYPE, PHONE_AREA_CODE, PHONE_NUMBER);
        person.setEmailAddress(EMAIL_ADDRESS);
        person.setRegisteredForUpdates(REGISTERED_FOR_UPDATES);

        return person;
    }
    
}
