package net.guard.passer.controller;

import net.guard.passer.entity.Message;
import net.guard.passer.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MyController {

    @Autowired
    private MessageService messageService;


    @GetMapping("/name")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World")
                                       String name, Model model) {
        model.addAttribute("name", name);
        return "all-messages";
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
