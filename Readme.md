# JNI Spring Boot http-rest example

# Build
## Generate jni header interface (Only when java OperationFacade is modified)
Call java compiler on OperationFacade.java to generate jni header file.

```
$JDKPath$\bin\javac.exe -cp $Classpath$ -h $FileDir$ $FileDir$/$FileName$
```

Copy header file in `jniimpl\src\include\jni`.

## Generate implementation dll
JAVA_HOME have to be defined.

```
    cd jniimpl
    mkdir build
    cd build
    cmake ..
    cmake --build ..
``` 
This will update application.properties in java parts, example:
``` 
rest-dll=<path>jniimpl/build/src/<build_type>/jnisrv_impl.dll
``` 

## Generate java http server
``` 
mvn clean package
``` 

# Run java server
``` 
jnisrv-0.0.1-SNAPSHOT.exe
``` 

Call in browser: `http://localhost:8080/jni/caller-get`
