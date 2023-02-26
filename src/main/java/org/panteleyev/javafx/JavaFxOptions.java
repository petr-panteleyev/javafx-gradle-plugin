/*
 Copyright © 2023 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
 */
package org.panteleyev.javafx;

import org.gradle.api.GradleException;
import org.gradle.api.Project;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.gradle.internal.os.OperatingSystem.current;

public class JavaFxOptions {
    private static final String CONFIGURATION = "implementation";

    private final Project project;

    public JavaFxOptions(Project project) {
        this.project = project;
    }

    public void modules(String version, List<String> modules) {
        if (version == null || version.isEmpty()) {
            throw new GradleException("JavaFX version cannot be null or empty");
        }
        if (modules == null || modules.isEmpty()) {
            throw new GradleException("JavaFX modules cannot be null or empty");
        }

        String variant = getVariant();

        calculateModuleSet(modules).stream()
                .map(module -> module.buildMavenArtifact(version, variant))
                .forEach(artifact -> project.getDependencies().add(CONFIGURATION, artifact));
    }

    private static String getVariant() {
        if (current().isWindows()) {
            return "win";
        } else if (current().isMacOsX()) {
            return "mac";
        } else if (current().isLinux()) {
            return "linux";
        } else {
            throw new GradleException("Unsupported operating system");
        }
    }

    static Collection<JavaFxModule> calculateModuleSet(Collection<String> moduleNames) {
        Set<JavaFxModule> moduleSet = new TreeSet<>();
        for (String name : moduleNames) {
            JavaFxModule module = JavaFxModule.ofName(name)
                    .orElseThrow(() -> new GradleException("Undefined module: " + name));
            moduleSet.add(module);
            moduleSet.addAll(module.getDependencies());
        }
        return moduleSet;
    }
}
