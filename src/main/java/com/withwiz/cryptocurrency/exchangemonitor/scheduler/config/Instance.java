package com.withwiz.cryptocurrency.exchangemonitor.scheduler.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Instance {
    /**
     * name
     */
    String name;

    /**
     * implements class
     */
    String className;
}
