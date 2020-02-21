package deprecated.classes;

import java.util.ArrayList;

public class List3D<T> {

    private ArrayList<ArrayList<ArrayList<T>>> list = new ArrayList<>();

    public List3D(int x, int y){
        for (int i = 0; i < x; i++) {
            ArrayList<ArrayList<T>> row = new ArrayList<>(x);
            list.add(row);
            for (int j = 0; j < y; j++) {
                ArrayList<T> column = new ArrayList<>(y);
                list.get(i).add(column);
            }
        }
    }

    public T get(int x,int y,int z){
        return list.get(x).get(y).get(z);
    }

    public ArrayList<T> get(int x, int y){
        return list.get(x).get(y);
    }

    public void add(int x, int y, T val) {
        list.get(x).get(y).add(val);
    }

}
