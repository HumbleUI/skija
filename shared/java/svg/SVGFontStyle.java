package io.github.humbleui.skija.svg;

import org.jetbrains.annotations.ApiStatus;

public enum SVGFontStyle {
    NORMAL,
    ITALIC,
    OBLIQUE,
    INHERIT;

    @ApiStatus.Internal public static final SVGFontStyle[] _values = values();
}