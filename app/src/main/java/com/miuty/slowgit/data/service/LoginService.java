package com.miuty.slowgit.data.service;

import com.miuty.slowgit.data.dao.model.request.BasicAuthRequest;
import com.miuty.slowgit.data.dao.model.response.BasicAuthResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;


/**
 * Created by Asus on 1/12/2018.
 */

public interface LoginService {

    @POST("authorizations")
    Observable<BasicAuthResponse> doBasicLogin(@Body BasicAuthRequest request, @Header("") Map<String, String> header);
}
