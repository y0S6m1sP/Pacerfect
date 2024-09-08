plugins {
    alias(libs.plugins.pacerect.android.library)
    alias(libs.plugins.pacerect.jvm.ktor)
}

android {
    namespace = "com.rocky.run.network"
}

dependencies {
    implementation(libs.bundles.koin)

    implementation(projects.core.domain)
    implementation(projects.core.data)
}