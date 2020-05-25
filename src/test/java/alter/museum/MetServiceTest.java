package alter.museum;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.junit.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MetServiceTest {


    @Test
    public void getDepartmentList() throws IOException {
        //GIVEN
        MetService service = new MetServiceFactory().getInstance();

        //WHEN
        Response<MetFeed.Department> response = service.getDepartmentList().execute();

        //THEN
        assertTrue(response.toString(), response.isSuccessful());
        MetFeed.Department feed = response.body();
        assertNotNull(feed);

            //NOT RECOGNIZING "feed.departments" as list in feed!
        List<MetFeed.Department> deptList = feed.departments;
        assertFalse(deptList.isEmpty());

        MetFeed.Department dept = deptList.get(0);
        assertNotNull(dept);
        assertNotNull(dept.departmentId);
        assertNotNull(dept.displayName);

    }

    @Test
    public void getObjectIDList() throws IOException {
        //GIVEN
        MetService service = new MetServiceFactory().getInstance();

        //WHEN
        Response<MetFeed.ObjectList> response = service.getDepartmentID(10).execute();

        //THEN
        assertTrue(response.toString(), response.isSuccessful());
        MetFeed.ObjectList objList = response.body();
        assertNotNull(objList);

        ArrayList<Integer> objID = objList.objectID;
        assertFalse(objID.isEmpty());
        int id = objID.get(0);
        assertNotNull(id);
    }

    @Test
    public void getObjectInfo() throws IOException {

        //GIVEN
        MetService service = new MetServiceFactory().getInstance();

        //WHEN
        Response<MetFeed.ObjectInfo> response = service.getObjectInfo(321824).execute();

        //THEN
        assertTrue(response.toString(), response.isSuccessful());
        MetFeed.ObjectInfo info = response.body();
        assertNotNull(info);
        assertNotNull(info.objectName);
        assertNotNull(info.culture);
        assertNotNull(info.primaryImage);


    }


}