package com.kriash.mainproject;

import java.util.ArrayList;

public class StateToSameZipcode extends FileProcessor{
    private String inputZip;
    private String noPhoneOrCoord;
    private String addressZip;
    private ArrayList<String> StoresInZip;

    public ArrayList<String> sameZip(ArrayList<String> s, String store, String zip) {
        inputZip = zip;
        StoresInZip = new ArrayList<String>();
        for (int i = 0; i < s.size();i++){
            noPhoneOrCoord = "";
            if ("mcdonalds".equals(store) || "burgerking".equals(store) || "wendys".equals(store) || "pizzahut".equals(store)){
                String[] parts = s.get(i).split ("(?<=,)");
                parts[parts.length-1] = "";
                parts[0] = "";
                parts[1] = "";
                parts[2] = "";
                parts[3] = "";
                for (int j = 0; j < parts.length-1; j++){
                    noPhoneOrCoord = noPhoneOrCoord + parts[j];
                }
                noPhoneOrCoord = noPhoneOrCoord.replace ("\"", "");
                Address ad = new Address (noPhoneOrCoord);
                String zipcode = ad.getZipcode();
                if (zipcode != null){
                    if (zipcode.contains(inputZip)){
                        StoresInZip.add(s.get(i));
                    }
                }
            }else {
                String[] parts = s.get(i).split ("(?<=,)");
                addressZip = parts[parts.length-2];
                if (addressZip.contains(zip)){
                    StoresInZip.add(s.get(i));
                }
            }
        }
        return StoresInZip;
    }  
}
