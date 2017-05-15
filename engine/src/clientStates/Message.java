package clientStates;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;

/**
 * The packet which is sent to the server.
 * @author Basel
 */
public class Message implements Serializable {

    public String type;
    public String username;
    public String chatMessage;
    public int command;
    public int id;

    public Message(String type, String username, int command) {
        this.type = type;
        this.username = username;
        this.command = command;

    }

    public String getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    public String getChatMessage() {
        return chatMessage;
    }

    public int getCommand() {
        return command;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setChatMessage(String chatMessage) {
        this.chatMessage = chatMessage;
    }

    public void setCommand(int command) {
        this.command = command;
    }

}
