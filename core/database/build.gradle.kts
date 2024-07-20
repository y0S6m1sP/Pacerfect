plugins {
    alias(libs.plugins.pacerect.android.library)
    alias(libs.plugins.pacerect.android.room)
}

android {
    namespace = "com.rocky.core.database"
}

dependencies {
    implementation(libs.org.mongodb.bson)

    implementation(projects.core.domain)
}