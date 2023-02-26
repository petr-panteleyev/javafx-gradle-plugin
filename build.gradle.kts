/*
 Copyright © 2023 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
 */

group = "org.panteleyev"
version = "1.0.0"

plugins {
    java
    `java-gradle-plugin`
    `maven-publish`
    id("com.gradle.plugin-publish") version "1.0.0"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.1")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

gradlePlugin {
    val javafx by plugins.creating {
        id = "org.panteleyev.javafxplugin"
        version = project.version
        displayName = "JavaFX Gradle Plugin"
        description = "A plugin that maintains JavaFX module dependencies"
        implementationClass = "org.panteleyev.javafx.JavaFxPlugin"
    }
}

pluginBundle {
    website = "https://github.com/petr-panteleyev/javafx-gradle-plugin"
    vcsUrl = "https://github.com/petr-panteleyev/javafx-gradle-plugin.git"
    tags = listOf("javafx")
}

tasks.withType<Test> {
    useJUnitPlatform()
}