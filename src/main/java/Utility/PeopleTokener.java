package Utility;

import Data.Person;
import org.json.JSONObject;

public class PeopleTokener {

    private static final String JSON_OBJECT_KEY = "PERSON";

    private static final String JSON_OBJECT_KEY_BIRTHDATE = "BIRTH_DATE";
    private static final String JSON_OBJECT_KEY_BIRTH_DAY = "BIRTH_DAY";
    private static final String JSON_OBJECT_KEY_BIRTH_MONTH = "BIRTH_MONTH";
    private static final String JSON_OBJECT_KEY_BIRTH_YEAR = "BIRTH_YEAR";

    private static final String JSON_OBJECT_KEY_EMAIL = "EMAIL";
    private static final String JSON_OBJECT_KEY_EMAIL_ADDRESS = "EMAIL_ADDRESS";
    private static final String JSON_OBJECT_KEY_REGISTERED_FOR_UPDATES = "REGISTERED_FOR_UPDATES";

    private static final String JSON_OBJECT_KEY_NAME = "NAME";
    private static final String JSON_OBJECT_KEY_NAME_FIRST = "NAME_FIRST";
    private static final String JSON_OBJECT_KEY_NAME_LAST = "NAME_LAST";

    private static final String JSON_OBJECT_KEY_PHONE = "PHONE";
    private static final String JSON_OBJECT_KEY_PHONE_AREA_CODE = "PHONE_AREA_CODE";
    private static final String JSON_OBJECT_KEY_PHONE_NUMBER = "PHONE_NUMBER";

    public static String personToJson(Person person) {
        var jsonObjectPerson = new JSONObject();

        var jsonObjectPersonName = new JSONObject();
        jsonObjectPersonName.put(JSON_OBJECT_KEY_NAME_FIRST, person.getNameFirst());
        jsonObjectPersonName.put(JSON_OBJECT_KEY_NAME_LAST, person.getNameLast());
        jsonObjectPerson.put(JSON_OBJECT_KEY_NAME, jsonObjectPersonName);

        var jsonObjectPersonBirthdate = new JSONObject();
        jsonObjectPersonBirthdate.put(JSON_OBJECT_KEY_BIRTH_DAY, person.getBirthDay());
        jsonObjectPersonBirthdate.put(JSON_OBJECT_KEY_BIRTH_MONTH, person.getBirthMonth());
        jsonObjectPersonBirthdate.put(JSON_OBJECT_KEY_BIRTH_YEAR, person.getBirthYear());
        jsonObjectPerson.put(JSON_OBJECT_KEY_BIRTHDATE, jsonObjectPersonBirthdate);

        var jsonObjectPersonPhone = new JSONObject();
        jsonObjectPersonPhone.put(JSON_OBJECT_KEY_PHONE_AREA_CODE, person.getPhoneAreaCode());
        jsonObjectPersonPhone.put(JSON_OBJECT_KEY_PHONE_NUMBER, person.getPhoneNumber());
        jsonObjectPerson.put(JSON_OBJECT_KEY_PHONE, jsonObjectPersonPhone);

        var jsonObjectPersonEmail = new JSONObject();
        jsonObjectPersonEmail.put(JSON_OBJECT_KEY_EMAIL_ADDRESS, person.getEmailAddress());
        jsonObjectPersonEmail.put(JSON_OBJECT_KEY_REGISTERED_FOR_UPDATES, person.isRegisteredForUpdates());
        jsonObjectPerson.put(JSON_OBJECT_KEY_EMAIL, jsonObjectPersonEmail);

        var jsonObject = new JSONObject();
        jsonObject.put(JSON_OBJECT_KEY, jsonObjectPerson);
        return jsonObject.toString(3);
    }

}
