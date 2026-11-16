package io.github.junjuric;
import java.util.ArrayList;
import java.util.List;
public class Inventory {
    private final ArrayList<Item> items = new ArrayList<>();
    private Item findItem(String name){
            for(Item a: items){
                if(a.getName().equals(name)){
                    return a;
                }
        }
        return null;
    }
    public void addItem(Item item){
        items.add(item);
    }
    public Item removeItem(String name){
        Item item = findItem(name);
        items.remove(item);
        return item;
    }
    public Item searchItem(String name){
        return findItem(name);
    }
    public List<Item> printItems(){
        return List.copyOf(items);
    }
    public double inventoryWeightCalculation(){
        double sum = 0;
        for(Item a: items){
            sum+=a.getWeight();
        }
        return sum;
    }
}
