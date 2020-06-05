package alter.museum;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


    public class MetFeed {

        static class DepartmentObjects {
            List<Departments> departments;        //departments is not being recognized in the test?

            // departments + IDs
          static  class  Departments {
                int departmentId;
                String displayName;

                @Override
                public String toString() {
                    return displayName;
                }
            }
       }

 //list of object IDs

    static class ObjectList{
        ArrayList<Integer> objectIDs;
    }

    //specific object data
    static class ObjectInfo {
        int objectID;
        String primaryImage;
        String objectName;
        String culture;
    }


}
