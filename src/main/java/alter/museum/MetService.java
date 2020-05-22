package alter.museum;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MetService {

    // department list
     @GET("/public/collection/v1/departments")
     Call<MetFeed.Departments> getDepartmentList();

    // list of object IDs
    @GET("/public/collection/v1/objects")
    Call<MetFeed.ObjectList> getDepartmentID(@Query("departmentID") String departmentID);

     //specific object info
     @GET("/public/collection/v1/objects/{objectID}")
     Call<MetFeed.ObjectInfo> getObjectInfo(@Path(value = "objectID") String objectID);



}

