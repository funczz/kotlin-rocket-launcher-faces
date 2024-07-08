package com.github.funczz.kotlin.rocket_launcher.faces

import jakarta.enterprise.context.SessionScoped
import java.io.Serializable

@SessionScoped
open class UiPresenter : Serializable {

    constructor()

    constructor(uiState: UiState) : this() {
        _uiState = uiState
    }

    protected open var _uiState = UiState()

    open val uiState: UiState
        get() = _uiState

    open fun render(output: UiState) {
        _uiState = output
    }

    companion object {
        private const val serialVersionUID: Long = 445339208047379622L
    }
}