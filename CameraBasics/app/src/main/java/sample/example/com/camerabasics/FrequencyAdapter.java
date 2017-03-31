package sample.example.com.camerabasics;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;



/**
 * Created by sudhanshu on 29/3/17.
 */

public class FrequencyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context context;
    private final int WORD = 0, STRING = 1;

    public FrequencyAdapter(Context context, ArrayList<Object> wordArrayList) {
        this.context = context;
        this.wordArrayList = wordArrayList;
    }

    private ArrayList<Object> wordArrayList = new ArrayList<>();




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        RecyclerView.ViewHolder viewHolder;
        switch (viewType) {
            case WORD:
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_layout, parent, false);
                viewHolder = new StringHolder(itemView);
                break;
            case STRING:
                View v2 =  LayoutInflater.from(parent.getContext()).inflate(R.layout.string_layout, parent, false);
                viewHolder = new PositionHolder(v2);
                break;
            default:
                View itemView1 = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_layout, parent, false);
                viewHolder = new StringHolder(itemView1);
                break;
        }
        return viewHolder;

    }

    @Override
    public int getItemViewType(int position) {
        if (wordArrayList.get(position) instanceof Word) {
            return WORD;
        } else if (wordArrayList.get(position) instanceof String) {
            return STRING;
        }

        return -1;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

       switch (holder.getItemViewType()){
           case WORD:
               StringHolder sh = (StringHolder)holder;
               Word word = (Word)wordArrayList.get(position);
               sh.frequency.setText(String.valueOf(word.getFrequency()));
               sh.mStringName.setText(word.getWordName());
               break;
           case STRING:
               PositionHolder ph = (PositionHolder)holder;
               String str = (String)wordArrayList.get(position);
               ph.frequencyPos.setText(str);
       }
    }



    @Override
    public int getItemCount() {
        return wordArrayList.size();
    }


    public static class StringHolder extends RecyclerView.ViewHolder {


        private TextView mStringName;
        private TextView frequency;

        public StringHolder(View v) {
            super(v);

            mStringName = (TextView) v.findViewById(R.id.string_name);
            frequency = (TextView) v.findViewById(R.id.no);

        }


    }

    public static class PositionHolder extends RecyclerView.ViewHolder {

        private TextView frequencyPos;

        public PositionHolder(View v) {
            super(v);


            frequencyPos = (TextView) v.findViewById(R.id.frefuencyPos);

        }


    }


}
