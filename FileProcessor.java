package com.kriash.mainproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FileProcessor 
{
        private String storeState;
	private String zc;
        private ArrayList<String> StoresInState;
        
	public ArrayList<String> readfile(String store, String state) throws IOException
	{
            StoresInState = new ArrayList<String>();
            try (BufferedReader fileIn = new BufferedReader(new FileReader("PATH_TO_FILE" + store + ".txt"))){
                String currentLine;
                while ((currentLine = fileIn.readLine()) != null){
                    String[] parts = currentLine.split (",");
                    if (parts.length==1 && parts[0] != null){
                        if (parts[0].contains(state)){
                            StoresInState.add(currentLine);
                        }
                    }else {
                        String storeState1loc = parts[parts.length-2];
                        String storeState2loc = parts[parts.length-1];
                        try {
                           if (storeState1loc.contains(state) || storeState2loc.contains(state)){
                                StoresInState.add(currentLine);
                            }
                        }catch (NullPointerException e){
                            //e.printStackTrace();
                        }
                    }
                }
            }catch (IOException e){
                e.printStackTrace();
            }
            return StoresInState;
        }
}