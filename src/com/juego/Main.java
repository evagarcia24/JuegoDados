package com.juego;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame {
    private Dado dado3;
    private Dado dado21;
    private JLabel labelResultado3;
    private JLabel labelResultado21;
    private JLabel labelDado3;
    private JLabel labelDado21;
    
    public Main() {
        dado3 = new Dado(3);
        dado21 = new Dado(21);
        
        setTitle("Juego de Rol - Sistema de Dados");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panelPrincipal = crearPanelConFondo();
        panelPrincipal.setLayout(new BorderLayout(20, 20));
        
        JLabel titulo = new JLabel("JUEGO DE ROL - SISTEMA DE DADOS", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);
        titulo.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 20));
        panelPrincipal.add(titulo, BorderLayout.NORTH);
        
        JPanel panelDados = new JPanel(new GridLayout(1, 2, 40, 0));
        panelDados.setOpaque(false);
        panelDados.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        
        // Crear dado 3
        labelDado3 = new JLabel("-");
        labelResultado3 = new JLabel("-");
        JPanel panelDado3 = crearPanelDado("DADO DE 3 CARAS", labelDado3, labelResultado3);
        panelDados.add(panelDado3);
        
        // Crear dado 21
        labelDado21 = new JLabel("-");
        labelResultado21 = new JLabel("-");
        JPanel panelDado21 = crearPanelDado("DADO DE 21 CARAS", labelDado21, labelResultado21);
        panelDados.add(panelDado21);
        
        panelPrincipal.add(panelDados, BorderLayout.CENTER);
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelBotones.setOpaque(false);
        panelBotones.setBorder(BorderFactory.createEmptyBorder(0, 20, 30, 20));
        
        JButton btnDado3 = crearBoton("Tirar Dado 3");
        btnDado3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tirarDado3();
            }
        });
        
        JButton btnDado21 = crearBoton("Tirar Dado 21");
        btnDado21.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tirarDado21();
            }
        });
        
        JButton btnAmbos = crearBoton("Tirar Ambos");
        btnAmbos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tirarAmbos();
            }
        });
        
        panelBotones.add(btnDado3);
        panelBotones.add(btnDado21);
        panelBotones.add(btnAmbos);
        
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        
        add(panelPrincipal);
    }
    
    private JPanel crearPanelConFondo() {
        return new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(102, 126, 234);
                Color color2 = new Color(118, 75, 162);
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
    }
    
    private JPanel crearPanelDado(String titulo, JLabel lblDado, JLabel lblResultado) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        
        // Título
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        // Panel del dado visual
        JPanel panelDadoVisual = new JPanel(new GridBagLayout());
        panelDadoVisual.setPreferredSize(new Dimension(200, 200));
        panelDadoVisual.setMaximumSize(new Dimension(200, 200));
        panelDadoVisual.setBackground(Color.WHITE);
        panelDadoVisual.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(51, 51, 51), 3),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        // Configurar label del dado
        lblDado.setFont(new Font("Arial", Font.BOLD, 80));
        lblDado.setForeground(new Color(51, 51, 51));
        panelDadoVisual.add(lblDado);
        
        // Configurar label del resultado
        lblResultado.setFont(new Font("Arial", Font.BOLD, 24));
        lblResultado.setForeground(Color.WHITE);
        lblResultado.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblResultado.setOpaque(true);
        lblResultado.setBackground(new Color(255, 255, 255, 50));
        lblResultado.setBorder(BorderFactory.createEmptyBorder(15, 40, 15, 40));
        
        panel.add(lblTitulo);
        panel.add(Box.createVerticalStrut(10));
        panel.add(panelDadoVisual);
        panel.add(Box.createVerticalStrut(20));
        panel.add(lblResultado);
        
        return panel;
    }
    
    private JButton crearBoton(String texto) {
        final JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setForeground(new Color(102, 126, 234));
        boton.setBackground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        boton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                boton.setBackground(new Color(240, 240, 240));
            }
            public void mouseExited(MouseEvent evt) {
                boton.setBackground(Color.WHITE);
            }
        });
        
        return boton;
    }
    
    private void tirarDado3() {
        animarDado(labelDado3, labelResultado3, dado3);
    }
    
    private void tirarDado21() {
        animarDado(labelDado21, labelResultado21, dado21);
    }
    
    private void tirarAmbos() {
        tirarDado3();
        Timer timer = new Timer(300, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tirarDado21();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
    
    private void animarDado(final JLabel labelDado, final JLabel labelResultado, final Dado dado) {
        labelDado.setText("?");
        labelResultado.setText("...");
        
        final int[] contador = new int[1];
        contador[0] = 0;
        
        Timer animacion = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                labelDado.setText(String.valueOf((int)(Math.random() * dado.getCaras()) + 1));
                contador[0]++;
                
                if (contador[0] >= 10) {
                    Timer temporizador = (Timer) e.getSource();
                    temporizador.stop();
                    int resultado = dado.tirar();
                    labelDado.setText(String.valueOf(resultado));
                    labelResultado.setText("Resultado: " + resultado);
                }
            }
        });
        
        animacion.start();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Main ventana = new Main();
                ventana.setVisible(true);
            }
        });
    }
}