package io.github.humbleui.skija.svg;

import lombok.Data;
import org.jetbrains.annotations.ApiStatus;

@Data
public class SVGDashArray {
    @ApiStatus.Internal public final SVGDashArrayType _type;
    @ApiStatus.Internal public final SVGLength[] _dashArray;

    @ApiStatus.Internal
    public SVGDashArray(SVGDashArrayType type, SVGLength[] dashArray) {
        _type = type;
        _dashArray = dashArray;
    }

    @ApiStatus.Internal
    public SVGDashArray(int type, SVGLength[] dashArray) {
        this(SVGDashArrayType._values[type], dashArray);
    }

    public SVGDashArray() {
        this(SVGDashArrayType.NONE, new SVGLength[]{});
    }

    public SVGDashArray(SVGLength[] dashArray) {
        this(SVGDashArrayType.DASH_ARRAY, dashArray);
    }

    public static SVGDashArray makeInherit() {
        return new SVGDashArray(SVGDashArrayType.INHERIT, new SVGLength[]{});
    }
}
