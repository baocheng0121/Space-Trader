package GUI;

import java.awt.EventQueue;
import javax.print.MultiDocPrintService;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import DummyAPI.DummyFacade;
import OnlineAPI.Console;
import OnlineAPI.OnlineConsole;
import org.json.simple.JSONObject;

public class LogInPage extends JFrame {
    static LogInPage frame;
    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;
    private final JButton loginButton;
    /** run the process **/
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new LogInPage(args[0], args[1], args[2]);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /** setting up the interface frame **/
    public LogInPage(String type, String username, String token) {
        /* setting the default value */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel loginLabel = new JLabel("User: " + username);
        loginLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        Console globalConsole = null;
        if (type.equals("online")){
            globalConsole = new OnlineConsole();
        } else if (type.equals("offline")){
            globalConsole = new DummyFacade();
        }

        JSONObject info = globalConsole.getUserInfo(username, token);

        JLabel nameLabel = new JLabel("username:");

        textField = new JTextField();
        textField.setColumns(10);

        loginButton = new JButton("Log In");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name= textField.getText();
                Console console = null;
                if (type.equals("online")){
                    console = new OnlineConsole();
                } else if (type.equals("offline")){
                    console = new DummyFacade();
                }
                if (console == null){
                    JOptionPane.showMessageDialog(LogInPage.this,"INVALID WORKING MODE");
                    return;
                }
                JSONObject json = console.claimUserName(name);
                String token = (String) json.get("token");
                System.out.println(token);
                JOptionPane.showMessageDialog(LogInPage.this,"Token: " + token);

                console.getUserInfo(name, token);
//                console.getUserInfo("baocheng2", "deb67bdb-2924-4539-991e-5e6540316fb1");
//                if(user != null){
//                    String[] userinfo = new String[2];
//                    userinfo[0] = name;
//                    userinfo[1] = password;
//                    if (user.getType().equalsIgnoreCase("cashier")){
//                        CashierInterface.main(userinfo);
//                        frame.dispose();
//                    } else if (user.getType().equalsIgnoreCase("seller")){
//
//                        SellerInterface.main(userinfo);
//                        frame.dispose();
//                    } else if (user.getType().equalsIgnoreCase("owner")){
//                        OwnerInterface.main(userinfo);
//                    }
//                    /* go to the next page */
//                    // AdminSection.main(s);
//                    frame.dispose();
//                }else{
//                    JOptionPane.showMessageDialog(StaffLogIn.this,"No such user!");
//                    textField.setText("");passwordField.setText("");
//                }
            }
        });

        GroupLayout layout = new GroupLayout(contentPane);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(140)
                                                .addComponent(loginLabel))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(25)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(nameLabel))
                                                .addGap(58)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(textField, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(180)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))
                                )
                                .addContainerGap(111, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(loginLabel)
                                .addGap(40)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(nameLabel)
                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(10)
                                .addGap(36)
                                .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                .addGap(50)
                                .addContainerGap(50, Short.MAX_VALUE))
        );
        contentPane.setLayout(layout);

    }
}
