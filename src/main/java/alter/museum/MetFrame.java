package alter.museum;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MetFrame extends JFrame {

    MetController controller;
    MetService service;
    private JComboBox<MetFeed.DepartmentObjects.Departments> departmentBox;
    private JPanel comboPanel;
    private JLabel departmentLabel;
    private JLabel departmentSelectedLabel;


    private JPanel arrowPanel;
    JButton leftButton;
    JButton rightButton;
    ArrayList<Integer> arrayListOfId;

    private JPanel infoPanel;
    JLabel nameLabel;
    JLabel cultureLabel;
    JLabel imageLabel;
    JLabel objectIdLabel;
    JLabel errorLabel;

    int counter = 0;


    MetFeed.DepartmentObjects.Departments selectedDepartment;

    public MetFrame() {

        setSize(700, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Met Museum UI");
        setLayout(new BorderLayout());


        //create combo box
        comboPanel = new JPanel();
        departmentLabel = new JLabel("Select a department");
        departmentBox = new JComboBox<>();
        departmentBox.setPreferredSize(new Dimension(300, 50));
        departmentSelectedLabel = new JLabel();


        comboPanel.add(departmentLabel);
        comboPanel.add(departmentBox);
        comboPanel.add(departmentSelectedLabel);
        add(comboPanel, BorderLayout.NORTH);


        //when department is selected
        departmentBox.addActionListener(actionEvent -> {
            //store department id
            selectedDepartment = (MetFeed.DepartmentObjects.Departments) departmentBox.getSelectedItem();
            departmentSelectedLabel.setText("You selected: " + selectedDepartment);

            //get department id and call method to get object ID list
            int departmentId = selectedDepartment.departmentId;
            controller.requestObjectList(departmentId);     // send department id to controller
        });


        //add buttons to go through objects
        arrowPanel = new JPanel();
        leftButton = new JButton("<< PREVIOUS");
        leftButton.setPreferredSize(new Dimension(200, 100));
        rightButton = new JButton("NEXT >>");
        rightButton.setPreferredSize(new Dimension(200, 100));
        arrowPanel.add(leftButton);

        arrowPanel.add(rightButton);
        add(arrowPanel, BorderLayout.SOUTH);

        //add action to buttons
        rightButton.addActionListener(actionEvent -> nextButton(controller));
        leftButton.addActionListener(actionEvent -> previousButton(controller));

        nameLabel = new JLabel();
        cultureLabel = new JLabel();
        imageLabel = new JLabel();
        objectIdLabel = new JLabel();
        errorLabel = new JLabel();

        infoPanel = new JPanel();
        infoPanel.add(nameLabel);
        infoPanel.add(cultureLabel);
        infoPanel.add(imageLabel);
        infoPanel.add(errorLabel);
        infoPanel.add(objectIdLabel);
        add(infoPanel, BorderLayout.CENTER);


        //get retrofit from factory
        service = new MetServiceFactory().getInstance();
        controller = new MetController(service, this, nameLabel, cultureLabel, imageLabel, objectIdLabel, errorLabel);
        controller.requestDepartmentList(departmentBox);
    }


    public void sendList(ArrayList<Integer> arrayListOfObjectID) {
        arrayListOfId = arrayListOfObjectID;
        controller.requestObjectInfo(arrayListOfId.get(0));
    }

    //try-catch for both buttons in case user tries to access an object NOT in the list
    //use counter to iterate through ID list
    private void nextButton(MetController controller) {
        counter++;
        try {
            controller.requestObjectInfo(arrayListOfId.get(counter));
        } catch (Exception e) {
            displayErrorMessage();
        }

    }

    private void previousButton(MetController controller) {
        counter--;
        try {
            controller.requestObjectInfo(arrayListOfId.get(counter));
        } catch (Exception e) {
            displayErrorMessage();
        }
    }

    private void displayErrorMessage() {
        errorLabel.setText("No Object to display. Please press the NEXT button");
        objectIdLabel.setText("");
        imageLabel.setText("");
        nameLabel.setText("");
        cultureLabel.setText("");
        imageLabel.setIcon(null);
    }

    public static void main(String[] args) {
        new MetFrame().setVisible(true);

    }


}

