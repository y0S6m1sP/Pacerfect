plugins {
    alias(libs.plugins.pacerect.jvm.library)
}

dependencies {
    implementation(projects.core.domain)
}