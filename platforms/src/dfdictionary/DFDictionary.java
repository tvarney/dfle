/*
 * DFDictionary.java
 *
 * Created on Aug 29, 2011, 12:55:23 PM
 */
package dfdictionary;

import dfdictionary.io.DFObjectParser;
import dfdictionary.object.DFObject;
import dfdictionary.object.DFSymbol;
import dfdictionary.object.DFTag;
import dfdictionary.object.DFWord;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author tvarney
 */
public class DFDictionary extends javax.swing.JFrame {

    public static final String symfname = "language_SYM.txt";
    public static final String wordsfname = "language_words.txt";
    public static final String symback = "language_SYM.txt.bak";
    public static final String wordsback = "language_words.txt.bak";
    
    public static final int YES_NO_OPTION = JOptionPane.YES_NO_OPTION;
    
    private Map<String, DFWord> words;
    private Map<String, DFSymbol> symbols;
    private JFileChooser jc;
    private File dir;
    private File symFile, wordFile;
    private DFWord wordpWord, sympWord;
    private DFSymbol sympSymbol, wordpSymbol;

    /** Creates new form DFDictionary */
    public DFDictionary() {
        initComponents();

        words = new HashMap<String, DFWord>();
        symbols = new HashMap<String, DFSymbol>();

        wordpWord = null;
        sympWord = null;
        sympSymbol = null;
        wordpSymbol = null;
        
        File pwd = new File(System.getProperty("user.dir"));
        /**
         * Uncomment for OSX builds. On OS X, the users current working
         * directory is inside of the .app package. To fix this, we get the
         * parent directory 4 times, leaving us at the folder the .app package
         * is in.
         * / //< Remove the space
        pwd = pwd.getParentFile().getParentFile().getParentFile().getParentFile();
        /**/
        
        jc = new JFileChooser(pwd);
        jc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        wordpWordList = new javax.swing.JList();
        wordpAddWordButton = new javax.swing.JButton();
        wordpRemoveWordButton = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        wordTagTextField = new javax.swing.JTextField();
        nounCheckBox = new javax.swing.JCheckBox();
        adjectiveCheckBox = new javax.swing.JCheckBox();
        verbCheckBox = new javax.swing.JCheckBox();
        prefixCheckBox = new javax.swing.JCheckBox();
        jScrollPane4 = new javax.swing.JScrollPane();
        wordpSymbolList = new javax.swing.JList();
        wordpAddSymbolButton = new javax.swing.JButton();
        wordpRemoveSymbolButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        nounSingularFrontCBox = new javax.swing.JCheckBox();
        nounPluralTheCBox = new javax.swing.JCheckBox();
        nounPluralFrontCBox = new javax.swing.JCheckBox();
        nounSingularTheCBox = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nounPluralRearCBox = new javax.swing.JCheckBox();
        nounSingularRearCBox = new javax.swing.JCheckBox();
        nounSingularOfCBox = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        nounPluralOfCBox = new javax.swing.JCheckBox();
        nounSingularCompoundCBox = new javax.swing.JCheckBox();
        nounPluralCompoundCBox = new javax.swing.JCheckBox();
        nounPluralField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        nounSingularField = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        adjRearCBox = new javax.swing.JCheckBox();
        adjFrontCBox = new javax.swing.JCheckBox();
        adjCompoundCBox = new javax.swing.JCheckBox();
        adjDistanceField = new javax.swing.JTextField();
        adjWordField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        verbInfinitiveField = new javax.swing.JTextField();
        verbPresentPartField = new javax.swing.JTextField();
        verbPastPartField = new javax.swing.JTextField();
        verbPastField = new javax.swing.JTextField();
        verbPresentField = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        verbStandardCBox = new javax.swing.JCheckBox();
        jLabel17 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        prefixFrontCBox = new javax.swing.JCheckBox();
        prefixTextField = new javax.swing.JTextField();
        prefixCompoundCBox = new javax.swing.JCheckBox();
        jLabel18 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        sympSymbolList = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        sympWordList = new javax.swing.JList();
        sympAddSymbolButton = new javax.swing.JButton();
        sympRemoveSymbolButton = new javax.swing.JButton();
        sympAddWordButton = new javax.swing.JButton();
        sympRemoveWordButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        saveMenuItem = new javax.swing.JMenu();
        newMenuItem = new javax.swing.JMenuItem();
        loadMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        undoMenuItem = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        redoMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        wordpWordList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        wordpWordList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                wordpWordListValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(wordpWordList);

        wordpAddWordButton.setText("+");
        wordpAddWordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wordpAddWordButtonActionPerformed(evt);
            }
        });

        wordpRemoveWordButton.setText("-");
        wordpRemoveWordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wordpRemoveWordButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Tag:");

        wordTagTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wordTagTextFieldActionPerformed(evt);
            }
        });

        nounCheckBox.setText("Noun");
        nounCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nounCheckBoxActionPerformed(evt);
            }
        });

        adjectiveCheckBox.setText("Adjective");
        adjectiveCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adjectiveCheckBoxActionPerformed(evt);
            }
        });

        verbCheckBox.setText("Verb");
        verbCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verbCheckBoxActionPerformed(evt);
            }
        });

        prefixCheckBox.setText("Prefix");
        prefixCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prefixCheckBoxActionPerformed(evt);
            }
        });

        wordpSymbolList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = {};
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(wordpSymbolList);

        wordpAddSymbolButton.setText("+");
        wordpAddSymbolButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wordpAddSymbolButtonActionPerformed(evt);
            }
        });

        wordpRemoveSymbolButton.setText("-");
        wordpRemoveSymbolButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wordpRemoveSymbolButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(prefixCheckBox)
                    .add(verbCheckBox)
                    .add(adjectiveCheckBox)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jLabel1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(wordTagTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
                    .add(nounCheckBox))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(wordpAddSymbolButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(wordpRemoveSymbolButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(65, 65, 65))
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jScrollPane4, 0, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel1)
                            .add(wordTagTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(nounCheckBox)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(adjectiveCheckBox)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(verbCheckBox)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(prefixCheckBox))
                    .add(jScrollPane4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(wordpAddSymbolButton)
                    .add(wordpRemoveSymbolButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );

        jTabbedPane2.addTab("Main", jPanel3);

        nounSingularFrontCBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nounSingularFrontCBoxActionPerformed(evt);
            }
        });

        nounPluralTheCBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nounPluralTheCBoxActionPerformed(evt);
            }
        });

        nounPluralFrontCBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nounPluralFrontCBoxActionPerformed(evt);
            }
        });

        nounSingularTheCBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nounSingularTheCBoxActionPerformed(evt);
            }
        });

        jLabel5.setText("The Compound:");

        jLabel9.setText("Rear Compound:");

        jLabel4.setText("Of:");

        nounPluralRearCBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nounPluralRearCBoxActionPerformed(evt);
            }
        });

        nounSingularRearCBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nounSingularRearCBoxActionPerformed(evt);
            }
        });

        nounSingularOfCBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nounSingularOfCBoxActionPerformed(evt);
            }
        });

        jLabel2.setText("Singular:");

        nounPluralOfCBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nounPluralOfCBoxActionPerformed(evt);
            }
        });

        nounSingularCompoundCBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nounSingularCompoundCBoxActionPerformed(evt);
            }
        });

        nounPluralCompoundCBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nounPluralCompoundCBoxActionPerformed(evt);
            }
        });

        nounPluralField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nounPluralFieldActionPerformed(evt);
            }
        });

        jLabel6.setText("S.");

        jLabel3.setText("The:");

        jLabel7.setText("P.");

        jLabel8.setText("Plural:");

        jLabel10.setText("Front Compound:");

        nounSingularField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nounSingularFieldActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel4Layout.createSequentialGroup()
                                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                    .add(jLabel5)
                                    .add(jLabel4)
                                    .add(jLabel3)
                                    .add(jLabel10)
                                    .add(jLabel9))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPanel4Layout.createSequentialGroup()
                                        .add(nounSingularFrontCBox)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(nounPluralFrontCBox))
                                    .add(jPanel4Layout.createSequentialGroup()
                                        .add(nounSingularTheCBox)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(nounPluralTheCBox))
                                    .add(jPanel4Layout.createSequentialGroup()
                                        .add(nounSingularOfCBox)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(nounPluralOfCBox))
                                    .add(jPanel4Layout.createSequentialGroup()
                                        .add(nounSingularCompoundCBox)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(nounPluralCompoundCBox))
                                    .add(jPanel4Layout.createSequentialGroup()
                                        .add(nounSingularRearCBox)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(nounPluralRearCBox))))
                            .add(jPanel4Layout.createSequentialGroup()
                                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                    .add(jLabel8)
                                    .add(jLabel2))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                    .add(nounPluralField)
                                    .add(nounSingularField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)))))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel4Layout.createSequentialGroup()
                        .add(142, 142, 142)
                        .add(jLabel6)
                        .add(18, 18, 18)
                        .add(jLabel7)))
                .add(60, 60, 60))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(nounSingularField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(nounPluralField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel8))
                .add(18, 18, 18)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel6)
                    .add(jLabel7))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(nounSingularTheCBox)
                    .add(jLabel3)
                    .add(nounPluralTheCBox))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel4)
                    .add(nounSingularOfCBox)
                    .add(nounPluralOfCBox))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                        .add(jLabel5)
                        .add(nounSingularCompoundCBox))
                    .add(nounPluralCompoundCBox))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel10)
                    .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(nounSingularFrontCBox)
                        .add(nounPluralFrontCBox)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel9)
                    .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(nounSingularRearCBox)
                        .add(nounPluralRearCBox)))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Noun", jPanel4);

        jLabel11.setText("Adjective:");

        adjRearCBox.setText("Rear Compound");
        adjRearCBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adjRearCBoxActionPerformed(evt);
            }
        });

        adjFrontCBox.setText("Front Compound");
        adjFrontCBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adjFrontCBoxActionPerformed(evt);
            }
        });

        adjCompoundCBox.setText("The Compound");
        adjCompoundCBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adjCompoundCBoxActionPerformed(evt);
            }
        });

        adjDistanceField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adjDistanceFieldActionPerformed(evt);
            }
        });

        adjWordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adjWordFieldActionPerformed(evt);
            }
        });

        jLabel12.setText("Adj. Dist.");

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel5Layout.createSequentialGroup()
                        .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel11)
                            .add(jLabel12))
                        .add(1, 1, 1)
                        .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(adjWordField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 112, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(adjDistanceField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 53, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(adjCompoundCBox)
                    .add(adjFrontCBox)
                    .add(adjRearCBox))
                .addContainerGap(222, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel11)
                    .add(adjWordField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel12)
                    .add(adjDistanceField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(adjCompoundCBox)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(adjFrontCBox)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(adjRearCBox)
                .addContainerGap(160, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Adjective", jPanel5);

        jLabel13.setText("Past Participle:");

        jLabel14.setText("Present Participle:");

        verbInfinitiveField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verbInfinitiveFieldActionPerformed(evt);
            }
        });

        verbPresentPartField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verbPresentPartFieldActionPerformed(evt);
            }
        });

        verbPastPartField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verbPastPartFieldActionPerformed(evt);
            }
        });

        verbPastField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verbPastFieldActionPerformed(evt);
            }
        });

        verbPresentField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verbPresentFieldActionPerformed(evt);
            }
        });

        jLabel15.setText("Infinitive:");

        jLabel16.setText("Past Tense:");

        verbStandardCBox.setText("Standard Verb");
        verbStandardCBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verbStandardCBoxActionPerformed(evt);
            }
        });

        jLabel17.setText("Present Simple:");

        org.jdesktop.layout.GroupLayout jPanel6Layout = new org.jdesktop.layout.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel17)
                    .add(jLabel15)
                    .add(jLabel16)
                    .add(jLabel13)
                    .add(jLabel14))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, verbStandardCBox)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                        .add(verbPastField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 111, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(verbPresentField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 111, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(verbInfinitiveField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 111, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(verbPastPartField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 111, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, verbPresentPartField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 111, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(156, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(verbInfinitiveField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel15))
                .add(2, 2, 2)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(verbPresentField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel17))
                .add(4, 4, 4)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(verbPastField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel16))
                .add(2, 2, 2)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(verbPastPartField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel13))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(verbPresentPartField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel14))
                .add(10, 10, 10)
                .add(verbStandardCBox)
                .addContainerGap(108, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Verb", jPanel6);

        prefixFrontCBox.setText("Front Compound");
        prefixFrontCBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prefixFrontCBoxActionPerformed(evt);
            }
        });

        prefixTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prefixTextFieldActionPerformed(evt);
            }
        });

        prefixCompoundCBox.setText("The Compound");
        prefixCompoundCBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prefixCompoundCBoxActionPerformed(evt);
            }
        });

        jLabel18.setText("Prefix:");

        org.jdesktop.layout.GroupLayout jPanel7Layout = new org.jdesktop.layout.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel18)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(prefixFrontCBox)
                    .add(prefixCompoundCBox)
                    .add(prefixTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 98, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(209, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel18)
                    .add(prefixTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(prefixCompoundCBox)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(prefixFrontCBox)
                .addContainerGap(219, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Prefix", jPanel7);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(wordpAddWordButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(wordpRemoveWordButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 42, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 129, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jTabbedPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(wordpAddWordButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(wordpRemoveWordButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .add(jTabbedPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Words", jPanel2);

        sympSymbolList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        sympSymbolList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                sympSymbolListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(sympSymbolList);

        sympWordList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(sympWordList);

        sympAddSymbolButton.setText("+");
        sympAddSymbolButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sympAddSymbolButtonActionPerformed(evt);
            }
        });

        sympRemoveSymbolButton.setText("-");
        sympRemoveSymbolButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sympRemoveSymbolButtonActionPerformed(evt);
            }
        });

        sympAddWordButton.setText("+");
        sympAddWordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sympAddWordButtonActionPerformed(evt);
            }
        });

        sympRemoveWordButton.setText("-");
        sympRemoveWordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sympRemoveWordButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 122, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(sympAddSymbolButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(sympRemoveSymbolButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(31, 31, 31)
                        .add(sympAddWordButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(sympRemoveWordButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 42, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 131, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(311, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jScrollPane2)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(sympAddSymbolButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(sympRemoveSymbolButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(sympAddWordButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(sympRemoveWordButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Symbols", jPanel1);

        saveMenuItem.setText("File");

        newMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        newMenuItem.setText("New");
        newMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newMenuItemActionPerformed(evt);
            }
        });
        saveMenuItem.add(newMenuItem);

        loadMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        loadMenuItem.setText("Load");
        loadMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadMenuItemActionPerformed(evt);
            }
        });
        saveMenuItem.add(loadMenuItem);
        saveMenuItem.add(jSeparator1);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Save");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        saveMenuItem.add(jMenuItem3);

        saveAsMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        saveAsMenuItem.setText("Save As");
        saveMenuItem.add(saveAsMenuItem);

        jMenuBar1.add(saveMenuItem);

        undoMenuItem.setText("Edit");

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setText("Undo");
        undoMenuItem.add(jMenuItem5);

        redoMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        redoMenuItem.setText("Redo");
        undoMenuItem.add(redoMenuItem);

        jMenuBar1.add(undoMenuItem);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
// <editor-fold defaultstate="expanded" desc="ActionListeners">
private void loadMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadMenuItemActionPerformed
    if ((jc.showOpenDialog(this)) == JFileChooser.APPROVE_OPTION) {
        dir = jc.getSelectedFile();

        if (!dir.isDirectory()) {
            JOptionPane.showMessageDialog(this, "Invalid directory!");
        }

        symFile = new File(dir, symfname);
        wordFile = new File(dir, wordsfname);

        words.clear();
        symbols.clear();

        String fmt = "Error reading %s\n%s";
        if (symFile.isFile()) {
            try {
                readSymbols(symFile);
            } catch (FileNotFoundException fnfe) {
                /* Shouldn't be possible */
            } catch (IOException ioe) {
                String msg = String.format(fmt, symfname, ioe.toString());
                JOptionPane.showMessageDialog(this, msg);
            }
        } else {
            System.err.printf("language_SYM.txt does not exist\n");
        }
        if (wordFile.isFile()) {
            try {
                readWords(wordFile);
            } catch (FileNotFoundException fnfe) {
                /* Shouldn't be possible */
            } catch (IOException ioe) {
                String msg = String.format(fmt, wordsfname, ioe.toString());
                JOptionPane.showMessageDialog(this, msg);
            }
        } else {
            System.err.printf("language_words.txt does not exist\n");
        }

        for (DFSymbol sym : symbols.values()) {
            String[] warr = sym.wordArray();
            for (String s : warr) {
                try {
                    words.get(s).add(sym.tag);
                } catch (Exception e) {
                    System.err.printf("Bad Reference");
                }
            }
        }

        updateWordpWordList();
        updateSympSymbolList();
    }
}//GEN-LAST:event_loadMenuItemActionPerformed
private void nounSingularFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nounSingularFieldActionPerformed
    if (wordpWord == null) {
        return;
    
    }
    
    wordpWord.noun.singular = nounSingularField.getText();
}//GEN-LAST:event_nounSingularFieldActionPerformed
private void wordpWordListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_wordpWordListValueChanged
    String s = (String) wordpWordList.getSelectedValue();
    if (s != null) {
        wordpWord = words.get(s);
        wordTagTextField.setText(wordpWord.tag);
        
        nounCheckBox.setSelected(wordpWord.is_noun);
        adjectiveCheckBox.setSelected(wordpWord.is_adj);
        verbCheckBox.setSelected(wordpWord.is_verb);
        prefixCheckBox.setSelected(wordpWord.is_prefix);
        
        DFWord.Noun noun = wordpWord.noun;
        nounSingularField.setText(noun.singular);
        nounSingularTheCBox.setSelected(noun.the_singular);
        nounSingularOfCBox.setSelected(noun.of_singular);
        nounSingularCompoundCBox.setSelected(noun.the_compound_singular);
        nounSingularFrontCBox.setSelected(noun.front_compound_singular);
        nounSingularRearCBox.setSelected(noun.rear_compound_singular);
        nounPluralField.setText(noun.plural);
        nounPluralTheCBox.setSelected(noun.the_plural);
        nounPluralOfCBox.setSelected(noun.of_plural);
        nounPluralCompoundCBox.setSelected(noun.the_compound_plural);
        nounPluralFrontCBox.setSelected(noun.front_compound_plural);
        nounPluralRearCBox.setSelected(noun.rear_compound_plural);
        
        DFWord.Adjective adj = wordpWord.adj;
        adjWordField.setText(adj.adj);
        adjDistanceField.setText(((Integer)adj.adjective_distance).toString());
        adjCompoundCBox.setSelected(adj.the_adj);
        adjFrontCBox.setSelected(adj.front_adj);
        adjRearCBox.setSelected(adj.rear_adj);
        
        DFWord.Verb verb = wordpWord.verb;
        verbInfinitiveField.setText(verb.infinitive);
        verbPresentField.setText(verb.present);
        verbPastField.setText(verb.past);
        verbPresentPartField.setText(verb.presentpart);
        verbPastPartField.setText(verb.pastpart);
        verbStandardCBox.setSelected(verb.standard_verb);
        
        DFWord.Prefix prfx = wordpWord.prefix;
        prefixTextField.setText(prfx.pfx);
        prefixCompoundCBox.setSelected(prfx.the_prefix);
        prefixFrontCBox.setSelected(prfx.front_prefix);
        
        updateWordpSymbolList();
    }
}//GEN-LAST:event_wordpWordListValueChanged
private void wordpAddSymbolButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wordpAddSymbolButtonActionPerformed
    if (wordpWord == null) {
        return;
    }

    String s = JOptionPane.showInputDialog(this, "Add symbol to word:");
    if (s != null && !s.equals("")) {
        if (!symbols.containsKey(s)) {
            JOptionPane.showMessageDialog(this, "Symbol does not exist");
            return;
        }
        wordpWord.add(s);
        symbols.get(s).add(wordpWord.tag);

        updateWordpSymbolList();
        if (sympSymbol != null && sympSymbol.tag.equals(s)) {
            updateSympWordList();
        }
    }
}//GEN-LAST:event_wordpAddSymbolButtonActionPerformed
private void wordpRemoveSymbolButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wordpRemoveSymbolButtonActionPerformed
    if (wordpWord == null) {
        return;
    }

    String s = (String) wordpSymbolList.getSelectedValue();
    if (s != null) {
        wordpWord.remove(s);
        try {
            symbols.get(s).remove(wordpWord.tag);
        } catch (Exception e) {
            /* Ignore exceptions, the end result is the same for us */
        }

        updateWordpSymbolList();
        if (sympSymbol != null && sympSymbol.tag.equals(s)) {
            updateSympWordList();
        }
    }
}//GEN-LAST:event_wordpRemoveSymbolButtonActionPerformed
private void wordpAddWordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wordpAddWordButtonActionPerformed
    String s = JOptionPane.showInputDialog(this, "Input new Word:");
    if (s != null && !s.equals("")) {
        if (words.containsKey(s)) {
            return;
        }
        words.put(s, new DFWord(s));
        updateWordpWordList();
    }
}//GEN-LAST:event_wordpAddWordButtonActionPerformed
private void wordpRemoveWordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wordpRemoveWordButtonActionPerformed
    if (wordpWord == null) {
        return;
    }

    words.remove(wordpWord.tag);
    wordpWord = null;
    updateWordpWordList();
    updateWordpSymbolList();
}//GEN-LAST:event_wordpRemoveWordButtonActionPerformed
private void nounCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nounCheckBoxActionPerformed
    if (wordpWord == null) {
        return;
    }

    wordpWord.is_noun = nounCheckBox.isSelected();
}//GEN-LAST:event_nounCheckBoxActionPerformed
private void adjectiveCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adjectiveCheckBoxActionPerformed
    if (wordpWord == null) {
        return;
    }

    wordpWord.is_adj = adjectiveCheckBox.isSelected();
}//GEN-LAST:event_adjectiveCheckBoxActionPerformed
private void verbCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verbCheckBoxActionPerformed
    if (wordpWord == null) {
        return;
    }

    wordpWord.is_verb = verbCheckBox.isSelected();
}//GEN-LAST:event_verbCheckBoxActionPerformed
private void prefixCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prefixCheckBoxActionPerformed
    if (wordpWord == null) {
        return;
    }

    wordpWord.is_prefix = prefixCheckBox.isSelected();
}//GEN-LAST:event_prefixCheckBoxActionPerformed
private void wordTagTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wordTagTextFieldActionPerformed
    if(wordpWord == null) {
        return;
    }
    String oldtag = wordpWord.tag;
    
    words.remove(wordpWord.tag);
    wordpWord.tag = wordTagTextField.getText();
    words.put(wordpWord.tag, wordpWord);
    
    // Update symbols
    DFSymbol sym;
    String[] swords;
    for(String s : symbols.keySet()) {
        sym = symbols.get(s);
        sym.remove(oldtag);
        sym.add(wordpWord.tag);
    }
    
    updateSympSymbolList();
    updateWordpWordList();
}//GEN-LAST:event_wordTagTextFieldActionPerformed

private void nounPluralFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nounPluralFieldActionPerformed
    if(wordpWord == null) {
        return;
    }
    
    wordpWord.noun.plural = nounPluralField.getText();
}//GEN-LAST:event_nounPluralFieldActionPerformed
private void nounSingularTheCBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nounSingularTheCBoxActionPerformed
    if(wordpWord != null) {
        wordpWord.noun.the_singular= nounSingularTheCBox.isSelected();
    }
}//GEN-LAST:event_nounSingularTheCBoxActionPerformed
private void nounPluralTheCBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nounPluralTheCBoxActionPerformed
    if(wordpWord != null) {
        wordpWord.noun.the_plural = nounPluralTheCBox.isSelected();
    }
}//GEN-LAST:event_nounPluralTheCBoxActionPerformed
private void nounSingularOfCBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nounSingularOfCBoxActionPerformed
    if(wordpWord != null) {
        wordpWord.noun.of_singular = nounSingularOfCBox.isSelected();
    }
}//GEN-LAST:event_nounSingularOfCBoxActionPerformed
private void nounPluralOfCBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nounPluralOfCBoxActionPerformed
    if(wordpWord != null) {
        wordpWord.noun.of_plural = nounPluralOfCBox.isSelected();
    }
}//GEN-LAST:event_nounPluralOfCBoxActionPerformed
private void nounSingularCompoundCBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nounSingularCompoundCBoxActionPerformed
    if(wordpWord == null) {
        return;
    }
    wordpWord.noun.the_compound_singular = nounSingularCompoundCBox.isSelected();
}//GEN-LAST:event_nounSingularCompoundCBoxActionPerformed
private void nounPluralCompoundCBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nounPluralCompoundCBoxActionPerformed
    if(wordpWord == null) {
        return;
    }
    wordpWord.noun.the_compound_plural = nounPluralCompoundCBox.isSelected();
}//GEN-LAST:event_nounPluralCompoundCBoxActionPerformed
private void nounSingularFrontCBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nounSingularFrontCBoxActionPerformed
    if(wordpWord == null) {
        return;
    }
    wordpWord.noun.front_compound_singular = nounSingularFrontCBox.isSelected();
}//GEN-LAST:event_nounSingularFrontCBoxActionPerformed
private void nounPluralFrontCBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nounPluralFrontCBoxActionPerformed
    if(wordpWord == null) {
        return;
    }
    wordpWord.noun.front_compound_plural = nounPluralFrontCBox.isSelected();
}//GEN-LAST:event_nounPluralFrontCBoxActionPerformed
private void nounSingularRearCBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nounSingularRearCBoxActionPerformed
    if(wordpWord == null) {
        return;
    }
    wordpWord.noun.rear_compound_singular = nounSingularRearCBox.isSelected();
}//GEN-LAST:event_nounSingularRearCBoxActionPerformed
private void nounPluralRearCBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nounPluralRearCBoxActionPerformed
    if(wordpWord == null) {
        return;
    }
    wordpWord.noun.rear_compound_plural = nounPluralRearCBox.isSelected();
}//GEN-LAST:event_nounPluralRearCBoxActionPerformed

private void adjWordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adjWordFieldActionPerformed
    if(wordpWord != null) {
        wordpWord.adj.adj = adjWordField.getText();
    }
}//GEN-LAST:event_adjWordFieldActionPerformed
private void adjDistanceFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adjDistanceFieldActionPerformed
    if(wordpWord != null) {
        try {
            wordpWord.adj.adjective_distance = Integer.parseInt(adjDistanceField.getText());
        }catch(Exception e) {
            
        }
    }
}//GEN-LAST:event_adjDistanceFieldActionPerformed
private void adjCompoundCBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adjCompoundCBoxActionPerformed
    if(wordpWord != null) {
        wordpWord.adj.the_adj = adjCompoundCBox.isSelected();
    }
}//GEN-LAST:event_adjCompoundCBoxActionPerformed
private void adjFrontCBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adjFrontCBoxActionPerformed
    if(wordpWord != null) {
        wordpWord.adj.front_adj = adjFrontCBox.isSelected();
    }
}//GEN-LAST:event_adjFrontCBoxActionPerformed
private void adjRearCBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adjRearCBoxActionPerformed
    if(wordpWord != null) {
        wordpWord.adj.rear_adj = adjRearCBox.isSelected();
    }
}//GEN-LAST:event_adjRearCBoxActionPerformed

private void verbInfinitiveFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verbInfinitiveFieldActionPerformed
    if(wordpWord != null) {
        wordpWord.verb.infinitive = verbInfinitiveField.getText();
    }
}//GEN-LAST:event_verbInfinitiveFieldActionPerformed
private void verbPresentFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verbPresentFieldActionPerformed
    if(wordpWord != null) {
        wordpWord.verb.present = verbPresentField.getText();
    }
}//GEN-LAST:event_verbPresentFieldActionPerformed
private void verbPastFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verbPastFieldActionPerformed
    if(wordpWord != null) {
        wordpWord.verb.past = verbPastField.getText();
    }
}//GEN-LAST:event_verbPastFieldActionPerformed
private void verbPastPartFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verbPastPartFieldActionPerformed
    if(wordpWord != null) {
        wordpWord.verb.pastpart = verbPastPartField.getText();
    }
}//GEN-LAST:event_verbPastPartFieldActionPerformed
private void verbPresentPartFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verbPresentPartFieldActionPerformed
    if(wordpWord != null) {
        wordpWord.verb.presentpart = verbPresentPartField.getText();
    }
}//GEN-LAST:event_verbPresentPartFieldActionPerformed
private void verbStandardCBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verbStandardCBoxActionPerformed
    if(wordpWord != null) {
        wordpWord.verb.standard_verb = verbStandardCBox.isSelected();
    }
}//GEN-LAST:event_verbStandardCBoxActionPerformed

private void prefixTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prefixTextFieldActionPerformed
    if(wordpWord != null) {
        wordpWord.prefix.pfx = prefixTextField.getText();
    }
}//GEN-LAST:event_prefixTextFieldActionPerformed
private void prefixCompoundCBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prefixCompoundCBoxActionPerformed
    if(wordpWord != null) {
        wordpWord.prefix.the_prefix = prefixCompoundCBox.isSelected();
    }
}//GEN-LAST:event_prefixCompoundCBoxActionPerformed
private void prefixFrontCBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prefixFrontCBoxActionPerformed
    if(wordpWord != null) {
        wordpWord.prefix.front_prefix = prefixFrontCBox.isSelected();
    }
}//GEN-LAST:event_prefixFrontCBoxActionPerformed

private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
    if(!dir.isDirectory()) {
        dir.mkdirs();
    }
    
    if(symFile == null) {
        symFile = new File(dir, symfname);
    }
    if(wordFile == null) {
        wordFile = new File(dir, wordsfname);
    }
    
    if(symFile.isFile()) {
        try {
            if(!symFile.renameTo(new File(dir, symback))) {
                String msg = "Could not back up symbol file\nContinue?";
                int i = JOptionPane.showConfirmDialog(this, msg, "Error",
                                                      YES_NO_OPTION);
                if(i == JOptionPane.NO_OPTION) {
                    return;
                }
            }
        }catch(Exception e) {
            String msg = "Error occured while backing up symbol file\nContinue?";
            int i = JOptionPane.showConfirmDialog(this, msg, "Error",
                                                  YES_NO_OPTION);
            if(i == JOptionPane.NO_OPTION) {
                return;
            }
        }
    }
    if(wordFile.isFile()) {
        try {
            if(!wordFile.renameTo(new File(dir, wordsback))) {
                String msg = "Could not back up words file\nContinue?";
                int i = JOptionPane.showConfirmDialog(this, msg, "Error",
                                                      YES_NO_OPTION);
                if(i == JOptionPane.NO_OPTION) {
                    return;
                }
            }
        }catch(Exception e) {
            String msg = "Error occured while backing up words file\nContinue?";
            int i = JOptionPane.showConfirmDialog(this, msg, "Error",
                                                  YES_NO_OPTION);
            if(i == JOptionPane.NO_OPTION) {
                return;
            }
        }
    }
    
    wordFile = new File(dir, wordsfname);
    symFile = new File(dir, symfname);
    
    writeFiles();
}//GEN-LAST:event_jMenuItem3ActionPerformed
private void newMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newMenuItemActionPerformed
    words.clear();
    symbols.clear();
    
    wordpWord = null;
    sympSymbol = null;
    
    updateWordpWordList();
    updateWordpSymbolList();
    updateSympSymbolList();
    updateSympWordList();
}//GEN-LAST:event_newMenuItemActionPerformed

private void sympSymbolListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_sympSymbolListValueChanged
    String s = (String)sympSymbolList.getSelectedValue();
    if(s == null) {
        sympSymbol = null;
    }else {
        try {
            sympSymbol = symbols.get(s);
        }catch(Exception e) {
            
        }
    }
    
    updateSympWordList();
}//GEN-LAST:event_sympSymbolListValueChanged
private void sympAddSymbolButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sympAddSymbolButtonActionPerformed
    String s = JOptionPane.showInputDialog(this, "Input new Symbol:");
    if (s != null && !s.equals("")) {
        if (symbols.containsKey(s)) {
            return;
        }
        symbols.put(s, new DFSymbol(s));
        updateSympSymbolList();
    }
}//GEN-LAST:event_sympAddSymbolButtonActionPerformed
private void sympRemoveSymbolButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sympRemoveSymbolButtonActionPerformed
    if (sympSymbol == null) {
        return;
    }
    
    symbols.remove(sympSymbol.tag);
    sympSymbol = null;
    
    updateSympSymbolList();
    updateSympWordList();
}//GEN-LAST:event_sympRemoveSymbolButtonActionPerformed
private void sympAddWordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sympAddWordButtonActionPerformed
    if(sympSymbol == null) {
        return;
    }
    
    String s = JOptionPane.showInputDialog(this, "New Word:");
    if(s != null && !s.equals("")) {
        if(words.containsKey(s)) {
            sympSymbol.add(s);
            try{
                words.get(s).add(sympSymbol.tag);
            }catch(Exception e) {
                
            }
            
            if(wordpWord != null && wordpWord.tag.equals(s)) {
                updateWordpWordList();
            }
        }else {
            JOptionPane.showMessageDialog(this, "Non-Existant word");
        }
    }
    
    updateSympWordList();
}//GEN-LAST:event_sympAddWordButtonActionPerformed

private void sympRemoveWordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sympRemoveWordButtonActionPerformed
    if (sympSymbol == null) {
        return;
    }

    String s = (String) sympWordList.getSelectedValue();
    if (s != null) {
        sympSymbol.remove(s);
        try {
            words.get(s).remove(sympSymbol.tag);
        } catch (Exception e) {
            /* Ignore exceptions, the end result is the same for us */
        }

        updateSympWordList();
        if(wordpWord != null && wordpWord.tag.equals(s)) {
            updateWordpSymbolList();
        }
    }
}//GEN-LAST:event_sympRemoveWordButtonActionPerformed
// </editor-fold>
    public void updateWordpWordList() {
        Object[] warr = {};
        if (words != null) {
            Object[] blah = words.keySet().toArray();
            if (blah != null) {
                warr = blah;
                Arrays.sort(warr); //< Modded Mergesort, O(n*log(n))
            }
        }

        wordpWordList.setListData(warr);
    }
    public void updateWordpSymbolList() {
        Object[] syms = {};
        if (wordpWord != null) {
            Object[] blah = wordpWord.getSymbolList();
            if (blah != null) {
                syms = blah;
                Arrays.sort(syms);
            }
        }

        wordpSymbolList.setListData(syms);
    }
    public void updateSympSymbolList() {
        //sympSymbolList, sympWordList
        Object[] syms = {};
        if(symbols != null) {
            Object[] blah = symbols.keySet().toArray();
            if(blah != null) {
                syms = blah;
                Arrays.sort(syms);
            }
        }
        
        sympSymbolList.setListData(syms);
    }
    public void updateSympWordList() {
        Object[] warr = {};
        if(sympSymbol != null) {
            Object[] blah = sympSymbol.wordArray();
            if(blah != null) {
                warr = blah;
                Arrays.sort(warr);
            }
        }
        
        sympWordList.setListData(warr);
    }
    public void readSymbols(File file)
            throws FileNotFoundException, IOException {
        DFObjectParser parser = new DFObjectParser(file);
        DFObject obj;

        while ((obj = parser.next()) != null) {
            if (obj.objectTag().equals(DFTag.SYMBOL)) {
                DFSymbol sym = (DFSymbol) obj;
                symbols.put(sym.tag, sym);
            } else if (obj.objectTag().equals(DFTag.WORD)) {
                DFWord word = (DFWord) obj;
                words.put(word.tag, word);
            } else {
                /* Err... */
            }
        }

        parser.close();
    }
    public void readWords(File file)
            throws FileNotFoundException, IOException {
        DFObjectParser parser = new DFObjectParser(file);
        DFObject obj;

        while ((obj = parser.next()) != null) {
            if (obj.objectTag().equals(DFTag.WORD)) {
                DFWord word = (DFWord) obj;
                words.put(word.tag, word);
            } else if (obj.objectTag().equals(DFTag.SYMBOL)) {
                DFSymbol sym = (DFSymbol) obj;
                symbols.put(sym.tag, sym);
            } else {
                /* Err... */
            }
        }

        parser.close();
    }
    public void writeFiles() {
        BufferedWriter writer;
        boolean failed = false;
        try {
            writer = new BufferedWriter(new FileWriter(symFile));
            writer.write(symfname);
            writer.write("\n\n");
            writer.write(DFTag.format(DFTag.OBJ, DFTag.LANG_OBJ));
            writer.write("\n\n");
            
            Object[] values = symbols.values().toArray();
            Arrays.sort(values);
            for(Object sym : values) {
                writer.write(((DFSymbol)sym).buildTagString());
                writer.write('\n');
            }
            
            writer.close();
        }catch(Exception e) {
            failed = true;
        }
        
        try {
            writer = new BufferedWriter(new FileWriter(wordFile));
            writer.write(wordsfname);
            writer.write("\n\n");
            writer.write(DFTag.format(DFTag.OBJ, DFTag.LANG_OBJ));
            writer.write("\n\n");
            
            Object[] values = words.values().toArray();
            Arrays.sort(values);
            for(Object word : values) {
                writer.write(((DFWord)word).buildTagString());
                writer.write('\n');
            }
            
            writer.close();
        }catch(Exception e) {
            failed = true;
        }
        
        if(failed) {
            JOptionPane.showMessageDialog(this, "Could not save files.");
        }
    }
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
            java.util.logging.Logger.getLogger(DFDictionary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DFDictionary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DFDictionary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DFDictionary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override //< lol, netbeans didn't add this automagically
            public void run() {
                DFDictionary dict = new DFDictionary();
                dict.setSize(600, 400);
                dict.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox adjCompoundCBox;
    private javax.swing.JTextField adjDistanceField;
    private javax.swing.JCheckBox adjFrontCBox;
    private javax.swing.JCheckBox adjRearCBox;
    private javax.swing.JTextField adjWordField;
    private javax.swing.JCheckBox adjectiveCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JMenuItem loadMenuItem;
    private javax.swing.JMenuItem newMenuItem;
    private javax.swing.JCheckBox nounCheckBox;
    private javax.swing.JCheckBox nounPluralCompoundCBox;
    private javax.swing.JTextField nounPluralField;
    private javax.swing.JCheckBox nounPluralFrontCBox;
    private javax.swing.JCheckBox nounPluralOfCBox;
    private javax.swing.JCheckBox nounPluralRearCBox;
    private javax.swing.JCheckBox nounPluralTheCBox;
    private javax.swing.JCheckBox nounSingularCompoundCBox;
    private javax.swing.JTextField nounSingularField;
    private javax.swing.JCheckBox nounSingularFrontCBox;
    private javax.swing.JCheckBox nounSingularOfCBox;
    private javax.swing.JCheckBox nounSingularRearCBox;
    private javax.swing.JCheckBox nounSingularTheCBox;
    private javax.swing.JCheckBox prefixCheckBox;
    private javax.swing.JCheckBox prefixCompoundCBox;
    private javax.swing.JCheckBox prefixFrontCBox;
    private javax.swing.JTextField prefixTextField;
    private javax.swing.JMenuItem redoMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenu saveMenuItem;
    private javax.swing.JButton sympAddSymbolButton;
    private javax.swing.JButton sympAddWordButton;
    private javax.swing.JButton sympRemoveSymbolButton;
    private javax.swing.JButton sympRemoveWordButton;
    private javax.swing.JList sympSymbolList;
    private javax.swing.JList sympWordList;
    private javax.swing.JMenu undoMenuItem;
    private javax.swing.JCheckBox verbCheckBox;
    private javax.swing.JTextField verbInfinitiveField;
    private javax.swing.JTextField verbPastField;
    private javax.swing.JTextField verbPastPartField;
    private javax.swing.JTextField verbPresentField;
    private javax.swing.JTextField verbPresentPartField;
    private javax.swing.JCheckBox verbStandardCBox;
    private javax.swing.JTextField wordTagTextField;
    private javax.swing.JButton wordpAddSymbolButton;
    private javax.swing.JButton wordpAddWordButton;
    private javax.swing.JButton wordpRemoveSymbolButton;
    private javax.swing.JButton wordpRemoveWordButton;
    private javax.swing.JList wordpSymbolList;
    private javax.swing.JList wordpWordList;
    // End of variables declaration//GEN-END:variables
}
