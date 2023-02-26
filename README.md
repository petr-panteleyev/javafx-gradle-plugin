# JavaFX Gradle Plugin

[![Gradle Plugin Portal](https://img.shields.io/maven-metadata/v/https/plugins.gradle.org/m2/org/panteleyev/javafxplugin/org.panteleyev.javafxplugin.gradle.plugin/maven-metadata.xml.svg?label=Gradle%20Plugin)](https://plugins.gradle.org/plugin/org.panteleyev.javafxplugin)
[![Gradle](https://img.shields.io/badge/Gradle-6.7.1%2B-green)](https://gradle.org/)
[![Java](https://img.shields.io/badge/Java-8-orange?logo=java)](https://www.oracle.com/java/technologies/javase-downloads.html)
[![GitHub](https://img.shields.io/github/license/petr-panteleyev/javafx-gradle-plugin)](LICENSE)

This is a minimal replacement for the official [JavaFX plugin](https://github.com/openjfx/javafx-gradle-plugin). 
The only functionality provided is adding Maven dependencies. Transitive module dependencies are added automatically
as well.

## Configuration

```kotlin
javafx {
    modules(19, listOf(
        "javafx.web",
        "javafx.fxml"
    ))
}
```