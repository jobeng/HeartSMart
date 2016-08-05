package model;

import com.google.gson.Gson;

import Controller.GSONSerializable;

/**
 * Created by Jesse Obeng
 *
 * Used by the Main Activity and List activity
 */
public class ResPostQuestion implements GSONSerializable{

    private Question question;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public static ResPostQuestion fromJson(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, ResPostQuestion.class);
    }

    @Override
    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
