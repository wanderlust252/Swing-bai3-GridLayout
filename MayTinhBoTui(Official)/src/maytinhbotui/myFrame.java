/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maytinhbotui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Admin
 */
public class myFrame extends JFrame {

    private Panel panelOUT;
    private JLabel lblOUT;
    private Panel panelnumber;
    private ArrayList<JButton> arr;
    private String num = "";
    private boolean checkTinhToan = true;
    private int KQ = 0;
    private int checkDau = 0;
    private Panel panelOut1;
    private JLabel lblempty;
    private String[] but1;

    myFrame() {
        super("May tinh cua Hieu");
        this.setSize(400, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.setBackground(Color.black);

        this.giaodien();
        this.xuly();

        //this.getContentPane().add(panel);
        //this.pack();
        this.setVisible(true);

    }

    private void giaodien() {
        // panel.setSize(400,400);

        panelOUT = new Panel();
        panelOUT.setSize(300, 500);
        panelOUT.setLayout(new BorderLayout());
        lblOUT = new JLabel("0");

        // lblOUT.setSize(10, 21);
        lblOUT.setFont(new Font("Arial", Font.BOLD, 100));
        panelOUT.add(lblOUT, BorderLayout.EAST);

        panelnumber = new Panel();
        panelnumber.setLayout(new GridLayout(4, 4, 10, 10));

        panelOut1 = new Panel();
        panelOut1.setBackground(new Color(66, 244, 200));
        panelOut1.add(panelOUT, BorderLayout.EAST);
        lblempty = new JLabel(" ");
        lblempty.setFont(new Font("Arial", Font.BOLD, 100));
        panelOut1.add(lblempty, BorderLayout.CENTER);

        String[] but = new String("7 8 9 + 4 5 6 - 1 2 3 * AC 0 = /").split(" ");
        but1 = new String("13 8 9 10 4 5 6 0 1 2 0 1 2 3 4 5 6 7 8 9").split(" ");
        arr = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            JButton btn = new JButton() {
                @Override
                protected void paintChildren(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                    g.setColor(Color.red);

                    g.drawRect(0, 0, (int) this.getSize().getWidth(), (int) this.getSize().getHeight());
                    super.paintChildren(g);
                }
            };
            btn.setText(but[i]);
            btn.setFont(new Font("Consolas", Font.BOLD, 20));

            panelnumber.add(btn);

            arr.add(btn);
        }

        this.add(panelOut1, BorderLayout.NORTH);
        this.add(panelnumber, BorderLayout.SOUTH);
        panelnumber.setPreferredSize(new Dimension(0, 400));
    }

    private void xuly() {
        for (int i = 0; i < 10; i++) {
            String index = arr.get(Integer.parseInt(but1[i])).getText();
            arr.get(Integer.parseInt(but1[i])).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    num += index;
                    lblOUT.setText(num);
                }
            });
        }
        arr.get(3).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkTinhToan == false) {
                    return;
                }
                tinhtoan(1);
            }
        });
        arr.get(7).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkTinhToan == false) {
                    return;
                }
                tinhtoan(2);
            }
        });
        arr.get(14).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KetQua();
            }
        });
        arr.get(12).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AllClear();
            }
        });
        arr.get(15).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkTinhToan == false) {
                    return;
                }
                tinhtoan(4);
            }
        });
        arr.get(11).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkTinhToan == false) {
                    return;
                }
                tinhtoan(3);
            }
        });
    }

    private void tinhtoan(int index) {
        KQ = Integer.parseInt(lblOUT.getText());
        num = "";
        lblOUT.setText(num);
        checkTinhToan = false;
        checkDau = index;

    }

    private void AllClear() {
        KQ = 0;
        num = "";
        lblOUT.setText("0");
        checkTinhToan = true;
    }

    private void KetQua() {
        if (lblOUT.getText() == "" || checkTinhToan == true) {
            return;
        }
        if (checkDau == 1) {
            KQ += Integer.parseInt(lblOUT.getText());
            lblOUT.setText(KQ + "");
        } else if (checkDau == 2) {
            KQ -= Integer.parseInt(lblOUT.getText());
            lblOUT.setText(KQ + "");

        } else if (checkDau == 3) {
            KQ *= Integer.parseInt(lblOUT.getText());
            lblOUT.setText(KQ + "");

        } else if (checkDau == 4) {
            KQ /= Integer.parseInt(lblOUT.getText());
            lblOUT.setText(KQ + "");

        }
        num = "";
        checkTinhToan = true;
    }

}
