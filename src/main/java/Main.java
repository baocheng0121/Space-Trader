import DummyAPI.DummyFacade;
import OnlineAPI.Console;
import OnlineAPI.OnlineConsole;

import GUI.Window;
import org.json.simple.JSONObject;

public class Main {


    public static void main(String[] args) {
        String[] arg = new String[2];
        Console console;
        if (args.length > 0){
            if (args[0].equals("offline")){
                System.out.println("OFFLINE");
                arg[0] = "offline";
                console = new DummyFacade();
            } else if (args[0].equals("online")){
                System.out.println("ONLINE");
                arg[0] = "online";
                console = new OnlineConsole();
            } else {
                System.out.println("Invalid Parameter");
                return;
            }

            Window.main(arg);

            //console.claimUserName("baocheng7");
            //console.getUserInfo("baocheng7", "56f0ffdd-8a49-4f60-829e-a0f38ea5ef7e");
            // console.requestLoan("56f0ffdd-8a49-4f60-829e-a0f38ea5ef7e", "baocheng7", "STARTUP");
            //console.viewAvailableShips("56f0ffdd-8a49-4f60-829e-a0f38ea5ef7e", "MK-I");
            //console.buyShip("baocheng7","56f0ffdd-8a49-4f60-829e-a0f38ea5ef7e", "OE-PM-TR","HM-MK-I");
            //console.viewMarketPlace("56f0ffdd-8a49-4f60-829e-a0f38ea5ef7e", "OE-PM-TR");
            //console.buyFuel("baocheng7", "56f0ffdd-8a49-4f60-829e-a0f38ea5ef7e","ckoolbeer10394141bs6dgzogprj","FUEL","19");
            //console.buyFuel("baocheng7", "56f0ffdd-8a49-4f60-829e-a0f38ea5ef7e","ckoolbeer10394141bs6dgzogprj","FUSION_REACTORS","2");
            //console.listNearBy("56f0ffdd-8a49-4f60-829e-a0f38ea5ef7e","OE","PLANET");
            //console.createFlightPlan("baocheng7","56f0ffdd-8a49-4f60-829e-a0f38ea5ef7e","ckoolbeer10394141bs6dgzogprj", "OE-CR");
            //console.viewMarketPlace("56f0ffdd-8a49-4f60-829e-a0f38ea5ef7e", "OE-CR");
            //console.sell("baocheng7","56f0ffdd-8a49-4f60-829e-a0f38ea5ef7e","ckoolbeer10394141bs6dgzogprj","FUSION_REACTORS", "1");

        }
    }

}
