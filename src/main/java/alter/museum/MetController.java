package alter.museum;

import com.google.gson.internal.$Gson$Preconditions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MetController {

    ArrayList<Integer> objectIDArray;
    private MetService service;
    private MetFrame frame;
    private JLabel nameLabel;
    private JLabel cultureLabel;
    private JLabel imageLabel;
    private JLabel objectIdLabel;
    Color brown = new Color(103, 71, 0, 188);
    Color darkPurple = new Color(117, 0, 26, 162);
    int height = 250;
    int width = 250;


    public MetController(MetService service, MetFrame frame, JLabel nameLabel, JLabel cultureLabel, JLabel imageLabel, JLabel objectIdLabel) {
        this.service = service;
        this.frame = frame;
        this.nameLabel = nameLabel;
        this.cultureLabel = cultureLabel;
        this.imageLabel = imageLabel;
        this.objectIdLabel = objectIdLabel;
    }


    //LIST OF DEPARTMENTS
    public void requestDepartmentList(JComboBox<MetFeed.DepartmentObjects.Departments> comboBox) {
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
    public void requestObjectList(int departmentID) {

        service.getObjectID(departmentID).enqueue(new Callback<MetFeed.ObjectList>() {
            @Override
            public void onResponse(Call<MetFeed.ObjectList> call, Response<MetFeed.ObjectList> response) {
                MetFeed.ObjectList listOfIds = response.body();

                objectIDArray = listOfIds.objectIDs;

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
    public void requestObjectInfo(int objectID) {
        service.getObjectInfo(objectID).enqueue(new Callback<MetFeed.ObjectInfo>() {
            @Override
            public void onResponse(Call<MetFeed.ObjectInfo> call, Response<MetFeed.ObjectInfo> response) {
                MetFeed.ObjectInfo objectInfo = response.body();

                objectIdLabel.setText("Object ID:" + objectInfo.objectID);
                objectIdLabel.setForeground(darkPurple);

                if(objectInfo.objectName.equals("")) {
                    nameLabel.setText("No Object Name Available");
                }
                else{
                    nameLabel.setText("Object Name: " + objectInfo.objectName);
                }

                if (objectInfo.culture.equals("")) {
                    cultureLabel.setText("No Culture Available");
                    cultureLabel.setForeground(Color.BLUE);
                } else {
                    cultureLabel.setText("Culture: " + objectInfo.culture);
                    cultureLabel.setForeground(Color.BLUE);
                }

                try {   //prevents malformedURLException
                    if (objectInfo.primaryImage.equals("")) {
                        imageLabel.setText("No Image Available");
                        imageLabel.setForeground(brown);
                    } else {
                        URL url = new URL(objectInfo.primaryImage);
                        Image image = ImageIO.read(url);
                        Image newImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                        imageLabel.setIcon(new ImageIcon(newImage));
                        imageLabel.setText("");     //removes "No image available"
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void onFailure(Call<MetFeed.ObjectInfo> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


}
