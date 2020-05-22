package alter.museum;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MetFeed {

    ArrayList<Departments> departments;


    //JSON departments + IDs  "/public/collection/v1/departments"
    class Departments {
        int departmentId;
        String displayName;
    }

    /* list of object IDs "/public/collection/v1/objects/{objectID}" */
    class ObjectList{
        ArrayList<Integer> objectID;
    }

    //specific object data   //"/public/collection/v1/objects"
    class ObjectInfo {
        int objectID;
        String primaryImage;
        String objectName;
        String culture;
    }

}
