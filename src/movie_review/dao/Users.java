package movie_review.dao;

import java.util.*;
import movie_review.dto.Person;
import movie_review.utility.CustomException;

public class Users {
    private List<Person> admins;
    private List<Person> critics;
    private List<Person> users;

    public Users() {
        this.admins = new LinkedList<>();
        this.critics = new LinkedList<>();
        this.users = new LinkedList<>();
    }
    public Person getCritic(String name){
        Person user = new Person(name);
        if(this.critics.contains(user)){
            return user;
        } return null;
    }
    public Person getUser(String name){
        Person user = new Person(name);
        if(this.users.contains(user)){
            return user;
        } return null;
    }
    public Person getCritic(Person user){
        if(this.critics.contains(user)){
            return user;
        } return null;
    }
    public Person getUser(Person user){
        if(this.users.contains(user)){
            return user;
        } return null;
    }
    public void createUser(String name) throws CustomException{
        Person user = new Person(name);
        if(users.contains(user)){
            throw new CustomException("User already exists");
        } else{
            users.add(user);
        }
    }

    public void makeCritic(Person user) throws CustomException{
        boolean isPresent = users.remove(user);
        if(isPresent){
            critics.add(user);
        } else {
            throw new CustomException("User with the name doesn't exists");
        }
    }
}
