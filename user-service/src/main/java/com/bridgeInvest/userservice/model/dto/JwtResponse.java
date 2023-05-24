package com.bridgeInvest.userservice.model.dto;

import lombok.Data;


/**

 Represents a JSON Web Token (JWT) response.
 */

public class JwtResponse {

      private String token;
    /**

     Constructs a new instance of the JwtResponse class.
     */
    public JwtResponse()
    {
        super();
    }




    // Constructors, getters, and setters...

    /**
     * Builder class for constructing JwtResponse objects.
     */
    public static class Builder{
        private String token;
        /**
         * Sets the JWT token.
         *
         * @param //token The JWT token.
         * @return The Builder instance.
         */
        public Builder() {

        }

        public  Builder settoken(String token){
            this.token=token;
            return this;
        }
        /**
         * Builds the JwtResponse object.
         *
         * @return The constructed JwtResponse object.
         */

        public JwtResponse build(){
            JwtResponse response=new JwtResponse();
            response.token=this.token;
            return response;
        }
    }


}
