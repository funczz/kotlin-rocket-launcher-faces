/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin application project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/8.1.1/userguide/building_java_projects.html
 */

/**
 * --- jakarta EE 10 TOP ---
 */

import com.github.funczz.gradle.plugin.payara_micro.payaraMicro

/**
 * plugins
 */
plugins {
    war
}

/**
 * buildscript
 */
buildscript {
    /**
     * repositories
     */
    repositories {
        //plugin: payara-micro-gradle-plugin
        maven {
            setUrl("https://funczz.github.io/payara-micro-gradle-plugin")
        }
    }
    /**
     * dependencies
     */
    dependencies {
        //plugin: payara-micro-gradle-plugin
        classpath("com.github.funczz:payara-micro-gradle-plugin:1.0.0")
    }
}

//plugin: payara-micro-gradle-plugin
apply(plugin = "payara-micro-gradle-plugin")

//plugin: payara-micro-gradle-plugin
payaraMicro {
    //val lib = File(projectDir, "../lib/build/download").canonicalPath
    //options = "--addLibs $lib --nocluster --port 8080 --contextroot /".split(" ")
    options = "--nocluster --port 8080 --contextroot /".split(" ")
}

dependencies {
    "jakarta.platform:jakarta.jakartaee-api:10.0.0".also {
        compileOnly(it)
        testCompileOnly(it)
    }
    "testRuntimeOnly"("fish.payara.extras:payara-micro:6.2023.8")
}

/**
 * --- jakarta EE 10 BOTTOM ---
 */

repositories {
    maven { setUrl("https://funczz.github.io/kotlin-fsm") }
    maven { setUrl("https://funczz.github.io/kotlin-sam") }
    maven { setUrl("https://funczz.github.io/kotlin-rocket-launcher-core") }
}

dependencies {
    implementation("com.github.funczz:rocket-launcher-core:0.2.0")
}
