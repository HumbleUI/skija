package io.github.humbleui.skija.test.svg;

import io.github.humbleui.skija.svg.*;
import io.github.humbleui.skija.test.runner.Executable;

import static io.github.humbleui.skija.test.runner.TestRunner.assertEquals;
import static io.github.humbleui.skija.test.runner.TestRunner.runner;

public class SVGNodePropsTest implements Executable {
    @Override
    public void execute() throws Exception {
        SVGNode node = new SVGSVG(SVGSVGType.ROOT);

        // Clip Rule

        node.setClipRule(SVGFillRule.EVEN_ODD);
        assertEquals(SVGFillRule.EVEN_ODD, node.getClipRule());

        node.setClipRule(null);
        if (node.getClipRule() != null) {
            runner.fail("clip rule should not have value");
        }

        // Color

        node.setColor(0xFFFFFF);
        assertEquals(0xFFFFFF, node.getColor());

        node.setColor(null);
        if (node.getColor() != null) {
            runner.fail("color should not have value");
        }

        // Color Interpolation

        node.setColorInterpolation(SVGColorSpace.SRGB);
        assertEquals(SVGColorSpace.SRGB, node.getColorInterpolation());

        node.setColorInterpolation(null);
        if (node.getColorInterpolation() != null) {
            runner.fail("color interpolation should not have value");
        }

        // Color Interpolation Filters

        node.setColorInterpolationFilters(SVGColorSpace.SRGB);
        assertEquals(SVGColorSpace.SRGB, node.getColorInterpolationFilters());

        node.setColorInterpolationFilters(null);
        if (node.getColorInterpolationFilters() != null) {
            runner.fail("color interpolation filters should not have value");
        }

        // Fill Rule

        node.setFillRule(SVGFillRule.EVEN_ODD);
        assertEquals(SVGFillRule.EVEN_ODD, node.getFillRule());

        node.setFillRule(null);
        if (node.getFillRule() != null) {
            runner.fail("fill rule should not have value");
        }

        // Fill

        node.setFill(new SVGPaint(0xFFFFFF));
        assertEquals(new SVGPaint(0xFFFFFF), node.getFill());
        node.setFill(new SVGPaint(new SVGColor()));
        assertEquals(new SVGPaint(new SVGColor()), node.getFill());
        node.setFill(new SVGPaint(new SVGColor(0xFFFFFF, new String[]{"test"})));
        assertEquals(new SVGPaint(new SVGColor(0xFFFFFF, new String[]{"test"})), node.getFill());
        node.setFill(new SVGPaint(new SVGIRI(), 0xFFFFFF));
        assertEquals(new SVGPaint(new SVGIRI(), 0xFFFFFF), node.getFill());
        node.setFill(new SVGPaint(new SVGIRI(SVGIRIType.DATA_URI, "test"), 0xFFFFFF));
        assertEquals(new SVGPaint(new SVGIRI(SVGIRIType.DATA_URI, "test"), 0xFFFFFF), node.getFill());

        node.setFill(null);
        if (node.getFill() != null) {
            runner.fail("fill should not have value");
        }

        // Fill Opacity

        node.setFillOpacity(1f);
        assertEquals(1f, node.getFillOpacity());

        node.setFillOpacity(null);
        if (node.getFillOpacity() != null) {
            runner.fail("fill opacity should not have value");
        }

        // Font Family

        node.setFontFamily(new SVGFontFamily("test"));
        assertEquals(new SVGFontFamily("test"), node.getFontFamily());
        node.setFontFamily(new SVGFontFamily());
        assertEquals(new SVGFontFamily(), node.getFontFamily());

        node.setFontFamily(null);
        if (node.getFontFamily() != null) {
            runner.fail("font family should not have value");
        }

        // Font Size

        node.setFontSize(new SVGFontSize(new SVGLength(1f)));
        assertEquals(new SVGFontSize(new SVGLength(1f)), node.getFontSize());
        node.setFontSize(new SVGFontSize());
        assertEquals(new SVGFontSize(), node.getFontSize());

        node.setFontSize(null);
        if (node.getFontSize() != null) {
            runner.fail("font size should not have value");
        }

        // Font Style

        node.setFontStyle(SVGFontStyle.ITALIC);
        assertEquals(SVGFontStyle.ITALIC, node.getFontStyle());

        node.setFontStyle(null);
        if (node.getFontStyle() != null) {
            runner.fail("fill style should not have value");
        }

        // Font Weight

        node.setFontWeight(SVGFontWeight.WEIGHT_100);
        assertEquals(SVGFontWeight.WEIGHT_100, node.getFontWeight());

        node.setFontWeight(null);
        if (node.getFontWeight() != null) {
            runner.fail("fill weight should not have value");
        }

        // Stroke

        node.setStroke(new SVGPaint(0xFFFFFF));
        assertEquals(new SVGPaint(0xFFFFFF), node.getStroke());
        node.setStroke(new SVGPaint(new SVGColor()));
        assertEquals(new SVGPaint(new SVGColor()), node.getStroke());
        node.setStroke(new SVGPaint(new SVGColor(0xFFFFFF, new String[]{"test"})));
        assertEquals(new SVGPaint(new SVGColor(0xFFFFFF, new String[]{"test"})), node.getStroke());
        node.setStroke(new SVGPaint(new SVGIRI(), 0xFFFFFF));
        assertEquals(new SVGPaint(new SVGIRI(), 0xFFFFFF), node.getStroke());
        node.setStroke(new SVGPaint(new SVGIRI(SVGIRIType.DATA_URI, "test"), 0xFFFFFF));
        assertEquals(new SVGPaint(new SVGIRI(SVGIRIType.DATA_URI, "test"), 0xFFFFFF), node.getStroke());

        node.setStroke(null);
        if (node.getStroke() != null) {
            runner.fail("stroke should not have value");
        }

        // Stroke Dash Array

        node.setStrokeDashArray(new SVGDashArray());
        assertEquals(new SVGDashArray(), node.getStrokeDashArray());
        node.setStrokeDashArray(new SVGDashArray(new SVGLength[]{new SVGLength(1f), new SVGLength(2f)}));
        assertEquals(new SVGDashArray(new SVGLength[]{new SVGLength(1f), new SVGLength(2f)}), node.getStrokeDashArray());
        node.setStrokeDashArray(SVGDashArray.makeInherit());
        assertEquals(SVGDashArray.makeInherit(), node.getStrokeDashArray());

        node.setStrokeDashArray(null);
        if (node.getStrokeDashArray() != null) {
            runner.fail("stroke dash array should not have value");
        }

        // Stroke Dash Offset

        node.setStrokeDashOffset(new SVGLength(1f));
        assertEquals(new SVGLength(1f), node.getStrokeDashOffset());
        node.setStrokeDashOffset(new SVGLength(1f, SVGLengthUnit.IN));
        assertEquals(new SVGLength(1f, SVGLengthUnit.IN), node.getStrokeDashOffset());

        node.setStrokeDashOffset(null);
        if (node.getStrokeDashOffset() != null) {
            runner.fail("stroke dash offset should not have value");
        }

        // Stroke Line Cap

        node.setStrokeLineCap(SVGLineCap.SQUARE);
        assertEquals(SVGLineCap.SQUARE, node.getStrokeLineCap());

        node.setStrokeLineCap(null);
        if (node.getStrokeLineCap() != null) {
            runner.fail("stroke line cap should not have value");
        }

        // Stroke Line Join

        node.setStrokeLineJoin(SVGLineJoin.ROUND);
        assertEquals(SVGLineJoin.ROUND, node.getStrokeLineJoin());

        node.setStrokeLineJoin(null);
        if (node.getStrokeLineJoin() != null) {
            runner.fail("stroke line join should not have value");
        }

        // Stroke Miter Limit

        node.setStrokeMiterLimit(1f);
        assertEquals(1f, node.getStrokeMiterLimit());

        node.setStrokeMiterLimit(null);
        if (node.getStrokeMiterLimit() != null) {
            runner.fail("stroke miter limit should not have value");
        }

        // Stroke Opacity

        node.setStrokeOpacity(0.5f);
        assertEquals(0.5f, node.getStrokeOpacity());

        node.setStrokeOpacity(null);
        if (node.getStrokeOpacity() != null) {
            runner.fail("stroke opacity should not have value");
        }

        // Stroke Width

        node.setStrokeWidth(new SVGLength(1f));
        assertEquals(new SVGLength(1f), node.getStrokeWidth());
        node.setStrokeWidth(new SVGLength(1f, SVGLengthUnit.IN));
        assertEquals(new SVGLength(1f, SVGLengthUnit.IN), node.getStrokeWidth());

        node.setStrokeWidth(null);
        if (node.getStrokeWidth() != null) {
            runner.fail("stroke width should not have value");
        }

        // Text Anchor

        node.setTextAnchor(SVGTextAnchor.MIDDLE);
        assertEquals(SVGTextAnchor.MIDDLE, node.getTextAnchor());

        node.setTextAnchor(null);
        if (node.getTextAnchor() != null) {
            runner.fail("text anchor should not have value");
        }

        // Visibility

        node.setVisibility(SVGVisibility.COLLAPSE);
        assertEquals(SVGVisibility.COLLAPSE, node.getVisibility());

        node.setVisibility(null);
        if (node.getVisibility() != null) {
            runner.fail("visibility should not have value");
        }

        // Clip Path

        node.setClipPath(new SVGFuncIRI());
        assertEquals(new SVGFuncIRI(), node.getClipPath());
        node.setClipPath(new SVGFuncIRI(new SVGIRI()));
        assertEquals(new SVGFuncIRI(new SVGIRI()), node.getClipPath());
        node.setClipPath(new SVGFuncIRI(new SVGIRI(SVGIRIType.NON_LOCAL, "test")));
        assertEquals(new SVGFuncIRI(new SVGIRI(SVGIRIType.NON_LOCAL, "test")), node.getClipPath());

        node.setClipPath(null);
        if (node.getClipPath() != null) {
            runner.fail("clip path should not have value");
        }

        // Display

        node.setDisplay(SVGDisplay.NONE);
        assertEquals(SVGDisplay.NONE, node.getDisplay());

        node.setDisplay(null);
        if (node.getDisplay() != null) {
            runner.fail("display should not have value");
        }

        // Mask

        node.setMask(new SVGFuncIRI());
        assertEquals(new SVGFuncIRI(), node.getMask());
        node.setMask(new SVGFuncIRI(new SVGIRI()));
        assertEquals(new SVGFuncIRI(new SVGIRI()), node.getMask());
        node.setMask(new SVGFuncIRI(new SVGIRI(SVGIRIType.NON_LOCAL, "test")));
        assertEquals(new SVGFuncIRI(new SVGIRI(SVGIRIType.NON_LOCAL, "test")), node.getMask());

        node.setMask(null);
        if (node.getMask() != null) {
            runner.fail("mask should not have value");
        }

        // Filter

        node.setFilter(new SVGFuncIRI());
        assertEquals(new SVGFuncIRI(), node.getFilter());
        node.setFilter(new SVGFuncIRI(new SVGIRI()));
        assertEquals(new SVGFuncIRI(new SVGIRI()), node.getFilter());
        node.setFilter(new SVGFuncIRI(new SVGIRI(SVGIRIType.NON_LOCAL, "test")));
        assertEquals(new SVGFuncIRI(new SVGIRI(SVGIRIType.NON_LOCAL, "test")), node.getFilter());

        node.setFilter(null);
        if (node.getFilter() != null) {
            runner.fail("filter should not have value");
        }

        // Opacity

        node.setOpacity(0.5f);
        assertEquals(0.5f, node.getOpacity());

        node.setOpacity(null);
        if (node.getOpacity() != null) {
            runner.fail("opacity should not have value");
        }

        // Stop Color

        node.setStopColor(new SVGColor(0xFFFFFF));
        assertEquals(new SVGColor(0xFFFFFF), node.getStopColor());
        node.setStopColor(new SVGColor());
        assertEquals(new SVGColor(), node.getStopColor());
        node.setStopColor(new SVGColor(0xFFFFFF, new String[]{"test"}));
        assertEquals(new SVGColor(0xFFFFFF, new String[]{"test"}), node.getStopColor());

        node.setStopColor(null);
        if (node.getStopColor() != null) {
            runner.fail("stop color should not have value");
        }

        // Stop Opacity

        node.setStopOpacity(0.5f);
        assertEquals(0.5f, node.getStopOpacity());

        node.setStopOpacity(null);
        if (node.getStopOpacity() != null) {
            runner.fail("stop opacity should not have value");
        }

        // Flood Color

        node.setFloodColor(new SVGColor(0xFFFFFF));
        assertEquals(new SVGColor(0xFFFFFF), node.getFloodColor());
        node.setFloodColor(new SVGColor());
        assertEquals(new SVGColor(), node.getFloodColor());
        node.setFloodColor(new SVGColor(0xFFFFFF, new String[]{"test"}));
        assertEquals(new SVGColor(0xFFFFFF, new String[]{"test"}), node.getFloodColor());

        node.setFloodColor(null);
        if (node.getFloodColor() != null) {
            runner.fail("flood color should not have value");
        }

        // Flood Opacity

        node.setFloodOpacity(0.5f);
        assertEquals(0.5f, node.getFloodOpacity());

        node.setFloodOpacity(null);
        if (node.getFloodOpacity() != null) {
            runner.fail("flood opacity should not have value");
        }

        // Lighting Color

        node.setLightingColor(new SVGColor(0xFFFFFF));
        assertEquals(new SVGColor(0xFFFFFF), node.getLightingColor());
        node.setLightingColor(new SVGColor());
        assertEquals(new SVGColor(), node.getLightingColor());
        node.setLightingColor(new SVGColor(0xFFFFFF, new String[]{"test"}));
        assertEquals(new SVGColor(0xFFFFFF, new String[]{"test"}), node.getLightingColor());

        node.setLightingColor(null);
        if (node.getLightingColor() != null) {
            runner.fail("lighting color should not have value");
        }
    }
}
