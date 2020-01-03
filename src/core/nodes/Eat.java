package core.nodes;

import core.API;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.script.TaskNode;

public class Eat extends TaskNode {

    @Override
    public boolean accept() {
        return canEat();
    }

    @Override
    public int execute() {
        if (getTabs().isOpen(Tab.INVENTORY)) {
            API.status = "Eating...";
            getInventory().interact("Lobster", "Eat");
            sleep(1500, 3000);
        } else {
            getTabs().openWithMouse(Tab.INVENTORY);
            sleepUntil(() -> getTabs().isOpen(Tab.INVENTORY), (int)Calculations.nextGaussianRandom(1500, 3000));
        }

        return API.sleep();
    }

    private boolean canEat() {
        return getInventory().contains("Lobster") && getSkills().getBoostedLevels(Skill.HITPOINTS) < Calculations.random(40, 60);
    }
}
