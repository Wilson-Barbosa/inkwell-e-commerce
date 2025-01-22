package com.theinkwell.server.domains.utils;

import java.io.Serializable;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * StandardWebException is the default representation of an http's response body.
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
public class WebResponseExceptionBody implements Serializable{

    private String message;
    private Instant time;
    private String path;
    
}
