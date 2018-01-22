package com.miuty.slowgit.data.repository.profile.remote;

import com.miuty.slowgit.data.model.profile.BasicProfile;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by igneel on 1/20/2018.
 */

public interface ProfileRestService {

    @GET("users/{loginId}")
    Observable<BasicProfile> getBasicProfile(@Path("loginId") String loginId);

    @GET("users/{loginId}/contributions")
    Observable<ResponseBody> getContributions(@Path("loginId") String loginId);

}
