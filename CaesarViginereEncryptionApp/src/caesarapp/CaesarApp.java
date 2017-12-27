package caesarapp;
public class CaesarApp {
    
    public static int ALPHABET_POWER = 26;
    public static String caesar(String enText,int key,int action) //1- шифруем, -1 -дешифруем
    {
        String output = "";
        for(int i=0;i<enText.length();i++)
        {
            char startChar = 'a';
            if(Character.isUpperCase(enText.charAt(i))) startChar = 'A';
            int newPos = (enText.charAt(i) - startChar + action * key + ALPHABET_POWER) % ALPHABET_POWER;
            output += (char)(newPos + startChar);
        }
        return output;
    }
    
    public static String vigenere(String enText,String keyWord,int action) //1 - шифр 2 - дешифр
    {
        String output = "";
        int difLength = enText.length()-keyWord.length();
        for(int i=0;i<difLength;i++)
            keyWord += keyWord.charAt(i % keyWord.length());
        for(int i=0;i<enText.length();i++)
        {
            char startChar = 'a';
            if(Character.isUpperCase(enText.charAt(i))) startChar = 'A';
            int newPos = (enText.charAt(i) - startChar + action * (keyWord.charAt(i) - startChar) + ALPHABET_POWER) % ALPHABET_POWER;
            output += (char)(newPos + startChar);
        }
        return output;
    }
  

    public static void main(String[] args) {
        //шифр цезаря
        String message = "Vova";
        String code = caesar(message,10,1);
        System.out.println(code);
        String decode = caesar(code,10,-1);
        System.out.println(decode);

       //шифр виженера
//
//        String message = "ATTACKATDAWN";
//        String keyWord = "LEMON";
//        String code = vigenere(message,keyWord,1);
//        System.out.println(code);
//        String decode = vigenere(code,keyWord,-1);
//        System.out.println(decode);
    }
    
}
