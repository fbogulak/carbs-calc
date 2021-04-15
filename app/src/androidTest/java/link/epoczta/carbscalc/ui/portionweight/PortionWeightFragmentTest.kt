package link.epoczta.carbscalc.ui.portionweight

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import link.epoczta.carbscalc.R
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
@MediumTest
class PortionWeightFragmentTest {
    
    @Test
    fun enterTwoNumbers_localePl_correctResultDisplayedInUi() {

        // Given locale set to PL and fresh PortionWeightFragment
        Locale.setDefault(Locale("pl", "PL"))
        launchFragmentInContainer<PortionWeightFragment>(themeResId = R.style.Theme_CarbsCalc)

        // When enter numbers for carbs in portion and carbs in 100g
        onView(withId(R.id.carbs_in_portion_edit)).perform(typeText("40"))
        onView(withId(R.id.carbs_in_100g_edit)).perform(typeText("37,4"))

        // Then correct result is displayed in UI
        onView(withId(R.id.portion_weight_text)).check(matches(isDisplayed()))
        onView(withId(R.id.portion_weight_text)).check(matches(withText("107")))
    }

    @Test
    fun enterTwoNumbers_localeUs_correctResultDisplayedInUi() {

        // Given locale set to US and fresh PortionWeightFragment
        Locale.setDefault(Locale("en", "US"))
        launchFragmentInContainer<PortionWeightFragment>(themeResId = R.style.Theme_CarbsCalc)

        // When enter numbers for carbs in portion and carbs in 100g
        onView(withId(R.id.carbs_in_portion_edit)).perform(typeText("40"))
        onView(withId(R.id.carbs_in_100g_edit)).perform(typeText("37.4"))

        // Then correct result is displayed in UI
        onView(withId(R.id.portion_weight_text)).check(matches(isDisplayed()))
        onView(withId(R.id.portion_weight_text)).check(matches(withText("107")))
    }
}