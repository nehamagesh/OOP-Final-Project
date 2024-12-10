class Item {
    private int itemId;
    private String name;
    private Category category;
    private float cost;

    public Item(int itemId, String name, Category category, float cost) {
        this.itemId = itemId;
        this.name = name;
        this.category = category;
        this.cost = cost;
    }

    public int getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return String.format("| %-9d | %-20s | %-12s | $%-9.2f |",
                        itemId, name, category.getCategoryName(), cost);
    }
}