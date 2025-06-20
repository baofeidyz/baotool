package com.baofeidyz.jpa.helper.support.impl;

import com.baofeidyz.jpa.helper.support.PageStrategy;
import org.springframework.data.domain.Pageable;

/**
 * 自增页码
 */
public class IncrementPageStrategy implements PageStrategy {

    private IncrementPageStrategy() {
    }

    @Override
    public Pageable getPageable(Pageable pageable) {
        return pageable.next();
    }

    private static final class InstanceHolder {
        static final IncrementPageStrategy INSTANCE = new IncrementPageStrategy();
    }

    public static IncrementPageStrategy newInstance() {
        return IncrementPageStrategy.InstanceHolder.INSTANCE;
    }
}
