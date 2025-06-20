package com.baofeidyz.jpa.helper.support;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuerySupplier<T> {
    Page<T> findAll(Pageable pageable);
}
