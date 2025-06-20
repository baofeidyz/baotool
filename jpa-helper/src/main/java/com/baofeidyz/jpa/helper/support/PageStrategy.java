package com.baofeidyz.jpa.helper.support;

import org.springframework.data.domain.Pageable;

public interface PageStrategy {

    Pageable getPageable(Pageable pageable);
}
