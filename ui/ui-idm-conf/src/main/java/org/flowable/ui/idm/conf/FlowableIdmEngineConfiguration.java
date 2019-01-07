package org.flowable.ui.idm.conf;

import org.flowable.common.engine.impl.runtime.Clock;
import org.flowable.idm.engine.IdmEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
    "org.flowable.idm.extension.conf", // For custom configuration classes
    "org.flowable.idm.extension.bean" // For custom beans
})
public class FlowableIdmEngineConfiguration {

    @Bean(name = "clock")
    public Clock getClock(IdmEngine idmEngine) {
        return idmEngine.getIdmEngineConfiguration().getClock();
    }
}
