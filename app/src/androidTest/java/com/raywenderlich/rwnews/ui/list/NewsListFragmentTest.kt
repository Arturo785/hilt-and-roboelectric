package com.raywenderlich.rwnews.ui.list

import android.provider.SyncStateContract.Helpers.insert
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.raywenderlich.rwnews.di.AppModule
import com.raywenderlich.rwnews.logger.RwNewsLogger
import com.raywenderlich.rwnews.repository.NewsRepository
import com.raywenderlich.rwnews.repository.entity.News
import com.raywenderlich.rwnews.util.FakeNewsLogger
import com.raywenderlich.rwnews.util.FakeNewsRepository
import com.raywenderlich.rwnews.util.launchFragmentInHiltContainer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.raywenderlich.rwnews.R


@HiltAndroidTest
@UninstallModules(AppModule::class) // HERE
class NewsListFragmentTest {

    @get:Rule
    var hiltAndroidRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
    }

    // we could do this or create our own module like this, I prefer making the file and module in a file in this same
    // directory of android test but this also works
/*    @Module
    @InstallIn(SingletonComponent::class) // 1
    object TestAppModule {

        @Provides
        fun provideNewsRepository(): NewsRepository { // 2
            return FakeNewsRepository().apply {
                insert(News(1, "First Title", "First Body"))
                insert(News(2, "Second Title", "Second Body"))
                insert(News(3, "Third Title", "Third Body"))
            }
        }

        @Provides
        fun provideNewsLogger(): RwNewsLogger = FakeNewsLogger() // 2
    }*/

    @Test
    fun whenDisplayed_newsListFromRepoIsDisplayed() { // 1
        // works right away because the fragment receives repository injected, and in here we provide
        // the fake one with our own di module in testing
        launchFragmentInHiltContainer<NewsListFragment>() {

        } // 2
        scrollAtAndCheckTestVisible(0, "First Title")
        scrollAtAndCheckTestVisible(1, "Second Title")
        scrollAtAndCheckTestVisible(2, "Third Title")


        // does not work
  /*      onView(ViewMatchers.withId(R.id.recycler_view))
            .perform(RecyclerViewActions.actionOnItemAtPosition<NewsListItemViewHolder>(0, click()))

        onView(withText("First Title")).check(matches(isDisplayed()))*/
    }

    fun scrollAtAndCheckTestVisible(position: Int, text: String) {
        onView(ViewMatchers.withId(R.id.recycler_view))
            .perform(
                RecyclerViewActions
                    .scrollToPosition<NewsListItemViewHolder>(position) // the viewHolder the one from the main app
            )
        onView(withText(text)).check(matches(isDisplayed()))
    }

    /*This test should be quite straightforward now, but here are a few things to note:
Implement the test, asserting that the data from NewsRepository actually display in the RecyclerView in NewsListFragment.
Use launchFragmentInHiltContainer() to launch NewsListFragment in the HiltActivityForTest you prepared earlier.
Now, you can simply run the test as usual. With a successful test, you’ll get something like this:
*/
}