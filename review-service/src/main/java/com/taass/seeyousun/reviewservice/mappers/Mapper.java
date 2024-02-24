package com.taass.seeyousun.reviewservice.mappers;

public interface Mapper<A,B> {
    public B mapTo(A a);
    public A mapFrom(B b);
}
