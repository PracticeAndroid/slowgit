package com.miuty.slowgit.ui.screen.profile;


import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.miuty.slowgit.di.qualifier.ActivityContext;
import com.miuty.slowgit.provider.navigator.ActivityNavigator;
import com.miuty.slowgit.provider.navigator.ActivityNavigatorImpl;
import com.miuty.slowgit.ui.base.activity.BaseFragment;
import com.miuty.slowgit.ui.screen.profile.overview.ProfileOverviewFragment;

import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ProfileModule {

    @Binds
    @ActivityContext
    abstract Context provideActivityContext(ProfileActivity activity);

    @Provides
    public static FragmentManager provideFragmentManager(@ActivityContext Context context) {
        ProfileActivity activity = (ProfileActivity) context;
        return activity.getSupportFragmentManager();
    }

    @Provides
    public static ActivityNavigator provideActivityNavigator(@ActivityContext Context context) {
        return new ActivityNavigatorImpl(context, (AppCompatActivity) context);
    }

   /* @Provides
    public static List<BaseFragment> provideListFragments() {
        return Stream.of(ProfileOverviewFragment.newInstance(),
                ProfileOverviewFragment.newInstance(),
                ProfileOverviewFragment.newInstance())
                .collect(Collectors.toList());
    }

    @Provides
    public static FragmentsPagerAdapter provideFragmentsPagerAdapter(FragmentManager fragmentManager, List<BaseFragment> fragments) {
        return new FragmentsPagerAdapter(fragmentManager, fragments);
    }*/
}
