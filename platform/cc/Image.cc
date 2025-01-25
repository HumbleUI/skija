#include <iostream>
#include <jni.h>
#include "SkBitmap.h"
#include "SkData.h"
#include "SkImage.h"
#include "SkShader.h"
#include "interop.hh"
#include "GrBackendSurface.h"
#include "GrDirectContext.h"
#include "include/gpu/GrBackendSurface.h"
#include "include/gpu/ganesh/SkImageGanesh.h"
#include "include/gpu/gl/GrGLTypes.h"

extern "C" JNIEXPORT jlong JNICALL Java_io_github_humbleui_skija_Image__1nMakeRasterFromBytes
  (JNIEnv* env, jclass jclass, jint width, jint height, jint colorType, jint alphaType, jlong colorSpacePtr, jbyteArray bytesArr, jlong rowBytes) {
    SkColorSpace* colorSpace = reinterpret_cast<SkColorSpace*>(static_cast<uintptr_t>(colorSpacePtr));
    SkImageInfo imageInfo = SkImageInfo::Make(width,
                                              height,
                                              static_cast<SkColorType>(colorType),
                                              static_cast<SkAlphaType>(alphaType),
                                              sk_ref_sp<SkColorSpace>(colorSpace));
    void* bytes = env->GetPrimitiveArrayCritical(bytesArr, 0);
    sk_sp<SkImage> image = SkImages::RasterFromPixmapCopy(SkPixmap(imageInfo, bytes, rowBytes));
    env->ReleasePrimitiveArrayCritical(bytesArr, bytes, 0);
    return reinterpret_cast<jlong>(image.release());
}

extern "C" JNIEXPORT jlong JNICALL Java_io_github_humbleui_skija_Image__1nMakeRasterFromData
  (JNIEnv* env, jclass jclass, jint width, jint height, jint colorType, jint alphaType, jlong colorSpacePtr, jlong dataPtr, jlong rowBytes) {
    SkColorSpace* colorSpace = reinterpret_cast<SkColorSpace*>(static_cast<uintptr_t>(colorSpacePtr));
    SkImageInfo imageInfo = SkImageInfo::Make(width,
                                              height,
                                              static_cast<SkColorType>(colorType),
                                              static_cast<SkAlphaType>(alphaType),
                                              sk_ref_sp<SkColorSpace>(colorSpace));
    SkData* data = reinterpret_cast<SkData*>(static_cast<uintptr_t>(dataPtr));
    sk_sp<SkImage> image = SkImages::RasterFromData(imageInfo, sk_ref_sp(data), rowBytes);
    return reinterpret_cast<jlong>(image.release());
}

extern "C" JNIEXPORT jlong JNICALL Java_io_github_humbleui_skija_Image__1nMakeRasterFromBitmap
  (JNIEnv* env, jclass jclass, jlong bitmapPtr) {
    SkBitmap* bitmap = reinterpret_cast<SkBitmap*>(static_cast<uintptr_t>(bitmapPtr));
    sk_sp<SkImage> image = SkImages::RasterFromBitmap(*bitmap);
    return reinterpret_cast<jlong>(image.release());
}

extern "C" JNIEXPORT jlong JNICALL Java_io_github_humbleui_skija_Image__1nMakeRasterFromPixmap
  (JNIEnv* env, jclass jclass, jlong pixmapPtr) {
    SkPixmap* pixmap = reinterpret_cast<SkPixmap*>(static_cast<uintptr_t>(pixmapPtr));
    sk_sp<SkImage> image = SkImages::RasterFromPixmap(*pixmap, nullptr, nullptr);
    return reinterpret_cast<jlong>(image.release());
}

extern "C" JNIEXPORT jlong JNICALL Java_io_github_humbleui_skija_Image__1nMakeDeferredFromEncodedBytes
  (JNIEnv* env, jclass jclass, jbyteArray encodedArray) {
    jsize encodedLen = env->GetArrayLength(encodedArray);
    jbyte* encoded = env->GetByteArrayElements(encodedArray, 0);
    sk_sp<SkData> encodedData = SkData::MakeWithCopy(encoded, encodedLen);
    env->ReleaseByteArrayElements(encodedArray, encoded, 0);

    sk_sp<SkImage> image = SkImages::DeferredFromEncodedData(encodedData);

    return reinterpret_cast<jlong>(image.release());
}

extern "C" JNIEXPORT jobject JNICALL Java_io_github_humbleui_skija_Image__1nGetImageInfo
  (JNIEnv* env, jclass jclass, jlong ptr) {
    SkImage* instance = reinterpret_cast<SkImage*>(static_cast<uintptr_t>(ptr));
    return skija::ImageInfo::toJava(env, instance->imageInfo());
}

extern "C" JNIEXPORT jlong JNICALL Java_io_github_humbleui_skija_Image__1nMakeShader
  (JNIEnv* env, jclass jclass, jlong ptr, jint tmx, jint tmy, jlong sampling, jfloatArray localMatrixArr) {
    SkImage* instance = reinterpret_cast<SkImage*>(static_cast<uintptr_t>(ptr));
    std::unique_ptr<SkMatrix> localMatrix = skMatrix(env, localMatrixArr);
    sk_sp<SkShader> shader = instance->makeShader(static_cast<SkTileMode>(tmx), static_cast<SkTileMode>(tmy), skija::SamplingMode::unpack(sampling), localMatrix.get());
    return reinterpret_cast<jlong>(shader.release());
}

extern "C" JNIEXPORT jobject JNICALL Java_io_github_humbleui_skija_Image__1nPeekPixels
  (JNIEnv* env, jclass jclass, jlong ptr) {
    SkImage* instance = reinterpret_cast<SkImage*>(static_cast<uintptr_t>(ptr));
    SkPixmap pixmap;
    if (instance->peekPixels(&pixmap))
        return env->NewDirectByteBuffer(pixmap.writable_addr(), pixmap.rowBytes() * pixmap.height());
    else
        return nullptr;
}

extern "C" JNIEXPORT jboolean JNICALL Java_io_github_humbleui_skija_Image__1nPeekPixelsToPixmap
  (JNIEnv* env, jclass jclass, jlong ptr, jlong pixmapPtr) {
    SkImage* instance = reinterpret_cast<SkImage*>(static_cast<uintptr_t>(ptr));
    SkPixmap* pixmap = reinterpret_cast<SkPixmap*>(static_cast<uintptr_t>(pixmapPtr));
    return instance->peekPixels(pixmap);
}

extern "C" JNIEXPORT jboolean JNICALL Java_io_github_humbleui_skija_Image__1nReadPixelsBitmap
  (JNIEnv* env, jclass jclass, jlong ptr, jlong contextPtr, jlong bitmapPtr, jint srcX, jint srcY, jboolean cache) {
    SkImage* instance = reinterpret_cast<SkImage*>(static_cast<uintptr_t>(ptr));
    GrDirectContext* context = reinterpret_cast<GrDirectContext*>(static_cast<uintptr_t>(contextPtr));
    SkBitmap* bitmap = reinterpret_cast<SkBitmap*>(static_cast<uintptr_t>(bitmapPtr));
    auto cachingHint = cache ? SkImage::CachingHint::kAllow_CachingHint : SkImage::CachingHint::kDisallow_CachingHint;
    return instance->readPixels(context, bitmap->info(), bitmap->getPixels(), bitmap->pixmap().rowBytes(), srcX, srcY, cachingHint);
}

extern "C" JNIEXPORT jboolean JNICALL Java_io_github_humbleui_skija_Image__1nReadPixelsPixmap
  (JNIEnv* env, jclass jclass, jlong ptr, jlong pixmapPtr, jint srcX, jint srcY, jboolean cache) {
    SkImage* instance = reinterpret_cast<SkImage*>(static_cast<uintptr_t>(ptr));
    SkPixmap* pixmap = reinterpret_cast<SkPixmap*>(static_cast<uintptr_t>(pixmapPtr));
    auto cachingHint = cache ? SkImage::CachingHint::kAllow_CachingHint : SkImage::CachingHint::kDisallow_CachingHint;
    return instance->readPixels(*pixmap, srcX, srcY, cachingHint);
}

extern "C" JNIEXPORT jboolean JNICALL Java_io_github_humbleui_skija_Image__1nScalePixels
  (JNIEnv* env, jclass jclass, jlong ptr, jlong pixmapPtr, jlong samplingOptions, jboolean cache) {
    SkImage* instance = reinterpret_cast<SkImage*>(static_cast<uintptr_t>(ptr));
    SkPixmap* pixmap = reinterpret_cast<SkPixmap*>(static_cast<uintptr_t>(pixmapPtr));
    auto cachingHint = cache ? SkImage::CachingHint::kAllow_CachingHint : SkImage::CachingHint::kDisallow_CachingHint;
    return instance->scalePixels(*pixmap, skija::SamplingMode::unpack(samplingOptions), cachingHint);
}
