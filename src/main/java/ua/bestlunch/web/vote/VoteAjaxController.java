package ua.bestlunch.web.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.bestlunch.AuthorizedUser;
import ua.bestlunch.model.Restaurant;
import ua.bestlunch.model.Role;
import ua.bestlunch.model.User;
import ua.bestlunch.model.Vote;
import ua.bestlunch.model.to.UserTo;
import ua.bestlunch.service.VoteService;
import ua.bestlunch.util.UserUtil;

import javax.validation.Valid;
import java.net.URI;
import java.util.EnumSet;
import java.util.List;

/**
 * Created by Виктор on 25.12.2016.
 */
@RestController
@RequestMapping(value = "ajax/votes")
//TODO Make AuthorizedUser.getUser()
public class VoteAjaxController {

    @Autowired
    private VoteService service;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vote> getAllByUser(){
        UserTo userTo = AuthorizedUser.get().getUserTo();
        return service.findAllByUser(UserUtil.createFromTo(userTo));
    }

    @RequestMapping(value = "/currentVote", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Vote getCurrentVote(){
        UserTo userTo = AuthorizedUser.get().getUserTo();
        return service.getCurrentVote(UserUtil.createFromTo(userTo));
    }

    @RequestMapping(value = "/{restId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Vote create(@PathVariable int restId){
        UserTo userTo = AuthorizedUser.get().getUserTo();
        return service.addVote(UserUtil.createFromTo(userTo), restId);

    }

    @RequestMapping(value = "/currentVote", method = RequestMethod.DELETE)
    public void deleteCurrentVote(){
        UserTo userTo = AuthorizedUser.get().getUserTo();
        service.cancelVote(UserUtil.createFromTo(userTo));
    }
}
