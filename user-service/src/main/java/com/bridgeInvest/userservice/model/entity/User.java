package com.bridgeInvest.userservice.model.entity;


import com.bridgeInvest.userservice.model.dto.JwtRequest;
import jakarta.persistence.*;

/**
 * Represents a user in the system.
 */
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    
    String name;
    
    String password;
    /**
     * Retrieves the ID of the user.
     *
     * @return The ID of the user.
     */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    /**
     * Retrieves the name of the user.
     *
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }
    /**
     * Retrieves the password of the user.
     *
     * @return The password of the user.
     */
    public String getpassword() {
        return password;
    }

    /**
     * Default Constructor for User class
     */

    public User()
    {
        super();
    }

    public static class Builder{
        private String name;
        private String password;
        /**
         * Default constructor for the Builder class.
         */

        public Builder(){

        }
        /**
         * Sets the username for the user being built.
         *
         * @param name The username.
         * @return The Builder instance.
         */
        public User.Builder setUsername(String name){
            this.name=name;
            return this;
        }
        /**
         * Sets the password for the user being built.
         *
         * @param password The password.
         * @return The Builder instance.
         */

        public User.Builder setPassword(String password){
            this.password=password;
            return this;
        }
        /**
         * Builds a User object using the provided username and password.
         *
         * @return The built User object.
         */
        public User Build(){
            User user=new User();
            user.name=this.name;
            user.password=this.password;

            return user;
        }
    }
    
}
