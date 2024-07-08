package com.github.funczz.kotlin.rocket_launcher.faces

import com.github.funczz.kotlin.rocket_launcher.faces.bean.BeanId
import java.io.Serializable

data class UiState(

    val currentCounter: Int = 0,

    val initialCounter: Int = 0,

    val isAborted: Boolean = false,

    val isLaunched: Boolean = false,

    val isStarted: Boolean = false,

    val isTransitioned: Boolean = false,

    val beanId: BeanId = BeanId.NOP,

    ) : Serializable {

    companion object {
        private const val serialVersionUID: Long = 5320417759361204939L
    }

}
