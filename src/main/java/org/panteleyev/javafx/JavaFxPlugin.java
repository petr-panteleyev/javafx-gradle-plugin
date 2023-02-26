/*
 Copyright © 2023 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
 */
package org.panteleyev.javafx;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class JavaFxPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.getExtensions().create("javafx", JavaFxOptions.class, project);
    }
}
