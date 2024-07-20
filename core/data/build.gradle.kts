plugins {
    alias(libs.plugins.pacerect.android.library)
    alias(libs.plugins.pacerect.jvm.ktor)
}

android {
    namespace = "com.rocky.core.data"
}

dependencies {
    implementation(libs.timber)

    implementation(projects.core.domain)
    implementation(projects.core.database)
}