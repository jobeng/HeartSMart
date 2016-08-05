package ca.queensu.heartsmart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;
import Controller.WatsonRestClient;
import model.Evidence;
import model.PostQuestionBody;
import model.ResPostQuestion;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Creates A list Activity
 * */
public class ListActivity extends AppCompatActivity {

    public static final String KEY_QUESTION_RESPONSE = "response";
    private static final String TAG = "List Activity";

    private Button askButton;
    private EditText watsonQuestion;

    //set listView and Adapter
    private ListView listView;
    private CustomListViewAdapter customListView;
    private static List<Evidence> list = MainActivity.evidenceList.evidenceList;

    /**
     * Same as in main method,
     * Create a class for makePostQuestion
     * */
    public void makePostQuestion(String question){
        Log.d(TAG, "makePostQuestion()");
        PostQuestionBody body = new PostQuestionBody(question);

        WatsonRestClient.get().postQuestion(body, new Callback<ResPostQuestion>() {
            @Override
            public void success(ResPostQuestion postQuestionBody, Response response) {
                if (response.getStatus() / 100 == 2) { // check if the response is successful )(i.e. 200, 201, 202...)
                    handlePostQuestionResponse(postQuestionBody);

                    // display the text of the first evidencelist in the response
                    MainActivity.evidenceList.setEvidenceList(postQuestionBody.getQuestion().getEvidencelist());
                    list = MainActivity.evidenceList.evidenceList;


                    // Test return from QAAPI
                    String text = list.get(4).getTitle();
                    //double value = evidenceList.evidenceList.get(0).getValue();
                    //String text = Double.toString(value);
                    Toast.makeText(ListActivity.this, text, Toast.LENGTH_LONG).show();


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

    public void handlePostQuestionResponse(ResPostQuestion res){
        Log.d(TAG, "handlePostQuestionResponse()");

        Intent i = new Intent(ListActivity.this, ListActivity.class);
        Log.d(TAG, res.toJson());
        i.putExtra(ListActivity.KEY_QUESTION_RESPONSE, res.toJson());
        startActivity(new Intent(ListActivity.this, ListActivity.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = (ListView) findViewById(R.id.qaList);

        //Set up list View adapter
        customListView = new CustomListViewAdapter(getApplicationContext(), list);
        listView.setAdapter(customListView);


        //Set up Buttons
        watsonQuestion = (EditText)findViewById(R.id.questionField);

        //Heart Smart Button
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
        getMenuInflater().inflate(R.menu.menu_list, menu);
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
