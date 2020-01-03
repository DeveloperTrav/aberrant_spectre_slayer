package core.nodes;

import core.API;
import core.Areas;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.TaskNode;
import org.dreambot.api.wrappers.interactive.GameObject;

import java.util.Objects;

public class Traverse extends TaskNode {

    @Override
    public int priority() {
        return 1;
    }

    @Override
    public boolean accept() {
        log("Traverse: " + canTraverse());
        return canTraverse();
    }

    @Override
    public int execute() {
        if (getBank().isOpen()) {
            getBank().close();
            sleepUntil(() -> !getBank().isOpen(), (int)Calculations.nextGaussianRandom(1500, 2500));
        }

        if (API.inBankArea()) {
            GameObject stairs = getGameObjects().closest(obj -> Objects.nonNull(obj) && obj.getName().equals("Staircase") && Areas.bank.contains(obj));

            if (Objects.nonNull(stairs)) {
                API.status = "Walking down stairs";
                stairs.interact("Climb-down");
                sleepUntil(() -> !API.inBankArea(), (int)Calculations.nextGaussianRandom(2000, 4000));
            }
        }

        if (API.inCaveArea()) {
            if (getWalking().shouldWalk(Calculations.random(4, 7))) {
                API.status = "Walking to aberrant spectre...";
                getWalking().walk(Areas.aberrantSpectre.getCenter().getRandomizedTile(Calculations.random(2, 4)));
            }
        }

        if (!API.inBankArea() && !API.inCaveArea()) {
            if (getWalking().shouldWalk(Calculations.random(4, 7))) {
                API.status = "Walking to cave entrance...";
                getWalking().walk(Areas.caveEntrance.getCenter().getRandomizedTile(2));
            }
        }

        if (API.inCaveEntranceArea()) {
            GameObject cave = getGameObjects().closest(obj -> Objects.nonNull(obj) && obj.getName().equals("Cave") && Areas.caveEntrance.contains(obj));

            if (Objects.nonNull(cave)) {
                API.status = "Entering cave...";
                cave.interact("Enter");
                sleepUntil(() -> !API.inCaveEntranceArea(), (int)Calculations.nextGaussianRandom(2000, 4000));
            }
        }

        return API.sleep();
    }

    private boolean canTraverse() {
        return getInventory().contains("Lobster") && !API.inAberrantSpectreArea();
    }
}
