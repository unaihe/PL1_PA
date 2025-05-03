/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaz;

import javax.swing.DefaultListModel;
import poo.apocalipsiszombie.ControlPausa;

/**
 *
 * @author unaih
 */
public class Interfaz extends javax.swing.JFrame {
    private ControlPausa controlPausa;
    private boolean pausado=false;
    /**
     * Creates new form Interfaz
     */
    public Interfaz(ControlPausa controlPausa) {
        initComponents();
        DescansoLista.setModel(modeloDescanso);
        ComedorLista.setModel(modeloComedor);
        ComunLista.setModel(modeloComun);
        TunelRefugio1.setModel(modeloTunelR1);
        TunelRefugio2.setModel(modeloTunelR2);
        TunelRefugio3.setModel(modeloTunelR3);
        TunelRefugio4.setModel(modeloTunelR4);
        Tunel1CruzandoS.setModel(modeloTunelC1);
        Tunel2CruzandoS.setModel(modeloTunelC2);
        Tunel3CruzandoS.setModel(modeloTunelC3);
        Tunel4CruzandoS.setModel(modeloTunelC4);
        Tunel1Riesgo.setModel(modeloTunelRi1);
        Tunel2Riesgo.setModel(modeloTunelRi2);
        Tunel3Riesgo.setModel(modeloTunelRi3);
        Tunel4Riesgo.setModel(modeloTunelRi4);
        ZonaRiesgo1H.setModel(modeloRi1H);
        ZonaRiesgo2H.setModel(modeloRi2H);
        ZonaRiesgo3H.setModel(modeloRi3H);
        ZonaRiesgo4H.setModel(modeloRi4H);
        ZonaRiesgo1Z.setModel(modeloRi1Z);
        ZonaRiesgo2Z.setModel(modeloRi2Z);
        ZonaRiesgo3Z.setModel(modeloRi3Z);
        ZonaRiesgo4Z.setModel(modeloRi4Z);
        Comida.setModel(modeloComida);
        this.controlPausa=controlPausa;
    }
    
    private DefaultListModel<String> modeloDescanso = new DefaultListModel<>();
    private DefaultListModel<String> modeloComedor = new DefaultListModel<>();
    private DefaultListModel<String> modeloComun = new DefaultListModel<>();
    private DefaultListModel<String> modeloTunelR1 = new DefaultListModel<>();
    private DefaultListModel<String> modeloTunelR2 = new DefaultListModel<>();
    private DefaultListModel<String> modeloTunelR3 = new DefaultListModel<>();
    private DefaultListModel<String> modeloTunelR4 = new DefaultListModel<>();
    private DefaultListModel<String> modeloTunelC1 = new DefaultListModel<>();
    private DefaultListModel<String> modeloTunelC2 = new DefaultListModel<>();
    private DefaultListModel<String> modeloTunelC3 = new DefaultListModel<>();
    private DefaultListModel<String> modeloTunelC4 = new DefaultListModel<>();
    private DefaultListModel<String> modeloTunelRi1 = new DefaultListModel<>();
    private DefaultListModel<String> modeloTunelRi2 = new DefaultListModel<>();
    private DefaultListModel<String> modeloTunelRi3 = new DefaultListModel<>();
    private DefaultListModel<String> modeloTunelRi4 = new DefaultListModel<>();
    
    private DefaultListModel<String> modeloRi1H = new DefaultListModel<>();
    private DefaultListModel<String> modeloRi2H = new DefaultListModel<>();
    private DefaultListModel<String> modeloRi3H = new DefaultListModel<>();
    private DefaultListModel<String> modeloRi4H = new DefaultListModel<>();
    private DefaultListModel<String> modeloRi1Z = new DefaultListModel<>();
    private DefaultListModel<String> modeloRi2Z = new DefaultListModel<>();
    private DefaultListModel<String> modeloRi3Z = new DefaultListModel<>();
    private DefaultListModel<String> modeloRi4Z = new DefaultListModel<>();
    private DefaultListModel<String> modeloComida = new DefaultListModel<>();

    // ----------- REFUGIO -----------
// Descanso
    public void actualizarDescanso(java.util.Collection<poo.apocalipsiszombie.hilos.Humano> humanos) {
        modeloDescanso.clear();
        for (var h : humanos) {
            modeloDescanso.addElement(h.getHumanoId());
        }
    }

// Comedor (personas)
    public void actualizarComedor(java.util.Collection<poo.apocalipsiszombie.hilos.Humano> humanos) {
        modeloComedor.clear();
        for (var h : humanos) {
            modeloComedor.addElement(h.getHumanoId());
        }
    }

// Comedor (comida)
    public void actualizarComida(int nComida) {
    modeloComida.clear();
    modeloComida.addElement(""+nComida);
}


// Zona común
    public void actualizarZonaComun(java.util.Collection<poo.apocalipsiszombie.hilos.Humano> humanos) {
        modeloComun.clear();
        for (var h : humanos) {
            modeloComun.addElement(h.getHumanoId());
        }
    }

// ----------- TÚNELES -----------
// Refugio (esperando para salir)
    public void actualizarTunelRefugio(int idTunel, java.util.Collection<poo.apocalipsiszombie.hilos.Humano> humanos) {
        switch (idTunel) {
            case 1 -> {
                modeloTunelR1.clear();
                for (var h : humanos) {
                    modeloTunelR1.addElement(h.getHumanoId());
                }
            }
            case 2 -> {
                modeloTunelR2.clear();
                for (var h : humanos) {
                    modeloTunelR2.addElement(h.getHumanoId());
                }
            }
            case 3 -> {
                modeloTunelR3.clear();
                for (var h : humanos) {
                    modeloTunelR3.addElement(h.getHumanoId());
                }
            }
            case 4 -> {
                modeloTunelR4.clear();
                for (var h : humanos) {
                    modeloTunelR4.addElement(h.getHumanoId());
                }
            }
        }
    }

// Cruzando
    public void actualizarTunelCruzando(int idTunel, java.util.Collection<poo.apocalipsiszombie.hilos.Humano> cruzando) {
        switch (idTunel) {
            case 1 -> {
                modeloTunelC1.clear();
                for (var h : cruzando) {
                    modeloTunelC1.addElement(h.getHumanoId());
                }
            }
            case 2 -> {
                modeloTunelC2.clear();
                for (var h : cruzando) {
                    modeloTunelC2.addElement(h.getHumanoId());
                }
            }
            case 3 -> {
                modeloTunelC3.clear();
                for (var h : cruzando) {
                    modeloTunelC3.addElement(h.getHumanoId());
                }
            }
            case 4 -> {
                modeloTunelC4.clear();
                for (var h : cruzando) {
                    modeloTunelC4.addElement(h.getHumanoId());
                }
            }
        }
    }

// Riesgo (esperando para volver)
    public void actualizarTunelRiesgo(int idTunel, java.util.Collection<poo.apocalipsiszombie.hilos.Humano> humanos) {
        switch (idTunel) {
            case 1 -> {
                modeloTunelRi1.clear();
                for (var h : humanos) {
                    modeloTunelRi1.addElement(h.getHumanoId());
                }
            }
            case 2 -> {
                modeloTunelRi2.clear();
                for (var h : humanos) {
                    modeloTunelRi2.addElement(h.getHumanoId());
                }
            }
            case 3 -> {
                modeloTunelRi3.clear();
                for (var h : humanos) {
                    modeloTunelRi3.addElement(h.getHumanoId());
                }
            }
            case 4 -> {
                modeloTunelRi4.clear();
                for (var h : humanos) {
                    modeloTunelRi4.addElement(h.getHumanoId());
                }
            }
        }
    }

// ----------- ZONA DE RIESGO -----------
// Humanos
    public void actualizarZonaRiesgoHumanos(int idZona, java.util.Collection<poo.apocalipsiszombie.hilos.Humano> humanos) {
        switch (idZona) {
            case 1 -> {
                modeloRi1H.clear();
                for (var h : humanos) {
                    modeloRi1H.addElement(h.getHumanoId());
                }
            }
            case 2 -> {
                modeloRi2H.clear();
                for (var h : humanos) {
                    modeloRi2H.addElement(h.getHumanoId());
                }
            }
            case 3 -> {
                modeloRi3H.clear();
                for (var h : humanos) {
                    modeloRi3H.addElement(h.getHumanoId());
                }
            }
            case 4 -> {
                modeloRi4H.clear();
                for (var h : humanos) {
                    modeloRi4H.addElement(h.getHumanoId());
                }
            }
        }
    }

// Zombis
    public void actualizarZonaRiesgoZombis(int idZona, java.util.Collection<poo.apocalipsiszombie.hilos.Zombi> zombis) {
        switch (idZona) {
            case 1 -> {
                modeloRi1Z.clear();
                for (var z : zombis) {
                    modeloRi1Z.addElement(z.getZombiId());
                }
            }
            case 2 -> {
                modeloRi2Z.clear();
                for (var z : zombis) {
                    modeloRi2Z.addElement(z.getZombiId());
                }
            }
            case 3 -> {
                modeloRi3Z.clear();
                for (var z : zombis) {
                    modeloRi3Z.addElement(z.getZombiId());
                }
            }
            case 4 -> {
                modeloRi4Z.clear();
                for (var z : zombis) {
                    modeloRi4Z.addElement(z.getZombiId());
                }
            }
        }
    }
     public void pausarBoton(){
        boton.setText("Reanudar");
        pausado=true;
    }
    public void reanudarBoton(){
        boton.setText("Pausar");
        pausado=false;
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Principal = new javax.swing.JPanel();
        Refugio = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        DescansoLista = new javax.swing.JList<>();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ComedorLista = new javax.swing.JList<>();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ComunLista = new javax.swing.JList<>();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        Comida = new javax.swing.JList<>();
        boton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        Tunel1CruzandoS = new javax.swing.JList<>();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        TunelRefugio1 = new javax.swing.JList<>();
        jScrollPane9 = new javax.swing.JScrollPane();
        Tunel1Riesgo = new javax.swing.JList<>();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        Tunel2CruzandoS = new javax.swing.JList<>();
        jScrollPane10 = new javax.swing.JScrollPane();
        TunelRefugio2 = new javax.swing.JList<>();
        jScrollPane11 = new javax.swing.JScrollPane();
        Tunel2Riesgo = new javax.swing.JList<>();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        Tunel3CruzandoS = new javax.swing.JList<>();
        jScrollPane12 = new javax.swing.JScrollPane();
        Tunel3Riesgo = new javax.swing.JList<>();
        jScrollPane13 = new javax.swing.JScrollPane();
        TunelRefugio3 = new javax.swing.JList<>();
        jScrollPane14 = new javax.swing.JScrollPane();
        TunelRefugio4 = new javax.swing.JList<>();
        jScrollPane15 = new javax.swing.JScrollPane();
        Tunel4CruzandoS = new javax.swing.JList<>();
        jScrollPane16 = new javax.swing.JScrollPane();
        Tunel4Riesgo = new javax.swing.JList<>();
        jLabel16 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane17 = new javax.swing.JScrollPane();
        ZonaRiesgo1H = new javax.swing.JList<>();
        jScrollPane18 = new javax.swing.JScrollPane();
        ZonaRiesgo1Z = new javax.swing.JList<>();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane19 = new javax.swing.JScrollPane();
        ZonaRiesgo2H = new javax.swing.JList<>();
        jScrollPane20 = new javax.swing.JScrollPane();
        ZonaRiesgo2Z = new javax.swing.JList<>();
        jScrollPane21 = new javax.swing.JScrollPane();
        ZonaRiesgo3H = new javax.swing.JList<>();
        jScrollPane22 = new javax.swing.JScrollPane();
        ZonaRiesgo3Z = new javax.swing.JList<>();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane23 = new javax.swing.JScrollPane();
        ZonaRiesgo4Z = new javax.swing.JList<>();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane24 = new javax.swing.JScrollPane();
        ZonaRiesgo4H = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Principal.setBackground(new java.awt.Color(255, 255, 255));
        Principal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Refugio.setBackground(new java.awt.Color(81, 209, 246));
        Refugio.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Refugio");
        Refugio.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 70));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Zona Descanso");
        Refugio.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 130, 40));

        DescansoLista.setBackground(new java.awt.Color(255, 255, 255));
        DescansoLista.setForeground(new java.awt.Color(0, 0, 0));
        DescansoLista.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(DescansoLista);

        Refugio.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 260, 80));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Comedor");
        Refugio.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 130, 40));

        ComedorLista.setBackground(new java.awt.Color(255, 255, 255));
        ComedorLista.setForeground(new java.awt.Color(0, 0, 0));
        ComedorLista.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(ComedorLista);

        Refugio.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 170, 80));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Zona Común");
        Refugio.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 130, 40));

        ComunLista.setBackground(new java.awt.Color(255, 255, 255));
        ComunLista.setForeground(new java.awt.Color(0, 0, 0));
        ComunLista.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(ComunLista);

        Refugio.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 260, 80));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Comida");
        Refugio.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 130, 40));

        Comida.setBackground(new java.awt.Color(255, 255, 255));
        Comida.setForeground(new java.awt.Color(0, 0, 0));
        Comida.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(Comida);

        Refugio.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 240, 40, 40));

        boton.setBackground(new java.awt.Color(255, 255, 255));
        boton.setForeground(new java.awt.Color(0, 0, 0));
        boton.setText("Pausar");
        boton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActionPerformed(evt);
            }
        });
        Refugio.add(boton, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 440, 110, 40));

        Principal.add(Refugio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 500));

        jPanel1.setBackground(new java.awt.Color(205, 150, 108));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Túneles");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 70));

        Tunel1CruzandoS.setBackground(new java.awt.Color(255, 255, 255));
        Tunel1CruzandoS.setForeground(new java.awt.Color(0, 0, 0));
        Tunel1CruzandoS.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane5.setViewportView(Tunel1CruzandoS);

        jPanel1.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 80, 20));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Túnel 1");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 300, 40));

        TunelRefugio1.setBackground(new java.awt.Color(255, 255, 255));
        TunelRefugio1.setForeground(new java.awt.Color(0, 0, 0));
        TunelRefugio1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane8.setViewportView(TunelRefugio1);

        jPanel1.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 80, 50));

        Tunel1Riesgo.setBackground(new java.awt.Color(255, 255, 255));
        Tunel1Riesgo.setForeground(new java.awt.Color(0, 0, 0));
        Tunel1Riesgo.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane9.setViewportView(Tunel1Riesgo);

        jPanel1.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 80, 50));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Túnel 2");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 300, 40));

        Tunel2CruzandoS.setBackground(new java.awt.Color(255, 255, 255));
        Tunel2CruzandoS.setForeground(new java.awt.Color(0, 0, 0));
        Tunel2CruzandoS.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane6.setViewportView(Tunel2CruzandoS);

        jPanel1.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 80, 20));

        TunelRefugio2.setBackground(new java.awt.Color(255, 255, 255));
        TunelRefugio2.setForeground(new java.awt.Color(0, 0, 0));
        TunelRefugio2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane10.setViewportView(TunelRefugio2);

        jPanel1.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 80, 50));

        jScrollPane11.setBackground(new java.awt.Color(255, 255, 255));

        Tunel2Riesgo.setBackground(new java.awt.Color(255, 255, 255));
        Tunel2Riesgo.setForeground(new java.awt.Color(0, 0, 0));
        Tunel2Riesgo.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane11.setViewportView(Tunel2Riesgo);

        jPanel1.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 200, 80, 50));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Túnel 3");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 300, 40));

        Tunel3CruzandoS.setBackground(new java.awt.Color(255, 255, 255));
        Tunel3CruzandoS.setForeground(new java.awt.Color(0, 0, 0));
        Tunel3CruzandoS.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane7.setViewportView(Tunel3CruzandoS);

        jPanel1.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 320, 80, 20));

        Tunel3Riesgo.setBackground(new java.awt.Color(255, 255, 255));
        Tunel3Riesgo.setForeground(new java.awt.Color(0, 0, 0));
        Tunel3Riesgo.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane12.setViewportView(Tunel3Riesgo);

        jPanel1.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 310, 80, 50));

        TunelRefugio3.setBackground(new java.awt.Color(255, 255, 255));
        TunelRefugio3.setForeground(new java.awt.Color(0, 0, 0));
        TunelRefugio3.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane13.setViewportView(TunelRefugio3);

        jPanel1.add(jScrollPane13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 80, 50));

        TunelRefugio4.setBackground(new java.awt.Color(255, 255, 255));
        TunelRefugio4.setForeground(new java.awt.Color(0, 0, 0));
        TunelRefugio4.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane14.setViewportView(TunelRefugio4);

        jPanel1.add(jScrollPane14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 80, 50));

        jScrollPane15.setForeground(new java.awt.Color(0, 0, 0));

        Tunel4CruzandoS.setBackground(new java.awt.Color(255, 255, 255));
        Tunel4CruzandoS.setForeground(new java.awt.Color(0, 0, 0));
        Tunel4CruzandoS.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane15.setViewportView(Tunel4CruzandoS);

        jPanel1.add(jScrollPane15, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 420, 80, 20));

        Tunel4Riesgo.setBackground(new java.awt.Color(255, 255, 255));
        Tunel4Riesgo.setForeground(new java.awt.Color(0, 0, 0));
        Tunel4Riesgo.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane16.setViewportView(Tunel4Riesgo);

        jPanel1.add(jScrollPane16, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 410, 80, 50));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Túnel 4");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 300, 40));

        Principal.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 300, 500));

        jPanel2.setBackground(new java.awt.Color(165, 32, 25));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Zona Riesgo");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 70));

        ZonaRiesgo1H.setBackground(new java.awt.Color(255, 255, 255));
        ZonaRiesgo1H.setForeground(new java.awt.Color(0, 0, 0));
        ZonaRiesgo1H.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane17.setViewportView(ZonaRiesgo1H);

        jPanel2.add(jScrollPane17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 130, 50));

        ZonaRiesgo1Z.setBackground(new java.awt.Color(255, 255, 255));
        ZonaRiesgo1Z.setForeground(new java.awt.Color(0, 0, 0));
        ZonaRiesgo1Z.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane18.setViewportView(ZonaRiesgo1Z);

        jPanel2.add(jScrollPane18, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 130, 50));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Zona 1");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 300, 40));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Zona 2");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 300, 40));

        ZonaRiesgo2H.setBackground(new java.awt.Color(255, 255, 255));
        ZonaRiesgo2H.setForeground(new java.awt.Color(0, 0, 0));
        ZonaRiesgo2H.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane19.setViewportView(ZonaRiesgo2H);

        jPanel2.add(jScrollPane19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 130, 50));

        ZonaRiesgo2Z.setBackground(new java.awt.Color(255, 255, 255));
        ZonaRiesgo2Z.setForeground(new java.awt.Color(0, 0, 0));
        ZonaRiesgo2Z.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane20.setViewportView(ZonaRiesgo2Z);

        jPanel2.add(jScrollPane20, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, 130, 50));

        ZonaRiesgo3H.setBackground(new java.awt.Color(255, 255, 255));
        ZonaRiesgo3H.setForeground(new java.awt.Color(0, 0, 0));
        ZonaRiesgo3H.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane21.setViewportView(ZonaRiesgo3H);

        jPanel2.add(jScrollPane21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 130, 50));

        ZonaRiesgo3Z.setBackground(new java.awt.Color(255, 255, 255));
        ZonaRiesgo3Z.setForeground(new java.awt.Color(0, 0, 0));
        ZonaRiesgo3Z.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane22.setViewportView(ZonaRiesgo3Z);

        jPanel2.add(jScrollPane22, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 310, 130, 50));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Zona 3");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 300, 40));

        ZonaRiesgo4Z.setBackground(new java.awt.Color(255, 255, 255));
        ZonaRiesgo4Z.setForeground(new java.awt.Color(0, 0, 0));
        ZonaRiesgo4Z.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane23.setViewportView(ZonaRiesgo4Z);

        jPanel2.add(jScrollPane23, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 410, 130, 50));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Zona 4");
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 300, 40));

        ZonaRiesgo4H.setBackground(new java.awt.Color(255, 255, 255));
        ZonaRiesgo4H.setForeground(new java.awt.Color(0, 0, 0));
        ZonaRiesgo4H.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane24.setViewportView(ZonaRiesgo4H);

        jPanel2.add(jScrollPane24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 130, 50));

        Principal.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 0, 300, 500));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Principal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Principal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActionPerformed
        // TODO add your handling code here:
        if (controlPausa == null) return;
        if(!pausado){
            boton.setText("Reanudar");
            controlPausa.pausar();
            pausado=true;
        } else{
            boton.setText("Detener");
            controlPausa.reanudar();
            pausado=false;
        }
    }//GEN-LAST:event_botonActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> ComedorLista;
    private javax.swing.JList<String> Comida;
    private javax.swing.JList<String> ComunLista;
    private javax.swing.JList<String> DescansoLista;
    private javax.swing.JPanel Principal;
    private javax.swing.JPanel Refugio;
    private javax.swing.JList<String> Tunel1CruzandoS;
    private javax.swing.JList<String> Tunel1Riesgo;
    private javax.swing.JList<String> Tunel2CruzandoS;
    private javax.swing.JList<String> Tunel2Riesgo;
    private javax.swing.JList<String> Tunel3CruzandoS;
    private javax.swing.JList<String> Tunel3Riesgo;
    private javax.swing.JList<String> Tunel4CruzandoS;
    private javax.swing.JList<String> Tunel4Riesgo;
    private javax.swing.JList<String> TunelRefugio1;
    private javax.swing.JList<String> TunelRefugio2;
    private javax.swing.JList<String> TunelRefugio3;
    private javax.swing.JList<String> TunelRefugio4;
    private javax.swing.JList<String> ZonaRiesgo1H;
    private javax.swing.JList<String> ZonaRiesgo1Z;
    private javax.swing.JList<String> ZonaRiesgo2H;
    private javax.swing.JList<String> ZonaRiesgo2Z;
    private javax.swing.JList<String> ZonaRiesgo3H;
    private javax.swing.JList<String> ZonaRiesgo3Z;
    private javax.swing.JList<String> ZonaRiesgo4H;
    private javax.swing.JList<String> ZonaRiesgo4Z;
    private javax.swing.JButton boton;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    // End of variables declaration//GEN-END:variables
}
