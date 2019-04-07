import java.util.ArrayList;

/**
 * https://leetcode.com/problems/wildcard-matching/
 * Recursive with remember checking
 */
public class WildcardMatching {

    // use this 2 dimension array to remember,
    // mark[si][pi] present s[0-> si] with p[0-> pi] is matched or not
    private int [][] mark;
    public boolean isMatch(String s, String p) {
        this.mark = new int[s.length() + 1][p.length() + 1];
        for (int si = 0; si <= s.length(); si++) {
            for (int pi = 0; pi <= p.length(); pi ++) {
                mark[si][pi] = -1;
            }
        }
        return doMatching(s, p, 0, 0);
    }

    private boolean doMatching(String s, String p, int si, int pi) {
        if (mark[si][pi] != -1) {
            return mark[si][pi] == 1;
        }

        if (pi == p.length()) {
            return si == s.length();
        }

        boolean matched = true;
        if (si == s.length()) {
            // if si is the end, check the remain of p is * or not.
            for (int i = pi; i < p.length(); i++) {
                if (p.charAt(i) != '*') {
                    matched =  false;
                    break;
                }
            }
        }

        else if (p.charAt(pi) == '*') {
            matched = doMatching(s, p, si, pi + 1) || doMatching(s, p, si + 1, pi);
        } else {
            boolean firstMatch = (p.charAt(pi) == s.charAt(si)) || p.charAt(pi) == '?';
            matched = firstMatch && doMatching(s, p, si + 1, pi + 1);
        }
        mark[si][pi] = matched ? 1 : 0;
        return matched;
    }
}



class TestWildcardMatching {
    public static void main(String[] args) {
        var sl = new ArrayList<String>();
        var pl = new ArrayList<String>();
        var expected = new ArrayList<Boolean>();
        String s, p;

        // false
        s = "aa";
        p = "a";
        sl.add(s);
        pl.add(p);
        expected.add(false);

        // true
        s = "aa";
        p = "*";
        sl.add(s);
        pl.add(p);
        expected.add(true);

        // false
        s = "cb";
        p = "?a";
        sl.add(s);
        pl.add(p);
        expected.add(false);

        // true
        s = "adceb";
        p = "*a*b";
        sl.add(s);
        pl.add(p);
        expected.add(true);

        // false
        s = "acdcb";
        p = "a*c?b";
        sl.add(s);
        pl.add(p);
        expected.add(false);

        // false
        s = "aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba";
        p = "a*******b";
        sl.add(s);
        pl.add(p);
        expected.add(false);

        var wm = new WildcardMatching();
        for (var i = 0; i < sl.size(); i++) {
            var matched = wm.isMatch(sl.get(i), pl.get(i));
            System.out.println(matched);
            if (matched != expected.get(i)) {
                System.out.println("Algorithm with shit, wrong at case: (si, pi) " + sl.get(i) + ", " + pl.get(i));
            }
        }
    }
}