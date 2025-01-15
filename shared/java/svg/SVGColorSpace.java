package io.github.humbleui.skija.svg;

import org.jetbrains.annotations.ApiStatus;

public enum SVGColorSpace {
    AUTO,
    SRGB,
    LINEAR_RGB;

    @ApiStatus.Internal public static final SVGColorSpace[] _values = values();
}
