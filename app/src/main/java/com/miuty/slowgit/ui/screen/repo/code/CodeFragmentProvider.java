package com.miuty.slowgit.ui.screen.repo.code;


import com.miuty.slowgit.ui.screen.repo.commits.CommitsFragmentProvider;
import com.miuty.slowgit.ui.screen.repo.contributors.ContributorsFragmentProvider;
import com.miuty.slowgit.ui.screen.repo.files.FilesFragmentProvider;
import com.miuty.slowgit.ui.screen.repo.readme.ReadMeFragmentProvider;
import com.miuty.slowgit.ui.screen.repo.releases.ReleasesFragmentProvider;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CodeFragmentProvider {

    @ContributesAndroidInjector(modules = {
            CodeModule.class,
            ReadMeFragmentProvider.class,
            FilesFragmentProvider.class,
            CommitsFragmentProvider.class,
            ReleasesFragmentProvider.class,
            ContributorsFragmentProvider.class
    })
    abstract CodeFragment provideCodeFragmentFactory();
}
