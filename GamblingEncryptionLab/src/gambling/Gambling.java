package gambling;

import java.util.Random;

public class Gambling {

    public static void main(String[] args) {
        String message = "vovavova";
        String gammaKey = generateGammaKey(message.length()*16);
        String cipher = encrypt(message,gammaKey);
        System.out.println("KEY : "+ gammaKey);
        System.out.println("CIPHER : "+binaryToString(cipher));
        String decryptedMessage = decrypt(cipher,gammaKey);
        decryptedMessage = binaryToString(decryptedMessage);
        System.out.println(decryptedMessage);
    }
    
    public static String generateGammaKey(int bitLength)
    {
        Random rand = new Random();
        String output = "";
        for(int i=0;i<bitLength;i++)
            output += String.valueOf(rand.nextInt(2));
        return output;
    }
    
    public static String encrypt(String message,String gammaKey)
    {
        return XOR(toBinary(message),gammaKey);
    }
    
    public static String decrypt(String binaryCipher,String gammaKey)
    {
        return XOR(binaryCipher,gammaKey);
    }
    
    public static String toBinary(String input)
    {
        String binaryOutput = "";
        for(int i = 0; i < input.length();i++)
        {
            String binaryChar = Integer.toBinaryString(input.charAt(i));
            while (binaryChar.length() < 16)
                binaryChar = "0" + binaryChar;
            binaryOutput += binaryChar;
        }
        return binaryOutput;
    }
    
    public static String binaryToString(String binaryInput) // в символе будет по 2 байта ,длина входа кратна 16
    {
        String output = "";
        for(int i = 0;i<binaryInput.length();i+=16)
        {
            int number = 0;
            String binaryNumber = binaryInput.substring(i,i+16);
            for(int j = binaryNumber.length()-1;j>=0;j--)
            {
                int digit = binaryNumber.charAt(j) - '0';
                number += digit * Math.pow(2,binaryNumber.length() - 1 - j);
            }
            output += (char)number;
        }
        return output;
    }
    
    public static String XOR(String a,String b) // a и b одинаковой длины
    {
        String output = "";
        for(int i=0;i<a.length();i++)
        {
            if(a.charAt(i) == b.charAt(i))
                output += '0';
            else output += '1';
        }
        return output;
    }
}
