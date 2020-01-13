package com.owen.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerviewAdapter";

    private ArrayList<String> image_names;
    private ArrayList<String> images;

    {
        images = new ArrayList<>();
        image_names = new ArrayList<>();
    }

    private Context context;

    public RecyclerviewAdapter(Context context, ArrayList<String> image_names, ArrayList<String> images) {
        this.image_names = image_names;
        this.images = images;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_itemview, parent , false);

        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Log.d(TAG,"onBindViewHolder");

        Glide.with(context)
                .asBitmap()
                .load(images.get(position))
                .into(holder.circleImageView);

        holder.image_name.setText(image_names.get(position));

        holder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Log.d(TAG, "onClick: clicked on " + image_names.get(position));
                Toast.makeText(context,image_names.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return image_names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView circleImageView;
        TextView image_name;
        RelativeLayout parent_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            circleImageView = itemView.findViewById(R.id.image_image);
            image_name = itemView.findViewById(R.id.image_name);
            parent_layout = itemView.findViewById(R.id.parent_recycler);

        }
    }
}
