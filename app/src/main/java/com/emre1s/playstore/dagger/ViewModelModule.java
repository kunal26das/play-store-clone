package com.emre1s.playstore.dagger;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.emre1s.playstore.ui.main.PageViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(PageViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(PageViewModel.class)
    protected abstract ViewModel movieListViewModel(PageViewModel moviesListViewModel);
}
