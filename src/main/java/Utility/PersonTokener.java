package Utility;

import Data.Person;
import org.json.JSONObject;
import org.json.JSONTokener;

public class PersonTokener {

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
    private static final String JSON_OBJECT_KEY_PHONE_TYPE = "PHONE_TYPE";

    public static Person personFromJson(String jsonString) {
        final var person = new Person();
        final var jsonObject = new JSONObject(new JSONTokener(jsonString));
        final var jsonPerson = (JSONObject) jsonObject.get(JSON_OBJECT_KEY);

        final var jsonPersonName = (JSONObject) jsonPerson.get(JSON_OBJECT_KEY_NAME);
        person.setNameFirst(jsonPersonName.get(JSON_OBJECT_KEY_NAME_FIRST).toString());
        person.setNameLast(jsonPersonName.get(JSON_OBJECT_KEY_NAME_LAST).toString());

        final var jsonPersonBirthdate = (JSONObject) jsonPerson.get(JSON_OBJECT_KEY_BIRTHDATE);
        person.setBirthDay(Integer.parseInt(jsonPersonBirthdate.get(JSON_OBJECT_KEY_BIRTH_DAY).toString()));
        person.setBirthMonth(Integer.parseInt(jsonPersonBirthdate.get(JSON_OBJECT_KEY_BIRTH_MONTH).toString()));
        person.setBirthYear(Integer.parseInt(jsonPersonBirthdate.get(JSON_OBJECT_KEY_BIRTH_YEAR).toString()));

        final var jsonPersonPhone = (JSONObject) jsonPerson.get(JSON_OBJECT_KEY_PHONE);
        person.setPhoneType(jsonPersonPhone.get(JSON_OBJECT_KEY_PHONE_TYPE).toString());
        person.setPhoneAreaCode(jsonPersonPhone.get(JSON_OBJECT_KEY_PHONE_AREA_CODE).toString());
        person.setPhoneNumber(jsonPersonPhone.get(JSON_OBJECT_KEY_PHONE_NUMBER).toString());

        final var jsonPersonEmail = (JSONObject) jsonPerson.get(JSON_OBJECT_KEY_EMAIL);
        person.setEmailAddress(jsonPersonEmail.get(JSON_OBJECT_KEY_EMAIL_ADDRESS).toString());
        person.setRegisteredForUpdates(Boolean.parseBoolean(jsonPersonEmail.get(JSON_OBJECT_KEY_REGISTERED_FOR_UPDATES).toString()));

        return person;
    }

    public static String personToJson(Person person) {
        final var jsonObjectPerson = new JSONObject();

        final var jsonObjectPersonName = new JSONObject();
        jsonObjectPersonName.put(JSON_OBJECT_KEY_NAME_FIRST, person.getNameFirst());
        jsonObjectPersonName.put(JSON_OBJECT_KEY_NAME_LAST, person.getNameLast());
        jsonObjectPerson.put(JSON_OBJECT_KEY_NAME, jsonObjectPersonName);

        final var jsonObjectPersonBirthdate = new JSONObject();
        jsonObjectPersonBirthdate.put(JSON_OBJECT_KEY_BIRTH_DAY, person.getBirthDay());
        jsonObjectPersonBirthdate.put(JSON_OBJECT_KEY_BIRTH_MONTH, person.getBirthMonth());
        jsonObjectPersonBirthdate.put(JSON_OBJECT_KEY_BIRTH_YEAR, person.getBirthYear());
        jsonObjectPerson.put(JSON_OBJECT_KEY_BIRTHDATE, jsonObjectPersonBirthdate);

        final var jsonObjectPersonPhone = new JSONObject();
        jsonObjectPersonPhone.put(JSON_OBJECT_KEY_PHONE_TYPE, person.getPhoneType());
        jsonObjectPersonPhone.put(JSON_OBJECT_KEY_PHONE_AREA_CODE, person.getPhoneAreaCode());
        jsonObjectPersonPhone.put(JSON_OBJECT_KEY_PHONE_NUMBER, person.getPhoneNumber());
        jsonObjectPerson.put(JSON_OBJECT_KEY_PHONE, jsonObjectPersonPhone);

        final var jsonObjectPersonEmail = new JSONObject();
        jsonObjectPersonEmail.put(JSON_OBJECT_KEY_EMAIL_ADDRESS, person.getEmailAddress());
        jsonObjectPersonEmail.put(JSON_OBJECT_KEY_REGISTERED_FOR_UPDATES, person.isRegisteredForUpdates());
        jsonObjectPerson.put(JSON_OBJECT_KEY_EMAIL, jsonObjectPersonEmail);

        final var jsonObject = new JSONObject();
        jsonObject.put(JSON_OBJECT_KEY, jsonObjectPerson);
        return jsonObject.toString(3);
    }

}
