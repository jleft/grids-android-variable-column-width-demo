package com.shinobicontrols.grids.demo.variablecolumnwidth;

import java.util.List;

class ShoppingCartItem {
    private static final int NUMBER_OF_ITEMS = 10;
    private static final double TAX_PERCENTAGE_RATE = 0.2;

    private static final String[] titles = new String[]{"shinobi Cuddly Toy", "shinobi Laptop " +
            "Bag", "shinobi Backpack", "shinobi T-Shirt", "shinobi Hoodie", "shinobi Polo Shirt",
            "shinobi Umbrella", "shinobi Mouse Mat", "shinobi Soft Ball", "shinobi Mug",};

    private static final String[] descriptions = new String[]{"A soft cuddly toy, great for " +
            "children", "A strong bag to keep your laptop safe", "A trendy backpack allowing you " +
            "to travel in style to your next developer conference", "A cotton t-shirt, with " +
            "shinobi branding, available in sizes S, M, L, XL", "A cotton hooded top, with " +
            "shinobi branding, available in sizes S, M, L, XL", "A cotton polo shirt, with shinobi " +
            "branding, available in sizes S, M, L, XL", "Keep yourself dry with this large " +
            "golf size umbrella, with shinobi branding", "A foam mouse mat, with integrated" +
            " wrist support and shinobi branding", "A soft ball - great fun for kids and " +
            "pets alike", "Your morning coffee will taste great from this branded porcelain " +
            "mug"};
    private static final double[] prices = new double[]{1.2, 3.4, 7.5, 1.4, 4.6, 7.3, 1.4, 6.8,
            5.7, 3.7};
    private static final int[] quantities = new int[]{3, 5, 9, 1, 4, 6, 7, 4, 8, 9};

    final String title;
    final String description;
    final int imageId;
    final double price;
    final double taxAmount;
    final int quantity;
    final double totalPrice;

    private ShoppingCartItem (ShoppingCartItem.Builder builder) {
        this.title = builder.title;
        this.description = builder.description;
        this.imageId = builder.imageId;
        this.price = builder.price;
        this.taxAmount = price * builder.taxPercentageRate;
        this.quantity = builder.quantity;
        this.totalPrice = (price + taxAmount) * quantity;
    }

    private static class Builder {
        private final String title;
        private String description = "";
        private int imageId = 0;
        private double price = 0.0;
        private double taxPercentageRate = 0.0;
        private int quantity = 0;

        Builder(String title) {
            this.title = title;
        }

        ShoppingCartItem.Builder description(String description) {
            this.description = description;
            return this;
        }
        ShoppingCartItem.Builder imageId(int imageId) {
            this.imageId = imageId;
            return this;
        }
        ShoppingCartItem.Builder price(double price) {
            this.price = price;
            return this;
        }
        ShoppingCartItem.Builder tax(double tax) {
            this.taxPercentageRate = tax;
            return this;
        }
        ShoppingCartItem.Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }
        ShoppingCartItem build() {
            return new ShoppingCartItem(this);
        }
    }

    static void populateShoppingCartItemsList(List<ShoppingCartItem> items) {
        for (int i = 0; i < NUMBER_OF_ITEMS; i++) {
            items.add(new ShoppingCartItem.Builder(titles[i])
                    .description(descriptions[i])
                    .imageId(R.drawable.shinobi)
                    .price(prices[i])
                    .tax(TAX_PERCENTAGE_RATE)
                    .quantity(quantities[i])
                    .build());
        }
    }
}
