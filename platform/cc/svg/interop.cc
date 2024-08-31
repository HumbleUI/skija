#include <jni.h>
#include "interop.hh"
#include "../interop.hh"

namespace skija {
    namespace svg {
        namespace SVGColor {
            jclass cls;
            jmethodID ctor;

            void onLoad(JNIEnv* env) {
                jclass local = env->FindClass("io/github/humbleui/skija/svg/SVGColor");
                cls  = static_cast<jclass>(env->NewGlobalRef(local));
                ctor = env->GetMethodID(cls, "<init>", "(II[Ljava/lang/String;)V");
            }

            void onUnload(JNIEnv* env) {
                env->DeleteGlobalRef(cls);
            }

            SkSVGColor fromJava(JNIEnv* env, jint jtype, jint color, jobjectArray vars) {
                SkSVGColor::Type type = static_cast<SkSVGColor::Type>(jtype);
                return SkSVGColor(SkSVGColor::Type::kCurrentColor, skStringVector(env, vars));
                switch (type) {
                    case SkSVGColor::Type::kCurrentColor:
                        return SkSVGColor();
                    case SkSVGColor::Type::kColor:
                        return SkSVGColor(color, skStringVector(env, vars));
                    case SkSVGColor::Type::kICCColor:
                        return SkSVGColor(type, skStringVector(env, vars));
                    default:
                        return SkSVGColor();
                }
            }

            jobject toJava(JNIEnv* env, const SkSVGColor& color) {
                return env->NewObject(cls, ctor,
                    static_cast<jint>(color.type()),
                    color.type() == SkSVGColor::Type::kColor ? color.color() : 0x000000,
                    javaStringArray(env, color.vars())
                );
            }
        }

        namespace SVGIRI {
            jclass cls;
            jmethodID ctor;

            void onLoad(JNIEnv* env) {
                jclass local = env->FindClass("io/github/humbleui/skija/svg/SVGIRI");
                cls  = static_cast<jclass>(env->NewGlobalRef(local));
                ctor = env->GetMethodID(cls, "<init>", "(ILjava/lang/String;)V");
            }

            void onUnload(JNIEnv* env) {
                env->DeleteGlobalRef(cls);
            }

            SkSVGIRI fromJava(JNIEnv* env, jint jtype, jstring iri) {
                SkSVGIRI::Type type = static_cast<SkSVGIRI::Type>(jtype);
                if (type == SkSVGIRI::Type::kLocal) {
                    return SkSVGIRI();
                } else {
                    return SkSVGIRI(type, skString(env, iri));
                }
            }

            jobject toJava(JNIEnv* env, const SkSVGIRI& paint) {
                return env->NewObject(cls, ctor, static_cast<jint>(paint.type()), javaString(env, paint.iri()));
            }
        }

        namespace SVGPaint {
            jclass cls;
            jmethodID ctorNone;
            jmethodID ctorColor;
            jmethodID ctorIRI;

            void onLoad(JNIEnv* env) {
                jclass local = env->FindClass("io/github/humbleui/skija/svg/SVGPaint");
                cls  = static_cast<jclass>(env->NewGlobalRef(local));
                ctorNone = env->GetMethodID(cls, "<init>", "()V");
                ctorColor = env->GetMethodID(cls, "<init>", "(Lio/github/humbleui/skija/svg/SVGColor;)V");
                ctorIRI = env->GetMethodID(cls, "<init>", "(Lio/github/humbleui/skija/svg/SVGIRI;I)V");
            }

            void onUnload(JNIEnv* env) {
                env->DeleteGlobalRef(cls);
            }

            SkSVGPaint fromJava(JNIEnv* env, jint jtype, jint colorType, jint color, jobjectArray vars, jint iriType, jstring iri) {
                SkSVGColor svgColor = skija::svg::SVGColor::fromJava(env, colorType, color, vars);
                SkSVGPaint::Type type = static_cast<SkSVGPaint::Type>(jtype);

                switch (type) {
                    case SkSVGPaint::Type::kNone:
                        return SkSVGPaint();
                    case SkSVGPaint::Type::kColor:
                        return SkSVGPaint(svgColor);
                    case SkSVGPaint::Type::kIRI:
                        return SkSVGPaint(skija::svg::SVGIRI::fromJava(env, iriType, iri), svgColor);
                    default:
                        return SkSVGPaint();
                }
            }

            jobject toJava(JNIEnv* env, const SkSVGPaint& paint) {
                jobject result = nullptr;
                switch (paint.type()) {
                    case SkSVGPaint::Type::kNone:
                        result = env->NewObject(cls, ctorNone);
                        break;
                    case SkSVGPaint::Type::kColor:
                        result = env->NewObject(cls, ctorColor, skija::svg::SVGColor::toJava(env, paint.color()));
                        break;
                    case SkSVGPaint::Type::kIRI:
                        result = env->NewObject(cls, ctorIRI,
                            skija::svg::SVGIRI::toJava(env, paint.iri()),
                            skija::svg::SVGColor::toJava(env, paint.color())
                        );
                        break;
                }
                return result;
            }
        }

        namespace SVGLength {
            jclass cls;
            jmethodID ctor;

            void onLoad(JNIEnv* env) {
                jclass local = env->FindClass("io/github/humbleui/skija/svg/SVGLength");
                cls  = static_cast<jclass>(env->NewGlobalRef(local));
                ctor = env->GetMethodID(cls, "<init>", "(FI)V");
            }

            void onUnload(JNIEnv* env) {
                env->DeleteGlobalRef(cls);
            }

            jobject toJava(JNIEnv* env, const SkSVGLength& length) {
                return env->NewObject(cls, ctor, length.value(), static_cast<jint>(length.unit()));
            }
        }

        namespace SVGPreserveAspectRatio {
            jclass cls;
            jmethodID ctor;

            void onLoad(JNIEnv* env) {
                jclass local = env->FindClass("io/github/humbleui/skija/svg/SVGPreserveAspectRatio");
                cls  = static_cast<jclass>(env->NewGlobalRef(local));
                ctor = env->GetMethodID(cls, "<init>", "(II)V");
            }

            void onUnload(JNIEnv* env) {
                env->DeleteGlobalRef(cls);
            }

            jobject toJava(JNIEnv* env, const SkSVGPreserveAspectRatio& ratio) {
                return env->NewObject(cls, ctor, static_cast<jint>(ratio.fAlign), static_cast<jint>(ratio.fScale));
            }
        }

        void onLoad(JNIEnv* env) {
            SVGColor::onLoad(env);
            SVGIRI::onLoad(env);
            SVGPaint::onLoad(env);
            SVGLength::onLoad(env);
            SVGPreserveAspectRatio::onLoad(env);
        }

        void onUnload(JNIEnv* env) {
            SVGPreserveAspectRatio::onUnload(env);
            SVGLength::onUnload(env);
            SVGPaint::onUnload(env);
            SVGIRI::onUnload(env);
            SVGColor::onUnload(env);
        }
    }
}