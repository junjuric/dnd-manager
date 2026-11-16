package io.github.junjuric;
import java.util.Scanner;
import java.util.List;
public class Main 
{
    private static String scanItemName(Scanner sc){
        String name;
        while(true){
            try{
                name = sc.nextLine();
                if (name.equals("")) throw new Exception("Введено пустое поле. Пробуйте ещё раз.");
                break; 
            }
            catch(Exception ex){
                System.out.println("Ошибка: "+ ex.getMessage());
                continue;
            }
        }
        return name;
    }
    private static double scanItemWeight(Scanner sc){
        double inputWeight;
        while (true) {
            try {
                inputWeight = Double.parseDouble(sc.nextLine());
                if (inputWeight < 0 || inputWeight > 100 || !Double.isFinite(inputWeight)) throw new Exception("Вес должен быть больше 0 и меньше 100");
                break;
            }
            catch(NumberFormatException ex){
                System.out.println("Ошибка: Введено не число. Попробуйте ещё раз:");
                continue;
            }
            catch(Exception ex){
                System.out.println("Ошибка: " + ex.getMessage()+". Попробуйте ещё раз:");
                continue;
            }
        }
        return inputWeight;
    }
    private static String scanItemType(Scanner sc){
        String itemType;
        while (true) {
            try {
                itemType = sc.nextLine();
                if (Integer.parseInt(itemType)<1 || Integer.parseInt(itemType) >8) throw new Exception("Введён несуществующий тип предмета. Попробуйте ещё раз: ");
                break;
            }
            catch(NumberFormatException ex){
                System.out.println("Ошибка: Введено не целое число. Попробуйте ещё раз:");
                continue;
            }
            catch(Exception ex){
                System.out.println("Ошибка: " + ex.getMessage()+". Попробуйте ещё раз:");
                continue;
            }
        }
        return itemType;
    }
    private static String scanItemRare(Scanner sc){
        String itemRare;
        while (true) {
            try {
                itemRare = sc.nextLine();
                if (Integer.parseInt(itemRare)<1 || Integer.parseInt(itemRare) >6) throw new Exception("Введён несуществующий тип предмета.");
                break;
            }
            catch(NumberFormatException ex){
                System.out.println("Ошибка: Введено не целое число. Попробуйте ещё раз:");
                continue;
            }
            catch(Exception ex){
                System.out.println("Ошибка: " + ex.getMessage()+". Попробуйте ещё раз:");
                continue;
            }
        }
        return itemRare;
    }
    
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
                        String nameToAdd = scanItemName(sc);
                        System.out.print("Введите вес предмета: ");
                        double weight = scanItemWeight(sc);
                        ItemType[] itemTypePrint = ItemType.values();
                        System.out.print("Выберите тип предмета: ");
                        int i=1;
                        for(var it: itemTypePrint){
                            System.out.print(i+". "+ it + " ");
                            i++;
                        }
                        System.out.println(": ");
                        String typeName = scanItemType(sc);
                        ItemType itemType = ItemType.WONDROUS_ITEMS;
                        switch (typeName) {
                            case "1":
                                itemType = ItemType.ARMOR;
                                break;
                            case "2":
                                itemType = ItemType.WEAPONS;
                                break;
                            case "3":
                                itemType = ItemType.POTIONS;
                                break;
                            case "4":
                                itemType = ItemType.SCROLLS;
                                break;
                            case "5":
                                itemType = ItemType.RINGS;
                                break;
                            case "6":
                                itemType = ItemType.MAGICIANS_WEAPONS;
                                break;
                            case "7":
                                itemType = ItemType.WONDROUS_ITEMS;
                                break;
                            case "8":
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
                        String rareName = scanItemRare(sc);
                        ItemRare itemRare = ItemRare.COMMON;
                        switch (rareName) {
                            case "1":
                                itemRare = ItemRare.COMMON;
                                break;
                            case "2":
                                itemRare = ItemRare.UNCOMMON;
                                break;
                            case "3":
                                itemRare = ItemRare.RARE;
                                break;
                            case "4":
                                itemRare = ItemRare.VERY_RARE;
                                break;
                            case "5":
                                itemRare = ItemRare.LEGENDARY;
                                break;
                            case "6":
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
                            printInterface();
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
