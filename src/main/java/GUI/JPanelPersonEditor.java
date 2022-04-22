package GUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.YearMonth;

final public class JPanelPersonEditor {

    private final JPanel panelRoot;

    private final JCheckBox checkBoxRegisteredForUpdates = new JCheckBox();
    private final JComboBox<Integer> comboBoxBirthDay = new JComboBox<>(), comboBoxBirthYear = new JComboBox<>();
    private final JComboBox<String> comboBoxBirthMonth = new JComboBox<>();
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

        this.panelRoot.add(panelForm);
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
        this.comboBoxBirthYear.addActionListener(e -> updateDayOfMonth());
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
        this.comboBoxBirthMonth.addActionListener(e -> updateDayOfMonth());
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
        final var labelRegisterForUpdates = new JLabel(TEXT_LABEL_REGISTERED_FOR_UPDATES);
        panelEmailBody.add(labelRegisterForUpdates);
        this.checkBoxRegisteredForUpdates.setSelected(true);
        panelEmailBody.add(this.checkBoxRegisteredForUpdates);
        panelEmail.add(panelEmailBody);

        return panelEmail;
    }

    private JPanel createPanelName(Dimension dimensionFormPanelPreferredSizeReference) {
        final int NAME_LENGTH_MAX = 12;

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
        final String TEXT_LABEL_PHONE_TITLE = "     Phone";
        final String TEXT_LABEL_PHONE_AREA_CODE = "Area Code: ";
        final String TEXT_LABEL_PHONE_NUMBER = "Number: ";

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
                final char c = event.getKeyChar();
                String text = textFieldPhoneNumber.getText();
                if (((c < '0') || (c > '9')) || (text.length() >= 8)) event.consume();

                if (text.length() == 3) {
                    textFieldPhoneNumber.setText(textFieldPhoneNumber.getText() + '-');
                }

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

    private boolean isEmailValid() {
        String text = this.textFieldEmailAddress.getText();
        return ((text.length() >= 6) && (text.indexOf('@') != -1) && text.endsWith(".com"));
    }

    private boolean isNameValid() {
        return ((this.textFieldNameFirst.getText().length() > 0) && (this.textFieldNameLast.getText().length() > 0));
    }

    private boolean isPhoneValid() {
        return ((this.textFieldPhoneAreaCode.getText().length() > 0) && (this.textFieldPhoneNumber.getText().length() > 0));
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
