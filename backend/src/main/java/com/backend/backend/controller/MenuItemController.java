package com.backend.backend.controller;

import com.backend.backend.model.MenuItem;
import com.backend.backend.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/menu")
public class MenuItemController {

    @Autowired
    private MenuItemService menuService;

    @PostMapping
    public ResponseEntity<String> addMenuItem(@RequestBody MenuItem menuItem, @RequestParam String categoryId) throws ExecutionException, InterruptedException {
        String response = menuService.createMenuItem(menuItem, categoryId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public List<MenuItem> getAllMenuItems() throws ExecutionException, InterruptedException {
        return menuService.getAllMenuItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable String id) throws ExecutionException, InterruptedException {
        MenuItem menuItem = menuService.getMenuItemById(id);
        return menuItem != null ? ResponseEntity.ok(menuItem) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMenuItem(@PathVariable String id, @RequestBody MenuItem menuItem, @RequestParam String categoryId) throws ExecutionException, InterruptedException {
        String response = menuService.updateMenuItem(id, menuItem, categoryId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable String id) throws ExecutionException, InterruptedException {
        menuService.deleteMenuItem(id);
        return ResponseEntity.noContent().build();
    }
}
