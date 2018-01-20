package com.miuty.slowgit.data.repository.profile.local;

import com.miuty.slowgit.data.model.profile.BasicProfile;

import io.reactivex.Observable;

/**
 * Created by igneel on 1/20/2018.
 */

public interface ProfileLocalService {

    Observable<BasicProfile> getCurrentProfile();

}
