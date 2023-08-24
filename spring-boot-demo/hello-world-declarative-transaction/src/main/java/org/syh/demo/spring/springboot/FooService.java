package org.syh.demo.spring.springboot;

public interface FooService {
    void insertRecord();
    void insertThenRollback() throws RollbackException;
    void invokeInsertThenRollback() throws RollbackException;
    void invokeInsertThenRollbackByAopContext() throws RollbackException;
    void invokeInsertThenRollbackBySelfService() throws RollbackException;
}
