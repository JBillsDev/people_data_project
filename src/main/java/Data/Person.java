package Data;

final public class Person {

    private String nameFirst;
    private String nameLast;

    private String emailAddress;

    private String phoneAreaCode;
    private String phoneNumber;
    private String phoneType;

    private int birthDay;
    private int birthMonth;
    private int birthYear;

    private boolean registeredForUpdates;

    public Person() {

    }

    public static String createNameEntry(Person person) {
        return (person.getNameLast() + ", " + person.getNameFirst());
    }

    public int getBirthDay() {
        return birthDay;
    }

    public int getBirthMonth() {
        return birthMonth;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public String getNameFirst() {
        return nameFirst;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getNameLast() {
        return nameLast;
    }

    public String getPhoneAreaCode() {
        return phoneAreaCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPhoneType() { return phoneType; }

    public boolean isRegisteredForUpdates() {
        return registeredForUpdates;
    }

    public void setBirthDay(int birthDay) {
        this.birthDay = birthDay;
    }

    public void setBirthDate(int birthDay, int birthMonth, int birthYear) {
        this.setBirthDay(birthDay);
        this.setBirthMonth(birthMonth);
        this.setBirthYear(birthYear);
    }

    public void setBirthMonth(int birthMonth) {
        this.birthMonth = birthMonth;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setName(String nameFirst, String nameLast) {
        this.setNameFirst(nameFirst);
        this.setNameLast(nameLast);
    }

    public void setNameFirst(String nameFirst) {
        this.nameFirst = nameFirst;
    }

    public void setNameLast(String nameLast) {
        this.nameLast = nameLast;
    }

    public void setPhone(String phoneType, String phoneAreaCode, String phoneNumber) {
        this.setPhoneType(phoneType);
        this.setPhoneAreaCode(phoneAreaCode);
        this.setPhoneNumber(phoneNumber);
    }

    public void setPhoneAreaCode(String phoneAreaCode) {
        this.phoneAreaCode = phoneAreaCode;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPhoneType(String phoneType) { this.phoneType = phoneType; }

    public void setRegisteredForUpdates(boolean registeredForUpdates) {
        this.registeredForUpdates = registeredForUpdates;
    }

    @Override
    public String toString() {
        return (this.nameFirst + " " + this.nameLast + ", " + ((this.birthDay < 10) ? "0" : "") + this.birthDay + "/" +
                ((this.birthMonth < 10) ? "0" : "") + this.birthMonth + "/" + this.birthYear + ", " + this.emailAddress);
    }
}
