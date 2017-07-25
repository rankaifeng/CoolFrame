package cool.frame.com.coolframe.api;

import cool.frame.com.coolframe.model.JuHeOut;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by rankaifeng on 2017/7/24.
 */

public interface GitJuHeApi {
    @GET("/cook/query?key=1d2e476415bafcd9bf227323b5be850e&menu")
    Call<JuHeOut> getNews(@Query("menu") String menu, @Query("rn") int rn,@Query("pn") int pn);
}
