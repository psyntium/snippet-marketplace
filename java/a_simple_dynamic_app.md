## A simple Java app that loads libraries dynamically 

An app that demonstrates how to load .jar files at runtime. By default, the Java runtime only has the standard libraries. This 
sample extends the available libraries by adding additional .jar files at runtime. 

To import another library, start your code with this syntax: 

```java
jar@http://repo1.maven.org/maven2/org/apache/airavata/levenshtein-distance-service/0.2-incubating/levenshtein-distance-service-0.2-incubating.jar
```

For security reasons, you can only import .jar files hosted at `maven.org`.

To import more than one .jar file, separate the filenames with semicolons: 

```java
jar@http://repo1.maven.org/.../jar1.jar;http://repo1.maven.org/.../jar2.jar
```

If you're curious, you can learn more about the mathematical underpinnings of the service in 
[the Wikipedia article on Levenshtein distances](https://en.wikipedia.org/wiki/Levenshtein_distance). 
