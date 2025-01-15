package io.github.humbleui.skija.svg;

import org.jetbrains.annotations.ApiStatus;

public enum SVGPaintType {
    NONE,
    COLOR,
    IRI;

    @ApiStatus.Internal public static final SVGPaintType[] _values = values();
}
