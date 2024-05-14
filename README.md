**To-Do List App README**

---

### Overview
This To-Do List app is designed to help users organize their tasks efficiently. Developed using Kotlin, Room Database, and Jetpack Compose, it offers a user-friendly interface for managing tasks with features like adding, editing, marking as complete, and deleting tasks. Additionally, it includes a search bar on the home screen for easy navigation through the wish list.

### Features

1. **Add Wish:** Users can add wishes to their list by clicking on the floating action button (FAB) located at the bottom right corner of the main page.

2. **Delete Wishes:** Added wishes are displayed on the main page. Swiping a wish from right to left deletes it from the list.

3. **Edit Wish:** To edit a wish, simply click on it. This action opens up the wish details for modification.

4. **Mark as Completed:** Each wish has two checkboxes for marking it as completed or partially completed. This feature changes the appearance of the wish accordingly.

5. **Search Functionality:** The home screen includes a search bar for easily searching through the wish list.

### Implementation Details

#### Technologies Used:
- **Kotlin:** The primary programming language used for app development.
- **Room Database:** For local storage and management of wish data.
- **Jetpack Compose:** Used for building the user interface components with a modern and declarative approach.

#### App Flow:

1. **Main Page:** 
    - Displays the list of wishes.
    - Provides a floating action button (FAB) for adding new wishes.
    - Includes a search bar for searching through the wish list.

2. **Adding a Wish:** 
    - Clicking on the FAB opens a form for adding wish details, such as title, description, and completion status.

3. **Editing a Wish:**
    - Clicking on an existing wish opens a form pre-filled with its details, allowing users to modify them.

4. **Deleting a Wish:**
    - Swiping a wish from right to left removes it from the list.

5. **Marking as Completed:**
    - Each wish has checkboxes for marking it as completed or partially completed, changing its appearance accordingly.

6. **Searching Wishes:**
    - Users can enter search queries in the search bar to filter and find specific wishes in the list.

### How to Use:

1. **Clone the Repository:**
    ```
    git clone https://github.com/Devesh-Ingale/TODO_List
    ```

2. **Open in Android Studio:**
    - Import the project into Android Studio.

3. **Build and Run:**
    - Build and run the project on an Android emulator or a physical device.

4. **Interact with the App:**
    - Add wishes using the floating action button.
    - Edit wishes by clicking on them.
    - Mark wishes as completed or partially completed using the checkboxes.
    - Swipe wishes from right to left to delete them.
    - Use the search bar to search for specific wishes.

### Dependencies:
- **Kotlin:** Kotlin language dependencies.
- **Jetpack Compose:** Jetpack Compose library dependencies.
- **Room Database:** Room Database dependencies.

### Contributors:
- **Devesh Ingale**



---

Feel free to reach out for any further assistance or clarification!
