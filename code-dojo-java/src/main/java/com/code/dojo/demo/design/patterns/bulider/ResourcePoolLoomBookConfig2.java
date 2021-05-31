package com.code.dojo.demo.design.patterns.bulider;

import com.google.common.base.Preconditions;
import lombok.Builder;
import lombok.Data;


@Data
@Builder()
public class ResourcePoolLoomBookConfig2 {

    private String name;
    private int maxTotal;
    private int maxIdle;
    private int minIdle;


    private void valid() {
        Preconditions.checkNotNull(name, "name should not be null");
        Preconditions.checkArgument(maxIdle > minIdle,String.format("maxIdle %s should bigger than  minIdle  %s", maxIdle,minIdle));
        Preconditions.checkArgument(minIdle > maxTotal || minIdle > maxIdle);
    }

    // 这里通过继承lombok生成的builder(重写build()方法加入校验)，重写builder()静态方法，来返回自己builder。这样就大功告成了。
    public static class InternalBuilder extends ResourcePoolLoomBookConfig2Builder {
        InternalBuilder() {
            super();
        }

        @Override
        public ResourcePoolLoomBookConfig2 build() {
            ResourcePoolLoomBookConfig2 model = super.build();
            model.valid();
            return model;
        }
    }

    public static ResourcePoolLoomBookConfig2Builder builder() {
        return new InternalBuilder();
    }
}
