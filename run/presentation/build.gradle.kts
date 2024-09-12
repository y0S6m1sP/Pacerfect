plugins {
    alias(libs.plugins.pacerfect.android.feature.ui)
    alias(libs.plugins.mapsplatform.secrets.plugin)
}

android {
    namespace = "com.rocky.run.presentation"
}

dependencies {
    implementation(libs.coil.compose)
    implementation(libs.google.maps.android.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.timber)

    implementation(projects.core.domain)
    implementation(projects.run.domain)
}