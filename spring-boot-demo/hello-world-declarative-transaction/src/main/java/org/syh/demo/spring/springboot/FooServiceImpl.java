package org.syh.demo.spring.springboot;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class FooServiceImpl implements FooService {
    @Lazy
    private FooService fooService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void insertRecord() {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('ben')");
    }

    @Override
    @Transactional(rollbackFor = RollbackException.class)
    public void insertThenRollback() throws RollbackException {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('tom')");
        throw new RollbackException();
    }

    @Override
    public void invokeInsertThenRollback() throws RollbackException {
        insertThenRollback();
    }

    @Override
    public void invokeInsertThenRollbackByAopContext() throws RollbackException {
        ((FooService)(AopContext.currentProxy())).insertThenRollback();
    }

    @Override
    public void invokeInsertThenRollbackBySelfService() throws RollbackException {
        fooService.insertThenRollback();
    }
}
