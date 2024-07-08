package com.github.funczz.kotlin.rocket_launcher.faces.bean

import jakarta.enterprise.context.SessionScoped
import jakarta.inject.Named
import java.io.Serializable

@Named
@SessionScoped
@Suppress("unused")
open class ErrorBean : Serializable {

    open var errorMassage: String = ""

    open fun errorMassage(): String {
        return errorMassage
    }

    open fun error(message: String): String {
        errorMassage = message
        return BeanId.Error.returnValue
    }

    companion object {
        private const val serialVersionUID: Long = -7655130545615137189L
    }

}