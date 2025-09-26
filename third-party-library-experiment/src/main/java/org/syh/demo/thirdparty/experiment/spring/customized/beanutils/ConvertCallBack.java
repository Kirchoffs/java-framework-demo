package org.syh.demo.thirdparty.experiment.spring.customized.beanutils;

@FunctionalInterface
public interface ConvertCallBack<S, T> {
    void callBack(S source, T target);
}
