# OOP-Final-Project

**Names:**
Neha Magesh (nm3818@nyu.edu), Alexandra Przysucha (ajp9010@nyu.edu), Oluwapelumi Adesiyan (oea9714@nyu.edu)

**Project Summary:** A command line budgeting tool that functions as a spreadsheet. Users can input their purchases and can perform a variety of functions.

**Classes**
- Item (Contains int itemID, String name, Category category, float cost)
- Category(Contains int categoryID, String name)
- Spreadsheet (Contains List<Item>)

**Methods**
- Generic getters and setters
- createCategory(String name, int categoryID): creates a new Category instance
- createItem(int itemID, String name, Category category, float cost): creates a new Item instance
- addCharge(int itemID, String name, float cost, int categoryID): adds a charge to the spreadsheet and populates the relevant information
- removeCharge (int itemID): remove a charge from the spreadsheet and removes the charge from all records
- editCharge(int itemID): edit the cost or category of an item
- totalAllPurchases(): outputs the current running total of all purchases logged into the spreadsheet
- totalByCategory(int categoryID):  outputs the current total of the designated category
- viewDashboard(): displays a breakdown of purchases made in each category, can be filtered by time
- viewCategory(int categoryID): displays all items under the designated category
- searchForItem(string itemName/float cost): search for a specific item in the spreadsheet using the name of them item or the price of the purchased item

**User Walkthrough:**
- User enters the system
- User adds their items to spreadsheet
- User can view/edit/delete items & balances
