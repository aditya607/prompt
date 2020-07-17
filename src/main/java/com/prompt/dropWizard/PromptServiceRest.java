package com.prompt.dropWizard;

import com.prompt.dao.PromptDao;
import com.prompt.model.Prompt;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Path("/")
public class PromptServiceRest {
    PromptDao promptDao=new PromptDao();


    @GET
    @Path("prompts/{promptId}") // todo prompt id
    @Produces(MediaType.APPLICATION_JSON)
    public Prompt promptVersion(@PathParam("promptId") String promptId){
        Prompt prompt=new Prompt(); //todo checking
        prompt=promptDao.getPrompts(promptId);
        return prompt;

    }
    @GET
    @Path("allprompts")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String,Prompt> allPrompts(){
        return promptDao.allPrompts();
    }

    @POST
    @Path("addPrompts")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProject(Prompt prompt){
        boolean res=promptDao.addPrompt(prompt);
        promptDao.saveData();
        String result;
        if(res)
            result="added successfully";
        else
            result="not added as prompt alreday exist";
        return Response.status(201).entity(result).build();
    }

    @PUT
    @Path("updatePrompt")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public String updatePrompt(Prompt prompt){
        boolean result=promptDao.updatePrompt(prompt);
        promptDao.saveData();
        if(result){
            return "updated successfully";
        }else{
            return "it need to first exist, then to be updated";
        }
    }

    @DELETE
    @Path("deletePrompt/{promptId}")
    public Response deletePrompt(@PathParam("promptId")String promptId){
        boolean result=promptDao.deletePrompt(promptId);
        promptDao.saveData();
        if(result)
            return Response.status(200).entity("deleted succesfully").build();
        else
            return Response.status(200).entity("not deleted succesfully as not prompt by that name").build();
    }


}
