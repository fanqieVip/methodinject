package com.fanjun.methodinjectdemo;

import com.fanjun.methodinject.api.InjectProcessor;

public class TestInject implements InjectProcessor {
    @Override
    public boolean intercept() {
        return false;
    }
}
