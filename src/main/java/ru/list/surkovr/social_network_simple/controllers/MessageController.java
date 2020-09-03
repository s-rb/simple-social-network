package ru.list.surkovr.social_network_simple.controllers;

import org.springframework.web.bind.annotation.*;
import ru.list.surkovr.social_network_simple.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("message")
public class MessageController {

    private int counter = 4;
    private List<Map<String, String>> messages = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{
            put("id", "1");
            put("text", "First message");
        }});
        add(new HashMap<String, String>() {{
            put("id", "2");
            put("text", "Second message");
        }});
        add(new HashMap<String, String>() {{
            put("id", "3");
            put("text", "Third message");
        }});
    }};

    @GetMapping
    public List<Map<String, String>> list() {
        return messages;
    }

    @GetMapping("{id}")
    public Map<String, String> getMessage(@PathVariable String id) {
        return getMessageById(id);
    }

    @PostMapping
    public Map<String, String> createMsg(@RequestBody Map<String, String> message) {
        message.put("id", String.valueOf(counter++));
        messages.add(message);
        return message;
    }

    @PutMapping("{id}")
    public Map<String, String> updateMessage(@PathVariable String id,
                                             @RequestBody Map<String, String> message) {
        Map<String, String> msg = getMessageById(id);
        msg.putAll(message);
        msg.put("id", id);

        return msg;
    }

    @DeleteMapping("{id}")
    public void deleteMessage(@PathVariable String id) {
        Map<String, String> messageById = getMessageById(id);
        messages.remove(messageById);
    }

    private Map<String, String> getMessageById(String id) {
        return messages.stream()
                .filter(message -> message.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }
}