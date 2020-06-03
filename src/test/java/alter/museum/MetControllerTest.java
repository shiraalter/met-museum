package alter.museum;

import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;


import javax.swing.*;

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
        Response<MetFeed> response = mock(Response.class);  //needed?

        MetController controller = new MetController(service, frame, label, label, label, label, label, comboBox);

        //create list
        MetFeed.DepartmentObjects list = new MetFeed.DepartmentObjects();
        MetFeed.DepartmentObjects.Departments depts = new MetFeed.DepartmentObjects.Departments();
        depts.displayName = "museum";
        List<MetFeed.DepartmentObjects.Departments> departmentList = new ArrayList<>();
        departmentList.add(depts);
        list.departments = departmentList;


        //test still passes without the doReturns + response
        doReturn(list).when(response).body();
        doReturn(comboBox).when(response).body();

        //when
        controller.populateComboBox(departmentList, comboBox);

        //then
        verify(comboBox).addItem(list.departments.get(0));


    }

}