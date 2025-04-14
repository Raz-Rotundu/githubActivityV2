package com.lumius.APIgetterV2;

public class App 
{
    public static void main( String[] args )
    {

        if (args.length != 1) {
     	   System.out.println("Usage: GitHubActivityV2 <Username>");
        } else {
     	   String url = "https://api.github.com/users/" + args[0] + "/events";
        }
    }
}
