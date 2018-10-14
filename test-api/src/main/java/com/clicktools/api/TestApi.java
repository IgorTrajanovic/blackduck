package com.clicktools.api;

import com.clicktools.model.Test;
import com.clicktools.model.TestDao;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("test")
@Produces(MediaType.TEXT_PLAIN)
public class TestApi {
    @Context
    public ResourceContext resourceContext;

    @GET
    @Path("/list")
    public String test() {
        TestDao dao = new TestDao();
        List<Test> testList = dao.selectTest();
        String str = "";

        try {
            ObjectMapper mapper = new ObjectMapper( );
            str = mapper.writeValueAsString(testList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
}
