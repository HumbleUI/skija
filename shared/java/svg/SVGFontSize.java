package io.github.humbleui.skija.svg;

import lombok.Data;
import org.jetbrains.annotations.ApiStatus;

@Data
public class SVGFontSize {
    @ApiStatus.Internal public final SVGFontSizeType _type;
    @ApiStatus.Internal public final SVGLength _size;

    @ApiStatus.Internal
    public SVGFontSize(SVGFontSizeType type, SVGLength size) {
        _type = type;
        _size = size;
    }

    @ApiStatus.Internal
    public SVGFontSize(int type, SVGLength size) {
        this(SVGFontSizeType._values[type], size);
    }

    public SVGFontSize() {
        this(SVGFontSizeType.INHERIT, new SVGLength(0f));
    }

    public SVGFontSize(SVGLength size) {
        this(SVGFontSizeType.LENGTH, size);
    }
}
