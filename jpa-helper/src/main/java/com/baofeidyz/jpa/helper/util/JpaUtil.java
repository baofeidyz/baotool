package com.baofeidyz.jpa.helper.util;

import com.baofeidyz.jpa.helper.support.Consumer;
import com.baofeidyz.jpa.helper.support.Function;
import com.baofeidyz.jpa.helper.support.PageStrategy;
import com.baofeidyz.jpa.helper.support.QuerySupplier;
import com.baofeidyz.jpa.helper.support.impl.IncrementPageStrategy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * 针对Spring Data Jpa提供一些常用的方法
 */
public class JpaUtil {

    private final static int DEFAULT_PAGE_NUMBER = 0;
    private final static int DEFAULT_PAGE_SIZE = 500;
    private final static PageStrategy DEFAULT_PAGE_STRATEGY = IncrementPageStrategy.newInstance();

    public static <T> List<T> findAll(QuerySupplier<T> querySupplier, Sort sort) {
        return findAll(querySupplier, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE, sort);
    }

    public static <T> List<T> findAll(QuerySupplier<T> querySupplier, int page, int size, Sort sort) {
        List<T> list = new ArrayList<>();
        Page<T> pageResult;
        Pageable pageable = PageRequest.of(page, size, sort);
        do {
            pageResult = querySupplier.findAll(pageable);
            list.addAll(pageResult.getContent());
            pageable = pageable.next();
        } while (pageResult.hasNext());
        return list;
    }

    public static <T, R> List<R> doWithPage(QuerySupplier<T> querySupplier, Function<T, R> function, Sort sort) {
        return doWithPage(querySupplier, function, DEFAULT_PAGE_STRATEGY, sort);
    }

    public static <T, R> List<R> doWithPage(QuerySupplier<T> querySupplier, Function<T, R> function, PageStrategy pageStrategy, Sort sort) {
        return doWithPage(querySupplier, function, pageStrategy, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE, sort);
    }

    public static <T, R> List<R> doWithPage(QuerySupplier<T> querySupplier, Function<T, R> function, PageStrategy pageStrategy,
                                            int page, int size, Sort sort) {
        Page<T> pageResult;
        List<R> resultList = new ArrayList<>();
        do {
            pageResult = querySupplier.findAll(pageStrategy.getPageable(PageRequest.of(page, size, sort)));
            if (pageResult.hasContent()) {
                resultList.addAll(function.apply(pageResult.getContent()));
            }
        } while (pageResult.hasNext());
        return resultList;
    }

    public static <T> void doWithPage(QuerySupplier<T> querySupplier, Consumer<T> consumer, Sort sort) {
        doWithPage(querySupplier, consumer, DEFAULT_PAGE_STRATEGY, sort);
    }

    public static <T> void doWithPage(QuerySupplier<T> querySupplier, Consumer<T> consumer, PageStrategy pageStrategy, Sort sort) {
        doWithPage(querySupplier, consumer, pageStrategy, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE, sort);
    }

    public static <T> void doWithPage(QuerySupplier<T> querySupplier, Consumer<T> consumer, PageStrategy pageStrategy,
                                      int page, int size, Sort sort) {
        Page<T> pageResult;
        do {
            pageResult = querySupplier.findAll(pageStrategy.getPageable(PageRequest.of(page, size, sort)));
            if (pageResult.hasContent()) {
                consumer.accept(pageResult.getContent());
            }
        } while (pageResult.hasNext());
    }
}
