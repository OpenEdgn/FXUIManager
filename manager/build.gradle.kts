import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    `maven-publish`
    id("org.openjfx.javafxplugin")

}
java.sourceCompatibility = JavaVersion.VERSION_11

val compileKotlin: KotlinCompile by tasks
val compileJava: JavaCompile by tasks
compileJava.destinationDir = compileKotlin.destinationDir

java{
    withSourcesJar()
    modularity.inferModulePath.set(true)

}


dependencies {
    val logger4kVersion:String by rootProject
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib"))
    api("com.github.OpenEdgn.Logger4K:core:$logger4kVersion"){
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

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = rootProject.group.toString()
            artifactId = project.name
            version = rootProject.version.toString()
            from(components["java"])
        }

    }

    repositories {
        mavenLocal()
    }
}

javafx {
    version = "15.0.1"
    modules("javafx.controls", "javafx.fxml")
    configuration = "compileOnly"
}

