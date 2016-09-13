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
 * Created by Vasili on 9/13/2016.
 */
public class StickyScrollListener<INIT extends Initial> extends RecyclerView.OnScrollListener {

    private final TextView tv;
    private int textViewIndex;
    private RecyclerView sticky_recycler;
    private List initials;
    private IndexAdapter indexAdapter;

    public StickyScrollListener(RecyclerView stickyRecycler,
                                TextView textView,
                                int textViewIndex) {

        sticky_recycler = stickyRecycler;

        if (stickyRecycler.getAdapter() != null && stickyRecycler.getAdapter() instanceof IndexAdapter) {


            indexAdapter = (IndexAdapter) stickyRecycler.getAdapter();
            this.initials = indexAdapter.getInitials();
            this.tv = textView;
            this.textViewIndex = textViewIndex;
        } else {
            throw new RuntimeException("RecyclerView must contain Adapter that implements IndexAdapter");
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int x = ((LinearLayoutManager) sticky_recycler.getLayoutManager()).findFirstVisibleItemPosition();
        if (recyclerView != null) {
            View firstVisibleView = sticky_recycler.getChildAt(0);
            View secondVisibleView = sticky_recycler.getChildAt(1);

            TextView firstRowIndex = (TextView) firstVisibleView.findViewById(textViewIndex);
            TextView secondRowIndex = (TextView) secondVisibleView.findViewById(textViewIndex);

            int visibleRange = recyclerView.getChildCount();
            int actual = recyclerView.getChildPosition(firstVisibleView);
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


    public static void bindRecyclerViews(final RecyclerView mainRecycler, final RecyclerView indexRecycler) {
        RecyclerView.OnScrollListener mLeftOSL = new SelfRemovingOnScrollListener() {
            @Override
            public void onScrolled(@NonNull final RecyclerView recyclerView, final int dx, final int dy) {
                super.onScrolled(recyclerView, dx, dy);
                recyclerView.scrollBy(dx, dy);
            }
        };

        RecyclerView.OnScrollListener mRightOSL = new SelfRemovingOnScrollListener() {
            @Override
            public void onScrolled(@NonNull final RecyclerView recyclerView, final int dx, final int dy) {
                super.onScrolled(recyclerView, dx, dy);
                indexRecycler.scrollBy(dx, dy);
            }
        };
        StickyRecyclviewTouchListener(indexRecycler);
        SetRecycleViewTouchListener(mainRecycler, indexRecycler, mLeftOSL, mRightOSL);
    }


    private static void StickyRecyclviewTouchListener(RecyclerView stickyRecyclerView) {
        stickyRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
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
        });
    }

    private static void SetRecycleViewTouchListener(RecyclerView recyclerView, final RecyclerView stickyRecyclerView,
                                                    RecyclerView.OnScrollListener mLeftOSL
            , final RecyclerView.OnScrollListener mRightOSL


    ) {
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

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
                    rv.addOnScrollListener(mRightOSL);
                } else {
                    if (action == MotionEvent.ACTION_UP && rv.getScrollY() == mLastY) {
                        rv.removeOnScrollListener(mRightOSL);
                    }
                }
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(final boolean disallowIntercept) {
                Log.d("debug", "RIGHT: onRequestDisallowInterceptTouchEvent");
            }
        });


    }


}
