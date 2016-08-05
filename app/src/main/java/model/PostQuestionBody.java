package model;

/**
 * Created by Jessie Obeng on 2016-04-05.
 *
 * PostQuestionBody class is used to create a QuestionText class
 * to prepare for posting the query to return a response
 */
public class PostQuestionBody {

    public PostQuestionBody(String question){
        this.question = new QuestionText();
        this.question.setQuestionText(question);
    }

    private QuestionText question;

    public QuestionText getQuestion() {
        return question;
    }

    public void setQuestion(QuestionText question) {
        this.question = question;
    }
}
