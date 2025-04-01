package com.api.dopaminedb.storageEngine;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBFactory;
import org.iq80.leveldb.Options;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import static com.api.dopaminedb.constants.LevelDBConfigConstant.CREATED_FILE;
import static org.fusesource.leveldbjni.JniDBFactory.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class LevelDBStorageEngine {

  private DB db;


  public String createFile(String filePath) {
    try {
      Options options = new Options();
      options.createIfMissing(true);
      db = factory.open(new File(filePath), options);
    } catch (IOException e) {
      throw new RuntimeException("Error in Creating the File in Dopamine DB", e);
    }
    return CREATED_FILE;

  }

  public void saveData(String key, String value) {
    db.put(key.getBytes(), value.getBytes());
  }


  public String get(String key) {
    byte[] value = db.get(key.getBytes());
    return value == null ? null : new String(value);
  }

  public void delete(String key) {
    db.delete(key.getBytes());
  }

  public boolean exists(String key) {
    byte[] value = db.get(key.getBytes());
    return value != null;
  }

  private byte[] bytes(String str) {
    return str.getBytes();
  }


  private String asString(byte[] bytes) {
    return new String(bytes);
  }

  public void close() throws IOException {
    if (db != null) {
      db.close();
    }
  }

  @PostConstruct
  public void initaliseDopamine(){
    createFile("dopamine.db");
  }
}
