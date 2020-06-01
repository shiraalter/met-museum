package alter.museum;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MetController {

    ArrayList<Integer> objectIDArray;
    private MetService service;
    private MetFrame frame;
    private JLabel nameLabel;
    private JLabel cultureLabel;
    private JButton rightButton;

    public MetController(MetService service, MetFrame frame, JLabel nameLabel, JButton rightButton, JLabel cultureLabel) {
        this.service = service;
        this.frame = frame;
        this.nameLabel = nameLabel;
        this.rightButton = rightButton;
        this.cultureLabel = cultureLabel;

    }


    //LIST OF DEPARTMENTS
    public void requestDepartmentList(JComboBox<MetFeed.DepartmentObjects.Departments> comboBox){
        service.getDepartmentList().enqueue(new Callback<MetFeed.DepartmentObjects>() {

            @Override
            public void onResponse(Call<MetFeed.DepartmentObjects> call, Response<MetFeed.DepartmentObjects> response) {
                List<MetFeed.DepartmentObjects.Departments> departmentList = response.body().departments;


                //loop through department list and add object combobox --> Will display the toString from Feed class
                for (int i = 0; i < departmentList.size(); i++) {
                    comboBox.addItem(departmentList.get(i));
                }
            }

            @Override
            public void onFailure(Call<MetFeed.DepartmentObjects> call, Throwable t) {
                    t.printStackTrace();
            }
        });
    }


    //LIST OF OBJECT IDs
    public void requestObjectList(int departmentID){

        service.getObjectID(departmentID).enqueue(new Callback<MetFeed.ObjectList>() {
            @Override
            public void onResponse(Call<MetFeed.ObjectList> call, Response<MetFeed.ObjectList> response) {
                    MetFeed.ObjectList listOfIds= response.body();      //store response into object

                    objectIDArray = listOfIds.objectIDs;        // it's only storing object IDs from the FIRST DEPARTMENT no matter what I click

                    //send arraylist to frame
                    frame.sendList(objectIDArray);              //only sending IDs from FIRST department



            }

            @Override
            public void onFailure(Call<MetFeed.ObjectList> call, Throwable t) {
            t.printStackTrace();
            }
        });
    }


    //METADATA
    public void requestObjectInfo(int objectID){
        service.getObjectInfo(objectID).enqueue(new Callback<MetFeed.ObjectInfo>() {
            @Override
            public void onResponse(Call<MetFeed.ObjectInfo> call, Response<MetFeed.ObjectInfo> response) {
                MetFeed.ObjectInfo objectInfo = response.body();
                nameLabel.setText(objectInfo.objectName);
                cultureLabel.setText(objectInfo.culture);
            }

            @Override
            public void onFailure(Call<MetFeed.ObjectInfo> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


}
