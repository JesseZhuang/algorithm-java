package tree;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tree.PopulatingNextRightPointersII.Node;

import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class PopulatingNextRightPointersIITest {
    static Stream<Named<UnaryOperator<Node>>> connectors() {
        return Stream.of(
                Named.of("BFS", PopulatingNextRightPointersII::connect),
                Named.of("constant space", PopulatingNextRightPointersII::connectConstantSpace));
    }

    @ParameterizedTest
    @MethodSource("connectors")
    void returnsNullForEmptyTree(UnaryOperator<Node> connect) {
        assertNull(connect.apply(null));
    }

    @ParameterizedTest
    @MethodSource("connectors")
    void terminatesSingleNodeLevel(UnaryOperator<Node> connect) {
        Node root = new Node(1);
        root.next = new Node(2);

        assertSame(root, connect.apply(root));
        assertNull(root.next);
    }

    @ParameterizedTest
    @MethodSource("connectors")
    void linksSparseLevelsAcrossParents(UnaryOperator<Node> connect) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        Node first = root.left.left = new Node(4);
        Node middle = root.left.right = new Node(5);
        Node last = root.right.right = new Node(6);
        Node leftLeaf = first.right = new Node(7);
        Node rightLeaf = last.left = new Node(8);
        last.next = new Node(9);

        assertSame(root, connect.apply(root));
        assertNull(root.next);
        assertSame(root.right, root.left.next);
        assertNull(root.right.next);
        assertSame(middle, first.next);
        assertSame(last, middle.next);
        assertNull(last.next);
        assertSame(rightLeaf, leftLeaf.next);
        assertNull(rightLeaf.next);
    }

    @ParameterizedTest
    @MethodSource("connectors")
    void terminatesEveryLevelOfOneSidedTree(UnaryOperator<Node> connect) {
        Node root = new Node(1);
        root.right = new Node(2);
        root.right.right = new Node(3);

        assertSame(root, connect.apply(root));
        assertNull(root.next);
        assertNull(root.right.next);
        assertNull(root.right.right.next);
    }
}
