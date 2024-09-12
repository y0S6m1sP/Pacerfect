plugins {
    alias(libs.plugins.pacerfect.android.feature.ui)
}

android {
    namespace = "com.rocky.auth.presentation"
}

dependencies {
    implementation(projects.auth.domain)
    implementation(projects.core.domain)
}