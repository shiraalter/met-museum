package alter.museum;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MetService {

    // department list
     @GET("/public/collection/v1/departments")
     Call<MetFeed.DepartmentObjects> getDepartmentList();

    // list of object IDs
    @GET("/public/collection/v1/objects")
    Call<MetFeed.ObjectList> getObjectID(@Query("departmentIds") int departmentID);

     //specific object info
     @GET("/public/collection/v1/objects/{objectID}")
     Call<MetFeed.ObjectInfo> getObjectInfo(@Path("objectID") int objectID);



}

