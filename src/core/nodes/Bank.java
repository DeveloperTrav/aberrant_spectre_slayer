package core.nodes;

import core.API;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.TaskNode;

public class Bank extends TaskNode {

    @Override
    public boolean accept() {
        return canBank();
    }

    @Override
    public int execute() {
        if (getBank().isOpen()) {
            if (!getEquipment().contains("Nose peg") || !getEquipment().contains("Slayer helmet")) {
                if (getBank().contains("Slayer helmet")) {
                    getBank().withdraw("Slayer helmet");
                    sleepUntil(() -> getInventory().contains("Slayer helmet"), (int)Calculations.nextGaussianRandom(1500, 2500));
                } else {
                    getBank().withdraw("Nose peg");
                    sleepUntil(() -> getInventory().contains("Nose peg"), (int)Calculations.nextGaussianRandom(1500, 2500));
                }
            }

            getBank().withdrawAll("Lobster");
            sleepUntil(() -> getInventory().contains("Lobster"), (int)Calculations.nextGaussianRandom(2000, 4000));
        } else {
            API.status = "Opening bank...";
            getBank().openClosest();
            sleepUntil(() -> getBank().isOpen(), (int)Calculations.nextGaussianRandom(2000, 3500));
        }

        return API.sleep();
    }

    private boolean canBank() {
        return !getInventory().contains("Lobster") && API.inBankArea();
    }
}
