package graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * LeetCode 690, medium, tags: hash table, depth-first search, breadth-first search.
 * You have a data structure of employee information, including the employee's unique ID, importance value,
 * and direct subordinates' IDs.
 * <p>
 * You are given an array of employees where:
 * <p>
 * employees[i].id is the ID of the ith employee.
 * employees[i].importance is the importance value of the ith employee.
 * employees[i].subordinates is a list of the IDs of the direct subordinates of the ith employee.
 * Given an integer id that represents an employee's ID, return the total importance value of this employee
 * and all their direct and indirect subordinates.
 * <p>
 * Example 1:
 * <p>
 * Input: employees = [[1,5,[2,3]],[2,3,[]],[3,3,[]]], id = 1
 * Output: 11
 * Explanation: Employee 1 has an importance value of 5 and has two direct subordinates: employee 2 and employee 3.
 * They both have an importance value of 3.
 * Thus, the total importance value of employee 1 is 5 + 3 + 3 = 11.
 * Example 2:
 * <p>
 * Input: employees = [[1,2,[5]],[5,-3,[]]], id = 5
 * Output: -3
 * Explanation: Employee 5 has an importance value of -3 and has no direct subordinates.
 * Thus, the total importance value of employee 5 is -3.
 * <p>
 * Constraints:
 * <p>
 * 1 <= employees.length <= 2000
 * 1 <= employees[i].id <= 2000
 * All employees[i].id are unique.
 * -100 <= employees[i].importance <= 100
 * One employee has at most one direct leader and may have several subordinates.
 * The IDs in employees[i].subordinates are valid IDs.
 */
public class EmployeeImportance {
    // 10ms, 60.3 Mb. O(n) tme, O(n) space. BFS.
    public int getImportanceBFS(List<Employee> employees, int id) {
        Map<Integer, Employee> idToEmployee = new HashMap<>();
        for (Employee e : employees) idToEmployee.put(e.id, e);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(id);
        int result = 0;
        while (!queue.isEmpty()) {
            Employee e = idToEmployee.get(queue.remove());
            result += e.importance;
            for (Integer subordinate : e.subordinates) queue.offer(subordinate);
        }
        return result;
    }

    // 4ms, 43.2 Mb. O(n) tme, O(n) space. DFS.
    public int getImportanceDFS(List<Employee> employees, int id) {
        Map<Integer, Employee> idToEmployee = new HashMap<>();
        for (Employee e : employees) idToEmployee.put(e.id, e);
        return helper(idToEmployee, id);
    }

    private int helper(Map<Integer, Employee> idToEmployee, int id) {
        Employee e = idToEmployee.get(id);
        int result = e.importance;
        for (Integer sub : e.subordinates) result += helper(idToEmployee, sub);
        return result;
    }
}

class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
}
