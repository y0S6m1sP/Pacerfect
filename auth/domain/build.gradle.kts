plugins {
    alias(libs.plugins.pacerfect.jvm.library)
    alias(libs.plugins.pacerfect.jvm.junit5)
}

dependencies {
    implementation(projects.core.domain)
}