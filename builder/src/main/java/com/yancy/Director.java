package com.yancy;

import lombok.AllArgsConstructor;

/**
 * Director
 * @author yancy0109
 */
@AllArgsConstructor
public class Director {

    private Builder builder;

    public Bike construct() {
        builder.setSeat();
        builder.setFrame();
        return builder.build();
    }
}
