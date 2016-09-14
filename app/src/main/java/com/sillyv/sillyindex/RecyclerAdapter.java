package com.sillyv.sillyindex;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemHolder> {
    private final Context context;
    private List<String> strings;
    private final View.OnClickListener listener;

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }

    public RecyclerAdapter(Context context, List<String> strings, View.OnClickListener listener) {
        this.context = context;
        this.strings = strings;
        this.listener = listener;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ItemHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        holder.text.setText(strings.get(position));
        holder.text2.setText(strings.get(position).substring(1, 1));
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }


    public class ItemHolder extends RecyclerView.ViewHolder {

        public TextView text;
        public TextView text2;

        public ItemHolder(View itemView, View.OnClickListener listener) {
            super(itemView);

            text = (TextView) itemView.findViewById(R.id.index_textview);
            text2 = (TextView) itemView.findViewById(R.id.index_textview2);
        }
    }
}
