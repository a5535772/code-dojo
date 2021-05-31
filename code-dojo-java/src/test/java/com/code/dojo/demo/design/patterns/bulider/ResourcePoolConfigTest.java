package com.code.dojo.demo.design.patterns.bulider;

import org.junit.Test;

public class ResourcePoolConfigTest {

    @Test(expected = IllegalArgumentException.class)
    public void resourcePoolConfig_IllegalArgumentException_test() {
        // 这段代码会抛出IllegalArgumentException，因为minIdle>maxIdle
        new ResourcePoolConfig.Builder().setName("dbconnectionpool").setMaxTotal(16).setMaxIdle(10).setMinIdle(12)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void ResourcePoolLoomBookConfig_IllegalArgumentException_test() {
        // 这段代码会抛出IllegalArgumentException，因为minIdle>maxIdle
        ResourcePoolLoomBookConfig.builder().name("dbconnectionpool").maxTotal(16).maxIdle(10).minIdle(12).build();
    }
}

