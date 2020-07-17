package com.prompt.dao;

import com.prompt.model.Prompt;
import redis.clients.jedis.Jedis;

import java.util.*;

public class PromptDao {
    Jedis jedis = new Jedis("localhost");

// for saving the data after adding, updating or deleting any prompts.
    public void saveData(){
        jedis.bgsave();
    }

    // listing all the version of a prompt......................................................................
    public Prompt getPrompts(String promptId) {
        Prompt prompt=new Prompt();
        Map<String,String> hm=new HashMap<String,String>();
        String key="db:"+promptId;
        hm = jedis.hgetAll(key);                     // getting all the data of prompts -name and languages
        prompt.setId(promptId);
        for(Map.Entry<String,String> m:hm.entrySet()){    // for setting name and languages in a prompt
            String key1=m.getKey();
            if(key1.equals("name")){
                prompt.setName(m.getValue());
            }else {
               prompt.setLanguage(key1,m.getValue());
            }
        }
        return prompt;
    }

    public void addDescription(Prompt prompt){
        Map<String,String> hm=new HashMap<String,String>();
        hm.put("name",prompt.getName());
        for(Map.Entry<String,List<String>> m:(prompt.getLanguage()).entrySet()){
            hm.put(m.getKey(),m.getValue().get(0));
        }
        String key="db:"+prompt.getId();
        jedis.hmset(key,hm);

    }
    // for adding a prompt....................................................................................
    public boolean addPrompt(Prompt prompt) {
        if(jedis.sismember("db:allprompts",prompt.getId())){   // seeing if the prompt is already available
            return false;
        }else {
            String key = "db:allprompts";
            jedis.sadd(key, prompt.getId());
            addDescription(prompt);
            return true;
        }

    }

// for updating existing prompt....................................................................................
    public boolean updatePrompt(Prompt prompt) {
        if(jedis.sismember("db:allprompts",prompt.getId())){
            addDescription(prompt);
            return true;
        }else{
            return false;
        }
    }
// for deleting existing prompt..................................................................................
    public boolean deletePrompt(String promptId){

        if(jedis.sismember("db:allprompts",promptId)){
            String key="db:"+promptId;
            jedis.del(key);
            jedis.srem("db:allprompts",promptId);
            return true;
        }else{
            return false;
        }
    }
// for viewing all the prompts...................................................................................
    public Map<String,Prompt> allPrompts(){
        Set<String> prompts = new HashSet<String>();
        prompts = jedis.smembers("db:allprompts");   // getting all the prompts ids
        Iterator<String> iterator = prompts.iterator();
        Map<String,Prompt> allprompts=new HashMap<String,Prompt>();
        while (iterator.hasNext()) {                     // for each ids , getting the prompt details
            String id=iterator.next();
            allprompts.put(id,getPrompts(id));
        }
        return allprompts;

    }
}
