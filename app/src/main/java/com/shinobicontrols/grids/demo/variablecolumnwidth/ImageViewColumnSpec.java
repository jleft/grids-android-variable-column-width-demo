package com.shinobicontrols.grids.demo.variablecolumnwidth;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shinobicontrols.grids.core.Column;
import com.shinobicontrols.grids.core.ColumnSpec;
import com.shinobicontrols.grids.core.ItemViewHolderCreator;
import com.shinobicontrols.grids.supplement.PropertyBinder;
import com.shinobicontrols.grids.supplement.TextColumnStyle;

class ImageViewColumnSpec implements ColumnSpec {

    private final PropertyBinder<Integer> propertyBinder;
    private final TextColumnStyle defaultStyle;
    private final TextColumnStyle alternateStyle;
    private final int width;

    ImageViewColumnSpec(PropertyBinder<Integer> propertyBinder, int width) {
        this.propertyBinder = propertyBinder;
        defaultStyle = new TextColumnStyle();
        alternateStyle = new TextColumnStyle();
        this.width = width;
    }

    @Override
    public void initialize(Column.Callback callback) {
    }

    @Override
    public int getItemViewType(int rowIndex) {
        return R.id.image_view_column_item_view_type;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int rowIndex) {
        final ImageViewHolder imageViewHolder = (ImageViewHolder) viewHolder;
        imageViewHolder.imageView.setImageResource(propertyBinder.bind(rowIndex));
        final TextColumnStyle style = rowIndex % 2 == 0 ? defaultStyle : alternateStyle;
        imageViewHolder.imageView.setBackgroundColor(style.getBackgroundColor());
    }

    @Override
    public int getHeaderItemViewType() {
        return com.shinobicontrols.grids.R.id.sg_header_text_view;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
    }

    @Override
    public boolean hasHeader() {
        return true;
    }

    @Override
    public void onColumnAdded(Context context) {
        final TypedArray styledAttributes = context.obtainStyledAttributes(com
                .shinobicontrols.grids.R.styleable.ShinobiGridTheme);
        defaultStyle.setBackgroundColor(styledAttributes.getColor(com.shinobicontrols.grids.R
                .styleable.ShinobiGridTheme_sg_itemBackgroundColor, Color.TRANSPARENT));
        alternateStyle.setBackgroundColor(styledAttributes.getColor(com.shinobicontrols.grids
                .R.styleable.ShinobiGridTheme_sg_itemAlternateBackgroundColor, Color
                .TRANSPARENT));
        styledAttributes.recycle();
    }

    @Override
    public Integer getWidth() {
        return width;
    }

    @Override
    public void saveState(Parcelable parcelable) {
    }

    @Override
    public void restoreState(Parcelable parcelable) {
    }

    @Override
    public Parcelable createParcelable() {
        return null;
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder {
        static class Creator implements ItemViewHolderCreator {
            @Override
            public int getItemViewType() {
                return R.id.image_view_column_item_view_type;
            }

            @Override
            public RecyclerView.ViewHolder createItemViewHolder(ViewGroup parent) {
                Context context = parent.getContext();
                ImageView imageView = new ImageView(context);
                return new ImageViewHolder(imageView);
            }
        }

        private final ImageView imageView;

        ImageViewHolder(ImageView imageView) {
            super(imageView);
            this.imageView = imageView;
        }
    }
}
