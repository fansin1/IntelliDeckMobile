package org.fansin.intellideck.di

import dagger.Module
import dagger.Provides
import org.fansin.intellideck.MainActivity

@Module
class ActivityModule(@get:Provides val activity: MainActivity) {
}