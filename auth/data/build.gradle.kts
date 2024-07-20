plugins {
    alias(libs.plugins.pacerect.android.library)
    alias(libs.plugins.pacerect.jvm.ktor)
}

android {
    namespace = "com.rocky.auth.data"
}

dependencies {
    implementation(projects.auth.domain)
    implementation(projects.core.domain)
    implementation(projects.core.data)
}