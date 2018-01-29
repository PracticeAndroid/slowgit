package com.miuty.slowgit.ui.screen.repo.code;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.miuty.slowgit.di.qualifier.ActivityContext;
import com.miuty.slowgit.di.qualifier.ChildFragmentManager;
import com.miuty.slowgit.provider.navigator.FragmentNavigator;
import com.miuty.slowgit.provider.navigator.FragmentNavigatorImpl;
import com.miuty.slowgit.ui.base.activity.BaseFragment;
import com.miuty.slowgit.ui.screen.repo.commits.CommitsFragment;
import com.miuty.slowgit.ui.screen.repo.contributors.ContributorsFragment;
import com.miuty.slowgit.ui.screen.repo.files.FilesFragment;
import com.miuty.slowgit.ui.screen.repo.readme.ReadMeFragment;
import com.miuty.slowgit.ui.screen.repo.releases.ReleasesFragment;

import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class CodeModule {

    @Provides
    FragmentNavigator provideFragmentNavigator(@ActivityContext Context context) {
        return new FragmentNavigatorImpl(context);
    }

    @Provides
    @ChildFragmentManager
    FragmentManager provideFragmentManager(CodeFragment fragment) {
        return fragment.getChildFragmentManager();
    }

    @Provides
    List<BaseFragment> provideBaseFragments() {
        return Stream.of(
                ReadMeFragment.newInstance(),
                FilesFragment.newInstance(),
                CommitsFragment.newInstance(),
                ReleasesFragment.newInstance(),
                ContributorsFragment.newInstance()
        ).collect(Collectors.toList());
    }

    @Provides
    CodePagerAdapter provideCodePagerAdapter(@ChildFragmentManager FragmentManager fragmentManager, List<BaseFragment> fragments) {
        return new CodePagerAdapter(fragmentManager, fragments);
    }
}
