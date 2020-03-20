public class Revert {
    public static void main(String[] args) {
        System.out.println(revert(""));
        System.out.println(revert("a"));
        System.out.println(revert("ab"));
        System.out.println(revert("abc"));
        System.out.println(revert("0123456789"));
    }

    public static String revert(String str) {
        int len = str.length();
        if (len >= 2) {
            return str.substring(len - 1) + revert(str.substring(0, len - 1));
        } else {
            return str;
        }
    }
}
