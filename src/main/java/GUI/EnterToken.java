package GUI;

import DummyAPI.DummyFacade;
import OnlineAPI.Console;
import OnlineAPI.OnlineConsole;
import org.json.simple.JSONObject;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnterToken extends JFrame {
    static EnterToken frame;
    private JPanel contentPane;
    private JTextField tokenField;
    private final JButton loginButton;
        /** run the process **/
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new EnterToken(args[0], args[1]);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

        /** setting up the interface frame **/
    public EnterToken(String type, String name) {
        /* setting the default value */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel loginLabel = new JLabel("Please Enter Your Token");
        loginLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        JLabel tokenLabel = new JLabel("token: ");

        tokenField = new JTextField();
        tokenField.setColumns(36);

        loginButton = new JButton("Log In");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String token= tokenField.getText();
                Console console = null;
                if (type.equals("online")){
                    console = new OnlineConsole();
                } else if (type.equals("offline")){
                    console = new DummyFacade();
                }
                if (console == null){
                    JOptionPane.showMessageDialog(EnterToken.this,"INVALID WORKING MODE");
                    return;
                }
                JSONObject json = console.getUserInfo(name, token);
                if (json == null){
                    JOptionPane.showMessageDialog(EnterToken.this, "MISSING TOKEN OR TOKEN NOT MATCHING WITH THE USERNAME");
                    String[] passArgs = new String[2];
                    passArgs[0] = type;
                    passArgs[1] = name;
                    EnterToken.main(new String[2]);
                    frame.dispose();
                } else {
                    String[] passArgs = new String[3];
                    passArgs[0] = type;
                    passArgs[1] = name;
                    passArgs[2] = token;
                    LogInPage.main(passArgs);
                    frame.dispose();
                }
//                console.getUserInfo("baocheng2", "deb67bdb-2924-4539-991e-5e6540316fb1");
//                if(user != null){
//                    String[] userinfo = new String[2];
//                    userinfo[0] = token;
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
//                    tokenField.setText("");passwordField.setText("");
//                }
            }
        });

        GroupLayout layout = new GroupLayout(contentPane);
        layout.setHorizontalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(140)
                                                .addComponent(loginLabel))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(25)
                                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(tokenLabel))
                                                .addGap(58)
                                                .addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
                                                        .addComponent(tokenField, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(180)
                                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))
                                )
                                .addContainerGap(111, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(loginLabel)
                                .addGap(40)
                                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(tokenLabel)
                                        .addComponent(tokenField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(10)
                                .addGap(36)
                                .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                .addGap(50)
                                .addContainerGap(50, Short.MAX_VALUE))
        );
        contentPane.setLayout(layout);

    }
}
