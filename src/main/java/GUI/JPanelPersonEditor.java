package GUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.YearMonth;

final public class JPanelPersonEditor {

    JPanel panelRoot;

    JCheckBox checkBoxRegisteredForUpdates;
    JComboBox<Integer> comboBoxBirthDay, comboBoxBirthYear;
    JComboBox<String> comboBoxBirthMonth;
    JTextField textFieldEmailAddress;
    JTextField textFieldNameFirst, textFieldNameLast;
    JTextField textFieldPhoneAreaCode, textFieldPhoneNumber;

    Dimension dimensionFormPanelPreferredSize = new Dimension(440, 54);
    Dimension dimensionFormPanelSpacerPreferredSize = new Dimension(440, 4);

    String TEXT_LABEL_BIRTHDATE = "     Birthdate";
    String TEXT_LABEL_BIRTHDATE_DAY = "Day: ";
    String TEXT_LABEL_BIRTHDATE_MONTH = "Month: ";
    String TEXT_LABEL_BIRTHDATE_YEAR = "Year: ";
    String TEXT_LABEL_EMAIL_TITLE = "     Email Address";
    String TEXT_LABEL_EMAIL_ADDRESS = "Email: ";
    String TEXT_LABEL_PERSON_NAME = "     Name";
    String TEXT_LABEL_PERSON_NAME_FIRST = "First:";
    String TEXT_LABEL_PERSON_NAME_LAST = "Last:";
    String TEXT_LABEL_PHONE_TITLE = "     Phone";
    String TEXT_LABEL_PHONE_AREA_CODE = "Area Code: ";
    String TEXT_LABEL_PHONE_NUMBER = "Number: ";
    String TEXT_LABEL_REGISTERED_FOR_UPDATES = "Register for Updates: ";


    public JPanelPersonEditor() {
        this.panelRoot = new JPanel();

        var panelForm = new JPanel();
        panelForm.setLayout(new BoxLayout(panelForm, BoxLayout.PAGE_AXIS));
        panelForm.add(this.createPanelName());
        panelForm.add(this.createFormPanelSpacer());
        panelForm.add(this.createPanelDateOfBirth());
        panelForm.add(this.createFormPanelSpacer());
        panelForm.add(this.createPanelPhoneNumber());
        panelForm.add(this.createFormPanelSpacer());
        panelForm.add(this.createPanelEmail());

        this.panelRoot.add(panelForm);
    }

    private JPanel createPanelDateOfBirth() {
        var panelDateOfBirth = new JPanel();
        panelDateOfBirth.setPreferredSize(this.dimensionFormPanelPreferredSize);
        panelDateOfBirth.setLayout(new BoxLayout(panelDateOfBirth, BoxLayout.PAGE_AXIS));
        panelDateOfBirth.setBorder(new LineBorder(Color.BLACK));

        var panelDateOfBirthTitle = new JPanel();
        panelDateOfBirthTitle.setLayout(new BoxLayout(panelDateOfBirthTitle, BoxLayout.LINE_AXIS));
        var labelDateOfBirthTitle = new JLabel(this.TEXT_LABEL_BIRTHDATE);
        panelDateOfBirthTitle.add(labelDateOfBirthTitle);
        panelDateOfBirthTitle.add(Box.createHorizontalGlue());
        panelDateOfBirth.add(panelDateOfBirthTitle);

        var panelDateOfBirthBody = new JPanel();
        panelDateOfBirthBody.setLayout(new BoxLayout(panelDateOfBirthBody, BoxLayout.LINE_AXIS));

        var listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);

        var panelBirthYear = new JPanel();
        var labelDateOfBirthYear = new JLabel(this.TEXT_LABEL_BIRTHDATE_YEAR);
        panelBirthYear.add(labelDateOfBirthYear);
        this.comboBoxBirthYear = new JComboBox<>();
        this.comboBoxBirthYear.setRenderer(listRenderer);
        this.comboBoxBirthYear.addActionListener(e -> updateDayOfMonth());
        int yearCurrent = LocalDate.now().getYear();
        int yearEnd = yearCurrent - 120;
        for (int yearIter = yearCurrent; yearIter > yearEnd; --yearIter) {
            this.comboBoxBirthYear.addItem(yearIter);
        }
        panelBirthYear.add(this.comboBoxBirthYear);
        panelDateOfBirthBody.add(panelBirthYear);

        var panelBirthMonth = new JPanel();
        var labelDateOfBirthMonth = new JLabel(this.TEXT_LABEL_BIRTHDATE_MONTH);
        panelBirthMonth.add(labelDateOfBirthMonth);
        this.comboBoxBirthMonth = new JComboBox<>();
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

        var panelBirthDay = new JPanel();
        var labelDateOfBirthDay = new JLabel(this.TEXT_LABEL_BIRTHDATE_DAY);
        panelBirthDay.add(labelDateOfBirthDay);
        this.comboBoxBirthDay = new JComboBox<>();
        this.comboBoxBirthDay.setRenderer(listRenderer);
        this.updateDayOfMonth();
        panelBirthDay.add(this.comboBoxBirthDay);
        panelDateOfBirthBody.add(panelBirthDay);
        panelDateOfBirth.add(panelDateOfBirthBody);
        return panelDateOfBirth;
    }

    private JPanel createPanelEmail() {
        var panelEmail = new JPanel();
        panelEmail.setPreferredSize(this.dimensionFormPanelPreferredSize);
        panelEmail.setLayout(new BoxLayout(panelEmail, BoxLayout.PAGE_AXIS));
        panelEmail.setBorder(new LineBorder(Color.BLACK));

        var panelEmailTitle = new JPanel();
        panelEmailTitle.setLayout(new BoxLayout(panelEmailTitle, BoxLayout.LINE_AXIS));
        var labelEmailTitle = new JLabel(this.TEXT_LABEL_EMAIL_TITLE);
        panelEmailTitle.add(labelEmailTitle);
        panelEmailTitle.add(Box.createHorizontalGlue());
        panelEmail.add(panelEmailTitle);
        var panelEmailBody = new JPanel();
        var labelEmailAddress = new JLabel(this.TEXT_LABEL_EMAIL_ADDRESS);
        panelEmailBody.add(labelEmailAddress);
        this.textFieldEmailAddress = new JTextField(20);
        panelEmailBody.add(this.textFieldEmailAddress);
        var labelRegisterForUpdates = new JLabel(this.TEXT_LABEL_REGISTERED_FOR_UPDATES);
        panelEmailBody.add(labelRegisterForUpdates);
        this.checkBoxRegisteredForUpdates = new JCheckBox();
        this.checkBoxRegisteredForUpdates.setSelected(true);
        panelEmailBody.add(this.checkBoxRegisteredForUpdates);
        panelEmail.add(panelEmailBody);

        return panelEmail;
    }

    private JPanel createPanelName() {
        var panelName = new JPanel();
        panelName.setPreferredSize(this.dimensionFormPanelPreferredSize);
        panelName.setLayout(new BoxLayout(panelName, BoxLayout.PAGE_AXIS));
        panelName.setBorder(new LineBorder(Color.BLACK));

        var panelNameTitle = new JPanel();
        panelNameTitle.setLayout(new BoxLayout(panelNameTitle, BoxLayout.LINE_AXIS));
        var labelNameTitle = new JLabel(this.TEXT_LABEL_PERSON_NAME);
        panelNameTitle.add(labelNameTitle);
        panelNameTitle.add(Box.createHorizontalGlue());
        panelName.add(panelNameTitle);

        var panelNameBody = new JPanel();
        var labelPersonNameFirst = new JLabel(this.TEXT_LABEL_PERSON_NAME_FIRST);
        this.textFieldNameFirst = new JTextField(15);
        panelNameBody.add(labelPersonNameFirst);
        panelNameBody.add(this.textFieldNameFirst);

        var labelPersonNameLast = new JLabel(this.TEXT_LABEL_PERSON_NAME_LAST);
        this.textFieldNameLast = new JTextField(15);
        panelNameBody.add(labelPersonNameLast);
        panelNameBody.add(this.textFieldNameLast);
        panelName.add(panelNameBody);

        return panelName;
    }

    private JPanel createPanelPhoneNumber() {
        var panelPhone = new JPanel();
        panelPhone.setPreferredSize(this.dimensionFormPanelPreferredSize);
        panelPhone.setLayout(new BoxLayout(panelPhone, BoxLayout.PAGE_AXIS));
        panelPhone.setBorder(new LineBorder(Color.BLACK));

        var panelPhoneTitle = new JPanel();
        panelPhoneTitle.setLayout(new BoxLayout(panelPhoneTitle, BoxLayout.LINE_AXIS));
        var labelPhoneNumberTitle = new JLabel(this.TEXT_LABEL_PHONE_TITLE);
        panelPhoneTitle.add(labelPhoneNumberTitle);
        panelPhoneTitle.add(Box.createHorizontalGlue());
        panelPhone.add(panelPhoneTitle);

        var panelPhoneBody = new JPanel();
        var labelPhoneAreaCode = new JLabel(this.TEXT_LABEL_PHONE_AREA_CODE);
        panelPhoneBody.add(labelPhoneAreaCode);
        this.textFieldPhoneAreaCode = new JTextField(3);
        panelPhoneBody.add(this.textFieldPhoneAreaCode);
        var labelPhoneNumber = new JLabel(this.TEXT_LABEL_PHONE_NUMBER);
        panelPhoneBody.add(labelPhoneNumber);
        this.textFieldPhoneNumber = new JTextField(8);
        panelPhoneBody.add(this.textFieldPhoneNumber);
        panelPhone.add(panelPhoneBody);

        return panelPhone;
    }

    private JPanel createFormPanelSpacer() {
        var panelFormSpacer = new JPanel();
        panelFormSpacer.setPreferredSize(this.dimensionFormPanelSpacerPreferredSize);

        return panelFormSpacer;
    }

    public JPanel getPanelRoot() {
        return this.panelRoot;
    }

    private void updateDayOfMonth() {
        if (this.comboBoxBirthDay == null) {
            return;
        }

        int currentSelection = this.comboBoxBirthDay.getSelectedIndex();

        this.comboBoxBirthDay.removeAllItems();

        var yearMonthObject = YearMonth.of(Integer.parseInt(String.valueOf(this.comboBoxBirthYear.getSelectedItem())), this.comboBoxBirthMonth.getSelectedIndex() + 1);
        int dayEnd = yearMonthObject.lengthOfMonth();
        for (int dayIter = 1; dayIter <= dayEnd; ++dayIter) {
            this.comboBoxBirthDay.addItem(dayIter);
        }

        this.comboBoxBirthDay.setSelectedIndex(((currentSelection < this.comboBoxBirthDay.getItemCount()) && (currentSelection >= 0)) ? currentSelection : 0);
    }

}
