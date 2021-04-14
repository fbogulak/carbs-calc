package link.epoczta.carbscalc

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*

class MainViewModelTest {

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var portionCarbsViewModel: MainViewModel

    @Before
    fun setup() {
        portionCarbsViewModel = MainViewModel()
        Locale.setDefault(Locale("en", "US"))
    }

    @Test
    fun calculatePortionCarbs_someNumbers_returnsCorrectlyRoundedNumber() {
        // Given
        portionCarbsViewModel.portionWeightString.value = "527"
        portionCarbsViewModel.carbsIn100gString.value = "12.37"

        // When
        portionCarbsViewModel.calculatePortionCarbs()

        // Then
        val result = portionCarbsViewModel.carbsInPortionString.getOrAwaitValue()
        MatcherAssert.assertThat(result, CoreMatchers.`is`("65.19"))
    }

    @Test
    fun calculatePortionCarbs_emptyStringAndNumber_returnsZero() {
        // Given
        portionCarbsViewModel.portionWeightString.value = ""
        portionCarbsViewModel.carbsIn100gString.value = "12.37"

        // When
        portionCarbsViewModel.calculatePortionCarbs()

        // Then
        val result = portionCarbsViewModel.carbsInPortionString.getOrAwaitValue()
        MatcherAssert.assertThat(result, CoreMatchers.`is`("0.00"))
    }

    @Test
    fun calculatePortionCarbs_nullAndNumber_returnsZero() {
        // Given
        portionCarbsViewModel.portionWeightString.value = null
        portionCarbsViewModel.carbsIn100gString.value = "12.37"

        // When
        portionCarbsViewModel.calculatePortionCarbs()

        // Then
        val result = portionCarbsViewModel.carbsInPortionString.getOrAwaitValue()
        MatcherAssert.assertThat(result, CoreMatchers.`is`("0.00"))
    }

    @Test
    fun calculatePortionCarbs_nullAndEmptyString_returnsZero() {
        // Given
        portionCarbsViewModel.portionWeightString.value = null
        portionCarbsViewModel.carbsIn100gString.value = ""

        // When
        portionCarbsViewModel.calculatePortionCarbs()

        // Then
        val result = portionCarbsViewModel.carbsInPortionString.getOrAwaitValue()
        MatcherAssert.assertThat(result, CoreMatchers.`is`("0.00"))
    }
}