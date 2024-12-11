import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Spreadsheet {
    private List<Item> items;
    private List<Category> categories;
    private Map<Integer, Float> categoryTotals;
    private float monthlyBudget;

    public Spreadsheet() {
        this.items = new ArrayList<>();
        this.categories = new ArrayList<>();
        this.categoryTotals = new HashMap<>();
        this.monthlyBudget = 0;
    }

    public void addCategory(int categoryId, String categoryName) {
        for (Category category : categories) {
            if (category.getCategoryName().equalsIgnoreCase(categoryName)) {
                System.out.println("Category with this name already exists. Please choose a different name.");
                return;
            }
        }
        Category newCategory = new Category(categoryId, categoryName);
        categories.add(newCategory);
        categoryTotals.put(categoryId, 0f);
        System.out.println("Category added successfully.");
    }

    public void addItem(int itemId, String name, Category category, float cost) {
        if (!categoryTotals.containsKey(category.getCategoryId())) {
            System.out.println("Invalid category. Please add the category first.");
            return;
        }
        items.add(new Item(itemId, name, category, cost));
        categoryTotals.put(category.getCategoryId(), categoryTotals.get(category.getCategoryId()) + cost);
        System.out.println("Item added successfully.");
    }

    public void removeItem(int itemId) {
        Item itemToRemove = null;
        for (Item item : items) {
            if (item.getItemId() == itemId) {
                itemToRemove = item;
                break;
            }
        }
        if (itemToRemove != null) {
            items.remove(itemToRemove);
            categoryTotals.put(itemToRemove.getCategory().getCategoryId(), categoryTotals.get(itemToRemove.getCategory().getCategoryId()) - itemToRemove.getCost());
            System.out.println("Item removed successfully.");
        } else {
            System.out.println("Item not found.");
        }
    }

    public void editItem(int itemId, String newName, Category newCategory, float newCost) {
        if (!categoryTotals.containsKey(newCategory.getCategoryId())) {
            System.out.println("Invalid category. Please add the category first.");
            return;
        }
        for (Item item : items) {
            if (item.getItemId() == itemId) {
                categoryTotals.put(item.getCategory().getCategoryId(), categoryTotals.get(item.getCategory().getCategoryId()) - item.getCost());
                item.setCategory(newCategory);
                item.setCost(newCost);
                categoryTotals.put(newCategory.getCategoryId(), categoryTotals.get(newCategory.getCategoryId()) + newCost);
                System.out.println("Item updated successfully.");
                return;
            }
        }
        System.out.println("Item not found.");
    }

    public void viewSummary() {
        if (items.isEmpty()) {
            System.out.println("No items to display.");
            return;
        }

        System.out.println("Summary:");
        System.out.println("-----------------------------------------");
        System.out.printf("%-10s %-10s %-10s %-10s%n", "Item ID", "Name", "Category", "Cost");
        System.out.println("-----------------------------------------");

        float totalSpent = 0;
        for (Item item : items) {
            System.out.println(item);
            totalSpent += item.getCost();
        }

        System.out.println("--------------------------------");
        System.out.println("Total spent: $" + totalSpent);

        if (monthlyBudget == 0) {
            System.out.println("You have not set a budget.");
        } else if (totalSpent > monthlyBudget) {
            System.out.println("Warning: You have exceeded your budget!");
        } else {
            System.out.println("You are under your budget.");
        }
    }

    public void viewSummaryByCategory() {
        System.out.println("Category Breakdown:");
        for (Category category : categories) {
            System.out.println(category.getCategoryName() + ": $" + categoryTotals.get(category.getCategoryId()));
        }
    }

    public void searchItem(String name) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                System.out.println(item);
                return;
            }
        }
        System.out.println("Item not found.");
    }

    public void setMonthlyBudget(float budget) {
        this.monthlyBudget = budget;
        System.out.println("Monthly budget set to $" + budget);
    }

    public void downloadExpenseReport() {
        String home = System.getProperty("user.home");
        String directory = "";

        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            directory = home + "\\Downloads\\export_my_budget_report.txt";
        } else if (os.contains("mac") || os.contains("nix") || os.contains("nux")) {
            directory = home + "/Downloads/export_my_budget_report.txt";
        }

        try (FileWriter w = new FileWriter(directory)) {
            w.write("Expense Report:\n");
            for (Item item : items) {
                w.write(item.toString() + "\n");
            }
            w.write("Report downloaded successfully.\n");
            System.out.println("Expense report exported to: " + directory);
        } catch (IOException e) {
            System.out.println("Error while exporting the report: " + e.getMessage());
        }
    }

    public void viewCategories() {
        for (Category category : categories) {
            System.out.println(category);
        }
    }

    public void viewItems(){
        for (Item item : items) {
            System.out.println(item);
        }
    }

    public int numCategories(){
        return categories.size();
    }

    public int numItems(){
        return items.size();
    }

    public List<Category> getCategories(){
        return categories;
    }
}
