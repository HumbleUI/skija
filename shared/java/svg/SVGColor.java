package io.github.humbleui.skija.svg;

import lombok.Data;
import org.jetbrains.annotations.ApiStatus;

@Data
public class SVGColor {
    @ApiStatus.Internal public final SVGColorType _type;
    @ApiStatus.Internal public final int _color;
    @ApiStatus.Internal public final String[] _vars;

    @ApiStatus.Internal
    public SVGColor(SVGColorType type, int color, String[] vars) {
        _type = type;
        _color = color;
        _vars = vars;
    }

    @ApiStatus.Internal
    public SVGColor(int type, int color, String[] vars) {
        this(SVGColorType._values[type], color, vars);
    }

    public SVGColor() {
        this(SVGColorType.CURRENT_COLOR, 0x000000, new String[]{});
    }

    public SVGColor(int color) {
        this(SVGColorType.COLOR, color, new String[]{});
    }

    public SVGColor(SVGColorType type, String[] vars) {
        this(type, 0x000000, vars);
    }

    public SVGColor(int color, String[] vars) {
        this(SVGColorType.COLOR, color, vars);
    }
}
