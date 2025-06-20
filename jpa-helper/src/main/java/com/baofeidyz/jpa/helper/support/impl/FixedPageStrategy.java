package com.baofeidyz.jpa.helper.support.impl;

import com.baofeidyz.jpa.helper.support.PageStrategy;
import org.springframework.data.domain.Pageable;

/**
 * 固定页码
 */
public class FixedPageStrategy implements PageStrategy {

    private FixedPageStrategy() {
    }

    @Override
    public Pageable getPageable(Pageable pageable) {
        return pageable;
    }

    private static final class InstanceHolder {
        static final FixedPageStrategy INSTANCE = new FixedPageStrategy();
    }

    public static FixedPageStrategy newInstance() {
        return InstanceHolder.INSTANCE;
    }
}
