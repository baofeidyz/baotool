package com.baofeidyz.jpa.helper.support;

import java.util.List;

public interface Function<T, R> {
    List<R> apply(List<T> t);
}
