/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Te One
 */
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import model.*;

public class GameScreen {
    private final JPanel panel = new JPanel();
    
    
    private final JLabel headingText = new JLabel();
    private final ArrayList<JButton> boxButton = new ArrayList<>();
    
    public GameScreen() {
        panel.setLayout(new BorderLayout(20, 20));
    }
    
    public ArrayList<JButton> getBoxButtons() {
        return this.boxButton;
    }
    
    public JPanel getPanel() {
        return panel;
    }

    public void build(BoxModel boxModel, Banker bank, Player player) {
        panel.removeAll();
        boxButton.clear();

        headingText.setText("Please Select " + bank.getOfferCounter() + " Box's");
        headingText.setFont(new Font("Arial", Font.BOLD, 32));
        headingText.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(headingText, BorderLayout.NORTH);

        JPanel boxGrid = new JPanel(new GridLayout(5, 5, 25, 25));
        ArrayList<model.Box> boxes = boxModel.getBoxList();

        for (int i = 0; i < 25; i++) {
            model.Box box = boxes.get(i);
            JButton button = new JButton();

            if (box.isOpen()) {
                button.setEnabled(false);
                button.setText(String.valueOf(box.getValue()));
                button.setBackground(Color.DARK_GRAY);
            } else if (player.getBox() - 1 == i) {
                button.setText("Your Box");
                button.setBackground(Color.GRAY);
                button.setForeground(Color.WHITE);
            } else {
                button.setText("Box " + (i + 1));
                button.setActionCommand("Box " + (i + 1));
            }

            boxButton.add(button);
            boxGrid.add(button);
        }

        JPanel centerWrapper = new JPanel();
        centerWrapper.setLayout(new BoxLayout(centerWrapper, BoxLayout.Y_AXIS));
        centerWrapper.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        centerWrapper.add(boxGrid);

        panel.add(centerWrapper, BorderLayout.CENTER);
    }

    public JLabel getHeadingText() {
        return this.headingText;
    }
}
