package com.raywenderlich.rwnews.util

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

class HiltTestRunner : AndroidJUnitRunner() {

    // this is basically our app we make in the main project but for testing using hilt
    // and just like our application in the main app we tell in our gradle that this is the app
    // test runner we want to use to androidTests

    /*  testInstrumentationRunner "com.raywenderlich.rwnews.util.HiltTestRunner" // HERE*/
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(
            cl,
            HiltTestApplication::class.java.name, // this comes from hilt
            context
        )
    }
}