package alter.museum;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MetController {

    private MetService service;
    private MetFrame frame;

    public MetController(MetService service, MetFrame frame) {
        this.service = service;
        this.frame = frame;
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
                    ArrayList<Integer> objectIDArray = listOfIds.objectIDs;        //NOW can create an array list

                    //send arraylist to frame
                    frame.sendList(objectIDArray);


            }

            @Override
            public void onFailure(Call<MetFeed.ObjectList> call, Throwable t) {
            t.printStackTrace();
            }
        });
    }


    //METADATA
    public void requestObjectInfo(int objectID, JLabel nameLabel){
        service.getObjectInfo(objectID).enqueue(new Callback<MetFeed.ObjectInfo>() {
            @Override
            public void onResponse(Call<MetFeed.ObjectInfo> call, Response<MetFeed.ObjectInfo> response) {
                MetFeed.ObjectInfo objectInfo = response.body();
                nameLabel.setText(objectInfo.objectName);
            }

            @Override
            public void onFailure(Call<MetFeed.ObjectInfo> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


}
