import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("org.openjfx.javafxplugin")
    application
}
java.sourceCompatibility = JavaVersion.VERSION_11

val compileKotlin: KotlinCompile by tasks
val compileJava: JavaCompile by tasks
compileJava.destinationDir = compileKotlin.destinationDir

application{
    mainClass.set("simples.MainKt")
}

dependencies {
    val logger4kVersion:String by rootProject
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib"))
    implementation(project(":manager"))
    implementation("com.github.OpenEdgn.Logger4K:logger-console:$logger4kVersion"){
        exclude("org.jetbrains.kotlin","kotlin-reflect")
        exclude("org.jetbrains.kotlin","kotlin-stdlib")
    }
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
    testImplementation("org.junit.platform:junit-platform-launcher:1.6.2")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "11"
}

javafx {
    version = "15.0.1"
    modules("javafx.controls", "javafx.fxml")
}


java{
    modularity.inferModulePath.set(true)
}
