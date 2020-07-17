package com.prompt.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Prompt {
    private String name;
    private String id;

    private Map<String, List<String>> language= new HashMap<String, List<String>>();

    public void setLanguage(String lan,String desc){
        List<String> description=new ArrayList<String>();
        description.add(desc);
        language.put(lan,description);
    }
    public Map<String, List<String>> getLanguage(){
        return language;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
