package com.sillyv.sillyindex;

import com.sillyv.stickyindex.Initial;

/**
 * Created by Vasili on 9/13/2016.
 */
public class MyInitial implements Initial {
    private String text;
    private boolean isFirst;
    private boolean isLast;

    public void setText(String text) {
        this.text = text;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    public void setLast(boolean last) {
        isLast = last;
    }

    public MyInitial(String text, boolean isFirst, boolean isLast) {
        this.text = text;
        this.isFirst = isFirst;
        this.isLast = isLast;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public boolean isFirst() {
        return isFirst;
    }

    @Override
    public boolean isLast() {
        return isLast;
    }
}
