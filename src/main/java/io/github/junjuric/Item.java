package io.github.junjuric;
public class Item {
    private String name;
    private ItemRare rare;
    private ItemType type;
    private double weight;
    public Item (String name, ItemRare rare, ItemType type, double weight){
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