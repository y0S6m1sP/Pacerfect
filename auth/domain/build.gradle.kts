plugins {
    alias(libs.plugins.pacerfect.jvm.library)
}

dependencies {
    implementation(projects.core.domain)
}