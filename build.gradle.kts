group = "com.cache"
version = 1.0

plugins {
    kotlin("jvm") version "1.8.20"
    id("maven-publish")
}

repositories {
    mavenCentral()
    maven(url = "https://repo.runelite.net/")
    maven(url = "https://repo.openrs2.org/repository/openrs2-snapshots/")
    maven(url = "https://jitpack.io")
}



dependencies {
    implementation("io.github.microutils:kotlin-logging:1.12.5")
    implementation("org.slf4j:slf4j-simple:1.7.29")
    implementation("me.tongfei:progressbar:0.9.2")
    implementation("com.beust:klaxon:5.5")
    implementation("com.google.code.gson:gson:2.8.9")
    implementation("net.lingala.zip4j:zip4j:2.9.1")
    implementation("com.displee:rs-cache-library:6.8")
    implementation("commons-io:commons-io:2.11.0")

    implementation("it.unimi.dsi:fastutil:8.5.12")
    implementation("io.netty:netty-all:4.1.87.Final")
    implementation("org.openrs2", "openrs2", "0.1.0-SNAPSHOT")
    implementation("org.openrs2", "openrs2-buffer", "0.1.0-SNAPSHOT")
    implementation("org.openrs2", "openrs2-crypto", "0.1.0-SNAPSHOT")
    implementation("org.openrs2", "openrs2-cache", "0.1.0-SNAPSHOT")

    implementation("io.netty:netty-transport-native-kqueue:4.1.92.Final")
    implementation("io.netty.incubator:netty-incubator-transport-native-io_uring:0.0.21.Final")

    implementation("org.jctools:jctools-core:4.0.1")


}

tasks.withType<Jar>() {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/Mark7625/Osrs-Data-packer")
            credentials {
                username = System.getenv("GITHUB_ACTION")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
    publications {
        create<MavenPublication>("gpr") {
            groupId = "com.mark"
            artifactId = "osrs-cache-packer"
            version = "1.0"

            from(components["java"])
        }
    }
}
