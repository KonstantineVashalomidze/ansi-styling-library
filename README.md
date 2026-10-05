# ansi-styling-library

A zero-dependency Java library for styling terminal text with colors and attributes through a fluent API.

```java
foreground(RED).bold().apply("Error");
```

## Features

* Colors (8 foreground, 8 background) and the four attributes (bold, italic, underline, strikethrough)
* Fluent, chainable API
* Zero dependencies
* Respects __NO_COLOR__, with a __FORCE_COLOR__ override and automatic detection of non-terminal output
* Boxes around text, sized by visible width so styled text lines up

## Installation

Add the __JitPack__ repository and following  dependency to your `pom.xml`

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.KonstantineVashalomidze</groupId>
        <artifactId>ansi-styling-library</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```





