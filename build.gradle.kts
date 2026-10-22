plugins {
    id("java-toolchain")
    id("shadow")
    id("minecraft")
    id("ide")
    id("repositories")
    id("publishing")
    id("test")
}

dependencies {
    compileOnlyApi(libs.annotations)
    compileOnlyApi(libs.jspecify)

    implementation(deobf.of(libs.jei))
    implementation(deobf.of(libs.theOneProbe))
}

configurations {
    compileOnly {
        // exclude GNU trove, FastUtil is superior and still updated
        exclude(group = "net.sf.trove4j", module = "trove4j")
        // exclude javax.annotation from findbugs, jetbrains annotations are superior
        exclude(group = "com.google.code.findbugs", module = "jsr305")
        // exclude scala as we don't use it for anything and causes import confusion
        exclude(group = "org.scala-lang")
        exclude(group = "org.scala-lang.modules")
        exclude(group = "org.scala-lang.plugins")
    }
}
