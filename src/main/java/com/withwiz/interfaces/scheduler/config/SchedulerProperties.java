package com.withwiz.interfaces.scheduler.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Setter
@Getter
@ToString
@Component
@ConfigurationProperties("scheduler")
public class SchedulerProperties {
    List<Instance> instances;
}
