package com.sillyv.stickyindex;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;

import java.util.List;

/**
 * Created by Vasili on 9/13/2016.
 */
public interface IndexAdapter<INIT extends Initial,HOLDER extends ViewHolder > {
    List<INIT> getInitials();
}
