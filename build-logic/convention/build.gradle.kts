plugins {
    `kotlin-dsl`
}

group ="com.rocky.pacerfect.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication"){
            id = "pacerfect.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose"){
            id = "pacerfect.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibrary"){
            id = "pacerfect.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose"){
            id = "pacerfect.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidFeatureUi"){
            id = "pacerfect.android.feature.ui"
            implementationClass = "AndroidFeatureUiConventionPlugin"
        }
        register("androidDynamicFeature"){
            id = "pacerfect.android.dynamic.feature"
            implementationClass = "AndroidDynamicFeatureConventionPlugin"
        }
        register("androidRoom"){
            id = "pacerfect.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("jvmLibrary"){
            id = "pacerfect.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
        register("jvmKtor"){
            id = "pacerfect.jvm.ktor"
            implementationClass = "JvmKtorConventionPlugin"
        }
        register("jvmJUnit5"){
            id = "pacerfect.jvm.junit5"
            implementationClass = "JvmJUnit5ConventionPlugin"
        }
        register("androidJUnit5"){
            id = "pacerfect.android.junit5"
            implementationClass = "AndroidJUnit5ConventionPlugin"
        }
    }
}