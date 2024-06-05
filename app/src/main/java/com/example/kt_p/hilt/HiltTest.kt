package com.example.kt_p.hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject

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
