plugins {
    alias(libs.plugins.pacerfect.android.feature.ui)
}

android {
    namespace = "com.rocky.analytics.presentation"
}

dependencies {
    implementation(projects.analytics.domain)
    implementation(projects.core.domain)
}