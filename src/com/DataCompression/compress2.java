package com.DataCompression;
import java.util.ArrayList;
import java.util.Scanner;

public class compress2 {
    public static class tag{
        int position = 0, length = 0;
        char nextChar = ' ';

        public tag(int position, int length, char nextChar) {
            this.position = position;
            this.length = length ;
            this.nextChar = nextChar;
        }
    }
    public static ArrayList<tag> compress(String data)
    {
        ArrayList<tag> Tags = new ArrayList<tag>();
        int lastFoundPos = -1;
        String currentWord = "";
        String searchWindow = "";
        int i =0;
        while(i < data.length())
        {
           currentWord+=data.charAt(i);
           System.out.println(currentWord);
           int pos = searchWindow.indexOf(currentWord);
           if(pos==-1){
               char nextChar = data.charAt(i);
               if(lastFoundPos==-1){
                   int position = 0;
                   int length = 0;
                   Tags.add(new tag(position,length,nextChar));
               }
               else {
                   int position = searchWindow.length() - lastFoundPos;
                   int length = currentWord.length() - 1;
                   Tags.add(new tag(position, length, nextChar));
               }
               searchWindow+=currentWord;
               currentWord="";
               lastFoundPos = -1;
           }
           else{
               lastFoundPos = pos;
               if(i == data.length() - 1){
                   System.out.println(currentWord);
                   lastFoundPos = searchWindow.lastIndexOf(currentWord);
                   int position = searchWindow.length() - lastFoundPos;
                   int length = currentWord.length();
                   char nextChar = ' ';
                   Tags.add(new tag(position,length,nextChar));
               }
           }

            i++;
        }
        System.out.println("***************************\nCompressing...");
        return Tags;
    }
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter data u want to compress \n");
        String data = input.nextLine();
        ArrayList<tag> compressedData = compress(data);
        for(int i = 0; i < compressedData.size(); i++)
        {
            System.out.println(compressedData.get(i).position + " " + compressedData.get(i).length + " " +
                    (char) compressedData.get(i).nextChar);
        }
        System.out.println("****************************\nDecompressing...");
        String decompress="";
        for(int k=0; k<compressedData.size();k++){
            int position=compressedData.get(k).position;
            int length=compressedData.get(k).length;
            char nextChar =compressedData.get(k).nextChar;
            if(position==0 && length==0){
                decompress+=nextChar;
                continue;
            }
            for(int j=0; j<length;j++){
                decompress+=decompress.charAt(decompress.length()- position);
            }
            decompress+=nextChar;
        }
        System.out.println(decompress);
    }
}
