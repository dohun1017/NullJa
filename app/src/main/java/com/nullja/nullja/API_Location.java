package com.nullja.nullja;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class API_Location{

    public String [] name = new String [30];
    public String[] lat = new String[30];
    public String[] lng = new String[30];
    public String[] vicinity = new String[30];

    public API_Location() {}
    public String split(String line,String word1,String word2) { // 태그를 기준으로 단어 파싱
        if(line.contains(word1))
            return line.split("("+word1+")")[1].split("("+word2+")")[0];
        return "";
    }

    public String[] parser(String[] data,String word1,String word2) throws IOException{ // area와 word를 이용해서 data배열에 값을 저장
        String urlstr = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?key=AIzaSyC1epfuVA9Tl09Rz7z-VALUlLhVhPTrXlU&pagetoken=CtQDwQEAAA-o7KsJlp24DiDDT6hk8L8kfulBsuqSIwr2-pQJdqJssBzY2g7Fw2JZKJJpyvNUFeces16SFHpYFjW3-zMIM8ws-FVB6F6EWDSmb0msZVUFnZ4Mwp6EEbIf4PmYxUhHhK-7tp01gpJsIrAgEetFR2sR3gMaiCunfBhVQlGZHffU1mUgKZ6QKKn4ZRMYaeV1S1EcAXOXCVeas5wrWaxMEV8MeXdV9JsoesTOLHbT6_ae7K-le6lb8NszOC4ybLtvJ1vFtj0UZwcGcC7vz9tClUZGRSsiHkhzHqoRpugR_8R5NpeUZn2ScWTuuvYkDkxE9gYuBCAL8ePV5Ix8xQA_yO1dl7PLWHpH_OfM3gN3FoDkHRV-lBwsvM-YiMWKmeBJ8vJ5Mgr46lbAX9rokpWZVMM81pjSxXtldN2nhd-yogBddYa1_BwsFTpQ8HfIli6qDgL1Ii9Wwnuw6suw0_ZyppKGpeBAdx9aWS6XUqPlb7b00_nkVvc2dFpZ2fGPWRMF6FUF2tY4qedxT-fSSauQcGgx3wJN5R98m7D6SMCzcXPD4_CTtW5b0ftJsolSVIVqzRa4h-wCU1safM046v6USbAxcX7TEU4bH-HvIJzKzb8iEhDbq8YpggOWpAR63shd8SEOGhTbKStgX5Du9umj1w9H0KxEBDvFYw";
        URL url = new URL(urlstr);
        HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
        urlconnection.setRequestMethod("GET");
        BufferedReader br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(),"UTF-8"));
        String line="";
        String tmp="";
        int i=0;
        while((line = br.readLine()) != null) {
            tmp+=line.trim();
            if(line.contains("geometry")) {
                String n = split(tmp,word1,word2);
                if(!n.equals("")) {
                    data[i] =n;
                    i++;
                }
                tmp="";
            }
        }
        return data;
    }
    public void get_data() throws IOException {
        this.name = parser(name,"\"name\" : \"","\"");
        this.lat = parser(lat,"lat\" : ",",");
        this.lng = parser(lng,"lng\" : ","}");
        this.vicinity = parser(vicinity,"\"vicinity\" : \"","\"");

    }
    /////////////////////////////////////////////////////////////↓다른곳에서 활용할때//////////////////////////////////////////////////
    public static void main(String[] args) throws Exception{
        API_Location d = new API_Location();//객체 소환
        d.get_data();//데이터를 가져오는 함수
        for(int i=0;i<20;i++) {//그냥 출력
            System.out.println(d.name[i]+"  "+d.lat[i]+"  "+d.lng[i]+"  "+d.vicinity[i]);
        }
    }
}