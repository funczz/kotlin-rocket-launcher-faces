package com.github.funczz.kotlin.rocket_launcher.faces.bean

import com.github.funczz.kotlin.rocket_launcher.faces.UiPresenter
import jakarta.enterprise.context.SessionScoped
import jakarta.enterprise.inject.Default
import jakarta.inject.Inject
import jakarta.inject.Named
import java.io.Serializable

@Named
@SessionScoped
@Suppress("unused")
open class AbortedBean : Serializable {

    @field:Default
    @field:Inject
    protected open lateinit var uiPresenter: UiPresenter

    val currentCounter: Int
        get() = uiPresenter.uiState.currentCounter

    companion object {
        private const val serialVersionUID: Long = 2117172177549502040L
    }

}