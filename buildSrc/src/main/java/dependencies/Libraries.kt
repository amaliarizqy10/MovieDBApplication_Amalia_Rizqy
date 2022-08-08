package dependencies

import dependencies.android.androidX
import dependencies.android.vmLifeCycle
import dependencies.kotlin.coroutine
import dependencies.retrofit_okhttp.gson
import dependencies.retrofit_okhttp.okHttp
import dependencies.retrofit_okhttp.retrofit
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.appLibraries() {
    androidCore()
    materialDesign()
    testUnit()
    androidX()
    vmLifeCycle()
    navGraph()
    coroutine()
    gson()
    okHttp()
    retrofit()
    glide()
    gander()
    daggerHilt()
    androidPaging()
    youtubePlayer()
}