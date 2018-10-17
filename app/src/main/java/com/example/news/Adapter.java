package com.example.news;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ExampleViewHolder> {
    private Context mContext;
    private ArrayList<Item> mExampleList;
    private OnItemClickListener mListener;
    public Adapter(Context context, ArrayList<Item>examplelist){
        mContext = context;
        mExampleList = examplelist;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(mContext).inflate(R.layout.item,parent,false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position){
        Item currentItem = mExampleList.get(position);

        String imageUrl =  currentItem.getGambar();
        String Judul = currentItem.getTitle();
        String deskripsi = currentItem.getDeskripsi();
        holder.judul.setText(Judul);
        Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.mImageView);
        holder.deskripsi.setText(deskripsi);
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView judul, deskripsi;
        public ExampleViewHolder(View itemView){
            super(itemView);
            mImageView = itemView.findViewById(R.id.gambar);
            judul = itemView.findViewById(R.id.judulberita);
            deskripsi = itemView.findViewById(R.id.deskripsi);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
