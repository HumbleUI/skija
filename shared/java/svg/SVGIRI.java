package io.github.humbleui.skija.svg;

import lombok.Data;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

@Data
public class SVGIRI {
    @ApiStatus.Internal public final SVGIRIType _type;
    @ApiStatus.Internal @Nullable public final String _iri;

    public SVGIRI(SVGIRIType type, @Nullable String iri) {
        _type = type;
        _iri = iri;
    }

    @ApiStatus.Internal
    public SVGIRI(int type, @Nullable String iri) {
        this(SVGIRIType._values[type], iri);
    }

    public SVGIRI() {
        this(SVGIRIType.LOCAL, null);
    }
}
