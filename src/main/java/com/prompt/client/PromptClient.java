package com.prompt.client;

import com.prompt.service.PromptOperation;

public class PromptClient {


    public static void main(String[] args) {
        PromptOperation promptOperation=new PromptOperation();
        System.out.println("welcome to prompt site");
        promptOperation.operation();
    }
}
