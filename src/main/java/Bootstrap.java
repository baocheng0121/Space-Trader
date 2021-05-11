import java.util.Scanner;

public class Bootstrap {


    public static void main(String[] args) {
        Console console;
        if (args.length > 0){
            if (args[0].equals("offline")){
                System.out.println("OFFLINE");
                console = new OnlineConsole();
            } else if (args[0].equals("online")){
                System.out.println("ONLINE");
                console = new OnlineConsole();
                //console.claimUserName("baocheng2");
                //console.getConnect("baocheng2", "deb67bdb-2924-4539-991e-5e6540316fb1");
            } else {
                System.out.println("Invalid Parameter");
                return;
            }
            // console.getUserInfo("baocheng2", "deb67bdb-2924-4539-991e-5e6540316fb1");
            console.getSystemInfo("deb67bdb-2924-4539-991e-5e6540316fb1");
        }
    }

}
