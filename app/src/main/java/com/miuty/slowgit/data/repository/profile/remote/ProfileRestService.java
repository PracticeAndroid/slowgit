package com.miuty.slowgit.data.repository.profile.remote;

import com.miuty.slowgit.data.model.Repo;
import com.miuty.slowgit.data.model.profile.BasicProfile;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by igneel on 1/20/2018.
 */

public interface ProfileRestService {

    @GET("users/{loginId}")
    Observable<BasicProfile> getBasicProfile(@Path("loginId") String loginId);

    @GET("users/{loginId}/contributions")
    Observable<ResponseBody> getContributions(@Path("loginId") String loginId);

    @GET("users/{loginId}/repos")
    Observable<List<Repo>> getRepos(@Path("loginId") String loginId, @Query("page") int page);

}
