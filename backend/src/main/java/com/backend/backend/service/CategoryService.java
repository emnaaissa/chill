package com.backend.backend.service;

import com.backend.backend.model.Category;
import com.backend.backend.model.MenuItem;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class CategoryService {

    // Initialize Firestore database instance
    private final Firestore db = FirestoreClient.getFirestore();

    // Fetch all categories and their menuItems
    public List<Category> getAllCategories() throws ExecutionException, InterruptedException {
        List<Category> categories = new ArrayList<>();

        // Fetch categories from Firestore (Asynchronous query)
        ApiFuture<QuerySnapshot> future = db.collection("categories").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments(); // Blocking call to get documents

        // Loop through all the documents (categories)
        for (QueryDocumentSnapshot doc : documents) {
            // Convert Firestore document into Category object
            Category category = doc.toObject(Category.class);

            // Fetch the menuItems subcollection for each category (Asynchronous query)
            ApiFuture<QuerySnapshot> menuItemsFuture = db.collection("categories")
                    .document(doc.getId())
                    .collection("menuItems")
                    .get(); // Asynchronous query for menu items
            List<QueryDocumentSnapshot> menuItemsDocs = menuItemsFuture.get().getDocuments(); // Blocking call to get documents

            // Loop through menu items and convert them to MenuItem objects
            List<MenuItem> menuItems = new ArrayList<>();
            for (QueryDocumentSnapshot menuItemDoc : menuItemsDocs) {
                menuItems.add(menuItemDoc.toObject(MenuItem.class));
            }

            // Set the menuItems to the category object
            category.setMenuItems(menuItems);
            categories.add(category);
        }
        return categories; // Return list of categories with their associated menuItems
    }

    // Fetch a category by its ID and include its menuItems
    public Category getCategoryById(String id) throws ExecutionException, InterruptedException {
        // Fetch category document by ID (Blocking call)
        DocumentSnapshot doc = db.collection("categories").document(id).get().get();
        if (doc.exists()) {
            // Convert Firestore document into Category object
            Category category = doc.toObject(Category.class);

            // Fetch the menuItems subcollection for the category (Asynchronous query)
            ApiFuture<QuerySnapshot> menuItemsFuture = db.collection("categories")
                    .document(id)
                    .collection("menuItems")
                    .get(); // Asynchronous query for menu items
            List<QueryDocumentSnapshot> menuItemsDocs = menuItemsFuture.get().getDocuments(); // Blocking call to get documents

            // Loop through menu items and convert them to MenuItem objects
            List<MenuItem> menuItems = new ArrayList<>();
            for (QueryDocumentSnapshot menuItemDoc : menuItemsDocs) {
                menuItems.add(menuItemDoc.toObject(MenuItem.class));
            }

            // Set the menuItems to the category object
            category.setMenuItems(menuItems);
            return category; // Return the category with its menuItems
        }
        return null; // Return null if the category does not exist
    }

    // Create a new category
    public String createCategory(Category category) throws ExecutionException, InterruptedException {
        // Add the category document to Firestore
        ApiFuture<WriteResult> future = db.collection("categories").document().set(category);
        WriteResult result = future.get(); // Blocking call to get the result
        return result.getUpdateTime().toString(); // Return the timestamp of the update
    }

    // Update an existing category
    public String updateCategory(String id, Category category) throws ExecutionException, InterruptedException {
        // Update the category document in Firestore
        ApiFuture<WriteResult> future = db.collection("categories").document(id).set(category);
        WriteResult result = future.get(); // Blocking call to get the result
        return result.getUpdateTime().toString(); // Return the timestamp of the update
    }

    // Delete a category by its ID
    public String deleteCategory(String id) throws ExecutionException, InterruptedException {
        // Delete the category document from Firestore
        ApiFuture<WriteResult> future = db.collection("categories").document(id).delete();
        WriteResult result = future.get(); // Blocking call to get the result
        return result.getUpdateTime().toString(); // Return the timestamp of the delete operation
    }

    // Add a menu item to a category
    public String addMenuItemToCategory(String categoryId, MenuItem menuItem) throws ExecutionException, InterruptedException {
        // Add the menu item to the menuItems subcollection of the specified category
        ApiFuture<WriteResult> future = db.collection("categories")
                .document(categoryId)
                .collection("menuItems")
                .document()
                .set(menuItem);
        WriteResult result = future.get(); // Blocking call to get the result
        return result.getUpdateTime().toString(); // Return the timestamp of the update
    }

    // Update a menu item of a category
    public String updateMenuItem(String categoryId, String menuItemId, MenuItem menuItem) throws ExecutionException, InterruptedException {
        // Update the menu item document in the specified category's menuItems subcollection
        ApiFuture<WriteResult> future = db.collection("categories")
                .document(categoryId)
                .collection("menuItems")
                .document(menuItemId)
                .set(menuItem);
        WriteResult result = future.get(); // Blocking call to get the result
        return result.getUpdateTime().toString(); // Return the timestamp of the update
    }

    // Delete a menu item from a category
    public String deleteMenuItem(String categoryId, String menuItemId) throws ExecutionException, InterruptedException {
        // Delete the menu item document from the specified category's menuItems subcollection
        ApiFuture<WriteResult> future = db.collection("categories")
                .document(categoryId)
                .collection("menuItems")
                .document(menuItemId)
                .delete();
        WriteResult result = future.get(); // Blocking call to get the result
        return result.getUpdateTime().toString(); // Return the timestamp of the delete operation
    }
}
