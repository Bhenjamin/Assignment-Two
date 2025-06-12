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

public class DealOrNoDealScreen {
    private final JPanel dealOrNoDealPanel = new JPanel();
    private final JLabel headingText = new JLabel();
    private final ArrayList<JButton> boxButton = new ArrayList<>();

    public DealOrNoDealScreen() {
        dealOrNoDealPanel.setLayout(new BorderLayout(20, 20));
    }

    public JPanel getPanel() {
        return dealOrNoDealPanel;
    }

    public JLabel getHeadingText() {
        return headingText;
    }

    public ArrayList<JButton> getBoxButtons() {
        return this.boxButton;
    }

    public void build(BoxModel boxModel, Banker bank) {
        boxButton.clear();
        dealOrNoDealPanel.removeAll();
        headingText.removeAll();

        bank.bankerOffer(boxModel.getBoxList());
        headingText.setText("Bank Offers: " + bank.getOffer());
        headingText.setFont(new Font("Arial", Font.BOLD, 32));
        headingText.setHorizontalAlignment(SwingConstants.CENTER);
        dealOrNoDealPanel.add(headingText, BorderLayout.NORTH);

        JPanel dond = new JPanel(new GridLayout(1, 2, 100, 100));

        for (int i = 1; i <= 2; i++) {
            JButton button = new JButton();
            if (i == 1) {
                button.setText("Deal");
                button.setFont(new Font("Arial", Font.BOLD, 100));
                button.setActionCommand("Deal");
            }
            if (i == 2) {
                button.setText("No Deal");
                button.setFont(new Font("Arial", Font.BOLD, 100));
                button.setActionCommand("No Deal");
            }
            boxButton.add(button);
            dond.add(button);
        }

        JPanel centerWrapper = new JPanel();
        centerWrapper.setLayout(new BoxLayout(centerWrapper, BoxLayout.Y_AXIS));
        centerWrapper.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        centerWrapper.add(dond);

        dealOrNoDealPanel.add(centerWrapper, BorderLayout.CENTER);
    }
} 
