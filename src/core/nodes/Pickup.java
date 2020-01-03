package core.nodes;

import core.API;
import core.Areas;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.script.TaskNode;
import org.dreambot.api.wrappers.items.GroundItem;

import java.util.Arrays;
import java.util.Objects;

public class Pickup extends TaskNode {

    @Override
    public int priority() {
        return 4;
    }

    @Override
    public boolean accept() {
        log("Pickup: " + canPickup());
        return canPickup();
    }

    @Override
    public int execute() {
        GroundItem groundItem = getGroundItems().closest(item -> Objects.nonNull(item) && Arrays.asList(API.getPickupItems()).contains(item.getName())
                && Areas.aberrantSpectre.contains(item));

        if (Objects.nonNull(groundItem)) {
            if (getInventory().isFull() && !groundItem.getName().equals("Coins")) {
                if (getTabs().isOpen(Tab.INVENTORY)) {
                    getInventory().get("Lobster").interact("Eat");
                    sleepUntil(() -> !getInventory().isFull(), (int)Calculations.nextGaussianRandom(3000, 2000));
                } else {
                    getTabs().openWithMouse(Tab.INVENTORY);
                    sleepUntil(() -> getTabs().isOpen(Tab.INVENTORY), (int)Calculations.nextGaussianRandom(3000, 2000));
                }
            }

            API.status = "Picking up item...";
            groundItem.interact("take");
            sleepUntil(() -> false, (int)Calculations.nextGaussianRandom(4000, 2500));
        }

        return API.sleep();
    }

    private boolean canPickup() {
        GroundItem groundItem = getGroundItems().closest(item -> Objects.nonNull(item) && Arrays.asList(API.getPickupItems()).contains(item.getName())
                && Areas.aberrantSpectre.contains(item));
        return Objects.nonNull(groundItem) && Areas.aberrantSpectre.contains(groundItem) && API.inAberrantSpectreArea();
    }
}

