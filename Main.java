import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Spreadsheet spreadsheet = new Spreadsheet();
        int itemIdCounter = 1;
        int categoryIdCounter = 1;

        while (true) {
            try {
                System.out.println("\n--- Budget Management System ---");
                System.out.println("1. Add Category");
                System.out.println("2. Add Item");
                System.out.println("3. Remove Item");
                System.out.println("4. Edit Item");
                System.out.println("5. View Summary");
                System.out.println("6. View Summary by Category");
                System.out.println("7. Search Item");
                System.out.println("8. Set Monthly Budget");
                System.out.println("9. Download Expense Report");
                System.out.println("10. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter category name: ");
                        String categoryName = scanner.nextLine();
                        spreadsheet.addCategory(categoryIdCounter++, categoryName);
                        break;
                    case 2:
                        System.out.print("Enter item name: ");
                        String name = scanner.nextLine();
                        System.out.println("Available categories:");
                        spreadsheet.viewCategories();
                        boolean validCategory = false;
                        Category selectedCategory = null;

                        while (!validCategory) {
                            try {
                                System.out.print("Enter category ID: ");
                                int categoryId = scanner.nextInt();
                                scanner.nextLine();
                                for (Category category : spreadsheet.getCategories()) {
                                    if (category.getCategoryId() == categoryId) {
                                        selectedCategory = category;
                                        validCategory = true;
                                        break;
                                    }
                                }
                                if (!validCategory) {
                                    System.out.println("No category found with the given ID. Please enter a valid category ID.");
                                }
                            } catch (Exception e) {
                                System.out.println("Please enter a valid number for the category ID.");
                                scanner.nextLine();
                            }
                        }
                        boolean validCost = false;
                        float cost = 0;
                        while (!validCost) {
                            try {
                                System.out.print("Enter cost: ");
                                cost = scanner.nextFloat();
                                scanner.nextLine();
                                if (cost >= 0) {
                                    validCost = true;
                                } else {
                                    System.out.println("Cost must be a non-negative number.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Please enter a valid number for the cost.");
                                scanner.nextLine();
                            }
                        }
                        spreadsheet.addItem(itemIdCounter++, name, selectedCategory, cost);
                        break;
                    case 3:
                        System.out.println("Current items:");
                        spreadsheet.viewItems();

                        boolean validRemoveId = false;
                        int removeId = -1;

                        while (!validRemoveId) {
                            try {
                                System.out.print("Enter item ID to remove: ");
                                removeId = scanner.nextInt();
                                scanner.nextLine();

                                if (removeId > 0 && removeId <= spreadsheet.numItems()) {
                                    validRemoveId = true;
                                } else {
                                    System.out.println("Invalid Item ID. Please enter a valid item ID.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Please enter a valid number for the item ID.");
                                scanner.nextLine();
                            }
                        }
                        spreadsheet.removeItem(removeId);
                        break;
                    case 4:
                        System.out.println("Current items:");
                        spreadsheet.viewItems();

                        boolean validItem = false;
                        int editId = -1;

                        while (!validItem) {
                            try {
                                System.out.print("Enter item ID to edit: ");
                                editId = scanner.nextInt();
                                scanner.nextLine();

                                if (editId > 0 && editId <= spreadsheet.numItems()) {
                                    validItem = true;
                                } else {
                                    System.out.println("Invalid Item ID. Please enter a valid item ID.");
                                }
                            } catch (Exception e) {
                                System.out.println("Please enter a valid number for the item ID.");
                                scanner.nextLine();
                            }
                        }

                        System.out.print("Enter new name: ");
                        String newName = scanner.nextLine();

                        System.out.println("Available categories:");
                        spreadsheet.viewCategories();

                        validCategory = false;
                        Category newCategory = null;

                        while (!validCategory) {
                            try {
                                System.out.print("Enter the ID of the category you would like to switch to. If you would not like" +
                                        "to switch, enter the same ID: ");
                                int catAmount = spreadsheet.numCategories();
                                int newCategoryId = scanner.nextInt();
                                scanner.nextLine();

                                if (newCategoryId > 0 && newCategoryId <= catAmount) {
                                    for (Category category : spreadsheet.getCategories()) {
                                        if (category.getCategoryId() == newCategoryId) {
                                            newCategory = category;
                                            validCategory = true;
                                            break;
                                        }
                                    }
                                } else {
                                    System.out.println("Please enter a valid category ID.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Please enter a valid number for the category ID.");
                                scanner.nextLine();
                            }
                        }

                        validCost = false;
                        float newCost = 0;

                        while (!validCost) {
                            try {
                                System.out.print("Enter the cost. If you would not like to switch, enter the same cost: ");
                                newCost = scanner.nextFloat();
                                scanner.nextLine();
                                if (newCost >= 0) {
                                    validCost = true;
                                } else {
                                    System.out.println("Cost must be a valid number.");
                                }
                            } catch (Exception e) {
                                System.out.println("Please enter a valid number for the cost.");
                                scanner.nextLine();
                            }
                        }
                        spreadsheet.editItem(editId, newName, newCategory, newCost);
                        break;
                    case 5:
                        spreadsheet.viewSummary();
                        break;
                    case 6:
                        spreadsheet.viewSummaryByCategory();
                        break;
                    case 7:
                        System.out.print("Enter item name to search: ");
                        String searchName = scanner.nextLine();
                        spreadsheet.searchItem(searchName);
                        break;
                    case 8:
                        System.out.print("Enter monthly budget: ");
                        float budget = scanner.nextFloat();
                        spreadsheet.setMonthlyBudget(budget);
                        break;
                    case 9:
                        spreadsheet.downloadExpenseReport();
                        break;
                    case 10:
                        System.out.println("Exiting. Goodbye!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Please enter a number between 1 and 10.");
                scanner.nextLine();
            }
        }
    }
}