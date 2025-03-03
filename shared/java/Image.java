package io.github.humbleui.skija;

import java.nio.*;
import org.jetbrains.annotations.*;
import io.github.humbleui.skija.impl.*;

public class Image extends RefCnt implements IHasImageInfo {
    static { Library.staticLoad(); }

    @ApiStatus.Internal
    public ImageInfo _imageInfo = null;

    @ApiStatus.Internal
    public Image(long ptr) {
        super(ptr);
    }

    /**
     * @deprecated - use {@link #makeRasterFromBytes(ImageInfo, byte[], long)}
     */
    @Deprecated
    public static Image makeRaster(ImageInfo imageInfo, byte[] bytes, long rowBytes) {
        return makeRasterFromBytes(imageInfo, bytes, rowBytes);
    }

    /**
     * <p>Creates Image from an OpenGL texture.</p>
     *
     * <p>Image is returned if the texture is valid. Valid texture parameters include:</p>
     * <ul>
     * <li>textureId is a valid OpenGL texture ID;</li>
     * <li>width and height are greater than zero;</li>
     * <li>ColorType and AlphaType are valid, and ColorType is not ColorType.UNKNOWN;</li>
     * <li>colorSpace is a valid SkColorSpace;</li>
     * </ul>
     *
     * @param context     GrDirectContext
     * @param textureId   OpenGL texture ID
     * @param width       width of the texture
     * @param height      height of the texture
     * @param colorType   color type of the texture
     * @return            Image
     */
    public static Image adoptGLTextureFrom(DirectContext context, int textureId, int target, int width, int height, int format, SurfaceOrigin surfaceOrigin, ColorType colorType) {
        try {
            Stats.onNativeCall();
            long ptr = _nAdoptGLTextureFrom(Native.getPtr(context),
                                            textureId,
                                            target,
                                            width,
                                            height,
                                            format,
                                            surfaceOrigin.ordinal(),
                                            colorType.ordinal());
            if (ptr == 0)
                throw new RuntimeException("Failed to adoptGLTextureFrom " + textureId + " " + width + "x" + height);
            return new Image(ptr);
        } finally {
            ReferenceUtil.reachabilityFence(context);
        }
    }
        
    /**
     * <p>Creates Image from pixels.</p>
     *
     * <p>Image is returned if pixels are valid. Valid Pixmap parameters include:</p>
     * <ul>
     * <li>dimensions are greater than zero;</li>
     * <li>each dimension fits in 29 bits;</li>
     * <li>ColorType and AlphaType are valid, and ColorType is not ColorType.UNKNOWN;</li>
     * <li>row bytes are large enough to hold one row of pixels;</li>
     * <li>pixel address is not null.</li>
     * </ul>
     *
     * @param imageInfo  ImageInfo
     * @param bytes      pixels array
     * @param rowBytes   how many bytes in a row
     * @return           Image
     *
     * @see <a href="https://fiddle.skia.org/c/@Image_MakeRasterCopy">https://fiddle.skia.org/c/@Image_MakeRasterCopy</a>
     */
    public static Image makeRasterFromBytes(ImageInfo imageInfo, byte[] bytes, long rowBytes) {
        try {
            Stats.onNativeCall();
            long ptr = _nMakeRasterFromBytes(imageInfo._width,
                                             imageInfo._height,
                                             imageInfo._colorInfo._colorType.ordinal(),
                                             imageInfo._colorInfo._alphaType.ordinal(),
                                             Native.getPtr(imageInfo._colorInfo._colorSpace),
                                             bytes,
                                             rowBytes);
            if (ptr == 0)
                throw new RuntimeException("Failed to makeRaster " + imageInfo + " " + bytes + " " + rowBytes);
            return new Image(ptr);
        } finally {
            ReferenceUtil.reachabilityFence(imageInfo._colorInfo._colorSpace);
        }
    }

    /**
     * @deprecated - use {@link #makeRasterFromData(ImageInfo, Data, long)}
     */
    @Deprecated
    public static Image makeRaster(ImageInfo imageInfo, Data data, long rowBytes) {
        return makeRasterFromData(imageInfo, data, rowBytes);
    }

    /**
     * <p>Creates Image from pixels.</p>
     *
     * <p>Image is returned if pixels are valid. Valid Pixmap parameters include:</p>
     * <ul>
     * <li>dimensions are greater than zero;</li>
     * <li>each dimension fits in 29 bits;</li>
     * <li>ColorType and AlphaType are valid, and ColorType is not ColorType.UNKNOWN;</li>
     * <li>row bytes are large enough to hold one row of pixels;</li>
     * <li>pixel address is not null.</li>
     * </ul>
     *
     * @param imageInfo  ImageInfo
     * @param data       pixels array
     * @param rowBytes   how many bytes in a row
     * @return           Image
     */
    public static Image makeRasterFromData(ImageInfo imageInfo, Data data, long rowBytes) {
        try {
            Stats.onNativeCall();
            long ptr = _nMakeRasterFromData(imageInfo._width,
                                            imageInfo._height,
                                            imageInfo._colorInfo._colorType.ordinal(),
                                            imageInfo._colorInfo._alphaType.ordinal(),
                                            Native.getPtr(imageInfo._colorInfo._colorSpace),
                                            Native.getPtr(data),
                                            rowBytes);
            if (ptr == 0)
                throw new RuntimeException("Failed to makeRaster " + imageInfo + " " + data + " " + rowBytes);
            return new Image(ptr);
        } finally {
            ReferenceUtil.reachabilityFence(imageInfo._colorInfo._colorSpace);
            ReferenceUtil.reachabilityFence(data);
        }
    }

    /**
     * @deprecated - use {@link #makeRasterFromBitmap(Bitmap)}
     */
    @Deprecated
    public static Image makeFromBitmap(@NotNull Bitmap bitmap) {
        return makeRasterFromBitmap(bitmap);
    }

    /**
     * <p>Creates Image from bitmap, sharing or copying bitmap pixels. If the bitmap
     * is marked immutable, and its pixel memory is shareable, it may be shared
     * instead of copied.</p>
     *
     * <p>Image is returned if bitmap is valid. Valid Bitmap parameters include:</p>
     * <ul>
     * <li>dimensions are greater than zero;</li>
     * <li>each dimension fits in 29 bits;</li>
     * <li>ColorType and AlphaType are valid, and ColorType is not ColorType.UNKNOWN;</li>
     * <li>row bytes are large enough to hold one row of pixels;</li>
     * <li>pixel address is not nullptr.</li>
     * </ul>
     *
     * @param bitmap  ImageInfo, row bytes, and pixels
     * @return        created Image
     *
     * @see <a href="https://fiddle.skia.org/c/@Image_MakeFromBitmap">https://fiddle.skia.org/c/@Image_MakeFromBitmap</a>
     */
    @NotNull @Contract("_ -> new")
    public static Image makeRasterFromBitmap(@NotNull Bitmap bitmap) {
        try {
            assert bitmap != null : "Can’t makeFromBitmap with bitmap == null";
            Stats.onNativeCall();
            long ptr = _nMakeRasterFromBitmap(Native.getPtr(bitmap));
            if (ptr == 0)
                throw new RuntimeException("Failed to Image::makeFromBitmap " + bitmap);
            return new Image(ptr);
        } finally {
            ReferenceUtil.reachabilityFence(bitmap);
        }
    }

    /**
     * @deprecated - use {@link #makeRasterFromPixmap(Pixmap)}
     */
    @Deprecated
    public static Image makeFromPixmap(@NotNull Pixmap pixmap) {
        return makeRasterFromPixmap(pixmap);
    }

    @NotNull @Contract("_ -> new")
    public static Image makeRasterFromPixmap(@NotNull Pixmap pixmap) {
        try {
            assert pixmap != null : "Can’t makeFromPixmap with pixmap == null";
            Stats.onNativeCall();
            long ptr = _nMakeRasterFromPixmap(Native.getPtr(pixmap));
            if (ptr == 0)
                throw new RuntimeException("Failed to Image::makeFromRaster " + pixmap);
            return new Image(ptr);
        } finally {
            ReferenceUtil.reachabilityFence(pixmap);
        }
    }

    /**
     * @deprecated - use {@link #makeDeferredFromEncodedBytes(byte[])}
     */
    @Deprecated
    public static Image makeFromEncoded(byte[] bytes) {
        return makeDeferredFromEncodedBytes(bytes);
    }

    @NotNull @Contract("_ -> new")
    public static Image makeDeferredFromEncodedBytes(byte[] bytes) {
        Stats.onNativeCall();
        long ptr = _nMakeDeferredFromEncodedBytes(bytes);
        if (ptr == 0)
            throw new IllegalArgumentException("Failed to Image::makeFromEncoded");
        return new Image(ptr);
    }

    /**
     * Returns a ImageInfo describing the width, height, color type, alpha type, and color space
     * of the Image.
     *
     * @return  image info of Image.
     */
    @Override @NotNull
    public ImageInfo getImageInfo() {
        try {
            if (_imageInfo == null) {
                synchronized(this) {
                    if (_imageInfo == null) {
                        Stats.onNativeCall();
                        _imageInfo = _nGetImageInfo(_ptr);
                    }
                }
            }
            return _imageInfo;
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
    }

    /**
     * @deprecated - use {@link EncoderPNG#encode(Image)}, {@link EncoderJPEG#encode(Image)} or {@link EncoderWEBP#encode(Image)}
     */
    @Deprecated
    public Data encodeToData() {
        return encodeToData(EncodedImageFormat.PNG, 100);
    }

    /**
     * @deprecated - use {@link EncoderPNG#encode(Image)}, {@link EncoderJPEG#encode(Image)} or {@link EncoderWEBP#encode(Image)}
     */
    @Deprecated
    public Data encodeToData(EncodedImageFormat format) {
        return encodeToData(format, 100);
    }

    /**
     * @deprecated - use {@link EncoderPNG#encode(Image, EncodePNGOptions)}, {@link EncoderJPEG#encode(Image, EncodeJPEGOptions)} or {@link EncoderWEBP#encode(Image, EncodeWEBPOptions)}
     */
    @Deprecated
    public Data encodeToData(EncodedImageFormat format, int quality) {
        switch(format) {
        case PNG:
            return EncoderPNG.encode(this);
        case JPEG:
            return EncoderJPEG.encode(this, EncodeJPEGOptions.DEFAULT.withQuality(quality));
        case WEBP:
            return EncoderWEBP.encode(this, EncodeWEBPOptions.DEFAULT.withQuality(quality));
        default:
            throw new IllegalArgumentException("Format " + format + " is not supported");
        }
    }

    @NotNull
    public Shader makeShader() {
        return makeShader(FilterTileMode.CLAMP, FilterTileMode.CLAMP, SamplingMode.DEFAULT, null);
    }

    @NotNull
    public Shader makeShader(@Nullable Matrix33 localMatrix) {
        return makeShader(FilterTileMode.CLAMP, FilterTileMode.CLAMP, SamplingMode.DEFAULT, localMatrix);
    }

    @NotNull
    public Shader makeShader(@NotNull FilterTileMode tm) {
        return makeShader(tm, tm, SamplingMode.DEFAULT, null);
    }

    @NotNull
    public Shader makeShader(@NotNull FilterTileMode tmx, @NotNull FilterTileMode tmy) {
        return makeShader(tmx, tmy, SamplingMode.DEFAULT, null);
    }

    @NotNull
    public Shader makeShader(@NotNull FilterTileMode tmx,
                             @NotNull FilterTileMode tmy,
                             @Nullable Matrix33 localMatrix) {
        return makeShader(tmx, tmy, SamplingMode.DEFAULT, localMatrix);
    }

    @NotNull
    public Shader makeShader(@NotNull FilterTileMode tmx,
                             @NotNull FilterTileMode tmy,
                             @NotNull SamplingMode sampling,
                             @Nullable Matrix33 localMatrix) {
        try {
            assert tmx != null : "Can’t Bitmap.makeShader with tmx == null";
            assert tmy != null : "Can’t Bitmap.makeShader with tmy == null";
            assert sampling != null : "Can’t Bitmap.makeShader with sampling == null";
            Stats.onNativeCall();
            return new Shader(_nMakeShader(_ptr, tmx.ordinal(), tmy.ordinal(), sampling._pack(), localMatrix == null ? null : localMatrix._mat));
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
    }

    /**
     * If pixel address is available, return ByteBuffer wrapping it.
     * If pixel address is not available, return null.
     *
     * @return  ByteBuffer with direct access to pixels, or null
     *
     * @see <a href="https://fiddle.skia.org/c/@Image_peekPixels">https://fiddle.skia.org/c/@Image_peekPixels</a>
     */
    @Nullable
    public ByteBuffer peekPixels() {
        try {
            Stats.onNativeCall();
            return _nPeekPixels(_ptr);
        } finally {
            ReferenceUtil.reachabilityFence(this);
        }
    }

    public boolean peekPixels(Pixmap pixmap) {
        try {
            Stats.onNativeCall();
            return _nPeekPixelsToPixmap(_ptr, Native.getPtr(pixmap));
        } finally {
            ReferenceUtil.reachabilityFence(this);
            ReferenceUtil.reachabilityFence(pixmap);
        }
    }

    public boolean readPixels(@NotNull Bitmap dst) {
        return readPixels(null, dst, 0, 0, false);
    }

    public boolean readPixels(@NotNull Bitmap dst, int srcX, int srcY) {
        return readPixels(null, dst, srcX, srcY, false);
    }

    /**
     * <p>Copies Rect of pixels from Image to Bitmap. Copy starts at offset (srcX, srcY),
     * and does not exceed Image (getWidth(), getHeight()).</p>
     *
     * <p>dst specifies width, height, ColorType, AlphaType, and ColorSpace of destination.</p>
     *
     * <p>Returns true if pixels are copied. Returns false if:</p>
     * <ul>
     * <li>dst has no pixels allocated.</li>
     * </ul>
     *
     * <p>Pixels are copied only if pixel conversion is possible. If Image ColorType is
     * ColorType.GRAY_8, or ColorType.ALPHA_8; dst.getColorType() must match.
     * If Image ColorType is ColorType.GRAY_8, dst.getColorSpace() must match.
     * If Image AlphaType is AlphaType.OPAQUE, dst.getAlphaType() must
     * match. If Image ColorSpace is null, dst.getColorSpace() must match. Returns
     * false if pixel conversion is not possible.</p>
     *
     * <p>srcX and srcY may be negative to copy only top or left of source. Returns
     * false if getWidth() or getHeight() is zero or negative.</p>
     *
     * <p>Returns false if abs(srcX) &gt;= Image.getWidth(), or if abs(srcY) &gt;= Image.getHeight().</p>
     *
     * <p>If cache is true, pixels may be retained locally, otherwise pixels are not added to the local cache.</p>
     *
     * @param context the DirectContext in play, if it exists
     * @param dst     destination bitmap
     * @param srcX    column index whose absolute value is less than getWidth()
     * @param srcY    row index whose absolute value is less than getHeight()
     * @param cache   whether the pixels should be cached locally
     * @return        true if pixels are copied to dstPixels
     */
    public boolean readPixels(@Nullable DirectContext context, @NotNull Bitmap dst, int srcX, int srcY, boolean cache) {
        try {
            assert dst != null : "Can’t readPixels with dst == null";
            return _nReadPixelsBitmap(_ptr, Native.getPtr(context), Native.getPtr(dst), srcX, srcY, cache);
        } finally {
            ReferenceUtil.reachabilityFence(this);
            ReferenceUtil.reachabilityFence(context);
            ReferenceUtil.reachabilityFence(dst);
        }
    }

    public boolean readPixels(@NotNull Pixmap dst, int srcX, int srcY, boolean cache) {
        try {
            assert dst != null : "Can’t readPixels with dst == null";
            return _nReadPixelsPixmap(_ptr, Native.getPtr(dst), srcX, srcY, cache);
        } finally {
            ReferenceUtil.reachabilityFence(this);
            ReferenceUtil.reachabilityFence(dst);
        }
    }

    public boolean scalePixels(@NotNull Pixmap dst, SamplingMode samplingMode, boolean cache) {
        try {
            assert dst != null : "Can’t scalePixels with dst == null";
            return _nScalePixels(_ptr, Native.getPtr(dst), samplingMode._pack(), cache);
        } finally {
            ReferenceUtil.reachabilityFence(this);
            ReferenceUtil.reachabilityFence(dst);
        }
    }

    @ApiStatus.Internal public static native long _nAdoptGLTextureFrom(long contextPtr, int textureId, int target, int width, int height, int format, int surfaceOrigin, int colorType);
    @ApiStatus.Internal public static native long _nMakeRasterFromBytes(int width, int height, int colorType, int alphaType, long colorSpacePtr, byte[] pixels, long rowBytes);
    @ApiStatus.Internal public static native long _nMakeRasterFromData(int width, int height, int colorType, int alphaType, long colorSpacePtr, long dataPtr, long rowBytes);
    @ApiStatus.Internal public static native long _nMakeRasterFromBitmap(long bitmapPtr);
    @ApiStatus.Internal public static native long _nMakeRasterFromPixmap(long pixmapPtr);
    @ApiStatus.Internal public static native long _nMakeDeferredFromEncodedBytes(byte[] bytes);
    @ApiStatus.Internal public static native ImageInfo _nGetImageInfo(long ptr);
    @ApiStatus.Internal public static native long    _nMakeShader(long ptr, int tmx, int tmy, long samplingMode, float[] localMatrix);
    @ApiStatus.Internal public static native ByteBuffer _nPeekPixels(long ptr);
    @ApiStatus.Internal public static native boolean _nPeekPixelsToPixmap(long ptr, long pixmapPtr);
    @ApiStatus.Internal public static native boolean _nScalePixels(long ptr, long pixmapPtr, long samplingOptions, boolean cache);
    @ApiStatus.Internal public static native boolean _nReadPixelsBitmap(long ptr, long contextPtr, long bitmapPtr, int srcX, int srcY, boolean cache);
    @ApiStatus.Internal public static native boolean _nReadPixelsPixmap(long ptr, long pixmapPtr, int srcX, int srcY, boolean cache);
}