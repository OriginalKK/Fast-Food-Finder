package com.kriash.mainproject;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    
    private String[] output;
    private String[] output1;
    private String[] output2;
    private String[] output3;
    private String[] output4;
    private String[] output5;
    private String[] output6;
    
    public String[] StoresAndZipFromGUI (int[] A, String s) throws IOException{
        ZipToState zts = new ZipToState();
        String state = zts.ziptostate(s);
        FileProcessor fileprocessor = new FileProcessor();
        StateToSameZipcode stsz = new StateToSameZipcode();
        output = new String[0];
        output1 = new String[0];
        output2 = new String[0];
        output3 = new String[0];
        output4 = new String[0];
        output5 = new String[0];
        output6 = new String[0];
        if (A[0] == 1){
            String store1 = "mcdonalds";
            ArrayList<String> s1 = fileprocessor.readfile(store1, state);  //same state list
            ArrayList<String> o1 = stsz.sameZip (s1, store1, s);   //same zip code
            output1 = o1.toArray (new String[o1.size()]);
        }
        if (A[1] == 1){
            String store2 = "burgerking";
            ArrayList<String> s2 = fileprocessor.readfile(store2, state);
            ArrayList<String> o2 = stsz.sameZip (s2, store2, s);
            output2 = o2.toArray (new String[o2.size()]);
        }
        if (A[2] == 1){
            String store3 = "wendys";
            ArrayList<String> s3 = fileprocessor.readfile(store3, state);
            ArrayList<String> o3 = stsz.sameZip (s3, store3, s);
            output3 = o3.toArray (new String[o3.size()]);
        }
        if (A[3] == 1){
            String store4 = "pizzahut";
            ArrayList<String> s4 = fileprocessor.readfile(store4, state);
            ArrayList<String> o4 = stsz.sameZip (s4, store4, s);
            output4 = o4.toArray (new String[o4.size()]);
        }
        if (A[4] == 1){
            String store5 = "traderjoes";
            ArrayList<String> s5 = fileprocessor.readfile(store5, state);
            ArrayList<String> o5 = stsz.sameZip (s5, store5, s);
            output5 = o5.toArray (new String[o5.size()]);
        }
        if (A[5] == 1){
            String store6 = "wholefoods";
            ArrayList<String> s6 = fileprocessor.readfile(store6, state);
            ArrayList<String> o6 = stsz.sameZip (s6, store6, s);
            output6 = o6.toArray (new String[o6.size()]);
        }
        String[] set1 = concatenate (output1, output2);
        String[] set2 = concatenate (output3, output4);
        String[] set3 = concatenate (output5, output6);
        String[] set4 = concatenate (set1, set2);
        output = concatenate (set4, set3);
        System.out.println (output.length);
        return output;
    }
    public String[] concatenate (String[] A, String[] B){
        String[] C = new String[A.length+B.length];
        System.arraycopy (A, 0, C, 0, A.length);
        System.arraycopy (B, 0, C, A.length, B.length);
        return C;
    }
    public String[] StoresAndAddFromGUI (int[] A, String s) throws IOException, Exception{
        Address ad = new Address(s);
        String state = ad.getState();
        FileProcessor fileprocessor = new FileProcessor();
        String[] addressArray = new String[1];
        addressArray[0] = s;
        DistanceCalculator dc = new DistanceCalculator ();
        for (int i = 0; i < A.length; i++){
            System.out.println(A[i]);
        }
        output1 = new String[0];
        output2 = new String[0];
        output3 = new String[0];
        output4 = new String[0];
        output5 = new String[0];
        output6 = new String[0];
        if (A[0] == 1){
            String store1 = "mcdonalds";
            ArrayList<String> s1 = fileprocessor.readfile(store1, state);
            double[] d1 = dc.distance (addressArray, s1);
            ArrayList<String> o1 = dc.sortdistance (d1, s1);
            output1 = o1.toArray (new String[o1.size()]);
        }
        if (A[1] == 1){
            String store2 = "burgerking";
            ArrayList<String> s2 = fileprocessor.readfile(store2, state);
            double[] d2 = dc.distance (addressArray, s2);
            ArrayList<String> o2 = dc.sortdistance (d2, s2);
            output2 = o2.toArray (new String[o2.size()]);
        }
        if (A[2] == 1){
            String store3 = "wendys";
            ArrayList<String> s3 = fileprocessor.readfile(store3, state);
            double[] d3 = dc.distance (addressArray, s3);
            ArrayList<String> o3 = dc.sortdistance (d3, s3);
            output3 = o3.toArray (new String[o3.size()]);
        }
        if (A[3] == 1){
            String store4 = "pizzahut";
            ArrayList<String> s4 = fileprocessor.readfile(store4, state);
            double[] d4 = dc.distance (addressArray, s4);
            ArrayList<String> o4 = dc.sortdistance (d4, s4);
            output4 = o4.toArray (new String[o4.size()]);
        }
        if (A[4] == 1){
            String store5 = "traderjoes";
            ArrayList<String> s5 = fileprocessor.readfile(store5, state);
            double[] d5 = dc.distance (addressArray, s5);
            ArrayList<String> o5 = dc.sortdistance (d5, s5);
            output5 = o5.toArray (new String[o5.size()]);
        }
        if (A[5] == 1){
            String store6 = "wholefoods";
            ArrayList<String> s6 = fileprocessor.readfile(store6, state);
            double[] d6 = dc.distance (addressArray, s6);
            ArrayList<String> o6 = dc.sortdistance (d6, s6);
            output6 = o6.toArray (new String[o6.size()]);
        }
        String[] set1 = concatenate (output1, output2);
        String[] set2 = concatenate (output3, output4);
        String[] set3 = concatenate (output5, output6);
        String[] set4 = concatenate (set1, set2);
        output = concatenate (set4, set3);
        return output;
    }
}
