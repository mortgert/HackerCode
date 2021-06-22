# Spring Security ( Rough Overview )

This is a brief overview of a simple authentication chain, associated with JSON Web Tokens (**JWT**). This is by no means a complete purview of the Spring Security package, however for simple implementations of authenticated endpoints this will suffice.


# Filters
The basis of authenticating an endpoint starts with introducing Authentication Filters. By Extending the **BasicAuthenticationFilter**, you must implement the `doFilterInteral` method, as the **Filter Chain** must be passed along to iterate over the next filter in line. Authentication Filters take an **AuthenticationManager** bean in their constructor. This authentication manager can be accessed by the method `getAuthenticationManager()`

## Authentication Manager Builder

This builder is what builds the actual **AuthenticationManager** Bean. This builder is configured for how to create an **AuthenticationManager** in a configure method within a **WebSecurityConfigAdapater** class. A custom implementation of an **AuthenticationProvider**, an  implementation of **UserDetailsService** and a password encryptor implementation ( as a bean ).

## Authentication Manager

Encapsulates the behaviour of authentication and is available to Authentication Filters . Using the lone method `authenticate(Authentication authentication)`, an **Authentication** object is returned or an **AuthenticationException** is thrown based upon the behaviour of the **AuthenticationProvider**.

## Authentication Provider

Provides the authentication method and takes in an **Authentication** object and ties into either a cache of or the actual database itself to verify a set of credentials against. This is the logic that can then be tied into the eventual token that can be used to save a user into the Security Context to be used have method level authorization.

## User Details Service Implementation

An implementation of the User Details Service, this defines the only method available `loadUserbyUsername(String username)` this is a granular implementation that spring security stores in the context after being tied to a token that contains every piece of information that defines a user, rather than just what is used to verify a user.

[Basic Flow Overview For A Protected End Point](SpringSecurityBasicFlow.pdf)