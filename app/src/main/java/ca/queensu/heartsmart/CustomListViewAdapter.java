package ca.queensu.heartsmart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Evidence;

/**
 * Created by Jessie Obeng
 *
 * List View Adapter that controls the view of each row.
 * getView breaks down list passed in to determine title, text and confidence score,
 */
public class CustomListViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<Evidence> answers;
    private static LayoutInflater inflater = null;

    public CustomListViewAdapter(Context context, List<Evidence> data){

        mContext = context;
        answers = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return answers.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (convertView == null){

            view = inflater.inflate(R.layout.list_row, null);

            //Initialize
            TextView title = (TextView) view.findViewById(R.id.answerTitle);
            TextView confidence = (TextView) view.findViewById(R.id.confidenceScore);

            //Set
            title.setText(answers.get(position).getText());
            String confidenceScore = Double.toString(Math.round(answers.get(position).getValue() * 100));
            confidence.setText(confidenceScore);
        }

        return view;
    }
}
