package com.sillyv.sillyindex;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vasili on 9/7/2016.
 */
public class IndexAdapter extends RecyclerView.Adapter<IndexAdapter.ItemHolder> implements com.sillyv.stickyindex.IndexAdapter {
    private final Context context;


    public List<MyInitial> getInitials() {
        return initials;
    }

    private final List<MyInitial> initials;
    private final View.OnClickListener listener;

    public IndexAdapter(Context context, List<String> strings, View.OnClickListener listener) {
        this.context = context;
        this.initials = new ArrayList<>();

        if (strings.size() > 0) {
            initials.add(new MyInitial(strings.get(0), true, false));
            for (int i = 1; i < strings.size(); i++) {
                boolean isFirst = false;
                if (!strings.get(i).equals(strings.get(i - 1))) {
                    isFirst = true;

                    initials.get(i - 1).setLast(true);
                }
                initials.add(new MyInitial(strings.get(i), isFirst, false));
            }
        }
        this.listener = listener;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.index, parent, false);
        return new ItemHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        holder.text.setText(initials.get(position).getText());
        holder.text.setTag(position);
        if (!initials.get(position).isFirst()) {
            holder.text.setVisibility(View.INVISIBLE);
        }
        else
        {
            holder.text.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return initials.size();
    }


    public class ItemHolder extends RecyclerView.ViewHolder {

        public TextView text;

        public ItemHolder(View itemView, View.OnClickListener listener) {
            super(itemView);

            text = (TextView) itemView.findViewById(R.id.index_textview);
        }
    }
}
