package io.github.humbleui.skija.svg;

import org.jetbrains.annotations.ApiStatus;

public enum SVGFontWeight {
    WEIGHT_100,
    WEIGHT_200,
    WEIGHT_300,
    WEIGHT_400,
    WEIGHT_500,
    WEIGHT_600,
    WEIGHT_700,
    WEIGHT_800,
    WEIGHT_900,
    NORMAL,
    BOLD,
    BOLDER,
    LIGHTER,
    INHERIT;

    @ApiStatus.Internal public static final SVGFontWeight[] _values = values();
}
