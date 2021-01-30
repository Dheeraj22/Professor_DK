package com.example.loginapplication;

import java.util.HashMap;
import java.util.Map;

public class Credentials {

    private HashMap<String, String> credentialMapper = new HashMap<String, String>();

    public void addCredentials(String username, String password){
        credentialMapper.put(username, password);
    }

    public boolean checkUsername(String username){
        return credentialMapper.containsKey(username);
    }

    public boolean checkCredentials(String username, String password){

        if(credentialMapper.containsKey(username)){
            return password.equals(credentialMapper.get(username));
        }

        return false;
    }

    public void loadCredentials(Map<String, ?> preferencesMap){
        for(Map.Entry<String, ?> entries : preferencesMap.entrySet()){
            if(!entries.getKey().equals("RememberMeCheckbox") || !entries.getKey().equals("LastSavedUsername") ||
            !entries.getKey().equals("LastSavedPassword")){
                credentialMapper.put(entries.getKey(), entries.getValue().toString());
            }
        }
    }
}
