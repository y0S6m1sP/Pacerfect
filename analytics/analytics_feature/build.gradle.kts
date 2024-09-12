plugins {
    alias(libs.plugins.pacerfect.android.dynamic.feature)
}
android {
    namespace = "com.rocky.analytics.dynamicfeature"
}

dependencies {
    implementation(project(":app"))
    implementation(libs.androidx.navigation.compose)

    api(projects.analytics.presentation)
    implementation(projects.analytics.domain)
    implementation(projects.analytics.data)
    implementation(projects.core.database)
}