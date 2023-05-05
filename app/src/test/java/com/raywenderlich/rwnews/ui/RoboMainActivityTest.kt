package com.raywenderlich.rwnews.ui


import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.google.common.truth.Truth.assertThat
import com.raywenderlich.rwnews.di.ActivityModule
import com.raywenderlich.rwnews.ui.list.NewsListFragment
import com.raywenderlich.rwnews.ui.navigation.NavigationHelper
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import dagger.hilt.android.testing.UninstallModules
import fakes.FakeNavigationHelper
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode
import com.raywenderlich.rwnews.R
import com.raywenderlich.rwnews.ui.list.NewsListItemViewHolder

//https://www.notion.so/RoboElectric-and-hilt-usage-67b67328a3c5401e9808ee91049f8e05

@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(RobolectricTestRunner::class)
@LooperMode(LooperMode.Mode.PAUSED)
@UninstallModules(ActivityModule::class)
class RoboMainActivityTest {

    @get:Rule(order = 0)
    var hiltAndroidRule = HiltAndroidRule(this)

    @get:Rule(order = 1) // 2
    var activityScenarioRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java) // 1

    @BindValue // 1
    @JvmField // 2
    val navigator: NavigationHelper = FakeNavigationHelper()  // 3

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
    }

    @Test
    fun whenMainActivityLaunchedNavigatorIsInvokedForFragment() {
        activityScenarioRule.scenario // 1
        val fakeHelper = navigator as FakeNavigationHelper // 2

        with(fakeHelper.replaceRequests[0]) { // 3
            assertThat(anchorId)
                .isEqualTo(R.id.anchor)
            assertThat(fragment)
                .isInstanceOf(NewsListFragment::class.java)
            assertThat(backStack)
                .isNull()
        }
    }
}