package Controller;

import model.PostQuestionBody;
import model.ResPostQuestion;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/** Makes Post to retreive JSON
 *
 */
public interface GetWatsonApi {
    String ENDPOINT = "https://watson-wdc01.ihost.com/instance/541/deepqa/v1/";

    @POST("/question")
    void postQuestion(@Body PostQuestionBody body, Callback<ResPostQuestion> res);
}
