package com.thoughtworks;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

public class MemoryRepository<Student> implements Repository<Student> {
    private HashMap<String, Student> db = new HashMap<>();
    private ArrayList<String> logs = new ArrayList<>();
    private SimpleDateFormat logTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public ArrayList<String> getLogs() {
        return logs;
    }

    private String getLogTime() {
        return logTimeFormat.format(new Date());
    }

    @Override
    public boolean save(String id, Student entity) {
        boolean exists = db.containsKey(id);
        if (exists) {
            logs.add(String.format("%s [FAILED] Entity with ID: %s already exists, SAVE failed.", getLogTime(), id));
            return false;
        } else {
            db.put(id, entity);
            logs.add(String.format("%s [SUCCESS] Saved entity with ID: %s", getLogTime(), id));
            return true;
        }
    }

    @Override
    public Student get(String id) {
        boolean exists = db.containsKey(id);
        if (exists) {
            logs.add(String.format("%s [SUCCESS] Got entity with ID: %s", getLogTime(), id));
            return db.get(id);
        } else {
            logs.add(String.format("%s [FAILED] Entity with ID: %s does not exist, GET failed.", getLogTime(), id));
            return null;
        }
    }

    @Override
    public boolean delete(String id) {
        boolean exists = db.containsKey(id);
        if (exists) {
            db.remove(id);
            logs.add(String.format("%s [SUCCESS] Deleted entity with ID: %s", getLogTime(), id));
            return true;
        } else {
            logs.add(String.format("%s [FAILED] Entity with ID: %s does not exist, DELETE failed.", getLogTime(), id));
            return false;
        }
    }

    @Override
    public boolean update(String id, Student student) {
        boolean exists = db.containsKey(id);
        if (exists) {
            logs.add(String.format("%s [SUCCESS] Updated entity with ID: %s", getLogTime(), id));
            db.put(id, student);
            return false;
        } else {
            logs.add(String.format("%s [WARNING] Trying to Update a non-existing entity with ID: %s.", getLogTime(), id));
            db.put(id, student);
            logs.add(String.format("%s [SUCCESS] Saved/Updated entity with ID: %s.", getLogTime(), id));
            return true;
        }
    }

    @Override
    public Collection<Student> list() {
        return db.values();
    }
}
