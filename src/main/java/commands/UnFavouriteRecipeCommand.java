package commands;

import manager.RecipeManager;
import manager.UserManager;
import recipe.Recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class UnFavouriteRecipeCommand extends RecipeCommand{
    public ArrayList<ValuePair> valuePairs;
    public RecipeManager recipeManager;
    private static final HashSet<String> acceptArgs = new HashSet<>() {{
        add("id");
    }};

    public UnFavouriteRecipeCommand() {}

    /**
     * Parse the `commandLine` according to usage template and execute command after parsing.
     * @param commandLine The `commandLine` to be processed.
     */
    @Override
    public void run(String commandLine) {
        Token token = new Token(commandLine);
        this.valuePairs = new ArrayList<>();
        String[] valuePairs = token.body.split("&");
        for (String rawValuePair : valuePairs) {
            ValuePair valuePair = new ValuePair(rawValuePair);
            if (!acceptArgs.contains(valuePair.field)) {
                throw new Error("Invalid Command");
            }
            this.valuePairs.add(valuePair);
        }
        this.execute();
    }

    /**
     * Execute the command.
     */
    public void execute() {
        HashMap<String, String> map = new HashMap<>();
        for (ValuePair valuePair: this.valuePairs) {
            map.put(valuePair.field, valuePair.value);
        }
        UserManager userManager = UserManager.getInstance();
        this.recipeManager = RecipeManager.getInstance();
        Recipe recipe = this.recipeManager.getRecipe(Integer.parseInt(map.get("id")));
        userManager.unFavourite(recipe);
    }
}
