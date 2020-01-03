package core;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodContext;

public class API {

    private static MethodContext context;
    public static String status = "Script starting...";

    public API(MethodContext context) {
        API.context = context;
    }

    public static int sleep() { return (int)Calculations.nextGaussianRandom(400, 150); }
    public static boolean inBankArea() { return Areas.bank.contains(context.getLocalPlayer()); }
    public static boolean inAberrantSpectreArea() { return Areas.aberrantSpectre.contains(context.getLocalPlayer()); }
    public static boolean inLadderArea() { return Areas.ladder.contains(context.getLocalPlayer()); }
    public static boolean inCaveEntranceArea() { return Areas.caveEntrance.contains(context.getLocalPlayer()); }
    public static boolean inCaveArea() { return Areas.cave.contains(context.getLocalPlayer()); }

    public static String[] getPickupItems() {
        return new String[] {"Mithril kiteshield", "Lava battlestaff", "Adamant platelegs", "Rune full helm", "Mystic robe bottom (dark)", "Grimy ranarr weed",
            "Grimy irit leaf", "Grimy avantoe", "Grimy kwuarm", "Grimy cadantine", "Grimy lantadyme", "Snapdragon seed", "Lantadyme seed", "Snape grass seed",
            "Torstol seed", "Coins"};
    }
}
