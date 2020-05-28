package alter.museum;

import org.junit.Test;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class MetServiceTest {


        @Test
        public void getDepartmentList() throws IOException {
            //GIVEN
            MetService service = new MetServiceFactory().getInstance();

            //WHEN
            Response<MetFeed.DepartmentObjects> response = service.getDepartmentList().execute();

           //THEN
            assertTrue(response.toString(), response.isSuccessful());
            MetFeed.DepartmentObjects feed = response.body();
            assertNotNull(feed);


            List<MetFeed.DepartmentObjects.Departments> departments = feed.departments;
            assertFalse(departments.isEmpty());


            MetFeed.DepartmentObjects.Departments dept = departments.get(1);
            assertNotNull(dept);
            assertNotNull(dept.departmentId);
            assertNotNull(dept.displayName);

        }

        @Test
        public void getObjectIDList() throws IOException {
            //GIVEN
            MetService service = new MetServiceFactory().getInstance();

            //WHEN
            Response<MetFeed.ObjectList> response = service.getObjectID(10).execute();

            //THEN
            assertTrue(response.toString(), response.isSuccessful());
            MetFeed.ObjectList objList = response.body();
            assertNotNull(objList);

            List<Integer> objID = objList.objectIDs;
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
