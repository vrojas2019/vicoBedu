plugins {
    kotlin("jvm") version "1.8.0"
    kotlin("plugin.serialization") version "1.8.10"
    application

}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    //Serialización en JSON
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    //retrofit https://square.github.io/retrofit/
    implementation("com.squareup.retrofit2:retrofit:2.7.1")
    //Gson
    implementation("com.squareup.retrofit2:converter-gson:2.1.0")
//courotine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.4")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}