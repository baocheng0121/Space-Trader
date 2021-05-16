package GUI;

import DummyAPI.DummyFacade;
import GUI.loans.*;
import GUI.ships.*;
import GUI.market.*;
import OnlineAPI.Console;
import OnlineAPI.OnlineConsole;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserPage extends JFrame {
    static UserPage frame;
    private JPanel contentPane;
    private JButton ships;
    private JButton loans;
    private static Console console;

    /** run the process **/
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new UserPage(args[0], args[1], args[2]);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /** setting up the interface frame **/
    public UserPage(String type, String name, String token) {

        /* setting the default value */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel statusLabel = new JLabel("User Information");

        statusLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 15));

        if (type.equals("online")){
            console = new OnlineConsole();
        } else if (type.equals("offline")){
            console = new DummyFacade();
        } else {
            console = null;
        }
        if (console == null){
            JOptionPane.showMessageDialog(UserPage.this,"INVALID WORKING MODE");
            return;
        }
        JSONObject json = console.getUserInfo(name, token);
        String username = "";
        Long credits = 0L;
        JSONArray ship = null;
        try{
            JSONObject user = (JSONObject) json.get("user");
            username = (String) user.get("username");
            credits = (Long) user.get("credits");
            ship = (JSONArray) user.get("ships");
        }catch (Exception e){
            e.printStackTrace();
        }

        if (ship == null){
            System.out.println("NULL SHIPS");
        }

        String[][] userData = new String[4][2];
        userData[0][0] = "username";
        userData[1][0] = "credits";

        userData[0][1] = username;
        userData[1][1] = credits+"";

        String[] columnName = {"feature", "detail"};

        JTable itemsTable = new JTable(userData, columnName);
        JScrollPane items = new JScrollPane(itemsTable);

        this.ships = new JButton("Ships");
        this.loans = new JButton("Loans");

        JButton back = new JButton("active");

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JSONObject active = console.isGameActive();
                JOptionPane.showMessageDialog(UserPage.this, (String) active.get("status"));
            }
        });

        this.loans.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] passArgs = new String[3];
                passArgs[0] = type;
                passArgs[1] = name;
                passArgs[2] = token;
                MyLoans.main(passArgs);
                frame.dispose();
            }
        });

        this.ships.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] passArgs = new String[3];
                passArgs[0] = type;
                passArgs[1] = name;
                passArgs[2] = token;
                MyShips.main(passArgs);
                frame.dispose();
            }
        });


        GroupLayout layout = new GroupLayout(contentPane);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(back)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(150)
                                                .addComponent(statusLabel))
                                        .addGap(50)
                                        .addComponent(items, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
                                        .addGap(100)
                                        .addComponent(ships)
                                        .addGap(60)
                                        .addComponent(loans))
                                .addContainerGap(111, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(back)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(statusLabel)
                                .addGap(50)
                                .addComponent(items, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                                .addGap(50)
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup())
                                        .addComponent(ships)
                                        .addComponent(loans)
                                )
                                .addContainerGap(51, Short.MAX_VALUE))
        );
        contentPane.setLayout(layout);
    }
}
