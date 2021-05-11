import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public interface Console {

    /**
     * USERS
     * */
    void getUserInfo(String username, String token);

    void claimUserName(String username);

    /**
     * SYSTEM
     * */
    void getSystemInfo(String token);

    /**
     * SHIPS
     * */

    static void toJson(List<String> data, String filename, boolean appending) throws IOException {
        try{
            File file = new File(filename);
            FileOutputStream fio = new FileOutputStream(file, appending);
            for (String s : data){
                fio.write(s.getBytes());
            }
            fio.write("\n".getBytes());
            fio.flush();
            fio.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
