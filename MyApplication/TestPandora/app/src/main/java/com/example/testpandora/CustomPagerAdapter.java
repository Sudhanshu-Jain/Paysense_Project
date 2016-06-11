package com.example.testpandora;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.testpandora.models.Example;
import com.example.testpandora.models.Owner;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by sudhanshu on 13/4/16.
 */
public class CustomPagerAdapter extends PagerAdapter {

    public Context mContext;
    public LayoutInflater mInflater;
    public List<Example> postList;


    public CustomPagerAdapter(Context mContext,List<Example> posts) {
        this.mContext = mContext;
        this.postList = posts;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return postList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mInflater.inflate(R.layout.post_layout, container, false);
        ImageView imageView = (ImageView)itemView.findViewById(R.id.image);
        TextView title = (TextView)itemView.findViewById(R.id.title);
        Example example = postList.get(position);
        title.setText(example.getFull_name());
        Linkify.addLinks(title, Linkify.WEB_URLS);
        final Owner owner = example.getOwner();
        TextView idNumber = (TextView)itemView.findViewById(R.id.idNumber);
        TextView loginName = (TextView)itemView.findViewById(R.id.loginName);
        idNumber.setText(Integer.toString(owner.getId()));
        loginName.setText(owner.getLogin());
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = owner.getHtmlUrl();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                mContext.startActivity(i);
            }
        });
        String avatur_url = owner.getAvatarUrl();
        Picasso.with(mContext).load(avatur_url).fit().into(imageView);
        container.addView(itemView);



        return itemView;



    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==((RelativeLayout)object);
    }
}
