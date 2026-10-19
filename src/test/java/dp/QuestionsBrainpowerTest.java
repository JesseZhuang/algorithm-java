package dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionsBrainpowerTest {
    @Test
    void firstOfficialExample() {
        assertEquals(5L, QuestionsBrainpower.mostPoints(new int[][] {
                {3, 2}, {4, 3}, {4, 4}, {2, 5}
        }));
    }

    @Test
    void secondOfficialExample() {
        assertEquals(7L, QuestionsBrainpower.mostPoints(new int[][] {
                {1, 1}, {2, 2}, {3, 3}, {4, 4}, {5, 5}
        }));
    }

    @Test
    void singleQuestion() {
        assertEquals(8L, QuestionsBrainpower.mostPoints(new int[][] {{8, 1}}));
    }

    @Test
    void solvingFirstQuestionCanBeatSkippingIt() {
        assertEquals(14L, QuestionsBrainpower.mostPoints(new int[][] {
                {10, 1}, {3, 1}, {4, 1}
        }));
    }

    @Test
    void skippingFirstQuestionCanBeatSolvingIt() {
        assertEquals(11L, QuestionsBrainpower.mostPoints(new int[][] {
                {3, 1}, {5, 1}, {1, 1}, {6, 1}
        }));
    }

    @Test
    void jumpPastLastQuestion() {
        assertEquals(10L, QuestionsBrainpower.mostPoints(new int[][] {
                {10, 100_000}, {1, 1}
        }));
    }

    @Test
    void brainpowerOneAllowsSolvingEveryOtherQuestion() {
        assertEquals(8L, QuestionsBrainpower.mostPoints(new int[][] {
                {2, 1}, {3, 1}, {4, 1}, {5, 1}
        }));
    }

    @Test
    void maximumLengthAndPointsRequireLongResult() {
        int[][] questions = new int[100_000][2];
        for (int[] question : questions) {
            question[0] = 100_000;
            question[1] = 1;
        }

        assertEquals(5_000_000_000L, QuestionsBrainpower.mostPoints(questions));
    }
}
