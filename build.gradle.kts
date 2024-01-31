// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Classpath.gradle)
        classpath(Classpath.kotlin)
        classpath(Classpath.serialization)
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}