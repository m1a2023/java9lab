package com.example.task02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class SavedList<E extends Serializable> 
        extends AbstractList<E> implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<E> list;
    private File file;

    public SavedList(File file) {
        this.file = file;

        if (!file.exists() || file.length() == 0) {
            this.list = new ArrayList<>();
            serialize();
        } else {
            try {
                this.list = deserialize(file);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                this.list = new ArrayList<>(); 
            }
        }
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        E e = list.set(index, element);
        serialize();
        return e;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element) {
        list.add(index, element);
        serialize();
    }

    @Override
    public E remove(int index) {
        E e = list.remove(index);
        serialize();
        return e;
    }

    @Override
    public boolean remove(Object o) {
        boolean e = list.remove(o);
        if (e) {
            serialize();
        }
        return e;
    }

    private void serialize() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private List<E> deserialize(File file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<E>) ois.readObject();
        }
    }
}
