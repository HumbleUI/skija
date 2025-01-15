package io.github.humbleui.skija.svg;

import org.jetbrains.annotations.ApiStatus;

public enum SVGDashArrayType {
    NONE,
    DASH_ARRAY,
    INHERIT;

    @ApiStatus.Internal public static final SVGDashArrayType[] _values = values();
}
