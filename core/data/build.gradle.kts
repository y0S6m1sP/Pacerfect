plugins {
    alias(libs.plugins.pacerfect.android.library)
    alias(libs.plugins.pacerfect.jvm.ktor)
}

android {
    namespace = "com.rocky.core.data"
}

dependencies {
    implementation(libs.timber)

    implementation(libs.bundles.koin)

    implementation(projects.core.domain)
    implementation(projects.core.database)
}