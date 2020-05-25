package alter.museum;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MetFeed {


       List<Department> departments;        //departments is not being recognized in the test?

        // departments + IDs
        class Department {
            int departmentId;
            String displayName;
        }

 //list of object IDs

    class ObjectList{
        ArrayList<Integer> objectID;
    }

    //specific object data
    class ObjectInfo {
        int objectID;
        String primaryImage;
        String objectName;
        String culture;
    }

}
