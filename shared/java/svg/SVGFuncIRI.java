package io.github.humbleui.skija.svg;

import lombok.Data;
import org.jetbrains.annotations.ApiStatus;

@Data
public class SVGFuncIRI {
    @ApiStatus.Internal public final SVGFuncIRIType _type;
    @ApiStatus.Internal public final SVGIRI _iri;

    @ApiStatus.Internal
    public SVGFuncIRI(SVGFuncIRIType type, SVGIRI iri) {
        _type = type;
        _iri = iri;
    }

    public SVGFuncIRI() {
        this(SVGFuncIRIType.NONE, new SVGIRI());
    }

    public SVGFuncIRI(SVGIRI iri) {
        this(SVGFuncIRIType.IRI, iri);
    }
}
