package Homework_3;

import java.util.HashMap;
import java.util.Iterator;

class PhoneBook {
    private HashMap<String, String> records = new HashMap<>();

    void add(String name, String phone) {
        records.put(phone, name);
    }

    void show(String name) {
        Iterator it = this.records.entrySet().iterator();
        System.out.println("Телефоны абонента " + name + ":");
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)it.next();
            if(pair.getValue().equals(name)) System.out.println(pair.getKey());
        }
    }
}
