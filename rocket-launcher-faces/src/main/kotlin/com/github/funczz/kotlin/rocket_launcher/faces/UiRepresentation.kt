package com.github.funczz.kotlin.rocket_launcher.faces

import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamModel
import com.github.funczz.kotlin.rocket_launcher.core.sam.RocketLauncherSamState
import com.github.funczz.kotlin.rocket_launcher.faces.bean.BeanId
import com.github.funczz.kotlin.sam.SamStateRepresentation

object UiRepresentation : SamStateRepresentation<RocketLauncherSamModel, UiState> {

    override fun representation(model: RocketLauncherSamModel): UiState {
        val id = when {
            !model.isTransitioned -> BeanId.NOP
            RocketLauncherSamState.isReady(model = model) -> BeanId.Ready
            RocketLauncherSamState.isCounting(model = model) -> BeanId.Counting
            RocketLauncherSamState.isLaunched(model = model) -> BeanId.Launched
            RocketLauncherSamState.isAborted(model = model) -> BeanId.Aborted
            else -> throw IllegalStateException("model=$model")
        }
        return UiState(
            initialCounter = model.initialCounter,
            currentCounter = model.currentCounter,
            isStarted = model.isStarted,
            isLaunched = model.isLaunched,
            isAborted = model.isAborted,
            isTransitioned = model.isTransitioned,
            beanId = id,
        )
    }

}