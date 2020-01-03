package core.nodes;

import core.API;
import core.Areas;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.TaskNode;
import org.dreambot.api.wrappers.interactive.NPC;

import java.util.Objects;

public class Attack extends TaskNode {

    @Override
    public int priority() {
        return 3;
    }

    @Override
    public boolean accept() {
        log("Attack: " + canAttack());
        log("ReAttack: " + canReAttack());
        return canAttack() || canReAttack();
    }

    @Override
    public int execute() {
        if (!canReAttack()) {
            NPC target = getNpcs().closest(npc -> npc != null && !npc.isInCombat() && npc.getName().equals("Aberrant spectre"));

            if (Objects.nonNull(target)) {
                API.status = "Attacking abby spec...";
                target.interact("Attack");
                sleepUntil(() -> getLocalPlayer().isInCombat(), (int)Calculations.nextGaussianRandom(2000, 4000));
            }
        } else {
            API.status = "ReAttacking abby spec...";
            getLocalPlayer().getCharacterInteractingWithMe().interact("Attack");
            sleepUntil(() -> getLocalPlayer().isInCombat(), (int) Calculations.nextGaussianRandom(1500, 3000));
        }

        return API.sleep();
    }

    private boolean canAttack() {
        NPC target = getNpcs().closest(npc -> npc != null && !npc.isInCombat() && npc.getName().equals("Aberrant spectre"));
        return Objects.nonNull(target) && !getLocalPlayer().isInCombat() && getLocalPlayer().getCharacterInteractingWithMe() == null
                && API.inAberrantSpectreArea();
    }

    private boolean canReAttack() {
        return !getLocalPlayer().isInCombat() && getLocalPlayer().getCharacterInteractingWithMe() != null
                && Areas.aberrantSpectre.contains(getLocalPlayer().getCharacterInteractingWithMe())
                && getLocalPlayer().getCharacterInteractingWithMe().getName().equals("Aberrant spectre")
                && API.inAberrantSpectreArea();
    }
}
