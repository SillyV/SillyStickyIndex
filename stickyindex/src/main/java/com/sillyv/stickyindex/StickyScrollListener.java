package com.sillyv.stickyindex;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Vasili Fedotov on 9/13/2016.
 * This Listener has to be reapplied every time the content changed.
 * before reapplying listener, please remove it, clear listeners with the removeTouchListeners function
 * recreate it and apply it.
 * good day!
 */
public class StickyScrollListener<INIT extends Initial> extends RecyclerView.OnScrollListener {


    private final TextView tv;
    private int textViewIndex;
    private RecyclerView sticky_recycler;
    private List initials;
    private IndexAdapter indexAdapter;

    RecyclerView.OnScrollListener mRightOSL;
    private RecyclerView.OnItemTouchListener tempListener;
    private RecyclerView.OnItemTouchListener listener;

    public StickyScrollListener(RecyclerView stickyRecycler,
                                RecyclerView mainRecycler,
                                TextView textView,
                                int textViewIndex) {
        sticky_recycler = stickyRecycler;
        if (stickyRecycler.getAdapter() != null && stickyRecycler.getAdapter() instanceof IndexAdapter) {
            indexAdapter = (IndexAdapter) stickyRecycler.getAdapter();
            this.initials = indexAdapter.getInitials();
            this.tv = textView;
            this.textViewIndex = textViewIndex;
            bindRecyclerViews(mainRecycler, stickyRecycler, mRightOSL);

        } else {
            throw new RuntimeException("RecyclerView must contain Adapter that implements IndexAdapter");
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int x = ((LinearLayoutManager) sticky_recycler.getLayoutManager()).findFirstVisibleItemPosition();
        if (recyclerView != null && indexAdapter.getInitials().size() > 1) {
            View firstVisibleView = sticky_recycler.getChildAt(0);
            View secondVisibleView = sticky_recycler.getChildAt(1);

            TextView firstRowIndex = (TextView) firstVisibleView.findViewById(textViewIndex);
            TextView secondRowIndex = (TextView) secondVisibleView.findViewById(textViewIndex);

            int visibleRange = recyclerView.getChildCount();
            int actual = recyclerView.getChildAdapterPosition(firstVisibleView);
            int next = actual + 1;
            int last = actual + visibleRange;

            if (!((Initial) (initials.get(actual))).getText().equals("")) {
                tv.setText(((Initial) (initials.get(actual))).getText());
            }

            if (next <= last || dy <= 0) {
                if (isHeader(secondRowIndex, indexAdapter)) {
                    tv.setVisibility(View.INVISIBLE);
                    firstRowIndex.setVisibility(View.VISIBLE);
                    secondRowIndex.setVisibility(View.VISIBLE);
                } else {
                    tv.setVisibility(View.VISIBLE);
                    firstRowIndex.setVisibility(View.INVISIBLE);
                    secondRowIndex.setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    private int getIndexContext(TextView firstRowIndex) {
        return Integer.parseInt(firstRowIndex.getTag().toString());
    }

    private boolean isHeader(TextView secondRowIndex, IndexAdapter<INIT, ? extends RecyclerView.ViewHolder> indexAdapter) {
        return (indexAdapter.getInitials().get(getIndexContext(secondRowIndex))).isFirst();
    }

    public static void reBindRecyclerViews(final RecyclerView mainRecycler, final RecyclerView indexRecycler) {


    }

    public void bindRecyclerViews(final RecyclerView mainRecycler, final RecyclerView indexRecycler, RecyclerView.OnScrollListener mRightOSL) {

        StickyRecyclviewTouchListener(indexRecycler);
        SetRecycleViewTouchListener(mainRecycler, indexRecycler, mRightOSL);
    }


    private void StickyRecyclviewTouchListener(RecyclerView stickyRecyclerView) {
        listener = getOnItemTouchListener();
        stickyRecyclerView.addOnItemTouchListener(listener);
    }

    @NonNull
    private RecyclerView.OnItemTouchListener getOnItemTouchListener() {
        return new RecyclerView.OnItemTouchListener() {
            private int mLastY;

            @Override
            public boolean onInterceptTouchEvent(@NonNull final RecyclerView rv, @NonNull final
            MotionEvent e) {
                return Boolean.TRUE;
            }

            @Override
            public void onTouchEvent(@NonNull final RecyclerView rv, @NonNull final MotionEvent e) {
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(final boolean disallowIntercept) {
            }
        };
    }

    private void SetRecycleViewTouchListener(RecyclerView recyclerView, final RecyclerView stickyRecyclerView,
                                             RecyclerView.OnScrollListener mRightOSL) {
        if (mRightOSL == null) {
            mRightOSL = initiateScrollViews();
        }
        final RecyclerView.OnScrollListener finalMRightOSL = mRightOSL;
        if (tempListener == null) {
            tempListener = getListener(stickyRecyclerView, finalMRightOSL);
            recyclerView.addOnItemTouchListener(tempListener);
        }
    }


    public void removeTouchListeners(RecyclerView mainRV, RecyclerView stickyRV) {
        mainRV.removeOnItemTouchListener(tempListener);
    }

    @NonNull
    private RecyclerView.OnItemTouchListener getListener(final RecyclerView stickyRecyclerView, final RecyclerView.OnScrollListener finalMRightOSL) {
        return new RecyclerView.OnItemTouchListener() {

            private int mLastY;

            @Override
            public boolean onInterceptTouchEvent(@NonNull final RecyclerView rv, @NonNull final
            MotionEvent e) {
                Log.d("debug", "RIGHT: onInterceptTouchEvent");

                final Boolean ret = rv.getScrollState() != RecyclerView.SCROLL_STATE_IDLE;
                if (!ret) {
                    onTouchEvent(rv, e);
                }
                return Boolean.FALSE;
            }

            @Override
            public void onTouchEvent(@NonNull final RecyclerView rv, @NonNull final MotionEvent e) {
                Log.d("debug", "RIGHT: onTouchEvent");

                final int action;
                if ((action = e.getAction()) == MotionEvent.ACTION_DOWN && stickyRecyclerView
                        .getScrollState
                                () == RecyclerView.SCROLL_STATE_IDLE) {
                    mLastY = rv.getScrollY();
                    rv.addOnScrollListener(finalMRightOSL);
                } else {
                    if (action == MotionEvent.ACTION_UP && rv.getScrollY() == mLastY) {
                        rv.removeOnScrollListener(finalMRightOSL);
                    }
                }
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(final boolean disallowIntercept) {
                Log.d("debug", "RIGHT: onRequestDisallowInterceptTouchEvent");
            }
        };
    }

    private RecyclerView.OnScrollListener initiateScrollViews() {

        return new SelfRemovingOnScrollListener() {
            @Override
            public void onScrolled(@NonNull final RecyclerView recyclerView, final int dx, final int dy) {
                super.onScrolled(recyclerView, dx, dy);
                sticky_recycler.scrollBy(dx, dy);
            }
        };
    }
}
