plugins {
    alias(libs.plugins.pacerfect.jvm.library)
}

dependencies {
    implementation(projects.core.domain)
    implementation(libs.kotlinx.coroutines.core)
}