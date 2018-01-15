package com.miuty.slowgit.data.repository.remote;

import com.miuty.slowgit.data.dao.model.User;
import com.miuty.slowgit.data.dao.request.BasicAuthRequest;
import com.miuty.slowgit.data.dao.response.BasicAuthResponse;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Asus on 1/13/2018.
 */

public interface AuthRestService {

    @POST("authorizations")
    Observable<BasicAuthResponse> doBasicLogin(@Body BasicAuthRequest request);

    @GET("user")
    Maybe<User> getUser();
}
