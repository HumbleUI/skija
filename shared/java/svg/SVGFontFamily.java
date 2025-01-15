package io.github.humbleui.skija.svg;

import lombok.Data;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

@Data
public class SVGFontFamily {
    @ApiStatus.Internal public final SVGFontFamilyType _type;
    @ApiStatus.Internal @Nullable public final String _family;

    @ApiStatus.Internal
    public SVGFontFamily(SVGFontFamilyType type, @Nullable String family) {
        _type = type;
        _family = family;
    }

    @ApiStatus.Internal
    public SVGFontFamily(int type, @Nullable String family) {
        this(SVGFontFamilyType._values[type], family);
    }

    public SVGFontFamily(String family) {
        this(SVGFontFamilyType.FAMILY, family);
    }

    public SVGFontFamily() {
        this(SVGFontFamilyType.INHERIT, null);
    }
}
