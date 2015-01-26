/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoopPool;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.SequenceInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.DropMode;
import javax.swing.JFileChooser;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kamonson And Timothy
 */
class MyCustomFilter extends javax.swing.filechooser.FileFilter {

    @Override
    public boolean accept(File file) {
        // Allow only directories, or files with ".txt" extension
        return file.isDirectory() || file.getAbsolutePath().endsWith(".wav");
    }

    @Override
    public String getDescription() {
        // This description will be displayed in the dialog,
        // hard-coded = ugly, should be done via I18N
        return "Text documents (*.wav)";
    }
}

public class LoopPoolGUI extends javax.swing.JFrame {

    /**
     * Creates new form LoopPoolGUI
     */
    AudioFile a = new AudioFile();
    AudioFile a1 = new AudioFile();
    Boolean b = true;
    Vector<AudioFile> v = new Vector();
    int row;

    private final TransferHandler handler = new TableRowTransferHandler();

    public LoopPoolGUI() {
        initComponents();
        this.TableCombine.setDragEnabled(true);

        this.MusicTable.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.MusicTable.setTransferHandler(handler);
        this.MusicTable.setDropMode(DropMode.INSERT_ROWS);
        this.MusicTable.setDragEnabled(true);
        this.MusicTable.setFillsViewportHeight(true);

        this.MusicTable2.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.MusicTable2.setTransferHandler(handler);
        this.MusicTable2.setDropMode(DropMode.INSERT_ROWS);
        this.MusicTable2.setDragEnabled(true);
        this.MusicTable2.setFillsViewportHeight(true);

        this.TableCombine.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.TableCombine.setTransferHandler(handler);
        this.TableCombine.setDropMode(DropMode.INSERT_ROWS);
        this.TableCombine.setDragEnabled(true);
        this.TableCombine.setFillsViewportHeight(true);

    }

    /**
     *
     * @param S is the AudioFile and puts it into the table that is showing
     */
    public void addItem(AudioFile S) {
        if (this.jPanel1.isShowing()) {
            DefaultTableModel model = (DefaultTableModel) this.MusicTable.getModel();
            model.addRow(new Object[]{S});
        } else if (this.jPanel2.isShowing()) {
            DefaultTableModel model = (DefaultTableModel) this.MusicTable2.getModel();
            model.addRow(new Object[]{S});
        }
    }

    /**
     * This will stop the Audio from playing
     */
    void Stop() {
        for (int i = 0; i < v.size(); i++) {
            v.get(i).AudioStop();
        }

    }

    /**
     * Plays the songs that are selected in the table
     */
    void Play() {
        if (MusicTable.getSelectedRow() >= 0 && MusicTable2.getSelectedRow() < 0) {
            row = MusicTable.getSelectedRow();
            a = (AudioFile) this.MusicTable.getValueAt(row, 0);
            a.AudioPlay();
        }
        if (MusicTable.getSelectedRow() >= 0 && MusicTable2.getSelectedRow() >= 0) {
            row = MusicTable.getSelectedRow();
            a = (AudioFile) this.MusicTable.getValueAt(row, 0);
            a.AudioPlay();
            row = MusicTable2.getSelectedRow();
            a = (AudioFile) this.MusicTable2.getValueAt(row, 0);
            a.AudioPlay();
        }
        if (MusicTable.getSelectedRow() < 0 && MusicTable2.getSelectedRow() >= 0) {
            row = MusicTable2.getSelectedRow();
            a = (AudioFile) this.MusicTable2.getValueAt(row, 0);
            a.AudioPlay();
        }
    }

    /**
     *
     * @param r row position that is to be deleted
     * @param t the table that holds the row you want to delete
     */
    public void ClearRow(int r, javax.swing.JTable t) {
        DefaultTableModel model = (DefaultTableModel) t.getModel();
        {
            model.removeRow(r);
        }
    }

    /**
     * This will open the file chooser and import the selected audio files into
     * a vector of audio files
     */
    void importFile() {
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            //this.a.setName(fileChooser.getName(file));
            v.add(new AudioFile(file.getAbsolutePath()));
            addItem(v.get(v.size() - 1));

        } else {
            System.out.println("File access cancelled by user.");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        PlayB = new javax.swing.JButton();
        RecordB = new javax.swing.JButton();
        ExportB = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        MusicTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        MusicTable2 = new javax.swing.JTable();
        StopB = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableCombine = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Open = new javax.swing.JMenuItem();
        Exit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PlayB.setText("Play");
        PlayB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlayBActionPerformed(evt);
            }
        });
        PlayB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PlayBKeyTyped(evt);
            }
        });

        RecordB.setText("Record");
        RecordB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RecordBActionPerformed(evt);
            }
        });

        ExportB.setText("Export");
        ExportB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExportBActionPerformed(evt);
            }
        });

        jButton4.setText("Chang Start Time");

        MusicTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Layer 1"
            }
        ));
        MusicTable.setDragEnabled(true);
        MusicTable.setDropMode(javax.swing.DropMode.INSERT_ROWS);
        MusicTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MusicTableKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                MusicTableKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                MusicTableKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(MusicTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Layer 1", jPanel1);

        MusicTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Layer 2"
            }
        ));
        MusicTable2.setDragEnabled(true);
        MusicTable2.setDropMode(javax.swing.DropMode.INSERT_ROWS);
        MusicTable2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MusicTable2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                MusicTable2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                MusicTable2KeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(MusicTable2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Layer 2", jPanel2);

        StopB.setText("Stop");
        StopB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StopBActionPerformed(evt);
            }
        });

        TableCombine.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Loops to Combine"
            }
        ));
        TableCombine.setToolTipText("");
        TableCombine.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TableCombine.setDragEnabled(true);
        TableCombine.setDropMode(javax.swing.DropMode.INSERT_ROWS);
        TableCombine.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TableCombineMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(TableCombine);

        jMenu1.setText("File");

        Open.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        Open.setText("Open Audio File");
        Open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenActionPerformed(evt);
            }
        });
        jMenu1.add(Open);

        Exit.setText("Exit");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });
        jMenu1.add(Exit);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PlayB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RecordB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ExportB, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(StopB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jTabbedPane1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PlayB)
                    .addComponent(RecordB)
                    .addComponent(ExportB)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4)
                    .addComponent(StopB))
                .addGap(29, 29, 29)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/**
     *
     * @param evt the event when the open button is clicked
     */
    private void OpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenActionPerformed
        importFile();
        // jTextField1.setText(a.sLength());
    }//GEN-LAST:event_OpenActionPerformed
    /**
     *
     * @param evt Event of the exit button click
     */
    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ExitActionPerformed
    /**
     *
     * @param evt Event of the stop button click
     */
    private void StopBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StopBActionPerformed
        Stop();
    }//GEN-LAST:event_StopBActionPerformed
    /**
     *
     * @param evt Event of the play button click
     */
    private void PlayBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlayBActionPerformed
        Play();
    }//GEN-LAST:event_PlayBActionPerformed
    /**
     * Finds elements from the tablecombine and loops through adding each to the
     * conwav.wav file
     *
     * @param evt Event of the Export button click
     */
    private void ExportBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExportBActionPerformed
        try {
            for (int i = 0; i < this.TableCombine.getRowCount(); i++) {
                a = (AudioFile) this.TableCombine.getValueAt(i, 0);

                AudioInputStream clip1 = AudioSystem.getAudioInputStream(new File(a.getLocation()));

                AudioInputStream clip2 = AudioSystem.getAudioInputStream(new File("conwav.wav"));

                AudioInputStream appendedFiles
                        = new AudioInputStream(
                                new SequenceInputStream(clip1, clip2),
                                clip1.getFormat(),
                                clip1.getFrameLength() + clip2.getFrameLength());
                AudioSystem.write(appendedFiles, AudioFileFormat.Type.WAVE, new File("conwav.wav"));

            }
        } catch (Exception Ex) {
            Ex.printStackTrace();
        }
    }//GEN-LAST:event_ExportBActionPerformed
    /**
     *
     * @param evt Event of any key stroke while the play button is selected
     */
    private void PlayBKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PlayBKeyTyped
        Play();
    }//GEN-LAST:event_PlayBKeyTyped
    /**
     *
     * @param evt Event of any key stroke while table one is selected.. deletes
     * the given row selected in the table
     */
    private void MusicTableKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MusicTableKeyTyped
        char c = evt.getKeyChar();
        if (c == KeyEvent.VK_DELETE) {
            this.ClearRow(this.MusicTable.getSelectedRow(), this.MusicTable);
        }
    }//GEN-LAST:event_MusicTableKeyTyped
    /**
     *
     * @param evt when space bar is released the music starts
     */
    private void MusicTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MusicTableKeyReleased
        char c = evt.getKeyChar();
        if (c == KeyEvent.VK_SPACE) {
            Stop();
            b = true;
        }
    }//GEN-LAST:event_MusicTableKeyReleased
    /**
     *
     * @param evt when space bar is pressed the music starts
     */
    private void MusicTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MusicTableKeyPressed
        char c = evt.getKeyChar();
        if (c == KeyEvent.VK_SPACE) {
            if (b) {
                b = false;
                Play();
            }
        }
    }//GEN-LAST:event_MusicTableKeyPressed
    /**
     *
     * @param evt vent of any key stroke while table two is selected.. deletes
     * the given row selected in the table
     */
    private void MusicTable2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MusicTable2KeyPressed
        char c = evt.getKeyChar();
        if (c == KeyEvent.VK_DELETE) {
            this.ClearRow(this.MusicTable2.getSelectedRow(), this.MusicTable2);
        }
    }//GEN-LAST:event_MusicTable2KeyPressed

    private void MusicTable2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MusicTable2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_MusicTable2KeyReleased

    private void MusicTable2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MusicTable2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_MusicTable2KeyTyped

    private void TableCombineMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableCombineMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_TableCombineMouseReleased

    private void RecordBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RecordBActionPerformed
        try {
            Path srcFile = Paths.get(v.get(this.MusicTable.convertRowIndexToModel(this.MusicTable.getSelectedRow())).getLocation());
            Path srcFile2 = Paths.get(v.get(this.MusicTable2.convertRowIndexToModel(this.MusicTable2.getSelectedRow())).getLocation());
            File dstFile = new File("newwav.wav");
            try (FileOutputStream out = new FileOutputStream(dstFile)) {
                byte[] src1 = Files.readAllBytes(srcFile);
                byte[] src2 = Files.readAllBytes(srcFile2);
                byte[] buf = new byte[src1.length - 44];
                for (int i = 0; i < v.get(0).getFrameLength(); i++) {
                    buf[i] = (byte) ((src1[i] + src2[i]) >> 1); //Records empty sound
                }
                out.write(buf);
            }
        } catch (Exception EX) {
            System.out.printf(EX.toString());
        }
    }//GEN-LAST:event_RecordBActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoopPoolGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoopPoolGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoopPoolGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoopPoolGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoopPoolGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Exit;
    private javax.swing.JButton ExportB;
    private javax.swing.JTable MusicTable;
    private javax.swing.JTable MusicTable2;
    private javax.swing.JMenuItem Open;
    private javax.swing.JButton PlayB;
    private javax.swing.JButton RecordB;
    private javax.swing.JButton StopB;
    private javax.swing.JTable TableCombine;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JButton jButton4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
