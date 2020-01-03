import core.API;
import core.nodes.*;
import org.dreambot.api.methods.walking.pathfinding.impl.web.WebFinder;
import org.dreambot.api.methods.walking.web.node.AbstractWebNode;
import org.dreambot.api.methods.walking.web.node.impl.BasicWebNode;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.script.impl.TaskScript;

import java.awt.*;
import java.util.Timer;

@ScriptManifest(category = Category.COMBAT, name = "Aberrant Spectre Slayer", description = "Kills aberrant spectres in slayer cave", author = "NotJohn", version = 1.0)
public class Main extends TaskScript {
    private Timer timer = new Timer();
    private API api = new API(this);

    public void onStart() {
        addNodes(new Attack());
        addNodes(new Bank());
        addNodes(new Eat());
        addNodes(new Pickup());
        addNodes(new Traverse());

        BasicWebNode bwn1 = new BasicWebNode(2426, 9824);
        BasicWebNode bwn2 = new BasicWebNode(2424, 9817);
        BasicWebNode bwn3 = new BasicWebNode(2426, 9809);
        BasicWebNode bwn4 = new BasicWebNode(2422, 9804);
        BasicWebNode bwn5 = new BasicWebNode(2416, 9802);
        BasicWebNode bwn6 = new BasicWebNode(2417, 9793);
        BasicWebNode bwn7 = new BasicWebNode(2415, 9790);
        BasicWebNode bwn8 = new BasicWebNode(2415, 9784);
        BasicWebNode bwn9 = new BasicWebNode(2423, 9784);
        BasicWebNode bwn10 = new BasicWebNode(2436, 9783);
        BasicWebNode bwn11 = new BasicWebNode(2441, 9786);
        BasicWebNode bwn12 = new BasicWebNode(2444, 9783);
        BasicWebNode bwn13 = new BasicWebNode(2444, 9778);

        bwn1.addConnections(bwn2);
        bwn2.addConnections(bwn1);
        bwn2.addConnections(bwn3);
        bwn3.addConnections(bwn2);
        bwn3.addConnections(bwn4);
        bwn4.addConnections(bwn3);
        bwn4.addConnections(bwn5);
        bwn5.addConnections(bwn4);
        bwn5.addConnections(bwn6);
        bwn6.addConnections(bwn5);
        bwn6.addConnections(bwn7);
        bwn7.addConnections(bwn6);
        bwn7.addConnections(bwn8);
        bwn8.addConnections(bwn7);
        bwn8.addConnections(bwn9);
        bwn9.addConnections(bwn8);
        bwn9.addConnections(bwn10);
        bwn10.addConnections(bwn9);
        bwn10.addConnections(bwn11);
        bwn11.addConnections(bwn10);
        bwn11.addConnections(bwn12);
        bwn12.addConnections(bwn11);
        bwn12.addConnections(bwn13);
        bwn13.addConnections(bwn12);

        WebFinder webFinder = getWalking().getWebPathFinder();
        AbstractWebNode[] webNodes = { bwn1, bwn2, bwn3, bwn4, bwn5, bwn6, bwn7, bwn8, bwn9, bwn10, bwn11, bwn12, bwn13 };
        for (AbstractWebNode webNode : webNodes) {
            webFinder.addWebNode(webNode);
        }
    }

    public void onPaint(Graphics2D g) {
        g.drawString("Status: " + API.status, 10 , 30);
    }
}
