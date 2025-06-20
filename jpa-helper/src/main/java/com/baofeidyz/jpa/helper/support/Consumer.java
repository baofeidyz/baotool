package com.baofeidyz.jpa.helper.support;

import java.util.List;

public interface Consumer<T> {
    void accept(List<T> t);
}
