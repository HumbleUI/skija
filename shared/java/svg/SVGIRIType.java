package io.github.humbleui.skija.svg;

import org.jetbrains.annotations.ApiStatus;

public enum SVGIRIType {
    LOCAL,
    NON_LOCAL,
    DATA_URI;

    @ApiStatus.Internal public static final SVGIRIType[] _values = values();
}
