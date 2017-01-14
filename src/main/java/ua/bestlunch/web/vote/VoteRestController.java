package ua.bestlunch.web.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.bestlunch.model.User;
import ua.bestlunch.model.Vote;
import ua.bestlunch.service.UserService;
import ua.bestlunch.service.VoteService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * Created by Виктор on 27.11.2016.
 */
@RestController
@RequestMapping(value = "/users/{id}/votes")
public class VoteRestController {

    @Autowired
    private VoteService service;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vote> getAllByUser(){
        //return service.findAllByUser();
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Vote get(){
       // return service.find();
        return null;
    }

//    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity <Vote> create(@PathVariable int restId){
//        Vote created = service.addVote(vote);
//
//        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/users/{id}/votes/{voteid}")
//                .buildAndExpand(created.getId()).toUri();
//
//        return ResponseEntity.created(uriOfNewResource).body(created);
//
//    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id){
        //service.cancelVote(id);
    }
}
