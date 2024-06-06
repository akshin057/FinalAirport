// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    id("com.google.gms.google-services") version "4.4.2" apply false

}

buildscript{
    repositories{
        google()
        mavenCentral()
    }

    dependencies{
        classpath("com.h2database:h2:1.4.187")
        classpath(libs.google.services)
    }

}

allprojects{
    repositories{
        google()
        mavenCentral()
        maven(url = "https://maven.google.com")
    }
}