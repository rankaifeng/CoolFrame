package cool.frame.com.coolframes.api;


import cool.frame.com.coolframes.model.JuHeOut;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by rankaifeng on 2017/7/24.
 */

public interface GitJuHeApi {
    @GET("/cook/query?key=1d2e476415bafcd9bf227323b5be850e&menu")
    Call<JuHeOut> getNews(@Query("menu") String menu, @Query("rn") int rn, @Query("pn") int pn);


    @GET("/cook/query?key=1d2e476415bafcd9bf227323b5be850e&menu")
    Observable<JuHeOut> getHttpNews(@Query("menu") String menu, @Query("rn") int rn, @Query("pn") int pn);
}
