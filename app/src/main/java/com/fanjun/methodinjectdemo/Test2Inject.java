package com.fanjun.methodinjectdemo;

import com.fanjun.methodinject.api.InjectProcessor;

public class Test2Inject implements InjectProcessor {
    @Override
    public boolean intercept() {
        return true;
    }
}
