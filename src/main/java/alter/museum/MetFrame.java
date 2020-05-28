package alter.museum;



import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class MetFrame extends JFrame {

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

     MetFeed.DepartmentObjects.Departments selectedDepartment;   //made into field to give buttons access to IDs

    public MetFrame() {

        setSize(800, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Met Museum UI");
        setLayout(new BorderLayout());


        //get retrofit from factory
        service = new MetServiceFactory().getInstance();
        MetController controller = new MetController(service, this);

        comboPanel = new JPanel();
        departmentLabel = new JLabel("Select a department");
        departmentBox = new JComboBox<>();
        departmentBox.setPreferredSize(new Dimension(300, 50));
        departmentSelectedLabel = new JLabel();


         controller.requestDepartmentList(departmentBox);

        //when department is selected
        departmentBox.addActionListener(actionEvent -> {
            //store department id
            selectedDepartment = (MetFeed.DepartmentObjects.Departments) departmentBox.getSelectedItem();
            departmentSelectedLabel.setText("You selected: " + selectedDepartment);
            int departmentId  = selectedDepartment.departmentId;
            controller.requestObjectList(departmentId);     // send department id to controller
             });



        comboPanel.add(departmentLabel);
        comboPanel.add(departmentBox);
        comboPanel.add(departmentSelectedLabel);
        add(comboPanel, BorderLayout.NORTH);

        //add buttons to go through objects
        arrowPanel = new JPanel();
        leftButton = new JButton("<< PREVIOUS");
        leftButton.setPreferredSize(new Dimension(200,100));
        rightButton = new JButton("NEXT >>");
        rightButton.setPreferredSize(new Dimension(200,100));
        arrowPanel.add(leftButton);
        arrowPanel.add(rightButton);
        add(arrowPanel, BorderLayout.SOUTH);

        nameLabel = new JLabel();
        infoPanel = new JPanel();
        infoPanel.add(nameLabel);
        //add action to buttons
         rightButton.addActionListener(actionEvent -> nextButton(controller, nameLabel));

         add(infoPanel, BorderLayout.EAST);



    }

    private void nextButton(MetController controller, JLabel nameLabel) {

        for (int i = 0; i < arrayListOfId.size(); i++) {
            controller.requestObjectInfo(arrayListOfId.get(i), nameLabel);
            return;
        }

    }



    public static void main(String[] args) {
            new MetFrame().setVisible(true);

        }

    public void sendList(ArrayList<Integer> arrayListOfObjectID) {
        arrayListOfId = arrayListOfObjectID;

    }
}

