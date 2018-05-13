package com.kriash.mainproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProjectGUI {
    private static JCheckBox BKBox;
    private static JCheckBox McdsBox;
    private static JCheckBox WenBox;
    private static JCheckBox PHBox;
    private static JCheckBox TJBox;
    private static JCheckBox WFBox;
    private static JFrame ChoiceScreen;
    private static JFrame zipChosenScreen;
    private static JFrame addressChosenScreen;
    private static JFrame resultsScreen;
    private static JPanel pane1;
    private static JPanel pane2;
    private static JPanel pane3;
    private static JButton zipCodeBtn;
    private static JButton addressBtn;
    private static JButton GOBtn;
    private static JLabel zipLabel;
    private static JLabel storeLabel;
    private static JLabel addLabel;
    private static JTextField zipUserText;
    private static JTextField addUserText;
    private static String zipInput;
    private static String addInput;
    private static String[] output;
    
    public static void main (String[] args){     
        ChoiceScreen = new JFrame("Locator System");
        ChoiceScreen.setVisible(true);
        ChoiceScreen.setSize(1000,600);
        ChoiceScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        pane1 = new JPanel(new GridBagLayout());
        
        zipCodeBtn = new JButton("Zip Code");
        zipCodeBtn.addActionListener (new ZipChosen());
        
        addressBtn = new JButton("Address");
        addressBtn.addActionListener (new AddressChosen());
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.insets = new Insets(20, 10, 10, 10);
        c.gridx = 0;
        c.gridy = 1;
        pane1.add(zipCodeBtn, c);
        c.gridx = 0;
        c.gridy = 2;
        pane1.add(addressBtn, c);
        
        ChoiceScreen.add(pane1, BorderLayout.CENTER);
    }
    static class ZipChosen implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent e){
            //JFrame
            zipChosenScreen = new JFrame("Zip Code Chosen");
            zipChosenScreen.setVisible(true);
            zipChosenScreen.setSize(1000,600);
           
            pane2 = new JPanel(new GridBagLayout());
            
            GridBagConstraints c = new GridBagConstraints();
            
            c.insets = new Insets(20, 10, 10, 10);
            c.gridx = 0;
            c.gridy = 0;
            zipLabel = new JLabel("Enter Zip Code: ", JLabel.CENTER);
            pane2.add(zipLabel, c);
            c.gridx = 1;
            c.gridy = 0;
            zipUserText = new JTextField(5);
            zipUserText.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    zipInput = zipUserText.getText();
                }
            });
            pane2.add(zipUserText, c);
            
            storeLabel = new JLabel("Choose Stores/Restaurants:", JLabel.CENTER);
            c.gridx = 0;
            c.gridy = 1;
            pane2.add(storeLabel, c);
            
            McdsBox = new JCheckBox ("McDonalds");
            c.insets = new Insets(3, 3, 3, 3);
            c.gridx = 1;
            c.gridy = 1;
            pane2.add(McdsBox, c);
            BKBox = new JCheckBox ("Burger King");
            c.gridy = 2;
            pane2.add(BKBox, c);
            WenBox = new JCheckBox ("Wendys");
            c.gridy = 3;
            pane2.add(WenBox, c);
            PHBox = new JCheckBox ("Pizza Hut");
            c.gridy = 4;
            pane2.add(PHBox, c);
            TJBox = new JCheckBox ("Trader Joes");
            c.gridy = 5;
            pane2.add(TJBox, c);
            WFBox = new JCheckBox ("Whole Foods");
            c.gridy = 6;
            pane2.add(WFBox, c);
            
            pane3 = new JPanel();
            GOBtn = new JButton("GO");
            GOBtn.addActionListener(new GoZipButton());
            pane3.add(GOBtn);
            zipChosenScreen.add(pane2);
            zipChosenScreen.add(pane3, BorderLayout.SOUTH);
        }
    }
    static class GoZipButton implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent e){
            resultsScreen = new JFrame();
            resultsScreen.setVisible(true);
            resultsScreen.setSize(1000, 600);
            
            int[] selectedStores = new int[6];
            if (McdsBox.isSelected() == true){
                selectedStores[0] = 1;
            }else{
                selectedStores[0] = 0;
            }
            if (BKBox.isSelected() == true){
                selectedStores[1] = 1;
            }else{
                selectedStores[1] = 0;
            }
            if (WenBox.isSelected() == true){
                selectedStores[2] = 1;
            }else{
                selectedStores[2] = 0;
            }
            if (PHBox.isSelected() == true){
                selectedStores[3] = 1;
            }else{
                selectedStores[3] = 0;
            }
            if (TJBox.isSelected() == true){
                selectedStores[4] = 1;
            }else{
                selectedStores[4] = 0;
            }
            if (WFBox.isSelected() == true){
                selectedStores[5] = 1;
            }else{
                selectedStores[5] = 0;
            }
            Main main = new Main();
            try {
                output = main.StoresAndZipFromGUI(selectedStores, zipUserText.getText());
            } catch (IOException ex) {
                Logger.getLogger(ProjectGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JTextArea textArea = new JTextArea (10, 20);
            JScrollPane scrollPane = new JScrollPane (textArea);
            textArea.setEditable (false);
            for (int i = 0; i < output.length; i++){
                textArea.append(output[i] + "\n");
            }
            JPanel pane = new JPanel (new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            pane.add (textArea, c);
            pane.add(scrollPane);
            pane.repaint();
            resultsScreen.add(pane);
        }
    }
    static class AddressChosen implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent e){
            addressChosenScreen = new JFrame("Address Chosen");
            addressChosenScreen.setVisible(true);
            addressChosenScreen.setSize(1000,600);
            pane2 = new JPanel(new GridBagLayout());
            
            GridBagConstraints c = new GridBagConstraints();
            
            c.insets = new Insets(20, 10, 10, 10);
            c.gridx = 0;
            c.gridy = 0;
            addLabel = new JLabel("Enter address: ", JLabel.CENTER);
            pane2.add(addLabel, c);
            c.gridx = 1;
            c.gridy = 0;
            addUserText = new JTextField(15);
            addUserText.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    addInput = addUserText.getText();
                }
            });
            pane2.add(addUserText, c);
            
            storeLabel = new JLabel("Choose Stores/Restaurants:", JLabel.CENTER);
            c.gridx = 0;
            c.gridy = 1;
            pane2.add(storeLabel, c);
            
            McdsBox = new JCheckBox ("McDonalds");
            c.insets = new Insets(3, 3, 3, 3);
            c.gridx = 1;
            c.gridy = 1;
            pane2.add(McdsBox, c);
            BKBox = new JCheckBox ("Burger King");
            c.gridy = 2;
            pane2.add(BKBox, c);
            WenBox = new JCheckBox ("Wendys");
            c.gridy = 3;
            pane2.add(WenBox, c);
            PHBox = new JCheckBox ("Pizza Hut");
            c.gridy = 4;
            pane2.add(PHBox, c);
            TJBox = new JCheckBox ("Trader Joes");
            c.gridy = 5;
            pane2.add(TJBox, c);
            WFBox = new JCheckBox ("Whole Foods");
            c.gridy = 6;
            pane2.add(WFBox, c);
            
            pane3 = new JPanel();
            GOBtn = new JButton("GO");
            GOBtn.addActionListener(new GoAddButton());
            pane3.add(GOBtn);
            addressChosenScreen.add(pane2);
            addressChosenScreen.add(pane3, BorderLayout.SOUTH);
        }
    }
    static class GoAddButton implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent e){
            resultsScreen = new JFrame();
            resultsScreen.setVisible(true);
            resultsScreen.setSize(1000, 600);
            
            int[] selectedStores = new int[6];
            if (McdsBox.isSelected() == true){
                selectedStores[0] = 1;
            }else{
                selectedStores[0] = 0;
            }
            if (BKBox.isSelected() == true){
                selectedStores[1] = 1;
            }else{
                selectedStores[1] = 0;
            }
            if (WenBox.isSelected() == true){
                selectedStores[2] = 1;
            }else{
                selectedStores[2] = 0;
            }
            if (PHBox.isSelected() == true){
                selectedStores[3] = 1;
            }else{
                selectedStores[3] = 0;
            }
            if (TJBox.isSelected() == true){
                selectedStores[4] = 1;
            }else{
                selectedStores[4] = 0;
            }
            if (WFBox.isSelected() == true){
                selectedStores[5] = 1;
            }else{
                selectedStores[5] = 0;
            }
            Main main = new Main();
            try {
                output = main.StoresAndAddFromGUI(selectedStores, addUserText.getText());
            } catch (Exception ex) {
                Logger.getLogger(ProjectGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
