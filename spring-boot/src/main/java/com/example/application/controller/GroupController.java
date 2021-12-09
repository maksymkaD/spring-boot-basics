package com.example.application.controller;

import com.example.application.dal.model.Group;
import com.example.application.dal.model.User;
import com.example.application.dto.group.GroupCreateDTO;
import com.example.application.dto.student.StudentUpdateDTO;
import com.example.application.security.MyUserDetails;
import com.example.application.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class GroupController {
    @Autowired
    private GroupService groupService;

    @GetMapping("/groups")
    public String getGroupsPage(Model model) {
        List<Group> groups = groupService.getGroups();
        model.addAttribute("groups", groups);

        return "groups/list";
    }
    @GetMapping("/groups/create")
    public String getCreateGroupPage(GroupCreateDTO groupCreateDTO) {
        return "groups/create";
    }

    @PostMapping(path = "/groups/create")
    public String createGroup(@Valid GroupCreateDTO groupCreateDTO, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "groups/create";
        }

        groupService.createGroup(groupCreateDTO);
        List<Group> groups = groupService.getGroups();
        model.addAttribute("groups", groups);

        return "redirect:/groups";
    }

    @GetMapping("/groups/delete/{id}")
    public String deleteGroup(@PathVariable("id") int id, Model model) {
        groupService.deleteGroup(id);

        List<Group> groups = groupService.getGroups();
        model.addAttribute("groups", groups);

        return "redirect:/groups";
    }

    @GetMapping("/groups/join/{id}")
    public String joinGroup(@PathVariable("id") int id, Model model) {
        MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

       // Group currentGroup =  groupService.getGroupById(id);
        if(groupService.getGroupById(id).getUserList().contains(user.getId())) {

            System.out.println("You are already in here");
        }
        else
        {
            groupService.addStudetToGroup( groupService.getGroupById(id), user);
            System.out.println("Done");
        }



        List<Group> groups = groupService.getGroups();
        model.addAttribute("groups", groups);

        return "redirect:/groups";
    }
}