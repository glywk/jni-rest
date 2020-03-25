package com.glywk.jnisrv;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
public class JniRestController {

    @Inject
    OperationFacade operation;

    @GetMapping(value = "/jni/caller-get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> callerGet() throws InvalidHttpStatusException {
        return treatOperation(operation.get());
    }

    @PostMapping(value = "/jni/caller-post", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> callerPost(@RequestParam String arg) throws InvalidHttpStatusException {
        return treatOperation(operation.post(arg));
    }

    private ResponseEntity<String> treatOperation(MessageResultWrapper message) throws InvalidHttpStatusException {
        HttpStatus status = HttpStatus.resolve(message.getErrorCode());
        if (null == status) {
            throw new InvalidHttpStatusException();
        }
        return new ResponseEntity<String>(message.getMessage(), status);
    }
}
