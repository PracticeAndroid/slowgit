package com.miuty.slowgit.data.repository.profile;

import com.miuty.slowgit.data.model.Repo;
import com.miuty.slowgit.data.model.profile.BasicProfile;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by igneel on 1/20/2018.
 */

public interface ProfileRepository {

    Observable<BasicProfile> getBasicProfile(String loginId);

    Observable<ResponseBody> getContributions(String loginId);

    public Observable<List<Repo>> getRepos(String loginId, int page);
}
