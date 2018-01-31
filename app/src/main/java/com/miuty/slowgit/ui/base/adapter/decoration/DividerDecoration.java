package com.miuty.slowgit.ui.base.adapter.decoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Asus on 1/31/2018.
 */

public class DividerDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    private boolean hasLoadMore;
    private Drawable drawableDivider;

    public DividerDecoration(Context context, boolean hasLoadMore) {
        this.hasLoadMore = hasLoadMore;
        final TypedArray styledAttributes = context.obtainStyledAttributes(ATTRS);
        drawableDivider = styledAttributes.getDrawable(0);
        styledAttributes.recycle();
    }

    public DividerDecoration(Context context, boolean hasLoadMore, @DrawableRes int resId) {
        this.hasLoadMore = hasLoadMore;
        this.drawableDivider = context.getDrawable(resId);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    /*    if (parent.getChildAdapterPosition(view) == 0) {
            return;
        }
        outRect.top = drawableDivider.getIntrinsicHeight();*/
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        int delta = 0;
        if (hasLoadMore) {
            delta = 1;
        }
        for (int i = 0; i < childCount - delta; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;

            int bottom = top + drawableDivider.getIntrinsicHeight();

            drawableDivider.setBounds(left, top, right, bottom);
            drawableDivider.draw(canvas);
        }
    }
}
