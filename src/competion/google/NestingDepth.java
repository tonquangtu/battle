package competion.google;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * https://codingcompetitions.withgoogle.com/codejam/round/000000000019fd27/0000000000209a9f
 */
public class NestingDepth {

    public static void main(String[] args) {
        NestingDepth nestingDepth = new NestingDepth();
        nestingDepth.test("0000", "0000");
        nestingDepth.test("101", "(1)0(1)");
        nestingDepth.test("111000", "(111)000");
        nestingDepth.test("1", "(1)");
        nestingDepth.test("021", "0((2)1)");
        nestingDepth.test("312", "(((3))1(2))");
        nestingDepth.test("4", "((((4))))");
        nestingDepth.test("221", "((22)1)");
        nestingDepth.test("2213420001", "((22)1((3(4))2))000(1)");
        //nestingDepth.scanAndSolve();

    }

    public void scanAndSolve() {
        Scanner scanner = new Scanner(System.in);
        int testLength = Integer.parseInt(scanner.nextLine());
        for (int ts = 1; ts <= testLength; ts++) {
            String input = scanner.nextLine();
            String output = solve(input);
            System.out.println(String.format("Case #%s: %s", ts, output));
        }
    }

    public void test(String input, String expect) {
        String output = solve(input);
        System.out.println(String.format("input: %s, output: %s, expect: %s", input, output, expect));
        if (!expect.equals(output)) {
            throw new RuntimeException("Fail in case " + input);
        }
    }

    public String solve(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            int num = toInt(s.charAt(i));
            if (num == 0) {
                stack.push(toChar(num));
            } else {
                insertToCurrentParenthesis(stack, num);
            }
        }
        StringBuilder strBuilder = new StringBuilder();
        while (!stack.isEmpty()) {
            strBuilder.insert(0, stack.pop());
        }
        return strBuilder.toString();
    }

    void insertToCurrentParenthesis(Stack<Character> stack, int num) {
        int nestingCount = 0;
        List<Character> popTemp = new ArrayList<>();
        while (!stack.isEmpty()) {
            Character pop = stack.pop();
            popTemp.add(pop);
            if (pop == ')') {
                nestingCount ++;
                if (nestingCount == num) {
                    break;
                }
            } else if (Character.isDigit(pop)) {
                break;
            }
        }
        if (nestingCount == num) {
            stack.push(toChar(num));
            for (int i = popTemp.size() - 1; i >= 0; i--) {
                stack.push(popTemp.get(i));
            }
        } else {
            if (popTemp.size() > 0) {
                stack.push(popTemp.get(popTemp.size() - 1));
            }
            for (int i = 1; i <= num - nestingCount; i++) {
                stack.push('(');
            }
            stack.push(toChar(num));
            for (int i = 1; i <= num - nestingCount; i++) {
                stack.push(')');
            }
            for (int i = popTemp.size() - 2; i >=0; i--) {
                stack.push(popTemp.get(i));
            }
        }
    }

    char toChar(int num) {
        return (char)(num + '0');
    }

    int toInt(char c) {
        return c - '0';
    }
}
