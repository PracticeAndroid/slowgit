package com.miuty.slowgit.provider.scheduler;

import io.reactivex.ObservableTransformer;

/**
 * Created by Asus on 1/14/2018.
 */

public interface SchedulerProvider {

    <T> ObservableTransformer<T, T> observableIOScheduler();

    <T> ObservableTransformer<T, T> observableComputationScheduler();
}
