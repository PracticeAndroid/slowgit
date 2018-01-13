package com.miuty.slowgit.data.repository.login.remote;

import com.miuty.slowgit.data.dao.model.request.BasicAuthRequest;
import com.miuty.slowgit.data.dao.model.response.BasicAuthResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Asus on 1/13/2018.
 */

public interface AuthRestService {

    @POST("authorizations")
    Observable<BasicAuthResponse> doBasicLogin(@Body BasicAuthRequest request);
}
