package Retro;
import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;

public interface PetFinderApi {

    @GET("animals")
    Call<List<Post>> getPosts();
}
