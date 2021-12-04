package commands;

import manager.UserManager;
import recipe.Recipe;
import user.User;

import java.util.ArrayList;

public class FavouriteUserCommand extends UserCommand{
    public UserManager userManager;

    public FavouriteUserCommand() {}

    /**
     * Execute the command since there's no args to be taken in.
     * @param commandLine Keep the same structure.
     */
    @Override
    public void run(String commandLine) {
        this.execute();
    }

    /**
     * Execute the command.
     */
    public void execute() {
        StringBuilder outPut = new StringBuilder();
        this.userManager = UserManager.getInstance();
        User curr = this.userManager.getCurrentUser();
        ArrayList<Recipe> favouriteList = curr.favouriteList;
        if(favouriteList.isEmpty()) {
            System.out.println("No favourite recipes.");
        }
        else {
            for (Recipe recipe : favouriteList) {
                outPut.append("name: ").append(recipe.name).append(", id: ").append(recipe.id).append("\n")
                        .append("description: ").append(recipe.description);
            }
            System.out.println(outPut);
        }
    }
}
