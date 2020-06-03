package alter.museum;

import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;


import javax.swing.*;

import java.awt.*;
import java.util.List;

import static org.junit.Assert.*;
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
        List<MetFeed.DepartmentObjects.Departments> departmentList = mock(List.class);

        Response<MetFeed> response = mock(Response.class);  //needed?

        //what object is calling the populateCombobox aside from response?
        doReturn(departmentList).when(response).body();
        doReturn(comboBox).when(response).body();

        //when
        controller.populateComboBox(departmentList, comboBox);

        //then
       // verify(comboBox   );


    }

}