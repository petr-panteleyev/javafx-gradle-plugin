/*
 Copyright © 2023 Petr Panteleyev <petr@panteleyev.org>
 SPDX-License-Identifier: BSD-2-Clause
 */
package org.panteleyev.javafx;

import org.gradle.api.GradleException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.panteleyev.javafx.JavaFxModule.BASE;
import static org.panteleyev.javafx.JavaFxModule.CONTROLS;
import static org.panteleyev.javafx.JavaFxModule.GRAPHICS;
import static org.panteleyev.javafx.JavaFxModule.MEDIA;
import static org.panteleyev.javafx.JavaFxModule.WEB;

public class JavaFxOptionsTest {
    private static List<Arguments> testCalculateModuleSetArguments() {
        return Arrays.asList(
                Arguments.of(emptyList(), emptyList()),
                Arguments.of(
                        singletonList("javafx.base"),
                        singletonList(BASE)
                ),
                Arguments.of(
                        singletonList("javafx.graphics"),
                        Arrays.asList(BASE, GRAPHICS)
                ),
                Arguments.of(
                        Arrays.asList("javafx.graphics", "javafx.base"),
                        Arrays.asList(BASE, GRAPHICS)
                ),
                Arguments.of(
                        Arrays.asList("javafx.web", "javafx.graphics", "javafx.base"),
                        Arrays.asList(BASE, GRAPHICS, CONTROLS, MEDIA, WEB)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("testCalculateModuleSetArguments")
    public void testCalculateModuleSet(Collection<String> moduleNames, Collection<JavaFxModule> modules) {
        assertEquals(modules, new ArrayList<>(JavaFxOptions.calculateModuleSet(moduleNames)));
    }

    @Test
    public void testCalculateWithWrongModules() {
        assertThrows(GradleException.class, () -> {
            JavaFxOptions.calculateModuleSet(Arrays.asList("javafx.base", "javafx.wrong"));
        });
    }
}
