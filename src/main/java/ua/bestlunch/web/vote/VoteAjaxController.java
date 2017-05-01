package ua.bestlunch.web.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.bestlunch.AuthorizedUser;
import ua.bestlunch.model.User;
import ua.bestlunch.model.Vote;
import ua.bestlunch.model.to.UserTo;
import ua.bestlunch.service.UserService;
import ua.bestlunch.service.VoteService;
import ua.bestlunch.util.UserUtil;

import java.util.List;

/**
 * Created by Виктор on 25.12.2016.
 */
@RestController
@RequestMapping(value = "ajax/votes")
//TODO Make AuthorizedUser.getUser()
public class VoteAjaxController {

    @Autowired
    private VoteService voteService;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vote> getAllByUser(){
        UserTo userTo = AuthorizedUser.get().getUserTo();
        User user = userService.get(userTo.getId());
        return voteService.findAllByUser(user);
    }

    @RequestMapping(value = "/currentVote", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Vote getCurrentVote(){
        UserTo userTo = AuthorizedUser.get().getUserTo();
        return voteService.getCurrentVote(UserUtil.createFromTo(userTo));
    }

    @RequestMapping(value = "/{restId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Vote create(@PathVariable int restId){
        UserTo userTo = AuthorizedUser.get().getUserTo();
        return voteService.addVote(UserUtil.createFromTo(userTo), restId);

    }

    @RequestMapping(value = "/currentVote", method = RequestMethod.DELETE)
    public void deleteCurrentVote(){
        UserTo userTo = AuthorizedUser.get().getUserTo();
        voteService.cancelVote(UserUtil.createFromTo(userTo));
    }
}
