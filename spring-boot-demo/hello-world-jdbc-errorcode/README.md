# Notes

## Spring JDBC Exception
Spring JDBC will put all exception operation into __DataAccessException__.

Spring JDBC uses __SQLErrorCodeSQLExceptionTranslator__ to parse error code.

Error code is defined in org/springframework/jdbc/support/sql-error-codes.xml.
You can also override it by defining your own __sql-error-codes.xml__ in your classpath.

## Surefire
In order to run JUnit 4 tests when execute `mvn clean test`, we need to add dependency __surefire-junit4__.  
Also the version is important, __3.0.0-M5__ can work.

## DuplicateKeyException
DuplicateKeyException -> DataIntegrityViolationException -> NonTransientDataAccessException -> DataAccessException