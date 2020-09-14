package ru.list.surkovr.social_network_simple.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import ru.list.surkovr.social_network_simple.dto.EventType;
import ru.list.surkovr.social_network_simple.dto.ObjectType;
import ru.list.surkovr.social_network_simple.dto.WsEventDto;

import java.io.IOException;
import java.util.function.BiConsumer;

@Component
public class WsSender {

    // Отвечает за отправку сообщений через очереди сообщений
    private final SimpMessagingTemplate template;
    private final ObjectMapper mapper;

    @Autowired
    public WsSender(SimpMessagingTemplate template, ObjectMapper objectMapper) {
        this.template = template;
        this.mapper = objectMapper;
    }

    public <T> BiConsumer<EventType, T> getSender(ObjectType objectType, Class view) {
        ObjectWriter objectWriter = mapper
                .setConfig(mapper.getSerializationConfig()).writerWithView(view);

        return (EventType eventType, T payload) -> {
            String valueAsString = null;
            try {
                valueAsString = objectWriter.writeValueAsString(payload);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            template.convertAndSend("/topic/activity",
                    new WsEventDto(objectType, eventType, valueAsString)
            );
        };
    }
}
