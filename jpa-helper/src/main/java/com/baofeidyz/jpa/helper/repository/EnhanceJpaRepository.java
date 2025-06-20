package com.baofeidyz.jpa.helper.repository;

import com.baofeidyz.jpa.helper.util.JpaUtil;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * 在{@link JpaRepository}的基础上做一些增强
 *
 * <ol>
 *     <li>{@link #findAll(Sort)} 的基础上使用分页查询，避免查询结果过大导致的OOM</li>
 * </ol>
 */
@NoRepositoryBean
public interface EnhanceJpaRepository<T, ID> extends JpaRepository<T, ID> {

    @Override
    default List<T> findAll(Sort sort) {
        return JpaUtil.findAll(this::findAll, sort);
    }
}
