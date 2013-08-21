/**
 * @(#)BigInteger.java, Aug 19, 2013.
 */
package me.cocodrum.algorithm.bigint;

import java.util.regex.Pattern;

/**
 * @author xuhongfeng
 */
public class BigInteger {
    private static final int BASE = 1000;

    private static final int DIGITS = 3;

    private static final Pattern PATTERN = Pattern.compile("^-?\\d+$");

    public static final BigInteger ZERO = BigInteger.valueOf("0");

    private int[] values = new int[100];

    private int size = 1;

    private boolean positive = true;

    public static BigInteger valueOf(String str) {
        if (!validate(str)) {
            throw new IllegalArgumentException("str = " + str);
        }
        if (str.length() == 0) {
            return BigInteger.ZERO;
        }
        BigInteger ret = new BigInteger();
        char[] chars = str.toCharArray();
        int start = 0, end = chars.length - 1;
        if (chars[0] == '-') {
            ret.positive = false;
            start++;
        }
        int i = ret.values.length - 1;
        while (start <= end) {
            int e = end, s = end - DIGITS + 1;
            if (s < start) {
                s = start;
            }
            ret.values[i--] = parseChars(chars, s, e);
            end = s - 1;
        }
        ret.size = ret.values.length - i - 1;
        return ret;
    }

    private static int parseChars(char[] chars, int s, int e) {
        int v = 0;
        while (s <= e) {
            v = 10 * v + (chars[s++] - '0');
        }
        return v;
    }

    @Override
    public String toString() {
        if (size==1 && values[values.length-1] == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        if (!this.positive) {
            sb.append("-");
        }
        for (int i = values.length - size; i < values.length; i++) {
            if (i == values.length - size) {
                sb.append(values[i]);
            } else {
                sb.append(String.valueOf(BASE+values[i]).toCharArray(), 1, DIGITS);
            }
        }
        return sb.toString();
    }

    private static final boolean validate(String s) {
        return PATTERN.matcher(s).matches();
    }

    public static BigInteger add(BigInteger v1, BigInteger v2) {
        BigInteger ret = new BigInteger();
        if (v1.positive == v2.positive) {
            ret.positive = v1.positive;
            innerAdd(v1, v2, ret);
        } else {
            int cmp = compare(v1, v2);
            BigInteger big = v1, small = v2;
            if (cmp < 0) {
                big = v2;
                small = v1;
            }
            ret.positive = big.positive;
            innerSub(big, small, ret);
        }
        return ret;
    }

    public static BigInteger sub(BigInteger v1, BigInteger v2) {
        v2.positive = !v2.positive;
        BigInteger ret = add(v1, v2);
        v2.positive = !v2.positive;
        return ret;
    }

    private static void innerAdd(BigInteger v1, BigInteger v2, BigInteger ret) {
        int len = v1.values.length;
        int size = max(v1.size, v2.size);
        int i = len - 1;
        int carry = 0;
        while (len - i - 1 < size || carry == 1) {
            ret.values[i] = v1.values[i] + v2.values[i] + carry;
            if (ret.values[i] >= BASE) {
                ret.values[i] -= BASE;
                carry = 1;
            } else {
                carry = 0;
            }
            i--;
        }
        ret.size = len - 1 - i;
        if (ret.size == 0) {
            ret.size = 1;
        }
    }

    private static int max(int a, int b) {
        return a > b ? a : b;
    }

    private static void innerSub(BigInteger v1, BigInteger v2, BigInteger ret) {
        int borrow = 0, len = v1.values.length, i = len - 1;
        int size = 1;
        while (len - i - 1 < v1.size) {
            ret.values[i] = v1.values[i] - v2.values[i] - borrow;
            if (ret.values[i] < 0) {
                ret.values[i] += BASE;
                borrow = 1;
            } else {
                borrow = 0;
            }
            if (ret.values[i] > 0) {
                size = len - i;
            }
            i--;
        }
        ret.size = size;
    }

    private static int compare(BigInteger v1, BigInteger v2) {
        if (v1.size > v2.size) {
            return 1;
        } else if (v1.size < v2.size) {
            return -1;
        }
        for (int i = v1.values.length - v1.size; i < v1.values.length; i++) {
            if (v1.values[i] > v2.values[i]) {
                return 1;
            } else if (v1.values[i] < v2.values[i]) {
                return -1;
            }
        }
        return 0;
    }
    public static BigInteger mul(BigInteger v1, BigInteger v2) {
        BigInteger ret = new BigInteger();
        int len = v1.values.length;
        
        boolean oldV1Positive = v1.positive, oldV2Positive = v2.positive;
        v1.positive = true;
        v2.positive = true;
        for (int i=len-1; i>=len-v2.size; i--) {
            BigInteger t = mulInt(v1, v2.values[i]);
            leftShift(t, len-i-1);
            ret = add(ret, t);
        }
        v1.positive = oldV1Positive;
        v2.positive = oldV2Positive;
        ret.positive = v1.positive == v2.positive;
        return ret;
    }

    private static BigInteger mulInt(BigInteger v, int a) {
        BigInteger ret = new BigInteger();
        int len=v.values.length, carry=0;
        
        int i = len-1;
        for (; i>=len-v.size || carry>0; i--) {
            int t = v.values[i] * a + carry;
            if (t >= BASE) {
                carry = t/BASE;
                t = t % BASE;
            } else {
                carry = 0;
            }
            ret.values[i] = t;
        }
        ret.positive = v.positive;
        ret.size = len-i - 1;
        if (ret.size == 0) {
            ret.size = 1;
        }
        return ret;
    }

    private static void leftShift(BigInteger v, int k) {
        int len = v.values.length;
        for (int i=len-v.size; i<len; i++) {
            v.values[i-k] = v.values[i];
        }
        for (int i=len-k; i<len; i++) {
            v.values[i] = 0;
        }
        v.size += k;
    }

    public static void main(String[] args) {
        String a  = "-1000";
        String b = "-200";
        String c = BigInteger.mul(BigInteger.valueOf(a),
                BigInteger.valueOf(b)).toString();
        System.out.println(c);
    }
}
