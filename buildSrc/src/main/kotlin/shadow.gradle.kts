import utils.minimizeShadowedDependencies
import utils.relocateShadowedDependencies
import utils.shadowRelocationPrefix

plugins {
    java
    id("com.gradleup.shadow")
}

val shadowImplementation: Configuration by configurations.creating {
    isCanBeConsumed = false
    isCanBeResolved = true
}

configurations.implementation {
    extendsFrom(shadowImplementation)
}

tasks.shadowJar {
    archiveClassifier = "shadowed"

    dependencies {
        exclude(dependency("org.jspecify:jspecify:.*"))
        exclude(dependency("org.jetbrains:annotations:.*"))
    }

    configurations = listOf(shadowImplementation)

    val shadowDowngrade = project.configurations.findByName("shadowDowngrade")
    if (shadowDowngrade != null) {
        configurations.add(shadowDowngrade)
    }

    if (minimizeShadowedDependencies.toBoolean()) {
        minimize()
    }

    if (relocateShadowedDependencies.toBoolean() && shadowRelocationPrefix.isNotEmpty()) {
        enableAutoRelocation = true
        relocationPrefix = shadowRelocationPrefix
    }
}
