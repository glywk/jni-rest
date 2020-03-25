package com.glywk.jnisrv;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Invalid http status")
public class InvalidHttpStatusException extends Exception {
}
