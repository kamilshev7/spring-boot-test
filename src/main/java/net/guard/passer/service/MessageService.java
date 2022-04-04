package net.guard.passer.service;

import net.guard.passer.entity.Message;

import java.util.List;

public interface MessageService {
    public List<Message> getAllMessages();

    public void saveMessage(Message message);

    public Message getMessage(int id);

    public void deleteMessage(int id);
}
