package com.mortgert.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SecurityConstants {

        private SecurityConstants(){

        }

        public static final long EXPIRATION_TIME = 3_600_000; // 60 mins
        public static final String TOKEN_PREFIX = "Bearer ";
        public static final String HEADER_STRING = "Authorization";
        public static final String SIGN_UP_URL = "/user/register";

        @Value("${jwt.secret}")
        public static String SECRET;
}
