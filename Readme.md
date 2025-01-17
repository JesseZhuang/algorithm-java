## Setup

Use IntelliJ-Idea and gradle wrapper (fixing gradle to a specific version).

Run gradle wrapper task from gradle tool panel. First time it will generate the folder and files below

```bash
gradle/
    gradle-wrapper.jar
    gradle-wrapper.properties
gradlew
gradlew.bat
```

Delete `.gradle/` folder and run gradle wrapper task again. This will download the correct version of gradle to use for
the project.

Open project from existing sources, with IntelliJ 2020, two modules were created for `main` and `test`. The `test`
module depends on module `algorithm-java.main`.

On Windows in 2022, gradle build fails with some weird slf4j error. Just create a new gradle java project. It used
latest gradle wrapper (7.6.1 version).

### Setup Github Codespace

- install extension pack for java for gradle
- install debugger for java to run/debug
- may need to switch java to standard mode when seeing "Run/Debug feature requires Java language server to run in
  Standard mode. Do you want to switch it to Standard mode now?"

```
 $ java -version
openjdk version "1.8.0_352"
OpenJDK Runtime Environment (Temurin)(build 1.8.0_352-b08)
OpenJDK 64-Bit Server VM (Temurin)(build 25.352-b08, mixed mode)
```

## Algorithm Practice Websites

1. https://www.lintcode.com/problem/892/
2. https://practice.geeksforgeeks.org/problems/alien-dictionary/1

## Run Unit Tests

Right click any test and run. For IntelliJ 2020, in the bottom run console (output panel), at top left corner, click the
check mark button to show/hide passed tests. Click the forbidden symbol to show/hide ignored tests.

## General Tips to Avoid Bugs

1. collection empty check
1. array index boundary check
1. system invariant check: keep some states in order or updated in dynamic programming
1. object fields (set to null to avoid loitering)
1. use short variable names to save time and avoid time, explain in real code will use longer var name for readability

## Knowledge

### DFA vs NFA

https://www.geeksforgeeks.org/difference-between-dfa-and-nfa/
