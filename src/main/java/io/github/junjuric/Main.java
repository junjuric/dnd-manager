package io.github.junjuric;
import java.util.Scanner;
import java.util.List;
public class Main 
{
    private static String scanItemName(Scanner sc){
        String name;
        while(true){
            name = sc.nextLine().trim();
            if (!name.isBlank()) {
                return name;
            }
            System.out.println("Название не может быть пустым. Попробуйте ещё раз:");
        }
    }
    private static double scanItemWeight(Scanner sc){
        double inputWeight;
        while (true) {
            try {
                inputWeight = Double.parseDouble(sc.nextLine());
                
            }
            catch(NumberFormatException ex){
                System.out.println("Ошибка: Введено не число. Попробуйте ещё раз:");
                continue;
            }
            if (inputWeight <= 0 || inputWeight >= 100 || !Double.isFinite(inputWeight)){
                System.out.println("Вес должен быть больше 0 и меньше 100");
                continue;
            }
            else break;
        }
        return inputWeight;
    }
    private static String scanItemType(Scanner sc){
        int inputType;
        while (true) {
            try {
                inputType = Integer.parseInt(sc.nextLine());
            }
            catch(NumberFormatException ex){
                System.out.println("Ошибка: Неверный ввод. Попробуйте ещё раз:");
                continue;
            }
            if (inputType <1 || inputType >8 || !Double.isFinite(inputType)){
                System.out.println("Введите цифру от 1 до 8");
                continue;
            }
            else break;
        }
        return String.valueOf(inputType);
    }
    private static String scanItemRare(Scanner sc){
        int inputRare;
        while (true) {
            try {
                inputRare = Integer.parseInt(sc.nextLine());
            }
            catch(NumberFormatException ex){
                System.out.println("Ошибка: Неверный ввод. Попробуйте ещё раз:");
                continue;
            }
            if (inputRare <1 || inputRare >6 || !Double.isFinite(inputRare)){
                System.out.println("Введите цифру от 1 до 6");
                continue;
            }
            else break;
        }
        return String.valueOf(inputRare);
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
                        if(inventory.addItem(item) == 0){ 
                            System.out.println("Предмет не добавлен. Попробуйте ещё раз");
                            printInterface();
                            continue;
                        }
                        printInterface();
                        break;
                    case "2":
                        System.out.println("Введите название предмета");
                        String nameToDelete = scanItemName(sc);
                        if(inventory.removeItem(nameToDelete) == null){
                            System.out.println("Предмет не найден");
                        }
                        else System.out.println("Предмет успешно удалён");
                        printInterface();
                        break;
                    case "3":
                        System.out.println("Введите название предмета");
                        String nameToSearch = scanItemName(sc);
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
                        List<Item> itemsForPrint = inventory.getItems();
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
