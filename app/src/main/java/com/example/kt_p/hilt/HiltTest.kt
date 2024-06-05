package com.example.kt_p.hilt

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

class AnalyticsAdapter @Inject constructor(

) {
  fun analaytics(){

  }
}

interface AnalyticsService {
    fun analyticsMethods()

}

class AnalyticsServiceImpl @Inject constructor() : AnalyticsService {
    override fun analyticsMethods() {

    }

}

@Module
@InstallIn(ActivityComponent::class)
abstract class Modules {
    @Binds
    abstract fun bindAnalyticsService(asImpl: AnalyticsServiceImpl): AnalyticsService

}
//@InstallIn(Singleton::class)
//object AnalyticsHelper{
//  @Provides
//  fun provideAnalytics(@ApplicationContext context: ApplicationContext):AnalyticsService{
//      return AnalyticsServiceImpl()
//  }
//}
