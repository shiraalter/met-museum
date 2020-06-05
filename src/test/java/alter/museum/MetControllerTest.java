package alter.museum;

import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;


import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

public class MetControllerTest {

    //test request for department list
    @Test
    public void requestDepartmentList() {
        //given
        MetService service = mock(MetService.class);
        Call<MetFeed> call = mock(Call.class);
        JLabel label = mock(JLabel.class);
        JComboBox<MetFeed.DepartmentObjects.Departments> comboBox = mock(JComboBox.class);
        MetFrame frame = mock(MetFrame.class);

        doReturn(call).when(service).getDepartmentList();

        MetController controller = new MetController(service, frame, label, label, label, label, label, comboBox);



        //when
        controller.requestDepartmentList();

        //then
        verify(call).enqueue(any());
    }

    @Test
    public void populateComboBox() {

        //given
        MetService service = mock(MetService.class);
        JLabel label = mock(JLabel.class);
        JComboBox<MetFeed.DepartmentObjects.Departments> comboBox = mock(JComboBox.class);
        MetFrame frame = mock(MetFrame.class);

        MetController controller = new MetController(service, frame, label, label, label, label, label, comboBox);

        //create list
        MetFeed.DepartmentObjects list = new MetFeed.DepartmentObjects();
        MetFeed.DepartmentObjects.Departments depts = new MetFeed.DepartmentObjects.Departments();
        depts.displayName = "museum";
        List<MetFeed.DepartmentObjects.Departments> departmentList = new ArrayList<>();
        departmentList.add(depts);
        list.departments = departmentList;


        //test passes without following code:
   /*     //Response<MetFeed> response = mock(Response.class);

        //test still passes without the doReturns + response
        doReturn(list).when(response).body();
        doReturn(comboBox).when(response).body();*/

        //when
        controller.populateComboBox(departmentList, comboBox);

        //then
        verify(comboBox).addItem(list.departments.get(0));


    }

    @Test
    public void requestOBjectList() {

        //given
        MetService service = mock(MetService.class);
        Call<MetFeed> call = mock(Call.class);
        JLabel label = mock(JLabel.class);
        JComboBox<MetFeed.DepartmentObjects.Departments> comboBox = mock(JComboBox.class);
        MetFrame frame = mock(MetFrame.class);
        int id = 5;

        doReturn(call).when(service).getObjectID(id);

        MetController controller = new MetController(service, frame, label, label, label, label, label, comboBox);

        //when
        controller.requestObjectList(id);

        //then
        verify(call).enqueue(any());

    }

    @Test
    public void createAndSendObjectList(){
        //given
        Response<MetFeed> response = mock(Response.class);
        MetService service = mock(MetService.class);
        JLabel label = mock(JLabel.class);
        JComboBox<MetFeed.DepartmentObjects.Departments> comboBox = mock(JComboBox.class);
        MetFrame frame = mock(MetFrame.class);
        ArrayList<Integer> mockIDList = mock(ArrayList.class);    //mock ArrayList to pass to method to populate
        MetController controller = new MetController(service, frame, label, label, label, label, label, comboBox);

        //create MetFeed object and add object ArrayList values
        int id = 2;
        MetFeed.ObjectList list = new MetFeed.ObjectList();
        ArrayList<Integer> idList = new ArrayList<>();
        idList.add(id);
        list.objectIDs = idList;

        doReturn(frame).when(response).body();

        //when
        controller.createAndSendObjectList(list);

        //then
        verify(mockIDList).equals(list.objectIDs);      //verify that the method populates objectIDArray with the listOfIds.objectIDs
        verify(frame.arrayListOfId).equals(list); //verify that the ArrayList is sent to the frame and assigned to arrayListOfId in sendList method in controller

        //verify(frame...) getting erorr "argument passed to verify() should be a mock but is null //

    }


    @Test
    public void requestObjectInfo() {
        MetService service = mock(MetService.class);
        Call<MetFeed> call = mock(Call.class);
        JLabel label = mock(JLabel.class);
        JComboBox<MetFeed.DepartmentObjects.Departments> comboBox = mock(JComboBox.class);
        MetFrame frame = mock(MetFrame.class);
        int id = 10;

        doReturn(call).when(service).getObjectInfo(id);

        MetController controller = new MetController(service, frame, label, label, label, label, label, comboBox);

        //when
        controller.requestObjectInfo(id);

        //then
        verify(call).enqueue(any());

    }



}