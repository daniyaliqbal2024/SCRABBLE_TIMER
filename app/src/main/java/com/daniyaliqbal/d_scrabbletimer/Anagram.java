package com.daniyaliqbal.d_scrabbletimer;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Anagram {
    private static final List<String> WORD= new ArrayList<>();
    private static boolean LOADED= false;

    public static boolean isLoaded()
    {
        return LOADED;
    }

    public static void loadWords(Context context)
    {
        BufferedReader buff= null;
        try{
            buff = new BufferedReader(new InputStreamReader(context.getAssets().open("scrabble.txt")));
            String line = null;

            while ((line = buff.readLine())!= null){
                WORD.add(line.toUpperCase());
            }

        } catch (IOException e) { ;
        }
        finally {
            if(buff!= null){
                try{
                    buff.close();
                }catch (IOException e){

                }
            }
        }
    }

       public static boolean sameLetters(String a, String b)
       {
           if (a== null)
           {
              return b== null;
           }
           if (b== null)
           {
               return false;
           }

           char[] left= a.toCharArray();
           char[] right= b.toCharArray();

           Arrays.sort(left);
           Arrays.sort(right);

           return Arrays.equals(left, right);
       }

       public static List<String> listWords(String letters){
        List<String> list= new ArrayList<>();

        for (String word: WORD)
        {
            if(sameLetters(word, letters))
                list.add(word);
        }

        return list;
       }

}
