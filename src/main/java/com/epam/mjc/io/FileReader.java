package com.epam.mjc.io;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;



public class FileReader {
    public Profile getDataFromFile(File file) {
        try(FileInputStream fileInputStream=new FileInputStream(file)) {
            byte[] buffer = new byte[10];
            StringBuilder sb = new StringBuilder();
            while (fileInputStream.read(buffer) != -1) {
                sb.append(new String(buffer));
                buffer = new byte[10];
            }
            fileInputStream.close();
            String content = sb.toString();
            System.out.println(content);
            Map<String, String> map = new HashMap<>();
            String[] arr= content.split("\n");
            for (int i = 0; i < arr.length-1; i++) {
                String[] arr1= arr[i].split(": ");
                map.put(arr1[0],arr1[1]);
            }
            Profile profile=new Profile();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if(Objects.equals(entry.getKey(), "Name"))
                    profile.setName(entry.getValue());
                if(Objects.equals(entry.getKey(), "Age")) {
                    int age = Integer.parseInt(entry.getValue());
                    profile.setAge(age);
                }
                if(Objects.equals(entry.getKey(), "Email"))
                    profile.setEmail(entry.getValue());
                if(Objects.equals(entry.getKey(), "Phone")) {
                    Long phone=Long.parseLong(entry.getValue());
                    profile.setPhone(phone);
                }
            }
            return profile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
