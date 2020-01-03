package core.nodes;

import core.API;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.TaskNode;

public class Bank extends TaskNode {

    @Override
    public int priority() {
        return 2;
    }

    @Override
    public boolean accept() {
        log("BANK: " + canBank());
        return canBank();
    }

    @Override
    public int execute() {
        if (getBank().isOpen()) {
            if (!getEquipment().contains("Nose peg")) {
                if (getBank().contains("Slayer helmet")) {
                    getBank().withdraw("Slayer helmet");
                    sleepUntil(() -> getInventory().contains("Slayer helmet"), (int)Calculations.nextGaussianRandom(2500, 1500));
                } else {
                    getBank().withdraw("Nose peg");
                    sleepUntil(() -> getInventory().contains("Nose peg"), (int)Calculations.nextGaussianRandom(2500, 1500));
                }
            }

            getBank().withdrawAll("Lobster");
            sleepUntil(() -> getInventory().contains("Lobster"), (int)Calculations.nextGaussianRandom(4000, 2000));
        } else {
            API.status = "Opening bank...";
            getBank().openClosest();
            sleepUntil(() -> getBank().isOpen(), (int)Calculations.nextGaussianRandom(3500, 2000));
        }

        return API.sleep();
    }

    private boolean canBank() {
        return !getInventory().contains("Lobster") && API.inBankArea();
    }
}
