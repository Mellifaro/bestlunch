package ua.bestlunch.web.user;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ua.bestlunch.service.UserService;
import ua.bestlunch.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ua.bestlunch.UserTestData.*;

/**
 * Created by Виктор on 30.11.2016.
 */
public class AdminRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminRestController.REST_URL + "/";

    @Autowired
    protected UserService service;

    @Test
    public void testGet() throws Exception{
        mockMvc.perform(get(REST_URL + ADMIN_ID))
                //.MockHttpServletRequestBuilder
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(ADMIN));
    }

    public void testGetNotFound(){

    }

    public void testGetByEmail(){

    }

    public void testDelete(){

    }

    public void testDeleteNotFound(){

    }

    public void testGetUnAuth(){

    }

    public void testGetForbidden(){

    }

    public void testUpdate(){

    }

    public void testCreate(){

    }

    public void testGetAll(){

    }
}
