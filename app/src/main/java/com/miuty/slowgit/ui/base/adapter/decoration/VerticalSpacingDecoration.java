package com.miuty.slowgit.ui.base.adapter.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Asus on 1/20/2018.
 */

public class VerticalSpacingDecoration extends RecyclerView.ItemDecoration {

    private final int verticalSpaceHeight;
    private boolean hasSpaceLast;

    public VerticalSpacingDecoration(int verticalSpaceHeight, boolean hasSpaceLast) {
        this.verticalSpaceHeight = verticalSpaceHeight;
        this.hasSpaceLast = hasSpaceLast;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        if (hasSpaceLast) {
            outRect.bottom = verticalSpaceHeight;
        } else {
            if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1) {
                outRect.bottom = verticalSpaceHeight;
            }
        }
    }
}
