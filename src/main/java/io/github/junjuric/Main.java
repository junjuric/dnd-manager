package io.github.junjuric;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;
public class Main 
{
    private static void printInterface(){
        System.out.println("\nИНВЕНТАРЬ\n" + 
                        "\n" + 
                        "1. Добавить предмет\n" + 
                        "2. Удалить предмет\n" +
                        "3. Найти предмет\n" + 
                        "4. Показать инвентарь\n" +
                        "5. Показать общий вес\n" +
                        "0. Выход\n" +
                        "\n" + 
                        "Выберите действие: ");
    }
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        Inventory inventory = new Inventory();
        printInterface();
        boolean isRunning = true;
        while(isRunning){
                switch (sc.nextLine()) {
                    case "1":
                        System.out.print("Введите название предмета: ");
                        String nameToAdd = sc.nextLine();
                        double weight = 0;
                        boolean correctInput = false;
                        System.out.print("Введите вес предмета: ");
                        while (!correctInput) {
                            try {
                                double inputWeight = sc.nextDouble();
                                sc.nextLine();
                                if (inputWeight < 0 || inputWeight > 100 || !Double.isFinite(inputWeight)) {
                                    System.out.println("Некорректный вес. Введите число от 0 до 100: ");
                                }
                                else {
                                    weight = inputWeight;
                                    correctInput = true;
                                }

                            } catch (InputMismatchException e) {
                                System.out.println("Введено неверное значение. Попробуйте ещё раз: ");
                                sc.next();
                            }
                        }
                        ItemType[] itemTypePrint = ItemType.values();
                        System.out.print("Выберите тип предмета: ");
                        int i=1;
                        for(var it: itemTypePrint){
                            System.out.print(i+". "+ it + " ");
                            i++;
                        }
                        System.out.println(": ");
                        String typeName = sc.nextLine();
                        ItemType itemType = ItemType.WONDROUS_ITEMS;
                        switch (typeName) {
                            case "ARMOR", "1":
                                itemType = ItemType.ARMOR;
                                break;
                            case "WEAPONS", "2":
                                itemType = ItemType.WEAPONS;
                                break;
                            case "POTIONS", "3":
                                itemType = ItemType.POTIONS;
                                break;
                            case "SCROLLS", "4":
                                itemType = ItemType.SCROLLS;
                                break;
                            case "RINGS", "5":
                                itemType = ItemType.RINGS;
                                break;
                            case "MAGICIANS_WEAPONS", "6":
                                itemType = ItemType.MAGICIANS_WEAPONS;
                                break;
                            case "WONDROUS_ITEMS", "7":
                                itemType = ItemType.WONDROUS_ITEMS;
                                break;
                            case "ARTIFACTS", "8":
                                itemType = ItemType.ARTIFACTS;
                                break;
                            default:
                                System.out.println("Введено неверное значение");
                                printInterface();
                                continue;
                        }

                        ItemRare[] itemRarePrint = ItemRare.values();
                        System.out.print("Выберите редкость предмета: ");
                        int j=1;
                        for(var ir: itemRarePrint){
                            System.out.print(j+". "+ ir + " ");
                            j++;
                        }
                        System.out.println(": ");
                        String rareName = sc.nextLine();
                        ItemRare itemRare = ItemRare.COMMON;
                        switch (rareName) {
                            case "COMMON", "1":
                                itemRare = ItemRare.COMMON;
                                break;
                            case "UNCOMMON", "2":
                                itemRare = ItemRare.UNCOMMON;
                                break;
                            case "RARE", "3":
                                itemRare = ItemRare.RARE;
                                break;
                            case "VERY_RARE", "4":
                                itemRare = ItemRare.VERY_RARE;
                                break;
                            case "LEGENDARY", "5":
                                itemRare = ItemRare.LEGENDARY;
                                break;
                            case "ARTIFACT", "6":
                                itemRare = ItemRare.ARTIFACT;
                                break;
                            default:
                                System.out.println("Введено неверное значение");
                                printInterface();
                                continue;
                        }
                        
                        Item item = new Item(nameToAdd, itemRare, itemType, weight);
                        inventory.addItem(item);
                        
                        printInterface();
                        break;
                    case "2":
                        System.out.println("Введите название предмета");
                        String nameToDelete = sc.nextLine();
                        if(inventory.removeItem(nameToDelete) == null){
                            System.out.println("Предмет не найден");
                        }
                        else System.out.println("Предмет успешно удалён");
                        printInterface();
                        break;
                    case "3":
                        System.out.println("Введите название предмета");
                        String nameToSearch = sc.nextLine();
                        Item itemSearch = inventory.searchItem(nameToSearch);
                        if(itemSearch==null){
                            System.out.println("Предмет не найден");
                        }
                        else{
                            System.out.println("Такой предмет есть :)"+
                            "\nВес: "+itemSearch.getWeight()+
                            "\nТип: "+itemSearch.getType()+
                            "\nРедкость: "+itemSearch.getRare()
                            );
                        }
                        printInterface();
                        break;
                    case "4":
                        System.out.println("Список предметов в инвентаре: ");
                        List<Item> itemsForPrint = inventory.printItems();
                        if(itemsForPrint.isEmpty()){
                            System.out.println("Список пуст");
                            continue;
                        }
                        System.out.print("Список предметов: ");
                        for(Item itemForPrint: itemsForPrint){
                            System.out.print(itemForPrint.getName()+"; ");
                        }
                        printInterface();
                        break;
                    case "5":
                        System.out.println("Вес предметов в инвентаре: ");
                        System.out.println(inventory.inventoryWeightCalculation());
                        printInterface();
                        break;
                    case "0":
                        isRunning = false;
                        break;
                    default:
                        printInterface();
                        break;
                }
        }
        sc.close();
    }
}
