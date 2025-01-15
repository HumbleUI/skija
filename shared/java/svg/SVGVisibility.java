package io.github.humbleui.skija.svg;

import org.jetbrains.annotations.ApiStatus;

public enum SVGVisibility {
    VISIBLE,
    HIDDEN,
    COLLAPSE,
    INHERIT;

    @ApiStatus.Internal public static final SVGVisibility[] _values = values();
}
