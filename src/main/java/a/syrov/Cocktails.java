package a.syrov;

import java.util.Map;

public class Cocktails {
    private String name;
    private Map <String, Integer> ingredients;

    public Cocktails(String name, Map<String,Integer> ingredients){
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Коктейль: ")
                .append(name)
                .append("\nИнгредиенты:\n");
        for (Map.Entry<String, Integer> entry : ingredients.entrySet()) {
            sb.append("- ")
                    .append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue())
                    .append("\n");
        }
        return sb.toString();
    }

}
