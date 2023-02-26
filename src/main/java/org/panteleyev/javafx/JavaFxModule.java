/*
 Copyright © 2023 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
 */
package org.panteleyev.javafx;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

enum JavaFxModule {
    BASE("javafx.base"),
    GRAPHICS("javafx.graphics", BASE),
    CONTROLS("javafx.controls", BASE, GRAPHICS),
    FXML("javafx.fxml", BASE, GRAPHICS),
    MEDIA("javafx.media", BASE, GRAPHICS),
    SWING("javafx.swing", BASE, GRAPHICS),
    WEB("javafx.web", BASE, CONTROLS, GRAPHICS, MEDIA);

    private static final String GROUP = "org.openjfx";
    private final String moduleName;
    private final String artifact;
    private final List<JavaFxModule> dependencies;

    JavaFxModule(String moduleName, JavaFxModule... dependencies) {
        this.moduleName = moduleName;
        this.artifact = moduleName.replace('.', '-');
        this.dependencies = Arrays.asList(dependencies);
    }

    public List<JavaFxModule> getDependencies() {
        return dependencies;
    }

    public static Optional<JavaFxModule> ofName(String name) {
        return Stream.of(values())
                .filter(value -> value.moduleName.equals(name))
                .findFirst();
    }

    public String buildMavenArtifact(String version, String variant) {
        return GROUP + ":" + artifact + ":" + version + ":" + variant;
    }
}
