package com.withwiz.interfaces.scheduler.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

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
