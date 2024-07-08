package com.github.funczz.kotlin.rocket_launcher.faces.bean

import com.github.funczz.kotlin.rocket_launcher.core.event.Start
import com.github.funczz.kotlin.rocket_launcher.core.model.InputData
import com.github.funczz.kotlin.rocket_launcher.core.model.RocketLauncher
import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamAction
import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamModel
import com.github.funczz.kotlin.rocket_launcher.core.state.Ready
import com.github.funczz.kotlin.rocket_launcher.faces.UiPresenter
import com.github.funczz.kotlin.rocket_launcher.faces.UiRepresentation
import jakarta.enterprise.context.SessionScoped
import jakarta.enterprise.inject.Default
import jakarta.inject.Inject
import jakarta.inject.Named
import java.io.Serializable
import java.util.*

@Named
@SessionScoped
@Suppress("unused")
open class IndexBean : Serializable {

    open var inputCounter: Int? = null

    @field:Default
    @field:Inject
    protected open lateinit var uiPresenter: UiPresenter

    @field:Default
    @field:Inject
    protected open lateinit var errorBean: ErrorBean

    open fun start(): String? {
        val initialCounter = try {
            Optional.of(inputCounter!!.toInt())
        } catch (e: NullPointerException) {
            Optional.empty<Int>()
        } catch (e: NumberFormatException) {
            Optional.empty<Int>()
        } catch (e: Exception) {
            inputCounter = null
            return errorBean.error(e.stackTraceToString())
        }
        if (!initialCounter.isPresent) return null
        return try {
            val inputData = InputData(
                initialCounter = initialCounter.get(),
                currentCounter = initialCounter.get(),
                state = Ready,
                event = Start(initialCounter.get()),
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
            val beanId = uiPresenter.uiState.beanId
            if (beanId != BeanId.NOP) inputCounter = null
            beanId.returnValue
        } catch (e: Throwable) {
            inputCounter = null
            return errorBean.error(e.stackTraceToString())
        }
    }

    companion object {
        private const val serialVersionUID: Long = 1995866197924346622L
    }
}