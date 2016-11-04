package com.shinobicontrols.grids.demo.variablecolumnwidth;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.widget.TextView;

import com.shinobicontrols.grids.core.Column;
import com.shinobicontrols.grids.core.ColumnSpec;
import com.shinobicontrols.grids.supplement.HeaderTextViewHolder;
import com.shinobicontrols.grids.supplement.PropertyBinder;
import com.shinobicontrols.grids.supplement.TextColumnStyle;

import java.text.DecimalFormat;

class CurrencyColumnSpec implements ColumnSpec {
    private final CharSequence columnTitle;
    private final PropertyBinder<Double> propertyBinder;
    private final DecimalFormat decimalFormat;
    private final TextColumnStyle defaultStyle;
    private final TextColumnStyle alternateStyle;
    private final TextColumnStyle headerStyle;
    private final int width;

    CurrencyColumnSpec(CharSequence columnTitle, PropertyBinder<Double> propertyBinder,
                       int width, DecimalFormat decimalFormat) {
        this.columnTitle = columnTitle;
        this.propertyBinder = propertyBinder;
        defaultStyle = new TextColumnStyle();
        alternateStyle = new TextColumnStyle();
        headerStyle = new TextColumnStyle();
        this.width = width;
        this.decimalFormat = decimalFormat;
    }

    @Override
    public void initialize(Column.Callback callback) {
    }

    @Override
    public int getItemViewType(int rowIndex) {
        return com.shinobicontrols.grids.R.id.sg_text_view;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int rowIndex) {
        final double currencyValue = propertyBinder.bind(rowIndex);
        final TextView textView = (TextView) viewHolder.itemView;
        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.END);
        textView.setText(decimalFormat.format(currencyValue));
        final TextColumnStyle style = rowIndex % 2 == 0 ? defaultStyle : alternateStyle;
        textView.setBackgroundColor(style.getBackgroundColor());
        textView.setPadding(style.getPaddingLeft(), style.getPaddingTop(),
                style.getPaddingRight(), style.getPaddingBottom());
    }

    @Override
    public int getHeaderItemViewType() {
        return com.shinobicontrols.grids.R.id.sg_header_text_view;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder viewHolder) {
        HeaderTextViewHolder headerTextViewHolder = (HeaderTextViewHolder) viewHolder;
        headerTextViewHolder.headerTextView.setText(columnTitle);
        headerTextViewHolder.headerTextView.setPadding(headerStyle.getPaddingLeft(),
                headerStyle.getPaddingTop(), headerStyle.getPaddingRight(),
                headerStyle.getPaddingBottom());
    }

    @Override
    public boolean hasHeader() {
        return true;
    }

    @Override
    public void onColumnAdded(Context context) {
        final int DEFAULT_PADDING = 5;
        final TypedArray styledAttributes = context.obtainStyledAttributes(com.shinobicontrols
                .grids.R.styleable.ShinobiGridTheme);
        // We will use the same padding values for header, data rows
        final int paddingLeft = styledAttributes.getDimensionPixelSize(R.styleable
                .ShinobiGridTheme_sg_itemPaddingLeft, DEFAULT_PADDING);
        final int paddingTop = styledAttributes.getDimensionPixelSize(R.styleable
                .ShinobiGridTheme_sg_itemPaddingTop, DEFAULT_PADDING);
        final int paddingRight = styledAttributes.getDimensionPixelSize(R.styleable
                .ShinobiGridTheme_sg_itemPaddingRight, DEFAULT_PADDING);
        final int paddingBottom = styledAttributes.getDimensionPixelSize(R.styleable
                .ShinobiGridTheme_sg_itemPaddingBottom, DEFAULT_PADDING);

        defaultStyle.setBackgroundColor(styledAttributes.getColor(com.shinobicontrols.grids.R
                .styleable.ShinobiGridTheme_sg_itemBackgroundColor, Color.TRANSPARENT));
        defaultStyle.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        alternateStyle.setBackgroundColor(styledAttributes.getColor(com.shinobicontrols.grids.R
                .styleable.ShinobiGridTheme_sg_itemAlternateBackgroundColor, Color.TRANSPARENT));
        alternateStyle.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        headerStyle.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
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
}
