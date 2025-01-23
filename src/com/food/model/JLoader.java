package com.food.model;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.JFrame;

/**
 *
 * @author Savindu Sadeepa
 */
public class JLoader {

    public static void jPanelLoader(JPanel main, JPanel setPanel) {

        main.removeAll();
        GroupLayout layout = new GroupLayout(main);
        main.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(setPanel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(setPanel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
        );
        main.setVisible(true);
        main.repaint();
        System.gc();

    }

    public static void setVisible(JFrame jframe) {

        jframe.setVisible(true);
        System.gc();

    }

    public static void setVisible(JPanel jpanel) {

        jpanel.setVisible(true);
        System.gc();

    }

}
