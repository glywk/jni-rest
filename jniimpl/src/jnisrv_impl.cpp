#include <cuj/jni_master.h>
#include <jni/com_glywk_jnisrv_OperationFacade.h>

namespace {
/*!
 * \return MessageResultWrapper(int errorCode, String message)
 */
jobject make_MessageResultWrapper(JNIEnv *env, int errorCode,
                                  const std::string &message) {
  JNIClass objectClass(env, "com/glywk/jnisrv/MessageResultWrapper");
  jmethodID constructorId =
      env->GetMethodID(objectClass, "<init>", "(ILjava/lang/String;)V");

  return env->NewObject(objectClass, constructorId, errorCode,
                        env->NewStringUTF(message.c_str()));
}

} // namespace

/*
 * Class:     com_glywk_jnisrv_OperationFacade
 * Method:    getBasicAction
 * Signature: ()Lcom/glywk/jnisrv/MessageResultWrapper;
 */
JNIEXPORT jobject JNICALL
Java_com_glywk_jnisrv_OperationFacade_getBasicAction(JNIEnv *env, jclass obj) {
  try {
    return make_MessageResultWrapper(env, 200, "[{\"a\": \"b\"}]");
  } catch (const std::exception &e) {
    return make_MessageResultWrapper(env, 500, e.what());
  } catch (...) {
    return make_MessageResultWrapper(env, 500, "Unkown exception");
  }
}

/*
 * Class:     com_glywk_jnisrv_OperationFacade
 * Method:    postStringAction
 * Signature: (Ljava/lang/String;)Lcom/glywk/jnisrv/MessageResultWrapper;
 */
JNIEXPORT jobject JNICALL
Java_com_glywk_jnisrv_OperationFacade_postStringAction(JNIEnv *env, jclass obj,
                                                       jstring arg) {
  try {
    JNIStringUTFChars message(env, arg);
    return make_MessageResultWrapper(env, 200, message.asString());
  } catch (const std::exception &e) {
    return make_MessageResultWrapper(env, 500, e.what());
  } catch (...) {
    return make_MessageResultWrapper(env, 500, "Unkown exception");
  }
}