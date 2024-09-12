plugins {
    alias(libs.plugins.pacerfect.android.library)
    alias(libs.plugins.pacerfect.android.room)
}

android {
    namespace = "com.rocky.core.database"
}

dependencies {
    implementation(libs.org.mongodb.bson)
    implementation(libs.bundles.koin)

    implementation(projects.core.domain)
}