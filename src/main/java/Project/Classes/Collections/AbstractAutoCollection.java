package Project.Classes.Collections;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractAutoCollection<T>  {
    private final List<T> list;

    public AbstractAutoCollection(){list = new ArrayList<>();}

    public int delete (int index) {
        if (index >= list.size()) {
            return -1;
        }

        list.remove(index);
        return index;
    }

    public void insert(int index, T obj) {
        if (index >= list.size()) {
            list.add(obj);
        } else {
            list.add(index, obj);
        }
    }

    public void insert( T obj) {
        list.add(obj);
    }

    public List<T> getList() {
        return list;
    }

    public void sort(Comparator <T> comparator) {
        list.sort(comparator);
    }

}
