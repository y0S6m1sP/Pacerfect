plugins {
    alias(libs.plugins.pacerect.android.feature.ui)
}

android {
    namespace = "com.rocky.auth.presentation"
}

dependencies {
    implementation(projects.auth.domain)
    implementation(projects.core.domain)
}