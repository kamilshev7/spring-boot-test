package net.guard.passer.controller;

import net.guard.passer.entity.Message;
import net.guard.passer.entity.Role;
import net.guard.passer.entity.User;
import net.guard.passer.service.MessageService;
import net.guard.passer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MyController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;


    @GetMapping("/name")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World")
                                       String name, Model model) {
        model.addAttribute("name", name);
        return "all-messages";
    }


    @RequestMapping("/")
    public String viewMainPage(){
        return "mainPage";
    }

    @RequestMapping("/register")
    public String viewRegisterPage(Model model){
        model.addAttribute("newUser", new User());
        return "registerPage";
    }

    @RequestMapping("/process_register")
    public String processRegister(User user) {
        userService.saverUserWithDefaultRole(user);
        return "registerPageSuccess";
    }

    @RequestMapping("/users")
    public String getAllUsers(Model model){
        List<User> allUsers = userService.getAllUsers();

        model.addAttribute("allUsers",allUsers);
        return "all-users";
    }

    @RequestMapping("/users/update{id}")
    public String updateUser(@PathVariable Integer id, Model model){
        User user = userService.getUser(id);
        List<Role> listRoles = userService.getRoles();
        model.addAttribute("oneUser", user);
        model.addAttribute("listRoles", listRoles);
        return "one-user";
    }

    @RequestMapping("/users/delete{id}")
    public String deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @RequestMapping("/users/save")
    public String saveUser(User user){
        userService.saveUser(user);
        return "redirect:/users";
    }

    @RequestMapping("/messages")
    public String getAllMessage(Model model){
        List<Message> allMessage = messageService.getAllMessages();

        model.addAttribute("allMes",allMessage);
        return "all-messages";
    }

    @RequestMapping("/messages/addHere")
    public String addMessage(@RequestParam String text, @RequestParam String tag,
                             Model model){
        Message message = new Message(text, tag);
        messageService.saveMessage(message);
        List<Message> allMessage = messageService.getAllMessages();
        model.addAttribute("allMes",allMessage);
        return "redirect:/messages";
    }


    @RequestMapping("/messages/add")
    public String addNewMessage(Model model){
        Message message = new Message();
        model.addAttribute("oneMess", message);
        return "one-message";
    }

    @RequestMapping("/messages/save")
    public String saveMessage(@ModelAttribute("saveMess") Message message){
        messageService.saveMessage(message);
        return "redirect:/messages";
    }

    @RequestMapping("/messages/delete{id}")
    public String deleteMessage(@PathVariable Integer id){
        messageService.deleteMessage(id);
        return "redirect:/messages";
    }

    @RequestMapping("/messages/update{id}")
    public String updateMessage(@PathVariable Integer id, Model model){
        Message message = messageService.getMessage(id);
        model.addAttribute("oneMess", message);
        return "one-message";
    }

}
