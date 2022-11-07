package com.example.crudusers.dao;

import com.example.crudusers.models.User2;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DAOUser {

    private int id;

    private List<User2> users = new ArrayList<>();

    public void addToBaseNewUser(User2 user){
        user.setId(++id);
        users.add(user);

    }
    public User2 getUserById(int id){
        return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }
    public List<User2> getUsersList(){
        return users;
    }

    public void setUsers(List<User2> users) {
        this.users = users;
    }
    public void updateUser(int id, User2 user){
        User2 updateUser = getUserById(id);
        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        updateUser.setPatronymic(user.getPatronymic());
        updateUser.setAge(user.getAge());
        updateUser.setEmail(user.getEmail());
        updateUser.setPhone(user.getPhone());
    }
    public void deleteUser(int id){
        users.removeIf(user -> user.getId() == id);
    }
}
