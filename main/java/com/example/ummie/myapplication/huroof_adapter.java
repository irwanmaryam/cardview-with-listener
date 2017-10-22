package com.example.ummie.myapplication;

/**
 * Created by lenovo on 21-Oct-17.
 */

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

public class huroof_adapter extends RecyclerView.Adapter<huroof_adapter.MyViewHolder> {

    private Context mContext;
    private List<Huroof> huroofList;
    private ItemClickListener listener;



    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title, count;
        public ImageView thumbnail, overflow;
        public CardView cardView;
        public ItemClickListener itemClickListener;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
            cardView = (CardView) view.findViewById(R.id.card_view);
            cardView.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {
            this.itemClickListener.onItemClick(view, getLayoutPosition());
        }

        public void setItemClickListener(ItemClickListener listener)
        {
            this.itemClickListener = listener;
        }
    }


    public huroof_adapter(Context mContext, List<Huroof> huroofList) {
        this.mContext = mContext;
        this.huroofList = huroofList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.huroof_card, parent, false);

        return new MyViewHolder(itemView);
    }


    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Huroof huroof = huroofList.get(position);
        holder.title.setText(huroof.getName());
        holder.count.setText(huroof.getNumOfHuroof() + " Huroof");

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int v, ImageView position) {
                Snackbar.make(v,huroofList.get(v).getName(),Snackbar.LENGTH_SHORT).show();
            }
        });


        // loading album cover using Glide library
        Glide.with(mContext).load(huroof.getThumbnail()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);

            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                listener.onItemClick(position, holder.thumbnail);
                if(position == 0)
                {
                    Intent intent = new Intent (view.getContext(), HaaActivity.class);
                    mContext.startActivity(intent);
                }





            }
        });

        holder.thumbnail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                listener.onItemClick(position, holder.thumbnail);


            }
        });

    }


    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_main, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return huroofList.size();
    }


    public interface ItemClickListener
    {
        void onItemClick(int v, ImageView position);


    }


}