package com.spring.cursoapis.controller;

import com.spring.cursoapis.entity.Message;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private List<Message> mensaje = new ArrayList<>();


    public MessageController(){
        mensaje.add(new Message(1,"Cumplimiento de las actividades","Importante",false));
        mensaje.add(new Message(2,"Resoinder la encuenta","No importante",true));

    }

    @GetMapping
    public List<Message> getMensaje() {
        return mensaje;
    }

    @GetMapping("/{id}")
    public Message getOneMensaje(@PathVariable int id) {
        Optional<Message> message = mensaje.stream()
                .filter(m -> m.getId() == id)
                .findFirst();
        return message.orElse(new Message(9999,"Null","Null",false));

    }

    @PostMapping
    public Message createMessage(@RequestBody Message msj){
        mensaje.add(msj);
        return msj;
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable int id){
        mensaje.removeIf(m -> m.getId() == id);

    }
}
