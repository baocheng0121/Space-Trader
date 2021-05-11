public class Bootstrap {





    public static void main(String[] args) {
        if (args.length > 0){
            if (args[0].equals("offline")){
                System.out.println("OFFLINE");
            } else if (args[0].equals("online")){
                System.out.println("ONLINE");
            } else {
                System.out.println("OTHERS");
            }
        }
    }

}
