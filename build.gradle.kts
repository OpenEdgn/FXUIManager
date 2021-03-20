group = "com.github.openEDGN"
version = "last"

plugins {
    id("org.openjfx.javafxplugin") version "0.0.9" apply false
}

buildscript {
    repositories {
        mavenLocal()
        maven { url = project.uri("https://maven.aliyun.com/repository/public/") }
        jcenter()
        mavenCentral()
        maven { url = project.uri("https://jitpack.io") }
    }

    val kotlinVersion: String by rootProject

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}

allprojects {
    repositories {
        mavenLocal()
        maven { url = project.uri("https://maven.aliyun.com/repository/public/") }
        jcenter()
        mavenCentral()
        maven { url = project.uri("https://jitpack.io") }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
    for (childProject in childProjects.values) {
        delete(childProject.buildDir)
    }
}
