import java.util.*;
public class caesar
{

    static final String ALPHA="ABCDEFGHIJKLMNOPQRSTUVQXYZ";
    static final String alpha="abcdefghijklmnopqrstuvwxyz";

    //Function to encrypt the plaintext
    public static String encryption(String plain,int key){
        int n=plain.length();
        String c=""; //String to store the encrypted data

        for(int i=0;i<n;i++){
            char b;
            char a=plain.charAt(i);
            int x;

            //To check if character is in UpperCase
            if(Character.isUpperCase(a)){
                x=ALPHA.indexOf(a);
                if(x!=-1){
                    x=(x+key)%26;//To produce the character according to the key
                    if(x<0){
                        x=x+26;
                    }
                }
                b=ALPHA.charAt(x);
                c=c+b;
            }

            //To check if character is in LowerCase
            else if(Character.isLowerCase(a)){
                x=alpha.indexOf(a);
                if(x!=-1){
                    x=(x+key)%26;//To produce the character according to the key
                    if(x<0){
                        x=x+26;
                    }
                }
                b=alpha.charAt(x);
                c=c+b;
            }
            
            //To check if the plaintext contains any special character
            else{
                c="Invalid";
            }
        }

        return c;
    }


    //Function to decrypt the ciphertext
    public static String decryption(String e,int key){
        int n=e.length();

        String p="";//String to store the decrypted data

        for(int i=0;i<n;i++){
            char b;
            char a=e.charAt(i);
            int x;

            //To check if character is in UpperCase
            if(Character.isUpperCase(a)){
                x=ALPHA.indexOf(a);
                if(x!=-1){
                    x=(x-key)%26;//To produce the character according to the key
                    if(x<0){
                        x=x+26;
                    }
                }
                b=ALPHA.charAt(x);
                p=p+b;
            }

            //To check if character is in LowerCase
            else if(Character.isLowerCase(a)){
                x=alpha.indexOf(a);
                if(x!=-1){
                    x=(x-key)%26;//To produce the character according to the key
                    if(x<0){
                        x=x+26;
                    }
                }
                b=alpha.charAt(x);
                p=p+b;
            }
            
            //To check if  any special characters are present
            else{
                p="Invalid";
            }
        }

        return p;
        
    }

    public static void main(String [] args){

        Scanner sc=new Scanner(System.in);

        //Input the plaintext from the user
        System.out.println("Enter the text you want to encrypt: ");
        String plain=sc.nextLine();

        //Input the key from the user
        System.out.println("Enter the key for encryption & decryption: ");
        int key=sc.nextInt();

        //Calling the encryption function and print the ciphertext
        String e=encryption(plain,key);
        System.out.println("Encrypted text or the ciphertext is: "+e);

        //Calling the decryption function and print the plaintext 
        String d=decryption(e,key);
        System.out.println("Decrypted text or the plaintext is: "+d);

    }//end of main

}//end of class