package com.stream.wassup.recycler_items;

public class ChatData {
    String Data;
    int TYPE;

    public ChatData(String Data, int TYPE) {
        this.Data = Data;
        this.TYPE = TYPE;
    }

    public String getData() {
        return this.Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }

    public int getTYPE() {
        return this.TYPE;
    }

    public void setTYPE(int TYPE) {
        this.TYPE = TYPE;
    }
}
