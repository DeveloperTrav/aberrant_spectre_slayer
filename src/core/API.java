package core;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodContext;

public class API {

    private static MethodContext context;

    public API(MethodContext context) {
        API.context = context;
    }

    public static int sleep() { return Calculations.random(400, 150);
    }
}
