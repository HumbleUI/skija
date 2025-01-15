package io.github.humbleui.skija.svg;

import org.jetbrains.annotations.ApiStatus;

public enum SVGTextAnchor {
    START,
    MIDDLE,
    END,
    INHERIT;

    @ApiStatus.Internal public static final SVGTextAnchor[] _values = values();
}
