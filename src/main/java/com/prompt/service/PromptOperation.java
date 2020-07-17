package com.prompt.service;

import com.prompt.dao.PromptDao;
import com.prompt.model.Prompt;

import java.util.*;

public class PromptOperation {
//creating the object of dao class and scanner class.....................................................
    Scanner sc=new Scanner(System.in);
    PromptDao promptDao=new PromptDao();

// all the operation available for user.......................................................................
    public void operation() {
        System.out.println("enter the choices ");
        System.out.println("1. for adding a new prompt");
        System.out.println("2. viewing all prompts");
        System.out.println("3. view all version of a prompt");
        System.out.println("4. update a prompt");
        System.out.println("5. delete a prompt and its language");
        int choice=sc.nextInt();
        if(choice==1){
            addPrompt();
        }else if(choice==2){
            allPrompts();
        }else if(choice==3){
            promptVersion();
        }else if(choice==4){
            updatePrompt();
        }else if(choice==5){
           deletePrompt();
        }

    }

    // gives all the prompts version in different language.......................................................
    public void promptVersion(){
        System.out.println("enter prompt name");
        String promptName=sc.next();
        Prompt prompt=new Prompt();
        prompt=promptDao.getPrompts(promptName);
        System.out.println(prompt.getLanguage());
        operation();
    }

    // adding a new prompt with its language.....................................................................
    public void addPrompt(){
        Prompt prompt=new Prompt();
        System.out.println("enter prompt name");
        prompt.setName(sc.next());
        System.out.println("enter prompt id");
        prompt.setId(sc.next());
        System.out.println("enter prompt language");
        String language=sc.next();
        sc.nextLine();
        System.out.println("enter prompt description");
        String description=sc.nextLine();
        prompt.setLanguage(language,description);
        boolean result=promptDao.addPrompt(prompt);
        operation();
    }
    // for updating an existing prompt...........................................................................
    public void updatePrompt(){
        Prompt prompt=new Prompt();
        System.out.println("enter prompt name");
        prompt.setName(sc.next());
        System.out.println("enter prompt id");
        prompt.setId(sc.next());
        System.out.println("enter prompt language");
        String language=sc.next();
        sc.nextLine();
        System.out.println("enter prompt description");
        String description=sc.nextLine();
        prompt.setLanguage(language,description);
        boolean result=promptDao.updatePrompt(prompt);
        System.out.println(result);
        operation();
    }


    public void deletePrompt(){
        System.out.println("enter prompt name");
        boolean ans=promptDao.deletePrompt(sc.next());
        System.out.println(ans);
        operation();
    }
    public void allPrompts(){
        Map<String,Prompt> allprompts=new HashMap<String,Prompt>();
        allprompts=promptDao.allPrompts();
        System.out.println(allprompts.size());
    }
}
