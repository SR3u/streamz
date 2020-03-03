# streamex
Java 8 Streams and Optionals but with checked Exceptions

## Usage:
Add to your pom.xml a repository:
```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
```
Then a dependency
```xml
<dependency>
  <groupId>com.github.SR3u</groupId>
  <artifactId>streamex</artifactId>
  <version>Tag</version>
</dependency>
```

The rest is pretty similar to usual Stream and Optional API available since Java 1.8,
but you don't have to wrap internals in try-catch blocks.

```java 
// Print the content of all .java files in some folder
Streamex.of(new File("/path/to/some/folder"))
        .filter(File::isDirectory)
        .flatMap(file -> Streamex.of(file.listFiles()))
        .filter(file -> file.getName().endsWith(".java"))
        .map(File::toPath)
        .map(Files::lines) // throws IOException, would require try-cath if used in regular Stream
        .forEach(System.out::println);
```
