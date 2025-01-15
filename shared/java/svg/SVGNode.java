package io.github.humbleui.skija.svg;

import io.github.humbleui.skija.impl.Library;
import io.github.humbleui.skija.impl.RefCnt;
import io.github.humbleui.skija.impl.ReferenceUtil;
import io.github.humbleui.skija.impl.Stats;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Note about the lack of explicit inherit in attribute API: Internally, Skia uses a wrapper type called SkSVGProperty
 * in its attribute getters and setters. SkSVGProperty contains an instance variable _fstate of type
 * SkSVGPropertyState, which can take the values _kValue, _kUnspecified, or _kInherit, corresponding to the property
 * having a value, not having a value, or explicitly specifying it should inherit its value. However, as far as I can
 * tell from the SVG spec, most attributes are inherited when they do not have a value. Furthermore, from my reading of
 * Skia's source code, there are no public methods for accessing an SkSVGProperty's state, nor does Skia's SVG render
 * procedure make any distinction between the unspecified and inherit state. It's render logic only checks if properties
 * are in the value state, otherwise it applies attribute specific inheritance logic. In order to hide this seemingly
 * unnecessary complexity, the current Java API does not provide a way to explicitly set attributes to inherit. To
 * determine whether an attribute will be inherited, please refer to the SVG spec:
 * https://www.w3.org/TR/SVG2/painting.html.
 */
public abstract class SVGNode extends RefCnt {
    static { Library.staticLoad(); }

    @ApiStatus.Internal
    public SVGNode(long ptr) {
        super(ptr);
    }

    @NotNull
    public SVGTag getTag() {
        try {
            Stats.onNativeCall();
            return SVGTag._values[_nGetTag(_ptr)];
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
    }

    public boolean parseAndSetAttribute(String name, String value) {
        try {
            Stats.onNativeCall();
            return _nParseAndSetAttribute(_ptr, name, value);
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
    }

    @Nullable
    public SVGFillRule getClipRule() {
        try {
            Stats.onNativeCall();
            if (_nHasClipRule(_ptr)) {
                Stats.onNativeCall();
                return SVGFillRule._values[_nGetClipRule(_ptr)];
            }
            else {
                return null;
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
    }

    @NotNull @Contract("_ -> this")
    public SVGNode setClipRule(@Nullable SVGFillRule type) {
        try {
            Stats.onNativeCall();
            if (type != null) {
                _nSetClipRule(_ptr, type.ordinal());
            } else {
                _nSetClipRuleNull(_ptr);
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
        return this;
    }

    @Nullable
    public Integer getColor() {
        try {
            Stats.onNativeCall();
            if (_nHasColor(_ptr)) {
                Stats.onNativeCall();
                return _nGetColor(_ptr);
            }
            else {
                return null;
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
    }

    @NotNull @Contract("_ -> this")
    public SVGNode setColor(@Nullable Integer color) {
        try {
            Stats.onNativeCall();
            if (color != null) {
                _nSetColor(_ptr, color);
            } else {
                _nSetColorNull(_ptr);
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
        return this;
    }

    @Nullable
    public SVGColorSpace getColorInterpolation() {
        try {
            Stats.onNativeCall();
            if (_nHasColorInterpolation(_ptr)) {
                Stats.onNativeCall();
                return SVGColorSpace._values[_nGetColorInterpolation(_ptr)];
            }  else {
                return null;
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
    }

    @NotNull @Contract("_ -> this")
    public SVGNode setColorInterpolation(@Nullable SVGColorSpace color) {
        try {
            Stats.onNativeCall();
            if (color != null) {
                _nSetColorInterpolation(_ptr, color.ordinal());
            } else {
                _nSetColorInterpolationNull(_ptr);
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
        return this;
    }

    @Nullable
    public SVGColorSpace getColorInterpolationFilters() {
        try {
            Stats.onNativeCall();
            if (_nHasColorInterpolationFilters(_ptr)) {
                Stats.onNativeCall();
                return SVGColorSpace._values[_nGetColorInterpolationFilters(_ptr)];
            }
            else {
                return null;
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
    }

    @NotNull @Contract("_ -> this")
    public SVGNode setColorInterpolationFilters(@Nullable SVGColorSpace color) {
        try {
            Stats.onNativeCall();
            if (color != null) {
                _nSetColorInterpolationFilters(_ptr, color.ordinal());
            } else {
                _nSetColorInterpolationFiltersNull(_ptr);
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
        return this;
    }

    @Nullable
    public SVGFillRule getFillRule() {
        try {
            Stats.onNativeCall();
            if (_nHasFillRule(_ptr)) {
                Stats.onNativeCall();
                return SVGFillRule._values[_nGetFillRule(_ptr)];
            }
            else {
                return null;
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
    }

    @NotNull @Contract("_ -> this")
    public SVGNode setFillRule(@Nullable SVGFillRule type) {
        try {
            Stats.onNativeCall();
            if (type != null) {
                _nSetFillRule(_ptr, type.ordinal());
            } else {
                _nSetFillRuleNull(_ptr);
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
        return this;
    }

    @Nullable
    public SVGPaint getFill() {
        try {
            Stats.onNativeCall();
            return _nGetFill(_ptr);
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
    }

    @NotNull @Contract("_ -> this")
    public SVGNode setFill(int color) {
        return setFill(new SVGPaint(color));
    }

    @NotNull @Contract("_ -> this")
    public SVGNode setFill(@Nullable SVGPaint paint) {
        try {
            Stats.onNativeCall();
            if (paint != null) {
                _nSetFill(_ptr,
                        paint.getType().ordinal(),
                        paint.getColor().getType().ordinal(),
                        paint.getColor().getColor(),
                        paint.getColor().getVars(),
                        paint.getIri().getType().ordinal(),
                        paint.getIri().getIri()
                );
            } else {
                _nSetFillNull(_ptr);
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
        return this;
    }

    @Nullable
    public Float getFillOpacity() {
        try {
            Stats.onNativeCall();
            if (_nHasFillOpacity(_ptr)) {
                Stats.onNativeCall();
                return _nGetFillOpacity(_ptr);
            }
            else {
                return null;
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
    }

    @NotNull @Contract("_ -> this")
    public SVGNode setFillOpacity(@Nullable Float opacity) {
        try {
            Stats.onNativeCall();
            if (opacity != null) {
                _nSetFillOpacity(_ptr, opacity);
            } else {
                _nSetFillOpacityNull(_ptr);
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
        return this;
    }

    @Nullable
    public SVGFontFamily getFontFamily() {
        try {
            Stats.onNativeCall();
            return _nGetFontFamily(_ptr);
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
    }

    @NotNull @Contract("_ -> this")
    public SVGNode setFontFamily(@Nullable SVGFontFamily family) {
        try {
            Stats.onNativeCall();
            if (family != null) {
                _nSetFontFamily(_ptr, family._type.ordinal(), family._family);
            } else {
                _nSetFontFamilyNull(_ptr);
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
        return this;
    }

    @Nullable
    public SVGFontSize getFontSize() {
        try {
            Stats.onNativeCall();
            return _nGetFontSize(_ptr);
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
    }

    @NotNull @Contract("_ -> this")
    public SVGNode setFontSize(@Nullable SVGFontSize family) {
        try {
            Stats.onNativeCall();
            if (family != null) {
                _nSetFontSize(_ptr, family._type.ordinal(), family._size._value, family._size._unit.ordinal());
            } else {
                _nSetFontSizeNull(_ptr);
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
        return this;
    }

    @Nullable
    public SVGFontStyle getFontStyle() {
        try {
            Stats.onNativeCall();
            if (_nHasFontStyle(_ptr)) {
                Stats.onNativeCall();
                return SVGFontStyle._values[_nGetFontStyle(_ptr)];
            }
            else {
                return null;
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
    }

    @NotNull @Contract("_ -> this")
    public SVGNode setFontStyle(@Nullable SVGFontStyle style) {
        try {
            Stats.onNativeCall();
            if (style != null) {
                _nSetFontStyle(_ptr, style.ordinal());
            } else {
                _nSetFontStyleNull(_ptr);
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
        return this;
    }

    @Nullable
    public SVGFontWeight getFontWeight() {
        try {
            Stats.onNativeCall();
            if (_nHasFontWeight(_ptr)) {
                Stats.onNativeCall();
                return SVGFontWeight._values[_nGetFontWeight(_ptr)];
            }
            else {
                return null;
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
    }

    @NotNull @Contract("_ -> this")
    public SVGNode setFontWeight(@Nullable SVGFontWeight style) {
        try {
            Stats.onNativeCall();
            if (style != null) {
                _nSetFontWeight(_ptr, style.ordinal());
            } else {
                _nSetFontWeightNull(_ptr);
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
        return this;
    }

    @Nullable
    public SVGPaint getStroke() {
        try {
            Stats.onNativeCall();
            return _nGetStroke(_ptr);
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
    }

    @NotNull @Contract("_ -> this")
    public SVGNode setStroke(int color) {
        return setStroke(new SVGPaint(color));
    }

    @NotNull @Contract("_ -> this")
    public SVGNode setStroke(@Nullable SVGPaint paint) {
        try {
            Stats.onNativeCall();
            if (paint != null) {
                _nSetStroke(_ptr,
                        paint.getType().ordinal(),
                        paint.getColor().getType().ordinal(),
                        paint.getColor().getColor(),
                        paint.getColor().getVars(),
                        paint.getIri().getType().ordinal(),
                        paint.getIri().getIri()
                );
            } else {
                _nSetStrokeNull(_ptr);
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
        return this;
    }

    @Nullable
    public SVGDashArray getStrokeDashArray() {
        try {
            Stats.onNativeCall();
            return _nGetStrokeDashArray(_ptr);
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
    }

    @NotNull @Contract("_ -> this")
    public SVGNode setStrokeDashArray(@Nullable SVGDashArray dash) {
        try {
            Stats.onNativeCall();
            if (dash != null) {
                _nSetStrokeDashArray(_ptr, dash._type.ordinal(), dash._dashArray);
            } else {
                _nSetStrokeDashArrayNull(_ptr);
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
        return this;
    }

    @Nullable
    public SVGLength getStrokeDashOffset() {
        try {
            Stats.onNativeCall();
            return _nGetStrokeDashOffset(_ptr);
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
    }

    @NotNull @Contract("_ -> this")
    public SVGNode setStrokeDashOffset(@Nullable SVGLength length) {
        try {
            Stats.onNativeCall();
            if (length != null) {
                _nSetStrokeDashOffset(_ptr, length._value, length._unit.ordinal());
            } else {
                _nSetStrokeDashOffsetNull(_ptr);
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
        return this;
    }

    @Nullable
    public SVGLineCap getStrokeLineCap() {
        try {
            Stats.onNativeCall();
            if (_nHasStrokeLineCap(_ptr)) {
                Stats.onNativeCall();
                return SVGLineCap._values[_nGetStrokeLineCap(_ptr)];
            }
            else {
                return null;
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
    }

    @NotNull @Contract("_ -> this")
    public SVGNode setStrokeLineCap(@Nullable SVGLineCap cap) {
        try {
            Stats.onNativeCall();
            if (cap != null) {
                _nSetStrokeLineCap(_ptr, cap.ordinal());
            } else {
                _nSetStrokeLineCapNull(_ptr);
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
        return this;
    }

    @Nullable
    public SVGLineJoin getStrokeLineJoin() {
        try {
            Stats.onNativeCall();
            if (_nHasStrokeLineJoin(_ptr)) {
                Stats.onNativeCall();
                return SVGLineJoin._values[_nGetStrokeLineJoin(_ptr)];
            }
            else {
                return null;
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
    }

    @NotNull @Contract("_ -> this")
    public SVGNode setStrokeLineJoin(@Nullable SVGLineJoin join) {
        try {
            Stats.onNativeCall();
            if (join != null) {
                _nSetStrokeLineJoin(_ptr, join.ordinal());
            } else {
                _nSetStrokeLineJoinNull(_ptr);
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
        return this;
    }

    @Nullable
    public Float getStrokeMiterLimit() {
        try {
            Stats.onNativeCall();
            if (_nHasStrokeMiterLimit(_ptr)) {
                Stats.onNativeCall();
                return _nGetStrokeMiterLimit(_ptr);
            }
            else {
                return null;
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
    }

    @NotNull @Contract("_ -> this")
    public SVGNode setStrokeMiterLimit(@Nullable Float opacity) {
        try {
            Stats.onNativeCall();
            if (opacity != null) {
                _nSetStrokeMiterLimit(_ptr, opacity);
            } else {
                _nSetStrokeMiterLimitNull(_ptr);
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
        return this;
    }

    @Nullable
    public Float getStrokeOpacity() {
        try {
            Stats.onNativeCall();
            if (_nHasStrokeOpacity(_ptr)) {
                Stats.onNativeCall();
                return _nGetStrokeOpacity(_ptr);
            }
            else {
                return null;
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
    }

    @NotNull @Contract("_ -> this")
    public SVGNode setStrokeOpacity(@Nullable Float opacity) {
        try {
            Stats.onNativeCall();
            if (opacity != null) {
                _nSetStrokeOpacity(_ptr, opacity);
            } else {
                _nSetStrokeOpacityNull(_ptr);
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
        return this;
    }

    @Nullable
    public SVGLength getStrokeWidth() {
        try {
            Stats.onNativeCall();
            return _nGetStrokeWidth(_ptr);
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
    }

    @NotNull @Contract("_ -> this")
    public SVGNode setStrokeWidth(@Nullable SVGLength length) {
        try {
            Stats.onNativeCall();
            if (length != null) {
                _nSetStrokeWidth(_ptr, length._value, length._unit.ordinal());
            } else {
                _nSetStrokeWidthNull(_ptr);
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
        return this;
    }

    @Nullable
    public SVGTextAnchor getTextAnchor() {
        try {
            Stats.onNativeCall();
            if (_nHasTextAnchor(_ptr)) {
                Stats.onNativeCall();
                return SVGTextAnchor._values[_nGetTextAnchor(_ptr)];
            }
            else {
                return null;
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
    }

    @NotNull @Contract("_ -> this")
    public SVGNode setTextAnchor(@Nullable SVGTextAnchor join) {
        try {
            Stats.onNativeCall();
            if (join != null) {
                _nSetTextAnchor(_ptr, join.ordinal());
            } else {
                _nSetTextAnchorNull(_ptr);
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
        return this;
    }

    @Nullable
    public SVGVisibility getVisibility() {
        try {
            Stats.onNativeCall();
            if (_nHasVisibility(_ptr)) {
                Stats.onNativeCall();
                return SVGVisibility._values[_nGetVisibility(_ptr)];
            }
            else {
                return null;
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
    }

    @NotNull @Contract("_ -> this")
    public SVGNode setVisibility(@Nullable SVGVisibility visibility) {
        try {
            Stats.onNativeCall();
            if (visibility != null) {
                _nSetVisibility(_ptr, visibility.ordinal());
            } else {
                _nSetVisibilityNull(_ptr);
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
        return this;
    }

    @Nullable
    public SVGFuncIRI getClipPath() {
        try {
            Stats.onNativeCall();
            return _nGetClipPath(_ptr);
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
    }

    @NotNull @Contract("_ -> this")
    public SVGNode setClipPath(@Nullable SVGFuncIRI func) {
        try {
            Stats.onNativeCall();
            if (func != null) {
                _nSetClipPath(_ptr, func._type.ordinal(), func._iri._type.ordinal(), func._iri._iri);
            } else {
                _nSetClipPathNull(_ptr);
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
        return this;
    }

    @Nullable
    public SVGDisplay getDisplay() {
        try {
            Stats.onNativeCall();
            if (_nHasDisplay(_ptr)) {
                Stats.onNativeCall();
                return SVGDisplay._values[_nGetDisplay(_ptr)];
            }
            else {
                return null;
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
    }

    @NotNull @Contract("_ -> this")
    public SVGNode setDisplay(@Nullable SVGDisplay join) {
        try {
            Stats.onNativeCall();
            if (join != null) {
                _nSetDisplay(_ptr, join.ordinal());
            } else {
                _nSetDisplayNull(_ptr);
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
        return this;
    }

    @Nullable
    public SVGFuncIRI getMask() {
        try {
            Stats.onNativeCall();
            return _nGetMask(_ptr);
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
    }

    @NotNull @Contract("_ -> this")
    public SVGNode setMask(@Nullable SVGFuncIRI func) {
        try {
            Stats.onNativeCall();
            if (func != null) {
                _nSetMask(_ptr, func._type.ordinal(), func._iri._type.ordinal(), func._iri._iri);
            } else {
                _nSetMaskNull(_ptr);
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
        return this;
    }

    @Nullable
    public SVGFuncIRI getFilter() {
        try {
            Stats.onNativeCall();
            return _nGetFilter(_ptr);
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
    }

    @NotNull @Contract("_ -> this")
    public SVGNode setFilter(@Nullable SVGFuncIRI func) {
        try {
            Stats.onNativeCall();
            if (func != null) {
                _nSetFilter(_ptr, func._type.ordinal(), func._iri._type.ordinal(), func._iri._iri);
            } else {
                _nSetFilterNull(_ptr);
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
        return this;
    }

    @Nullable
    public Float getOpacity() {
        try {
            Stats.onNativeCall();
            if (_nHasOpacity(_ptr)) {
                Stats.onNativeCall();
                return _nGetOpacity(_ptr);
            }
            else {
                return null;
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
    }

    @NotNull @Contract("_ -> this")
    public SVGNode setOpacity(@Nullable Float opacity) {
        try {
            Stats.onNativeCall();
            if (opacity != null) {
                _nSetOpacity(_ptr, opacity);
            } else {
                _nSetOpacityNull(_ptr);
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
        return this;
    }

    @Nullable
    public SVGColor getStopColor() {
        try {
            Stats.onNativeCall();
            return _nGetStopColor(_ptr);
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
    }

    @NotNull @Contract("_ -> this")
    public SVGNode setStopColor(int color) {
        return setStopColor(new SVGColor(color));
    }

    @NotNull @Contract("_ -> this")
    public SVGNode setStopColor(@Nullable SVGColor color) {
        try {
            Stats.onNativeCall();
            if (color != null) {
                _nSetStopColor(_ptr,
                        color.getType().ordinal(),
                        color.getColor(),
                        color.getVars()
                );
            } else {
                _nSetStopColorNull(_ptr);
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
        return this;
    }

    @Nullable
    public Float getStopOpacity() {
        try {
            Stats.onNativeCall();
            if (_nHasStopOpacity(_ptr)) {
                Stats.onNativeCall();
                return _nGetStopOpacity(_ptr);
            }
            else {
                return null;
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
    }

    @NotNull @Contract("_ -> this")
    public SVGNode setStopOpacity(@Nullable Float opacity) {
        try {
            Stats.onNativeCall();
            if (opacity != null) {
                _nSetStopOpacity(_ptr, opacity);
            } else {
                _nSetStopOpacityNull(_ptr);
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
        return this;
    }

    @Nullable
    public SVGColor getFloodColor() {
        try {
            Stats.onNativeCall();
            return _nGetFloodColor(_ptr);
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
    }

    @NotNull @Contract("_ -> this")
    public SVGNode setFloodColor(int color) {
        return setFloodColor(new SVGColor(color));
    }

    @NotNull @Contract("_ -> this")
    public SVGNode setFloodColor(@Nullable SVGColor color) {
        try {
            Stats.onNativeCall();
            if (color != null) {
                _nSetFloodColor(_ptr,
                        color.getType().ordinal(),
                        color.getColor(),
                        color.getVars()
                );
            } else {
                _nSetFloodColorNull(_ptr);
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
        return this;
    }

    @Nullable
    public Float getFloodOpacity() {
        try {
            Stats.onNativeCall();
            if (_nHasFloodOpacity(_ptr)) {
                Stats.onNativeCall();
                return _nGetFloodOpacity(_ptr);
            }
            else {
                return null;
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
    }

    @NotNull @Contract("_ -> this")
    public SVGNode setFloodOpacity(@Nullable Float opacity) {
        try {
            Stats.onNativeCall();
            if (opacity != null) {
                _nSetFloodOpacity(_ptr, opacity);
            } else {
                _nSetFloodOpacityNull(_ptr);
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
        return this;
    }

    @Nullable
    public SVGColor getLightingColor() {
        try {
            Stats.onNativeCall();
            return _nGetLightingColor(_ptr);
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
    }

    @NotNull @Contract("_ -> this")
    public SVGNode setLightingColor(int color) {
        return setLightingColor(new SVGColor(color));
    }

    @NotNull @Contract("_ -> this")
    public SVGNode setLightingColor(@Nullable SVGColor color) {
        try {
            Stats.onNativeCall();
            if (color != null) {
                _nSetLightingColor(_ptr,
                        color.getType().ordinal(),
                        color.getColor(),
                        color.getVars()
                );
            } else {
                _nSetLightingColorNull(_ptr);
            }
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
        return this;
    }

    @ApiStatus.Internal public static native int _nGetTag(long ptr);
    @ApiStatus.Internal public static native boolean _nParseAndSetAttribute(long ptr, String name, String value);

    @ApiStatus.Internal public static native boolean _nHasClipRule(long ptr);
    @ApiStatus.Internal public static native int _nGetClipRule(long ptr);
    @ApiStatus.Internal public static native void _nSetClipRule(long ptr, int type);
    @ApiStatus.Internal public static native void _nSetClipRuleNull(long ptr);

    @ApiStatus.Internal public static native boolean _nHasColor(long ptr);
    @ApiStatus.Internal public static native int _nGetColor(long ptr);
    @ApiStatus.Internal public static native void _nSetColor(long ptr, int color);
    @ApiStatus.Internal public static native void _nSetColorNull(long ptr);

    @ApiStatus.Internal public static native boolean _nHasColorInterpolation(long ptr);
    @ApiStatus.Internal public static native int _nGetColorInterpolation(long ptr);
    @ApiStatus.Internal public static native void _nSetColorInterpolation(long ptr, int color);
    @ApiStatus.Internal public static native void _nSetColorInterpolationNull(long ptr);

    @ApiStatus.Internal public static native boolean _nHasColorInterpolationFilters(long ptr);
    @ApiStatus.Internal public static native int _nGetColorInterpolationFilters(long ptr);
    @ApiStatus.Internal public static native void _nSetColorInterpolationFilters(long ptr, int color);
    @ApiStatus.Internal public static native void _nSetColorInterpolationFiltersNull(long ptr);

    @ApiStatus.Internal public static native boolean _nHasFillRule(long ptr);
    @ApiStatus.Internal public static native int _nGetFillRule(long ptr);
    @ApiStatus.Internal public static native void _nSetFillRule(long ptr, int type);
    @ApiStatus.Internal public static native void _nSetFillRuleNull(long ptr);

    @ApiStatus.Internal public static native SVGPaint _nGetFill(long ptr);
    @ApiStatus.Internal public static native void _nSetFill(long ptr, int type, int colorType, int color, String[] vars, int iriType, String iri);
    @ApiStatus.Internal public static native void _nSetFillNull(long ptr);

    @ApiStatus.Internal public static native boolean _nHasFillOpacity(long ptr);
    @ApiStatus.Internal public static native float _nGetFillOpacity(long ptr);
    @ApiStatus.Internal public static native void _nSetFillOpacity(long ptr, float opacity);
    @ApiStatus.Internal public static native void _nSetFillOpacityNull(long ptr);

    @ApiStatus.Internal public static native SVGFontFamily _nGetFontFamily(long ptr);
    @ApiStatus.Internal public static native void _nSetFontFamily(long ptr, int type, String family);
    @ApiStatus.Internal public static native void _nSetFontFamilyNull(long ptr);

    @ApiStatus.Internal public static native SVGFontSize _nGetFontSize(long ptr);
    @ApiStatus.Internal public static native void _nSetFontSize(long ptr, int type, float value, int unit);
    @ApiStatus.Internal public static native void _nSetFontSizeNull(long ptr);

    @ApiStatus.Internal public static native boolean _nHasFontStyle(long ptr);
    @ApiStatus.Internal public static native int _nGetFontStyle(long ptr);
    @ApiStatus.Internal public static native void _nSetFontStyle(long ptr, int type);
    @ApiStatus.Internal public static native void _nSetFontStyleNull(long ptr);

    @ApiStatus.Internal public static native boolean _nHasFontWeight(long ptr);
    @ApiStatus.Internal public static native int _nGetFontWeight(long ptr);
    @ApiStatus.Internal public static native void _nSetFontWeight(long ptr, int type);
    @ApiStatus.Internal public static native void _nSetFontWeightNull(long ptr);

    @ApiStatus.Internal public static native SVGPaint _nGetStroke(long ptr);
    @ApiStatus.Internal public static native void _nSetStroke(long ptr, int type, int colorType, int color, String[] vars, int iriType, String iri);
    @ApiStatus.Internal public static native void _nSetStrokeNull(long ptr);

    @ApiStatus.Internal public static native SVGDashArray _nGetStrokeDashArray(long ptr);
    @ApiStatus.Internal public static native void _nSetStrokeDashArray(long ptr, int type, SVGLength[] dashArray);
    @ApiStatus.Internal public static native void _nSetStrokeDashArrayNull(long ptr);

    @ApiStatus.Internal public static native SVGLength _nGetStrokeDashOffset(long ptr);
    @ApiStatus.Internal public static native void _nSetStrokeDashOffset(long ptr, float value, int unit);
    @ApiStatus.Internal public static native void _nSetStrokeDashOffsetNull(long ptr);

    @ApiStatus.Internal public static native boolean _nHasStrokeLineCap(long ptr);
    @ApiStatus.Internal public static native int _nGetStrokeLineCap(long ptr);
    @ApiStatus.Internal public static native void _nSetStrokeLineCap(long ptr, int type);
    @ApiStatus.Internal public static native void _nSetStrokeLineCapNull(long ptr);

    @ApiStatus.Internal public static native boolean _nHasStrokeLineJoin(long ptr);
    @ApiStatus.Internal public static native int _nGetStrokeLineJoin(long ptr);
    @ApiStatus.Internal public static native void _nSetStrokeLineJoin(long ptr, int type);
    @ApiStatus.Internal public static native void _nSetStrokeLineJoinNull(long ptr);

    @ApiStatus.Internal public static native boolean _nHasStrokeMiterLimit(long ptr);
    @ApiStatus.Internal public static native float _nGetStrokeMiterLimit(long ptr);
    @ApiStatus.Internal public static native void _nSetStrokeMiterLimit(long ptr, float opacity);
    @ApiStatus.Internal public static native void _nSetStrokeMiterLimitNull(long ptr);

    @ApiStatus.Internal public static native boolean _nHasStrokeOpacity(long ptr);
    @ApiStatus.Internal public static native float _nGetStrokeOpacity(long ptr);
    @ApiStatus.Internal public static native void _nSetStrokeOpacity(long ptr, float opacity);
    @ApiStatus.Internal public static native void _nSetStrokeOpacityNull(long ptr);

    @ApiStatus.Internal public static native SVGLength _nGetStrokeWidth(long ptr);
    @ApiStatus.Internal public static native void _nSetStrokeWidth(long ptr, float value, int unit);
    @ApiStatus.Internal public static native void _nSetStrokeWidthNull(long ptr);

    @ApiStatus.Internal public static native boolean _nHasTextAnchor(long ptr);
    @ApiStatus.Internal public static native int _nGetTextAnchor(long ptr);
    @ApiStatus.Internal public static native void _nSetTextAnchor(long ptr, int type);
    @ApiStatus.Internal public static native void _nSetTextAnchorNull(long ptr);

    @ApiStatus.Internal public static native boolean _nHasVisibility(long ptr);
    @ApiStatus.Internal public static native int _nGetVisibility(long ptr);
    @ApiStatus.Internal public static native void _nSetVisibility(long ptr, int type);
    @ApiStatus.Internal public static native void _nSetVisibilityNull(long ptr);

    @ApiStatus.Internal public static native SVGFuncIRI _nGetClipPath(long ptr);
    @ApiStatus.Internal public static native void _nSetClipPath(long ptr, int funcType, int iriType, String iri);
    @ApiStatus.Internal public static native void _nSetClipPathNull(long ptr);

    @ApiStatus.Internal public static native boolean _nHasDisplay(long ptr);
    @ApiStatus.Internal public static native int _nGetDisplay(long ptr);
    @ApiStatus.Internal public static native void _nSetDisplay(long ptr, int type);
    @ApiStatus.Internal public static native void _nSetDisplayNull(long ptr);

    @ApiStatus.Internal public static native SVGFuncIRI _nGetMask(long ptr);
    @ApiStatus.Internal public static native void _nSetMask(long ptr, int funcType, int iriType, String iri);
    @ApiStatus.Internal public static native void _nSetMaskNull(long ptr);

    @ApiStatus.Internal public static native SVGFuncIRI _nGetFilter(long ptr);
    @ApiStatus.Internal public static native void _nSetFilter(long ptr, int funcType, int iriType, String iri);
    @ApiStatus.Internal public static native void _nSetFilterNull(long ptr);

    @ApiStatus.Internal public static native boolean _nHasOpacity(long ptr);
    @ApiStatus.Internal public static native float _nGetOpacity(long ptr);
    @ApiStatus.Internal public static native void _nSetOpacity(long ptr, float opacity);
    @ApiStatus.Internal public static native void _nSetOpacityNull(long ptr);

    @ApiStatus.Internal public static native SVGColor _nGetStopColor(long ptr);
    @ApiStatus.Internal public static native void _nSetStopColor(long ptr, int type, int color, String[] vars);
    @ApiStatus.Internal public static native void _nSetStopColorNull(long ptr);

    @ApiStatus.Internal public static native boolean _nHasStopOpacity(long ptr);
    @ApiStatus.Internal public static native float _nGetStopOpacity(long ptr);
    @ApiStatus.Internal public static native void _nSetStopOpacity(long ptr, float opacity);
    @ApiStatus.Internal public static native void _nSetStopOpacityNull(long ptr);

    @ApiStatus.Internal public static native SVGColor _nGetFloodColor(long ptr);
    @ApiStatus.Internal public static native void _nSetFloodColor(long ptr, int type, int color, String[] vars);
    @ApiStatus.Internal public static native void _nSetFloodColorNull(long ptr);

    @ApiStatus.Internal public static native boolean _nHasFloodOpacity(long ptr);
    @ApiStatus.Internal public static native float _nGetFloodOpacity(long ptr);
    @ApiStatus.Internal public static native void _nSetFloodOpacity(long ptr, float opacity);
    @ApiStatus.Internal public static native void _nSetFloodOpacityNull(long ptr);

    @ApiStatus.Internal public static native SVGColor _nGetLightingColor(long ptr);
    @ApiStatus.Internal public static native void _nSetLightingColor(long ptr, int type, int color, String[] vars);
    @ApiStatus.Internal public static native void _nSetLightingColorNull(long ptr);
}