package io.github.junjuric;
import java.util.ArrayList;
public class Inventory {
    private final ArrayList<Item> items = new ArrayList<>();
    public void addItem(Item item){
        items.add(item);
    }
    public void removeItem(String name){
        if(items.isEmpty()){
            System.out.println("Список пуст");
            return;
        }
        for(Item a: items){
            if(a.getName().equals(name)){
                items.remove(a);
                return;
            }
        }
        System.out.println("Удалять нечего, такого предмета нет.");
    }
    public void searchItem(String name){
        if(items.isEmpty()){
            System.out.println("Список пуст");
            return;
        }
        for(Item a: items){
            if(a.getName().equals(name)){
                System.out.println("Такой предмет есть :)"+
                    "\nВес: "+a.getWeight()+
                    "\nТип: "+a.getType()+
                    "\nРедкость: "+a.getRare()
                );
                return;
            }
        }
        System.out.println("Такого предмета нет :(");
    }
    public void printItems(){
        if(items.isEmpty()){
            System.out.println("Список пуст");
            return;
        }
        System.out.print("Список предметов: ");
        for(Item i: items){
        System.out.print(i.getName()+"; ");
        }
    }
    public double inventoryWeightCalcuation(){
        double sum = 0;
        for(Item a: items){
            sum+=a.getWeight();
        }
        return sum;
    }
}
