cmake_minimum_required(VERSION 3.12 FATAL_ERROR)

project(jniimpl)

# Required JAVA_HOME to find jni includes
find_package(JNI REQUIRED)

add_library(cuj_jni INTERFACE)

target_include_directories(cuj_jni INTERFACE
  ${JNI_INCLUDE_DIRS}
  $<BUILD_INTERFACE:${CMAKE_CURRENT_SOURCE_DIR}/include>
  $<INSTALL_INTERFACE:include>  # <prefix>/include
)

set(JNISRV_APPLICATION_PROPERTIES ${CMAKE_CURRENT_SOURCE_DIR}/../jnisrv/src/main/resources/application.properties CACHE PATH "")
set(JNISRV_TEST_APPLICATION_PROPERTIES ${CMAKE_CURRENT_SOURCE_DIR}/../jnisrv/src/test/resources/application-default.properties CACHE PATH "")

add_subdirectory(src)
