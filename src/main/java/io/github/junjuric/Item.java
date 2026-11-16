package io.github.junjuric;
public class Item {
    private final String name;
    private final ItemRare rare;
    private final ItemType type;
    private final double weight;
    public Item (String name, ItemRare rare, ItemType type, double weight){
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Название не может быть пустым");
        if (weight < 0 || weight > 100 || !Double.isFinite(weight)) throw new IllegalArgumentException("Вес должен быть больше 0 и меньше 100");
        if (type == null) throw new IllegalArgumentException("Тип предмета не должен быть null");
        if (rare == null) throw new IllegalArgumentException("Редкость предмета не должна быть null");
        this.name = name;
        this.rare = rare;
        this.type = type;
        this.weight = weight;
    }
    public String getName() {
        return name;
    }
    public ItemRare getRare() {
        return rare;
    }
    public ItemType getType() {
        return type;
    }
    public double getWeight() {
        return weight;
    }
}