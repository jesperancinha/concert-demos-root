# Concert Demos Root - Issues Page

1. MPP error for kotest versions above 4.4.0

```java
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.256 s - in org.jesperancinha.concerts.mvc.controllers.ConcertControllerImplITTest
[ERROR] Tests run: 1, Failures: 0, Errors: 1, Skipped: 0, Time elapsed: 0.338 s <<< FAILURE! - in Kotest
[ERROR] Kotest.Kotest  Time elapsed: 0.127 s  <<< ERROR!
java.lang.NoSuchMethodError: 'java.util.List io.kotest.mpp.Reflection.annotations(kotlin.reflect.KClass)'

[INFO] 
[INFO] Results:
[INFO] 
[ERROR] Errors: 
[ERROR]   'java.util.List io.kotest.mpp.Reflection.annotations(kotlin.reflect.KClass)'
[INFO] 
[ERROR] Tests run: 17, Failures: 0, Errors: 1, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
```