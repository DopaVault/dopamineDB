package com.api.dopaminedb.controller;

import com.api.dopaminedb.storageEngine.LevelDBStorageEngine;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/leveldb")
@RequiredArgsConstructor
public class TestController {

  @Autowired
  private LevelDBStorageEngine levelDBStorageEngine;


  @PostMapping("/create")
  public ResponseEntity<String> createFile(@RequestParam String filePath) {
    try {
      String response = levelDBStorageEngine.createFile(filePath);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      return ResponseEntity.status(500).body("Error creating file: " + e.getMessage());
    }
  }


  @PostMapping("/save")
  public ResponseEntity<String> saveData(@RequestParam String key, @RequestParam String value) {
    try {
      levelDBStorageEngine.saveData(key, value);
      return ResponseEntity.ok("Data saved successfully");
    } catch (Exception e) {
      return ResponseEntity.status(500).body("Error saving data: " + e.getMessage());
    }
  }


  @GetMapping("/get")
  public ResponseEntity<String> getData(@RequestParam String key) {
    try {
      String value = levelDBStorageEngine.get(key);
      if (value != null) {
        return ResponseEntity.ok(value);
      } else {
        return ResponseEntity.status(404).body("Data not found for key: " + key);
      }
    } catch (Exception e) {
      return ResponseEntity.status(500).body("Error retrieving data: " + e.getMessage());
    }
  }


  @DeleteMapping("/delete")
  public ResponseEntity<String> deleteData(@RequestParam String key) {
    try {
      levelDBStorageEngine.delete(key);
      return ResponseEntity.ok("Data deleted successfully");
    } catch (Exception e) {
      return ResponseEntity.status(500).body("Error deleting data: " + e.getMessage());
    }
  }


  @GetMapping("/exists")
  public ResponseEntity<String> checkExists(@RequestParam String key) {
    try {
      boolean exists = levelDBStorageEngine.exists(key);
      return ResponseEntity.ok(exists ? "Key exists" : "Key does not exist");
    } catch (Exception e) {
      return ResponseEntity.status(500).body("Error checking existence: " + e.getMessage());
    }
  }


  @PostMapping("/close")
  public ResponseEntity<String> closeDB() {
    try {
      levelDBStorageEngine.close();
      return ResponseEntity.ok("LevelDB closed successfully");
    } catch (IOException e) {
      return ResponseEntity.status(500).body("Error closing DB: " + e.getMessage());
    }
  }
}
