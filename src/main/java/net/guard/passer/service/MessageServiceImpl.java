package net.guard.passer.service;

import net.guard.passer.entity.Message;
import net.guard.passer.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService{
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<Message> getAllMessages() {
        //Сортировка по названию столбца в бд, хотя хз. Работает что с Id, что с id.
        return messageRepository.findAll(Sort.by("id"));
    }

    @Override
    public void saveMessage(Message message) {
        messageRepository.save(message);
    }

    @Override
    public Message getMessage(int id) {
        Message message = null;
        Optional<Message> optional = messageRepository.findById(id);
        if(optional.isPresent()){
            message = optional.get();
        }
        return message;
    }

    @Override
    public void deleteMessage(int id) {
        messageRepository.deleteById(id);
    }
}
