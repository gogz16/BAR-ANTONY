package a.syrov;

import java.util.*;

public class Bar {
    private Map<String, Integer> stock = new HashMap<>();
    private List<Cocktails> cocktails = new ArrayList<>();

    public void addCocktail(Cocktails cocktail){
        cocktails.add(cocktail);
    }

    public void listCocktails() {
        for (Cocktails c : cocktails) {
            System.out.println(c);
        }
    }

    public Cocktails findCocktail(String name) {
        for (Cocktails cocktail : cocktails) {
            if (cocktail.getName().equalsIgnoreCase(name)) {
                return cocktail;
            }
        }
        return null;
    }

    public List<Cocktails> getCocktails() {
        return cocktails;
    }

    public void addIngredient(String name, int count) {
        stock.put(name, stock.getOrDefault(name,0) + count);
    }

    public void sellCocktail(String name, int count) {
        Cocktails cocktail = findCocktail(name);
        if (cocktail == null) {
            System.out.println("Рецепт '" + name + "' не найден");
            return;
        }
        boolean enough = true;
        for (Map.Entry<String, Integer> entry : cocktail.getIngredients().entrySet()) {
            String ingredient = entry.getKey();
            int need = entry.getValue() * count;
            int have = stock.getOrDefault(ingredient, 0);

            if (have < need) {
                System.out.println("Не хватает: " + ingredient + " (" + (need - have) + ")");
                enough = false;
            }
        }
        if (enough == true) {
            for (Map.Entry<String, Integer> entry : cocktail.getIngredients().entrySet()) {
                String ingredient = entry.getKey();
                int need = entry.getValue() * count;
                stock.put(ingredient, stock.get(ingredient) - need);
            }
            System.out.println("Коктейль продан: " + cocktail.getName() + " в количестве " + count);
        }
    }

    public void listStock() {
        if (stock.isEmpty()) {
            System.out.println("Склад пуст");
        } else {
            for (Map.Entry<String, Integer> entry : stock.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }

}
