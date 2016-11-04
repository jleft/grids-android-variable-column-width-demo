package com.shinobicontrols.grids.demo.variablecolumnwidth;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;

import com.shinobicontrols.grids.core.AdapterSpec;
import com.shinobicontrols.grids.core.Column;
import com.shinobicontrols.grids.core.ShinobiGridView;
import com.shinobicontrols.grids.supplement.PropertyBinder;
import com.shinobicontrols.grids.supplement.TextColumnSpec;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class VariableColumnWidthActivity extends Activity {

    private final List<ShoppingCartItem> shoppingCartItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variable_column_width);
        final ShinobiGridView shinobiGridView = (ShinobiGridView) findViewById(R.id.my_grid);
        // TODO: IMPORTANT! Trial users MUST replace <trial_license_key_here> with your trial
        // license key
        shinobiGridView.setTrialLicenseKey("<trial_license_key_here>");
        ShoppingCartItem.populateShoppingCartItemsList(shoppingCartItems);
        shinobiGridView.setAdapterSpec(new AdapterSpec() {
            @Override
            public int getRowCount() {
                return shoppingCartItems.size();
            }
        });
        shinobiGridView.registerItemViewHolderCreator(new ImageViewColumnSpec.ImageViewHolder
                .Creator());
        shinobiGridView.setItemSelectionMode(null);
        shinobiGridView.getDefaultHeaderItemDecoration().setLineThickness(getResources()
                .getDimensionPixelSize(R.dimen.item_decoration_line_width));
        shinobiGridView.getDefaultDataItemDecoration().setLineThickness(getResources()
                .getDimensionPixelSize(R.dimen.item_decoration_line_width));
        final int rowHeight = getResources().getDimensionPixelSize(R.dimen.default_row_height);
        shinobiGridView.setDefaultRowHeight(rowHeight);
        createAndAddColumns(shinobiGridView);
    }

    private void createAndAddColumns(ShinobiGridView shinobiGridView) {
        final DecimalFormat decimalFormat = new DecimalFormat(getString(R.string
                .currency_format));
        final TextColumnSpec titleColumnSpec = new TextColumnSpec(getString(R.string.item_column_title),
                new PropertyBinder<CharSequence>() {
            @Override
            public CharSequence bind(int rowIndex) {
                ShoppingCartItem shoppingCartItem = shoppingCartItems.get(rowIndex);
                return shoppingCartItem.title;
            }
        });
        final int titleColumnWidth = getResources().getDimensionPixelSize(R.dimen
                .title_column_width);
        titleColumnSpec.setWidth(titleColumnWidth);

        shinobiGridView.addColumn(Column.create(titleColumnSpec));

        final TextColumnSpec descriptionColumnSpec = new TextColumnSpec(getString(R.string.item_details_column_title),
                new PropertyBinder<CharSequence>() {
            @Override
            public CharSequence bind(int rowIndex) {
                ShoppingCartItem shoppingCartItem = shoppingCartItems.get(rowIndex);
                return shoppingCartItem.description;
            }
        });
        shinobiGridView.addColumn(Column.create(descriptionColumnSpec));

        final ImageViewColumnSpec imageViewColumnSpec = new ImageViewColumnSpec(
                new PropertyBinder<Integer>() {
            @Override
            public Integer bind(int rowIndex) {
                final ShoppingCartItem shoppingCartItem = shoppingCartItems.get(rowIndex);
                return shoppingCartItem.imageId;
            }
        }, getResources().getDimensionPixelSize(R.dimen.image_column_width));
        shinobiGridView.addColumn(Column.create(imageViewColumnSpec));

        final CurrencyColumnSpec priceColumnSpec = new CurrencyColumnSpec(getString(R.string.price_column_title),
                new PropertyBinder<Double>() {
            @Override
            public Double bind(int rowIndex) {
                ShoppingCartItem shoppingCartItem = shoppingCartItems.get(rowIndex);
                return shoppingCartItem.price;
            }
        }, getResources().getDimensionPixelSize(R.dimen.currency_column_width) , decimalFormat);
        shinobiGridView.addColumn(Column.create(priceColumnSpec));

        final CurrencyColumnSpec taxColumnSpec = new CurrencyColumnSpec(getString(R.string.tax_column_title),
                new PropertyBinder<Double>() {
            @Override
            public Double bind(int rowIndex) {
                ShoppingCartItem shoppingCartItem = shoppingCartItems.get(rowIndex);
                return shoppingCartItem.taxAmount;
            }
        }, getResources().getDimensionPixelSize(R.dimen.currency_column_width) , decimalFormat);
        shinobiGridView.addColumn(Column.create(taxColumnSpec));

        final TextColumnSpec quantityColumnSpec = new TextColumnSpec(getString(R.string.qty_column_title),
                new PropertyBinder<CharSequence>() {
            @Override
            public CharSequence bind(int rowIndex) {
                ShoppingCartItem shoppingCartItem = shoppingCartItems.get(rowIndex);
                return Integer.toString(shoppingCartItem.quantity);
            }
        });
        final int quantityColumnWidth = getResources().getDimensionPixelSize(R.dimen
                .quantity_column_width);
        quantityColumnSpec.setWidth(quantityColumnWidth);
        quantityColumnSpec.getDefaultStyle().setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        quantityColumnSpec.getAlternateStyle().setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        shinobiGridView.addColumn(Column.create(quantityColumnSpec));

        CurrencyColumnSpec totalPriceColumnSpec = new CurrencyColumnSpec(getString(R.string.total_columnn_title),
                new PropertyBinder<Double>() {
            @Override
            public Double bind(int rowIndex) {
                ShoppingCartItem shoppingCartItem = shoppingCartItems.get(rowIndex);
                return shoppingCartItem.totalPrice;
            }
        }, getResources().getDimensionPixelSize(R.dimen.currency_column_width) , decimalFormat);
        shinobiGridView.addColumn(Column.create(totalPriceColumnSpec));
    }
}
