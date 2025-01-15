package io.github.humbleui.skija.svg;

import org.jetbrains.annotations.ApiStatus;

public enum SVGLineJoin {
    MITER,
    ROUND,
    BEVEL,
    INHERIT;

    @ApiStatus.Internal public static final SVGLineJoin[] _values = values();
}
