package ca.queensu.heartsmart;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import model.EvidenceList;
import model.PostQuestionBody;
import Controller.WatsonRestClient;
import model.ResPostQuestion;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Main Activity : View
 * Initializes and sets up buttons and text areas on screen to enter Questions
 * */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private EditText watsonQuestion;
    private Button askButton;
    private String question;
    protected static EvidenceList evidenceList;


    /** Make post is used to call the PostQuestionBody class
     ** Values of postQuestionBody is set by Watson Rest Client return value
     *  If it returns a status of a 200 value then it is good
     *  Otherwise, the call failed an an API is not initialized
     ** */
    public void makePostQuestion(String question){
        Log.d(TAG, "makePostQuestion()");
        PostQuestionBody body = new PostQuestionBody(question);

        //Callback<ResPostQuestion> is a retroFit class that returns the status of the call
        //200 is Good; 400, 500 not so Good.
        WatsonRestClient.get().postQuestion(body, new Callback<ResPostQuestion>() {
            @Override
            public void success(ResPostQuestion postQuestionBody, Response response) {
                if (response.getStatus() / 100 == 2) { // check if the response is successful )(i.e. 200, 201, 202...)
                    handlePostQuestionResponse(postQuestionBody);

                    // set the text of the first evidencelist in the response
                    evidenceList = new EvidenceList(postQuestionBody.getQuestion().getEvidencelist());

                    // Test return from QAAPI
                    String text = evidenceList.evidenceList.get(4).getTitle();
                    //double value = evidenceList.evidenceList.get(0).getValue();
                    //String text = Double.toString(value);
                    Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();


                } else {
                    displayFailure("Request failure: " + String.valueOf(response.getStatus()));
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "error makePostQuestion", error);
            }
        });
    }

    public void displayFailure(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * Pulls JSON to handle the request and sends to the List Activity
     * */
    public void handlePostQuestionResponse(ResPostQuestion res){
        Log.d(TAG, "handlePostQuestionResponse()");

        Intent i = new Intent(MainActivity.this, ListActivity.class);
        Log.d(TAG, res.toJson());
        i.putExtra(ListActivity.KEY_QUESTION_RESPONSE, res.toJson());
        startActivity(new Intent(MainActivity.this, ListActivity.class));
    }

    // end API Controller stuff

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Main page text field
        watsonQuestion = (EditText)findViewById(R.id.questionTextField);

        //Button to process request
        askButton = (Button)findViewById(R.id.askButton);
        askButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = watsonQuestion.getText().toString();

                makePostQuestion(question);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
