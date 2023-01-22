package algorithms.palindrom;

public class Palindrom {

    //kajak
    //kobyłamamałybok
    //kaftan
    //BCDSCB

    public static void main(String[] args) {
        System.out.println("Is palindrom: " + isPalindrom("kobyłamamałybok"));
    }

    private static boolean isPalindrom(String text) {
        int i = 0;
        int j = text.length() - 1;

        while (i < text.length() / 2) {
            if (text.charAt(i) != text.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

}
