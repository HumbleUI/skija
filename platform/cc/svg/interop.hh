#pragma once
#include <jni.h>
#include "SkSVGTypes.h"

namespace skija {
    namespace svg {
        namespace SVGColor {
            extern jclass cls;
            extern jmethodID ctor;

            void onLoad(JNIEnv* env);
            void onUnload(JNIEnv* env);
            SkSVGColor fromJava(JNIEnv* env, jint jtype, jint color, jobjectArray vars);
            jobject toJava(JNIEnv* env, const SkSVGColor& color);
        }

        namespace SVGDashArray {
            extern jclass cls;
            extern jmethodID ctor;

            void onLoad(JNIEnv* env);
            void onUnload(JNIEnv* env);
            SkSVGDashArray fromJava(JNIEnv* env, jint type, jobjectArray dashArray);
            jobject toJava(JNIEnv* env, const SkSVGDashArray& color);
        }

        namespace SVGIRI {
            extern jclass cls;
            extern jmethodID ctor;

            void onLoad(JNIEnv* env);
            void onUnload(JNIEnv* env);
            SkSVGIRI fromJava(JNIEnv* env, jint jtype, jstring iri);
            jobject toJava(JNIEnv* env, const SkSVGIRI& paint);
        }

        namespace SVGPaint {
            extern jclass cls;
            extern jmethodID ctorNone;
            extern jmethodID ctorColor;
            extern jmethodID ctorIRI;

            void onLoad(JNIEnv* env);
            void onUnload(JNIEnv* env);
            SkSVGPaint fromJava(JNIEnv* env, jint jtype, jint colorType, jint color, jobjectArray vars, jint iriType, jstring iri);
            jobject toJava(JNIEnv* env, const SkSVGPaint& paint);
        }

        namespace SVGFuncIRI {
            extern jclass cls;
            extern jmethodID ctorNone;
            extern jmethodID ctorIri;
            void onLoad(JNIEnv* env);
            void onUnload(JNIEnv* env);
            SkSVGFuncIRI fromJava(JNIEnv* env, jint jfuncType, jint iriType, jstring iri);
            jobject toJava(JNIEnv* env, const SkSVGFuncIRI& paint);
        }

        namespace SVGLength {
            extern jclass cls;
            extern jmethodID ctor;
            void onLoad(JNIEnv* env);
            void onUnload(JNIEnv* env);
            SkSVGLength fromJava(JNIEnv* env, jobject object);
            jobject toJava(JNIEnv* env, const SkSVGLength& length);
        }

        namespace SVGLengthUnit {
            extern jclass cls;
            extern jmethodID ordinalMethod;
            void onLoad(JNIEnv* env);
            void onUnload(JNIEnv* env);
            jint ordinal(JNIEnv* env, jobject unit);
        }

        namespace SVGFontFamily {
            extern jclass cls;
            extern jmethodID ctor;
            void onLoad(JNIEnv* env);
            void onUnload(JNIEnv* env);
            jobject toJava(JNIEnv* env, const SkSVGFontFamily& family);
        }

        namespace SVGFontSize {
            extern jclass cls;
            extern jmethodID ctor;
            void onLoad(JNIEnv* env);
            void onUnload(JNIEnv* env);
            jobject toJava(JNIEnv* env, const SkSVGFontSize& size);
        }

        namespace SVGPreserveAspectRatio {
            extern jclass cls;
            extern jmethodID ctor;
            void onLoad(JNIEnv* env);
            void onUnload(JNIEnv* env);
            jobject toJava(JNIEnv* env, const SkSVGPreserveAspectRatio& ratio);
        }

        void onLoad(JNIEnv* env);
        void onUnload(JNIEnv* env);
    }
}