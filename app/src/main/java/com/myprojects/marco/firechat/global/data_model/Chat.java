package com.myprojects.marco.firechat.global.data_model;

import com.myprojects.marco.firechat.user.data_model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Created by marco on 07/08/16.
 */

public class Chat {

    private final List<Message> messages;

    private HashMap<String,User> users;

    public Chat(List<Message> messages) {
        this.messages = messages;
        this.users = new HashMap<>();
    }

    public int size() {
        return messages.size();
    }

    public List<Message> getMessages() {
        return messages;
    }

    public Message get(int position) {
        return messages.get(position);
    }

    public boolean add(int i, Message message) {
        this.messages.add(i, message);
        return true;
    }

    public void add(Message message) {
        if (!messages.contains(message))
            messages.add(message);
    }

    public String getFirstKey() {
        return messages.get(0).getId();
    }

    public String getLastKey() {
        return messages.get(messages.size()-1).getId();
    }

    public User getUser(String uid) {
        return users.get(uid);
    }

    public void addUser(User user) {
        users.put(user.getUid(),user);
    }

    public Chat sortedByDate() {
        List<Message> sortedList = new ArrayList<>(messages);
        Collections.sort(sortedList,byDate());
        return new Chat(sortedList);
    }

    private static Comparator<? super Message> byDate() {
        return new Comparator<Message>() {
            @Override
            public int compare(Message o1, Message o2) {
                long time1 = Long.parseLong(o1.getTimestamp().replace("/",""));
                long time2 = Long.parseLong(o2.getTimestamp().replace("/",""));
                return time1 < time2 ? -1: time1 > time2 ? 1: 0;
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Chat chat = (Chat) o;

        return messages != null ? messages.equals(chat.messages) : chat.messages == null;

    }

    @Override
    public int hashCode() {
        return messages != null ? messages.hashCode() : 0;
    }

}
