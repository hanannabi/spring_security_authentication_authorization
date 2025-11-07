package com.example.springSecurityII.authorization;

public class Authorization {
}
//Roles & Permission : respective roles will have respective permissions e.g admin role will depend on its permissions , so each role will have one or more permissions to it
//The Architecture
//Simple Authorization Flow

///Authorization Architecture flow
//1. request will come and authentication will be done and principle object will be stored in securityContextHolder
//2. then AuthorizationFilter will be triggered and it will delegate request to AuthorizationManager -> it is a functional interface and has only one method check(),it will check if this request is authorized or not based on the rules that we have provided
///"RequestMatcherDelegatingAuthorizationManager" which we have used in filter chain will be used by the AuthorizationManager , apart from this it also uses preAuthorizeAuthorizationManager , and postAuthorizeAuthorizationManager which are used in method security
//RequestMatcherDelegatingAuthorizationManager this will tell us if this request is authorized or not, if the request is authorized then it will go to our respective controller otherwise accessDeniedException


///Spring Security Authorization Implementation
//Role based Authorization
//Permissions based Authorization
//getAuthorities
//Request Matcher Rules

///Now drawback to this is that what if we have thousands of api requests so then it becomes difficult to write for all those
///and will result in more messy securityConfig class

//To overcome this drawback we have Method Security and annotations like preAuthorize and postAuthorize comes into picture with that
//Method security?
//@preAuthorize internal working
//implementation
//@postAuthorization internal working
//implementation