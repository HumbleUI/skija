package io.github.humbleui.skija.svg;

import org.jetbrains.annotations.ApiStatus;

public enum SVGFillRule {
    NON_ZERO,
    EVEN_ODD,
    INHERIT;

    @ApiStatus.Internal public static final SVGFillRule[] _values = values();
}
