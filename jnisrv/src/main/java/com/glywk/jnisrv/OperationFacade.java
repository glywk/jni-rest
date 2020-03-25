package com.glywk.jnisrv;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Component
public class OperationFacade {

    @Inject
    //private ApplicationArguments args;
    @Value("${rest-dll}")
    private String restLibraryPath;

    @PostConstruct
    public void init(){
        //String restLibraryPath = args.getOptionValues("rest-dll").stream().findFirst().orElse("jnisrv_impl.dll");
        System.load(restLibraryPath);
    }

    /*
     * This is the native method we want to call
     * not that this time we expect return value
     * and we don't pass anything inside argument
     * block
     */
    private static native MessageResultWrapper getBasicAction();

    /*
     * This is the native method we want to call
     * not that this time we expect return value
     * and we pass a string inside argument
     * block
     */
    private static native MessageResultWrapper postStringAction(String arg);

    public MessageResultWrapper get() {
        return getBasicAction();
        //return new MessageResultWrapper(200, new String("[{\"a\": \"b\"}]"));
    }

    public MessageResultWrapper post(String arg) {
        return postStringAction(arg);
    }

}
