plugins {
    alias(libs.plugins.pacerfect.android.library)
    alias(libs.plugins.pacerfect.jvm.ktor)
}

android {
    namespace = "com.rocky.auth.data"
}

dependencies {
    implementation(libs.bundles.koin)

    implementation(projects.auth.domain)
    implementation(projects.core.domain)
    implementation(projects.core.data)
}