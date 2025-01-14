package com.theinkwell.server.domains.utils;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
public class StandardException {

    private String message;
    private Instant time;
    private String path;
    
}
