package GUI;

import Data.LiveData;
import Data.Person;
import Utility.PersonTokener;
import org.tinylog.Logger;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.YearMonth;

final public class JPanelPersonEditor {

    final int NAME_LENGTH_MAX = 12;

    private final JPanel panelRoot;

    private final JCheckBox checkBoxRegisteredForUpdates = new JCheckBox();
    private final JComboBox<Integer> comboBoxBirthDay = new JComboBox<>(), comboBoxBirthYear = new JComboBox<>();
    private final JComboBox<String> comboBoxBirthMonth = new JComboBox<>(), comboBoxPhoneType = new JComboBox<>();
    private final JComboBox<String> comboBoxPerson = new JComboBox<>();
    private final JTextField textFieldEmailAddress = new JTextField(20);
    private final JTextField textFieldNameFirst = new JTextField(15), textFieldNameLast = new JTextField(15);
    private final JTextField textFieldPhoneAreaCode = new JTextField(3), textFieldPhoneNumber = new JTextField(8);

    public JPanelPersonEditor() {
        final Dimension dimensionFormPanelPreferredSize = new Dimension(440, 54);

        this.panelRoot = new JPanel();

        final var panelForm = new JPanel();
        panelForm.setLayout(new BoxLayout(panelForm, BoxLayout.PAGE_AXIS));

        panelForm.add(this.createPanelName(dimensionFormPanelPreferredSize));
        panelForm.add(this.createFormPanelSpacer());
        panelForm.add(this.createPanelDateOfBirth(dimensionFormPanelPreferredSize));
        panelForm.add(this.createFormPanelSpacer());
        panelForm.add(this.createPanelPhoneNumber(dimensionFormPanelPreferredSize));
        panelForm.add(this.createFormPanelSpacer());
        panelForm.add(this.createPanelEmail(dimensionFormPanelPreferredSize));
        panelForm.add(this.createFormPanelSpacer());
        panelForm.add(this.createPanelFormSubmit(dimensionFormPanelPreferredSize));

        this.panelRoot.add(panelForm);
    }

    private void comboBoxPersonAdd(Person person) {
        final int comboBoxSize = this.comboBoxPerson.getItemCount();
        final String newItem = (person.getNameLast() + ", " + person.getNameFirst());
        for (int iter = 1; iter < comboBoxSize; ++iter) {
            if (this.comboBoxPerson.getItemAt(iter).compareTo(newItem) > 0) {
                this.comboBoxPerson.insertItemAt(newItem, iter);
                return;
            }
        }

        this.comboBoxPerson.addItem(newItem);
    }

    private JPanel createPanelDateOfBirth(Dimension dimensionFormPanelPreferredSizeReference) {
        final String TEXT_LABEL_BIRTHDATE = "     Birthdate";
        final String TEXT_LABEL_BIRTHDATE_DAY = "Day: ";
        final String TEXT_LABEL_BIRTHDATE_MONTH = "Month: ";
        final String TEXT_LABEL_BIRTHDATE_YEAR = "Year: ";

        final var panelDateOfBirth = new JPanel();
        panelDateOfBirth.setPreferredSize(dimensionFormPanelPreferredSizeReference);
        panelDateOfBirth.setLayout(new BoxLayout(panelDateOfBirth, BoxLayout.PAGE_AXIS));
        panelDateOfBirth.setBorder(new LineBorder(Color.BLACK));

        final var panelDateOfBirthTitle = new JPanel();
        panelDateOfBirthTitle.setLayout(new BoxLayout(panelDateOfBirthTitle, BoxLayout.LINE_AXIS));
        final var labelDateOfBirthTitle = new JLabel(TEXT_LABEL_BIRTHDATE);
        panelDateOfBirthTitle.add(labelDateOfBirthTitle);
        panelDateOfBirthTitle.add(Box.createHorizontalGlue());
        panelDateOfBirth.add(panelDateOfBirthTitle);

        final var panelDateOfBirthBody = new JPanel();
        panelDateOfBirthBody.setLayout(new BoxLayout(panelDateOfBirthBody, BoxLayout.LINE_AXIS));

        final var listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);

        final var panelBirthYear = new JPanel();
        final var labelDateOfBirthYear = new JLabel(TEXT_LABEL_BIRTHDATE_YEAR);
        panelBirthYear.add(labelDateOfBirthYear);
        this.comboBoxBirthYear.setRenderer(listRenderer);
        this.comboBoxBirthYear.addActionListener(event -> updateDayOfMonth());
        int yearCurrent = LocalDate.now().getYear();
        int yearEnd = yearCurrent - 120;
        for (int yearIter = yearCurrent; yearIter > yearEnd; --yearIter) {
            this.comboBoxBirthYear.addItem(yearIter);
        }
        panelBirthYear.add(this.comboBoxBirthYear);
        panelDateOfBirthBody.add(panelBirthYear);

        final var panelBirthMonth = new JPanel();
        final var labelDateOfBirthMonth = new JLabel(TEXT_LABEL_BIRTHDATE_MONTH);
        panelBirthMonth.add(labelDateOfBirthMonth);
        this.comboBoxBirthMonth.addActionListener(event -> updateDayOfMonth());
        this.comboBoxBirthMonth.setRenderer(listRenderer);
        this.comboBoxBirthMonth.addItem("Jan");
        this.comboBoxBirthMonth.addItem("Feb");
        this.comboBoxBirthMonth.addItem("Mar");
        this.comboBoxBirthMonth.addItem("Apr");
        this.comboBoxBirthMonth.addItem("May");
        this.comboBoxBirthMonth.addItem("Jun");
        this.comboBoxBirthMonth.addItem("Jul");
        this.comboBoxBirthMonth.addItem("Aug");
        this.comboBoxBirthMonth.addItem("Sep");
        this.comboBoxBirthMonth.addItem("Oct");
        this.comboBoxBirthMonth.addItem("Nov");
        this.comboBoxBirthMonth.addItem("Dec");
        panelBirthMonth.add(this.comboBoxBirthMonth);
        panelDateOfBirthBody.add(panelBirthMonth);

        final var panelBirthDay = new JPanel();
        final var labelDateOfBirthDay = new JLabel(TEXT_LABEL_BIRTHDATE_DAY);
        panelBirthDay.add(labelDateOfBirthDay);
        this.comboBoxBirthDay.setRenderer(listRenderer);
        this.updateDayOfMonth();
        panelBirthDay.add(this.comboBoxBirthDay);
        panelDateOfBirthBody.add(panelBirthDay);
        panelDateOfBirth.add(panelDateOfBirthBody);
        return panelDateOfBirth;
    }

    private JPanel createPanelEmail(Dimension dimensionFormPanelPreferredSizeReference) {
        final String TEXT_LABEL_EMAIL_TITLE = "     Email Address";
        final String TEXT_LABEL_EMAIL_ADDRESS = "Email: ";
        final String TEXT_LABEL_REGISTERED_FOR_UPDATES = "Register for Updates: ";

        final var panelEmail = new JPanel();
        panelEmail.setPreferredSize(dimensionFormPanelPreferredSizeReference);
        panelEmail.setLayout(new BoxLayout(panelEmail, BoxLayout.PAGE_AXIS));
        panelEmail.setBorder(new LineBorder(Color.BLACK));

        final var panelEmailTitle = new JPanel();
        panelEmailTitle.setLayout(new BoxLayout(panelEmailTitle, BoxLayout.LINE_AXIS));
        final var labelEmailTitle = new JLabel(TEXT_LABEL_EMAIL_TITLE);
        panelEmailTitle.add(labelEmailTitle);
        panelEmailTitle.add(Box.createHorizontalGlue());
        panelEmail.add(panelEmailTitle);
        final var panelEmailBody = new JPanel();
        final var labelEmailAddress = new JLabel(TEXT_LABEL_EMAIL_ADDRESS);
        panelEmailBody.add(labelEmailAddress);
        panelEmailBody.add(this.textFieldEmailAddress);

        this.textFieldEmailAddress.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent event) {
                char newChar = event.getKeyChar();
                if (newChar == ' ') event.consume();
            }
        });

        final var labelRegisterForUpdates = new JLabel(TEXT_LABEL_REGISTERED_FOR_UPDATES);
        panelEmailBody.add(labelRegisterForUpdates);
        this.checkBoxRegisteredForUpdates.setSelected(true);
        panelEmailBody.add(this.checkBoxRegisteredForUpdates);
        panelEmail.add(panelEmailBody);

        return panelEmail;
    }

    private JPanel createPanelFormSubmit(Dimension dimensionFormPanelPreferredSizeReference) {
        final String TEXT_BUTTON = "Submit";

        final var panelFormSubmit = new JPanel();
        panelFormSubmit.setPreferredSize(dimensionFormPanelPreferredSizeReference);
        panelFormSubmit.setLayout(new BoxLayout(panelFormSubmit, BoxLayout.PAGE_AXIS));
        panelFormSubmit.setBorder(new LineBorder(Color.BLACK));

        final var panelFormSubmitBody = new JPanel();
        StringBuilder stringBuilderEmptySelection = new StringBuilder();

        // Set the width of the person combo box by creating string of max name size, plus a comma and space
        final int MAX_COMBO_BOX_LENGTH = (this.NAME_LENGTH_MAX * 2) + 2;
        stringBuilderEmptySelection.append("Select a person");
        stringBuilderEmptySelection.append(" ".repeat(Math.max(0, MAX_COMBO_BOX_LENGTH - stringBuilderEmptySelection.length())));

        this.comboBoxPerson.addItem(stringBuilderEmptySelection.toString());
        panelFormSubmitBody.add(this.comboBoxPerson);

        final var buttonFormSubmit = new JButton(TEXT_BUTTON);
        buttonFormSubmit.addActionListener(event -> this.formSubmit());
        panelFormSubmitBody.add(buttonFormSubmit);

        panelFormSubmit.add(panelFormSubmitBody);
        return panelFormSubmit;
    }

    private JPanel createPanelName(Dimension dimensionFormPanelPreferredSizeReference) {
        final String TEXT_LABEL_PERSON_NAME = "     Name";
        final String TEXT_LABEL_PERSON_NAME_FIRST = "First:";
        final String TEXT_LABEL_PERSON_NAME_LAST = "Last:";

        final var panelName = new JPanel();
        panelName.setPreferredSize(dimensionFormPanelPreferredSizeReference);
        panelName.setLayout(new BoxLayout(panelName, BoxLayout.PAGE_AXIS));
        panelName.setBorder(new LineBorder(Color.BLACK));

        final var panelNameTitle = new JPanel();
        panelNameTitle.setLayout(new BoxLayout(panelNameTitle, BoxLayout.LINE_AXIS));
        final var labelNameTitle = new JLabel(TEXT_LABEL_PERSON_NAME);
        panelNameTitle.add(labelNameTitle);
        panelNameTitle.add(Box.createHorizontalGlue());
        panelName.add(panelNameTitle);

        final var panelNameBody = new JPanel();
        final var labelPersonNameFirst = new JLabel(TEXT_LABEL_PERSON_NAME_FIRST);

        // Prevent any characters other than letters and hyphen
        this.textFieldNameFirst.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent event) {
                final char key = event.getKeyChar();
                if (((key < 'a' || key > 'z') && (key < 'A' || key > 'Z') && (key != '-')) ||
                        ((textFieldNameFirst.getText().length()) >= NAME_LENGTH_MAX)) {
                    event.consume();
                }
            }
        });

        panelNameBody.add(labelPersonNameFirst);
        panelNameBody.add(this.textFieldNameFirst);

        final var labelPersonNameLast = new JLabel(TEXT_LABEL_PERSON_NAME_LAST);

        // Prevent any characters other than letters and hyphen
        this.textFieldNameLast.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent event) {
                final char key = event.getKeyChar();
                if (((key < 'a' || key > 'z') && (key < 'A' || key > 'Z') && (key != '-')) ||
                        ((textFieldNameLast.getText().length()) >= NAME_LENGTH_MAX)) {
                    event.consume();
                }
            }
        });

        panelNameBody.add(labelPersonNameLast);
        panelNameBody.add(this.textFieldNameLast);
        panelName.add(panelNameBody);

        return panelName;
    }

    private JPanel createPanelPhoneNumber(Dimension dimensionFormPanelPreferredSizeReference) {
        final String TEXT_LABEL_PHONE_AREA_CODE = "Area Code: ";
        final String TEXT_LABEL_PHONE_NUMBER = "Number: ";
        final String TEXT_LABEL_PHONE_TITLE = "     Phone";
        final String TEXT_LABEL_PHONE_TYPE = "Type: ";

        final var panelPhone = new JPanel();
        panelPhone.setPreferredSize(dimensionFormPanelPreferredSizeReference);
        panelPhone.setLayout(new BoxLayout(panelPhone, BoxLayout.PAGE_AXIS));
        panelPhone.setBorder(new LineBorder(Color.BLACK));

        final var panelPhoneTitle = new JPanel();
        panelPhoneTitle.setLayout(new BoxLayout(panelPhoneTitle, BoxLayout.LINE_AXIS));
        final var labelPhoneNumberTitle = new JLabel(TEXT_LABEL_PHONE_TITLE);
        panelPhoneTitle.add(labelPhoneNumberTitle);
        panelPhoneTitle.add(Box.createHorizontalGlue());
        panelPhone.add(panelPhoneTitle);

        final var panelPhoneBody = new JPanel();
        final var labelPhoneType = new JLabel(TEXT_LABEL_PHONE_TYPE);
        panelPhoneBody.add(labelPhoneType);
        this.comboBoxPhoneType.addItem("Home");
        this.comboBoxPhoneType.addItem("Cell");
        this.comboBoxPhoneType.addItem("Work");
        panelPhoneBody.add(this.comboBoxPhoneType);

        final var labelPhoneAreaCode = new JLabel(TEXT_LABEL_PHONE_AREA_CODE);
        panelPhoneBody.add(labelPhoneAreaCode);

        // Create key adapted that only accepts numbers
        this.textFieldPhoneAreaCode.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent event) {
                char c = event.getKeyChar();
                if (((c < '0') || (c > '9')) || (textFieldPhoneAreaCode.getText().length() >= 3)) event.consume();
            }
        });

        panelPhoneBody.add(this.textFieldPhoneAreaCode);
        final var labelPhoneNumber = new JLabel(TEXT_LABEL_PHONE_NUMBER);
        panelPhoneBody.add(labelPhoneNumber);

        // Create key adapted that only accepts numbers, and adds and removes the hyphen in phone number
        this.textFieldPhoneNumber.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent event) {
                // Set the caret to the end of the number before appending the new digit
                textFieldPhoneNumber.setCaretPosition(textFieldPhoneNumber.getText().length());

                final char c = event.getKeyChar();

                // Prevent the space bar from adding the hyphen without entering 4th digit
                if (c == ' ') {
                    event.consume();
                    return;
                }

                String text = textFieldPhoneNumber.getText();
                if (((c < '0') || (c > '9')) || (text.length() >= 8)) event.consume();

                // Add hyphen when the 4th digit is entered
                if (text.length() == 3) {
                    textFieldPhoneNumber.setText(textFieldPhoneNumber.getText() + '-');
                }

                // Erase the hyphen along with the 4th digit of the string
                if ((event.getKeyChar() == '\b') && (text.length() == 4)) {
                    textFieldPhoneNumber.setText(text.substring(0, 3));
                }
            }
        });

        panelPhoneBody.add(this.textFieldPhoneNumber);
        panelPhone.add(panelPhoneBody);

        return panelPhone;
    }

    private JPanel createFormPanelSpacer() {
        final Dimension dimensionFormPanelSpacerPreferredSize = new Dimension(440, 4);

        final var panelFormSpacer = new JPanel();
        panelFormSpacer.setPreferredSize(dimensionFormPanelSpacerPreferredSize);

        return panelFormSpacer;
    }

    private void formClear() {
        this.textFieldNameFirst.setText("");
        this.textFieldNameLast.setText("");

        this.comboBoxBirthYear.setSelectedIndex(0);
        this.comboBoxBirthMonth.setSelectedIndex(0);
        this.comboBoxBirthDay.setSelectedIndex(0);
        this.updateDayOfMonth();

        this.comboBoxPhoneType.setSelectedIndex(0);
        this.textFieldPhoneAreaCode.setText("");
        this.textFieldPhoneNumber.setText("");
        this.textFieldEmailAddress.setText("");
        this.checkBoxRegisteredForUpdates.setSelected(true);
    }

    private void formSubmit() {
        if (!isFormValid()) return;

        // Create person from form
        var person = new Person();
        person.setName(this.textFieldNameFirst.getText(), this.textFieldNameLast.getText());
        person.setBirthDate(Integer.parseInt(String.valueOf(this.comboBoxBirthDay.getSelectedItem())),
                this.comboBoxBirthMonth.getSelectedIndex() + 1,
                Integer.parseInt(String.valueOf(this.comboBoxBirthYear.getSelectedItem())));
        person.setPhone(this.comboBoxPhoneType.getItemAt(this.comboBoxPhoneType.getSelectedIndex()),
                        this.textFieldPhoneAreaCode.getText(), this.textFieldPhoneNumber.getText());
        person.setEmailAddress(this.textFieldEmailAddress.getText());
        person.setRegisteredForUpdates(this.checkBoxRegisteredForUpdates.isSelected());


        Logger.info(person.toString());
        String json = PersonTokener.personToJson(person);
        Logger.info(json);

        Person person2 = PersonTokener.personFromJson(json);
        Logger.info(person2.toString());

        // Add newly created person to the live database and the person-selector combo box
        LiveData.addPerson(person);
        this.comboBoxPersonAdd(person);

        this.formClear();
    }

    private boolean isEmailValid() {
        String text = this.textFieldEmailAddress.getText();
        return ((text.length() >= 6) && (text.indexOf('@') != -1) && text.endsWith(".com") && !text.endsWith("@.com"));
    }

    private boolean isNameValid() {
        return ((this.textFieldNameFirst.getText().length() > 0) && (this.textFieldNameLast.getText().length() > 0));
    }

    private boolean isPhoneValid() {
        return ((this.textFieldPhoneAreaCode.getText().length() == 3) && (this.textFieldPhoneNumber.getText().length() == 8));
    }

    private boolean isFormValid() {
        return (this.isEmailValid() && this.isNameValid() && this.isPhoneValid());
    }

    public JPanel getPanelRoot() {
        return this.panelRoot;
    }

    // Update the day combo box to reflect the correct number of days for the currently selected month and year
    private void updateDayOfMonth() {
        // Prevent crash before the combo boxes for birthdate are all initialized
        if ((this.comboBoxBirthMonth.getItemCount() < 1) || (this.comboBoxBirthYear.getItemCount() < 1)) return;

        if (this.comboBoxBirthDay.getItemCount() > 0) {
            this.comboBoxBirthDay.removeAllItems();
        }

        final int currentSelection = this.comboBoxBirthDay.getSelectedIndex();

        final var yearMonthObject = YearMonth.of(Integer.parseInt(String.valueOf(this.comboBoxBirthYear.getSelectedItem())), this.comboBoxBirthMonth.getSelectedIndex() + 1);
        final int dayEnd = yearMonthObject.lengthOfMonth();
        for (int dayIter = 1; dayIter <= dayEnd; ++dayIter) {
            this.comboBoxBirthDay.addItem(dayIter);
        }

        this.comboBoxBirthDay.setSelectedIndex(((currentSelection < this.comboBoxBirthDay.getItemCount()) && (currentSelection >= 0)) ? currentSelection : 0);
    }

}
