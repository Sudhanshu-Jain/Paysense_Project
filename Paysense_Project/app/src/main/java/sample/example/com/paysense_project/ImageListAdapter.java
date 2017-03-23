package sample.example.com.paysense_project;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sudhanshu on 23/3/17.
 */

public class ImageListAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {

    private List<String> itemList;
    private Context context;


    public ImageListAdapter(Context context, List<String> itemList) {
        this.itemList = itemList;
        this.context = context;
    }


    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        if(itemList.get(position).length()!=0)
            Picasso.with(context).load(itemList.get(position)).into(holder.photo);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

}

    class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {


        public ImageView photo;

        public RecyclerViewHolders(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            photo = (ImageView) itemView.findViewById(R.id.photo);
        }

        @Override
        public void onClick(View view) {

        }
    }
