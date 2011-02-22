package client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import utils.Properties;

public class Gui extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    //Menu
    public JMenuBar mb = new JMenuBar();
    public JMenu m_main = new JMenu("Main");
    public JMenuItem m_connect = new JMenuItem("Connect");
    public JMenuItem m_disconnect = new JMenuItem("Disconnect");
    public JMenuItem m_history = new JMenuItem("Get History");
    public JMenuItem m_exit = new JMenuItem("Exit");
    public Box box_chat;
    //Chat
    public JPanel pnl_chat = new JPanel();
    public JTextArea ta_chat = makeTextArea(Properties.CL_BLACK, Properties.CL_WHITE, Properties.FONT);
    public JTextField tf_chat = makeTextField(Properties.CL_BLACK, Properties.CL_WHITE, Properties.FONT);

    public Gui() {
        super("Chat Client");
        this.setJMenuBar(mb);
        mb.add(m_main);
        m_main.add(m_connect);
        m_main.add(m_disconnect);
        m_main.add(m_history);
        m_main.add(m_exit);

        pnl_chat.setLayout(new BorderLayout());
        box_chat = Box.createVerticalBox();
        box_chat.add(ta_chat);
        JScrollPane scrollPane = new JScrollPane(ta_chat);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        box_chat.add(scrollPane);
        box_chat.add(tf_chat);
        pnl_chat.add(box_chat);
        this.add(pnl_chat);
    }

    public Gui startGUI() {
        JFrame rahmen = this;


        rahmen.setSize(1024, 768);
        rahmen.setMaximumSize(new Dimension(1024, 768));
        rahmen.setResizable(false);
        rahmen.pack();

        rahmen.setVisible(true);

        return (Gui) rahmen;
    }

    public JTextArea makeTextArea(Color foreground, Color background, Font font) {
        JTextArea ta = new JTextArea("", 18, 40);
        ta.setColumns(80);
        ta.setLineWrap(true);

        ta.setAlignmentX(Component.CENTER_ALIGNMENT);
        ta.setAlignmentY(Component.CENTER_ALIGNMENT);
        ta.setForeground(foreground);
        ta.setBackground(background);

        ta.setColumns(80);
        ta.setLineWrap(true);

        ta.setFont(font);
        ta.setEditable(false);

        return ta;
    }

    public JTextField makeTextField(Color foreground, Color background, Font font) {
        JTextField tf = new JTextField(80);
        tf.setForeground(foreground);
        tf.setBackground(background);
        tf.setFont(font);
        tf.setEnabled(false);

        return tf;
    }

    public String show_ConnectionDialog() {
    	
        String username;

        username = (String) JOptionPane.showInputDialog(this, "Chose your Username", "");

        //System.out.println("Chosen Username = " + username);          
        return username;
    }

    public void throwErrorMessage(String s) {
        JOptionPane.showMessageDialog(
                this, s, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
