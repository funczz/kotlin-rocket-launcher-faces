package com.github.funczz.kotlin.rocket_launcher.faces

import com.github.funczz.kotlin.junit5.Cases
import com.github.funczz.kotlin.rocket_launcher.core.event.*
import com.github.funczz.kotlin.rocket_launcher.core.model.InputData
import com.github.funczz.kotlin.rocket_launcher.core.model.RocketLauncher
import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamAction
import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamModel
import com.github.funczz.kotlin.rocket_launcher.core.state.Counting
import com.github.funczz.kotlin.rocket_launcher.core.state.Ready
import com.github.funczz.kotlin.rocket_launcher.faces.bean.BeanId
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.TestFactory

class UiRepresentationTest : Cases {

    @TestFactory
    fun ready() = casesDynamicTest(
        Pair(10, BeanId.Counting::class.simpleName),
        Pair(0, BeanId.Launched::class.simpleName),
    ) { (counter, expected) ->
        val inputData = InputData(
            initialCounter = counter,
            currentCounter = counter,
            state = Ready,
            event = Start(counter),
        )
        val rocketLauncher = RocketLauncher(
            initialCounter = inputData.initialCounter,
            currentCounter = inputData.currentCounter,
            state = inputData.state,
            isTransitioned = false,
        )
        val rocketLauncherSamModel = RocketLauncherSamModel().apply {
            present(data = rocketLauncher)
        }
        representation(inputData = inputData, model = rocketLauncherSamModel)
        assertEquals(expected, uiState.beanId::class.simpleName)
    }

    @TestFactory
    fun counting() = casesDynamicTest(
        Pair(Decrement::class.simpleName, BeanId.NOP::class.simpleName),
        Pair(Abort::class.simpleName, BeanId.Aborted::class.simpleName),
    ) { (eventName, expected) ->
        val inputData = InputData(
            initialCounter = 10,
            currentCounter = 10,
            state = Counting,
            event = getEvent(name = eventName!!),
        )
        val rocketLauncher = RocketLauncher(
            initialCounter = inputData.initialCounter,
            currentCounter = inputData.currentCounter,
            state = inputData.state,
            isTransitioned = false,
        )
        val rocketLauncherSamModel = RocketLauncherSamModel().apply {
            present(data = rocketLauncher)
        }
        representation(inputData = inputData, model = rocketLauncherSamModel)
        assertEquals(expected, uiState.beanId::class.simpleName)
    }

    private fun representation(inputData: InputData, model: RocketLauncherSamModel) {
        RocketLauncherSamAction.accept(input = inputData, present = model::present)
        UiRepresentation.representation(model = model, render = ::render)
    }

    private fun render(output: UiState) {
        uiState = output
    }

    private fun getEvent(name: String): RocketLauncherEvent =
        when (name) {
            Decrement::class.simpleName -> Decrement
            Launch::class.simpleName -> Launch
            Abort::class.simpleName -> Abort
            else -> Start(10)
        }

    private lateinit var uiState: UiState

    private lateinit var uiPresenter: UiPresenter

    override fun setUpCases() {
        uiState = UiState()
        uiPresenter = UiPresenter()
    }

    override fun tearDownCases() {
    }

}