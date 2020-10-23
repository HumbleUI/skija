package org.jetbrains.skija;

import lombok.*;
import org.jetbrains.annotations.*;

@lombok.Data
@AllArgsConstructor
public class FontVariationAxis {
    @Getter(AccessLevel.NONE)
    @ApiStatus.Internal public final int _tag;
    @ApiStatus.Internal public final float _minValue;
    @ApiStatus.Internal public final float _defaultValue;
    @ApiStatus.Internal public final float _maxValue;
    @ApiStatus.Internal public final boolean _hidden;

    public String getTag() {
        return FontFeature.untag(_tag);
    }

    public FontVariationAxis(String tag, float min, float def, float max, boolean hidden) {
        this(FontFeature.tag(tag), min, def, max, hidden);
    }

    public FontVariationAxis(String tag, float min, float def, float max) {
        this(FontFeature.tag(tag), min, def, max, false);
    }
}