package me.cocodrum.algorithm.leetcode;
public class Solution {
    public String addBinary(String a, String b) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if (a==null || a.length()==0) return b;
        if (b==null || b.length()==0) return a;
        
        String longer=a, shorter=b;
        if (a.length() < b.length()) {
            longer = b;
            shorter = a;
        }
        
        int n = longer.length()+1;
        char[] c = new char[n];
        copy(longer.toCharArray(), c);
        add(c, shorter.toCharArray());
        
        int offset = 0;
        if (c[0] == '0') {
            offset = 1;
        }
        return new String(c, offset, n-offset);
    }
    
    private void copy(char[] src, char[] dest) {
        for (int i=0; i<src.length; i++) {
            dest[i+1] = src[i];
        }
        dest[0] = '0';
    }
    
    private void add(char[] a, char[] b) {
        int carry=0;
        int k = a.length-b.length;
        for (int i=b.length-1; i>=0; i--) {
            int t = carry + b[i]-'0' + a[i+k]-'0';
            carry = t>1 ? 1 : 0;
            a[i+k] = (char)(t%2 + '0');
        }
        if (carry == 0) return;
        int i=k-1;
        while(true) {
            if (a[i] == '0') {
                a[i] = '1';
                break;
            } else {
                a[i--] = '0';
            }
        }
    }
    
    public static void main(String[] args) {
        String a = "11";
        String b = "1";
        String c = new Solution().addBinary(a, b);
        
        System.out.println(c);
    }
}