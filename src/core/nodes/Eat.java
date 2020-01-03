package core.nodes;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.script.TaskNode;

public class Eat extends TaskNode {

    @Override
    public boolean accept() {
        return false;
    }

    @Override
    public int execute() {
        return 0;
    }

    public boolean canEat() {
        return getInventory().contains("Lobster") && getSkills().getBoostedLevels(Skill.HITPOINTS) < Calculations.random(40, 60);
    }
}
