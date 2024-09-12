plugins {
    alias(libs.plugins.pacerfect.android.library)
}

android {
    namespace = "com.rocky.analytics.data"
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.bundles.koin)

    implementation(projects.core.database)
    implementation(projects.core.domain)
    implementation(projects.analytics.domain)
}