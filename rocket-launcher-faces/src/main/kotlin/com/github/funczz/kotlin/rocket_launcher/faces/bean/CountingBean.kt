package com.github.funczz.kotlin.rocket_launcher.faces.bean

import com.github.funczz.kotlin.rocket_launcher.core.event.Abort
import com.github.funczz.kotlin.rocket_launcher.core.event.Decrement
import com.github.funczz.kotlin.rocket_launcher.core.model.InputData
import com.github.funczz.kotlin.rocket_launcher.core.model.RocketLauncher
import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamAction
import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamModel
import com.github.funczz.kotlin.rocket_launcher.core.state.Counting
import com.github.funczz.kotlin.rocket_launcher.faces.UiPresenter
import com.github.funczz.kotlin.rocket_launcher.faces.UiRepresentation
import jakarta.enterprise.context.SessionScoped
import jakarta.enterprise.inject.Default
import jakarta.inject.Inject
import jakarta.inject.Named
import java.io.Serializable

@Named
@SessionScoped
@Suppress("unused")
open class CountingBean : Serializable {

    open val counter: Int
        get() = uiPresenter.uiState.currentCounter

    @field:Default
    @field:Inject
    protected open lateinit var uiPresenter: UiPresenter

    @field:Default
    @field:Inject
    protected open lateinit var errorBean: ErrorBean

    open fun decrement(): String? {
        return try {
            val inputData = InputData(
                initialCounter = uiPresenter.uiState.initialCounter,
                currentCounter = uiPresenter.uiState.currentCounter,
                state = Counting,
                event = Decrement,
            )
            val rocketLauncher = RocketLauncher(
                initialCounter = inputData.initialCounter,
                currentCounter = inputData.currentCounter,
                state = inputData.state,
                isTransitioned = false
            )
            val rocketLauncherSamModel = RocketLauncherSamModel().apply {
                present(data = rocketLauncher)
            }
            RocketLauncherSamAction.accept(
                input = inputData,
                present = rocketLauncherSamModel::present
            )
            UiRepresentation.representation(
                model = rocketLauncherSamModel,
                render = uiPresenter::render
            )
            uiPresenter.uiState.beanId.returnValue
        } catch (e: Throwable) {
            return errorBean.error(e.stackTraceToString())
        }
    }

    open fun abort(): String? {
        return try {
            val inputData = InputData(
                initialCounter = uiPresenter.uiState.initialCounter,
                currentCounter = uiPresenter.uiState.currentCounter,
                state = Counting,
                event = Abort,
            )
            val rocketLauncher = RocketLauncher(
                initialCounter = inputData.initialCounter,
                currentCounter = inputData.currentCounter,
                state = inputData.state,
                isTransitioned = false
            )
            val rocketLauncherSamModel = RocketLauncherSamModel().apply {
                present(data = rocketLauncher)
            }
            RocketLauncherSamAction.accept(
                input = inputData,
                present = rocketLauncherSamModel::present
            )
            UiRepresentation.representation(
                model = rocketLauncherSamModel,
                render = uiPresenter::render
            )
            uiPresenter.uiState.beanId.returnValue
        } catch (e: Throwable) {
            return errorBean.error(e.stackTraceToString())
        }
    }

    companion object {
        private const val serialVersionUID: Long = -1435246263121430858L
    }

}