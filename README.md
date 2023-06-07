#  Warehouse Search System: Furniture Warehouse


## Task Requirements
### The application developed must meet the following requirements:

- After launching, display the following:
- The application name, which should correspond to your subject area, version, and creation date
- Information about the developer (e.g., name and email address)
- A list of the available commands (menu)
- Wait for a command to be input and the additional parameters for executing it.
- After entering the command and the required parameters, perform the calculations, print the result, and wait for the next command to be entered.
- One of the commands must exit the application.
- Read the inventory data from the pre-formatted text file.
- Allow users to search for products in the inventory based on various parameters.
- When a user searches for a product, display a list of matching products with their details (e.g., ID, name, category, price, quantity, etc.).
- If no matching products exist, display a message indicating no products were found.
- Allow users to see a list of all the products in the inventory, sorted by the selected order.
- Errors should be handled gracefully, displaying the appropriate error messages to the user.

## Functionality
### The application must be able to:

- Perform the tasks specified in the project requirements
- Take input from the user and provide output as expected
- Run efficiently, with minimal resource usage and no unnecessary delays or hang-ups

##  Code Quality
- The source code should be well-organized, readable, and maintainable. 
- It should follow best practices and adhere to the principles of object-oriented programming.
- 
- Use a Git repository for the source code. 
- This will allow the mentor to review the commit history and see the progress of the project over time. 
- The mentor will consider factors such as the frequency and quality of commits.

##  Testing
- The application should be tested thoroughly to ensure that it performs as expected and that all edge cases have been accounted for.


| Criteria / Points                                                                                                                          | 0 point                                                                           | 8 points	                                                                                             | 12 points                                                                                                          |
|--------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------|
| Architectural layers	                                                                                                                      | The application does not contain dedicated architectural layers.                  | Architectural layers are weakly expressed, and some layers are missing.                               | All architectural layers are present.                                                                              |  
| Application design                                                                                                                         | The application is not designed according to the interface-implementation scheme. | Some public functions are separated into specific interfaces.                                         | In each layer, public functions (actions) are declared via interfaces.                                             |
| Interaction between layers                                                                                                                 | Low coupling for interaction between layers is not used.                          | Low coupling is sometimes used to interact between layers.                                            | Low coupling is used to interact between layers.                                                                   |
| [Java code convention](https://www.oracle.com/technetwork/java/codeconventions-150003.pdf): proper naming of identifiers, access modifiers | The code does not comply with the rules; there are many errors.                   | The code does not fully comply with the rules.                                                        | The code fully complies with the rules.                                                                            |
| Copy-paste code                                                                                                                            | Copy-paste is present.                                                            | Copy-paste is present in 1 case or negligible.                                                        | Copy-paste is absent.                                                                                              |
| Quantity and quality of tests                                                                                                              | There are no tests in the application.                                            | Tests are available but in a non-working or unfinished state.                                         | The application contains the required number of tests (mentioned in the Data Access Layer section of this course). |
| Error handling in the application                                                                                                          | Exceptions in code are handled incorrectly.                                       | Correct exception handling is partially implemented.                                                  | Exceptions in code are handled correctly.                                                                          |
| Git history                                                                                                                                | There is no history in the Git repository.                                        | The Git repository has a history, but the dates do not correlate with the submitted development plan. | The Git repository has a history, but the dates do not correlate with the submitted development plan.              |


## Available commands after launching:

|Available commands:
 - add: Add a product
 - view: View product details
 - update: Update a product
 - delete: Delete a product
 - search: Search for products
 - save: save changes to the database
 - list: List all products
 - commands: warehouse menu
 - exit:   Exit the application
           
