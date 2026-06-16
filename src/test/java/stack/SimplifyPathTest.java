package stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimplifyPathTest {
    @Test void basic() { assertEquals("/home", SimplifyPath.simplifyPath("/home/")); }
    @Test void doubleDot() { assertEquals("/", SimplifyPath.simplifyPath("/../")); }
    @Test void multipleSlashes() { assertEquals("/home/foo", SimplifyPath.simplifyPath("/home//foo/")); }
    @Test void complex() { assertEquals("/c", SimplifyPath.simplifyPath("/a/./b/../../c/")); }
    @Test void root() { assertEquals("/", SimplifyPath.simplifyPath("/")); }
    @Test void deepPath() { assertEquals("/a/b/c/d", SimplifyPath.simplifyPath("/a/b/c/d")); }
    @Test void tripleDotsFile() { assertEquals("/...", SimplifyPath.simplifyPath("/...")); }
    @Test void doubleDotMiddle() { assertEquals("/a/c/e", SimplifyPath.simplifyPath("/a/b/../c/d/../e")); }
    @Test void popBeyondRoot() { assertEquals("/b", SimplifyPath.simplifyPath("/a/../../b")); }
}
