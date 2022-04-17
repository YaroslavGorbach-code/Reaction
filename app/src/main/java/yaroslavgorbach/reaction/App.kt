package yaroslavgorbach.reaction

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application(){

    val isProg: Boolean
        get() = BuildConfig.IS_PROD
}