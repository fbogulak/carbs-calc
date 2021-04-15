package link.epoczta.carbscalc.ui.portioncarbs

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import link.epoczta.carbscalc.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
@MediumTest
class PortionCarbsFragmentTest {

    @Test
    fun enterTwoNumbers_localePl_correctResultDisplayedInUi() {

        // Given locale set to PL and fresh PortionCarbsFragment
        Locale.setDefault(Locale("pl", "PL"))
        launchFragmentInContainer<PortionCarbsFragment>(themeResId = R.style.Theme_CarbsCalc)

        // When enter numbers for portion weight and carbs in 100g
        onView(withId(R.id.portion_weight_edit)).perform(typeText("527"))
        onView(withId(R.id.carbs_in_100g_edit)).perform(typeText("12,37"))

        // Then correct result is displayed in UI
        onView(withId(R.id.carbs_in_portion_text)).check(matches(isDisplayed()))
        onView(withId(R.id.carbs_in_portion_text)).check(matches(withText("65,19")))
    }

    @Test
    fun enterTwoNumbers_localeUs_correctResultDisplayedInUi() {

        // Given locale set to US and fresh PortionCarbsFragment
        Locale.setDefault(Locale("en", "US"))
        launchFragmentInContainer<PortionCarbsFragment>(themeResId = R.style.Theme_CarbsCalc)

        // When enter numbers for portion weight and carbs in 100g
        onView(withId(R.id.portion_weight_edit)).perform(typeText("527"))
        onView(withId(R.id.carbs_in_100g_edit)).perform(typeText("12.37"))

        // Then correct result is displayed in UI
        onView(withId(R.id.carbs_in_portion_text)).check(matches(isDisplayed()))
        onView(withId(R.id.carbs_in_portion_text)).check(matches(withText("65.19")))
    }
}