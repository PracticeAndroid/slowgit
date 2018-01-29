package com.miuty.slowgit.ui.screen.repo.files;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FilesFragmentProvider {

    @ContributesAndroidInjector(modules = {
            FilesModule.class
    })
    abstract FilesFragment providerFilesFragmentFactory();
}
