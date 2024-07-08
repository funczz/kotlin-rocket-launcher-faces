package com.github.funczz.kotlin.rocket_launcher.faces.bean

sealed interface BeanId {

    val returnValue: String

    object Ready : BeanId {
        override val returnValue: String = "index?faces-redirect=true"
    }

    object Counting : BeanId {
        override val returnValue: String = "counting?faces-redirect=true"
    }

    object Launched : BeanId {
        override val returnValue: String = "launched?faces-redirect=true"
    }

    object Aborted : BeanId {
        override val returnValue: String = "aborted?faces-redirect=true"
    }

    object Error : BeanId {
        override val returnValue: String = "error?faces-redirect=true"
    }

    object NOP : BeanId {
        override val returnValue: String = ""
    }

}