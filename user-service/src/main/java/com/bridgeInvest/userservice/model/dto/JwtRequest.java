package com.bridgeInvest.userservice.model.dto;
/**

 Represents a JSON Web Token (JWT) request.
 */
public class JwtRequest {

    private String username;
    private String password;
    /**
     * Get the username associated with the user.
     *
     * @return The username.
     */

    public String getUsername() {
        return username;
    }

   /* public void setUsername(String username) {
        this.username = username;
    }*/
    /**
     * Get the password associated with the user.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }


    /**
     * Constructs a new JwtRequest.
     */
    public JwtRequest()
    {
        super();
    }
    /**
     * Builder class for constructing JwtRequest objects.
     */
    public static class Builder{
        private String username;
        private String password;

        /**
         * Constructs a new Builder.
         */
        public Builder(){

        }
        /**
         * Sets the username for the JwtRequest.
         *
         * @param username The username.
         * @return The Builder instance.
         */
        public Builder setUsername(String username){
            this.username=username;
            return this;
        }
        /**
         * Sets the password for the JwtRequest.
         *
         * @param password The password.
         * @return The Builder instance.
         */
        public Builder setPassword(String password){
            this.password=password;
            return this;
        }
        /**
         * Builds a JwtRequest object.
         *
         * @return The constructed JwtRequest object.
         */
        public JwtRequest Build(){
            JwtRequest request=new JwtRequest();
            request.username=this.username;
            request.password=this.password;

            return request;
        }
    }
}
