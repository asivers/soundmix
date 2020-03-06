import javax.sound.sampled.AudioFormat;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class MainWindow extends JFrame {

    public File openedFile;
    public byte[] openedFileByteArray;
    public boolean openedFilePlaying;
    public File compressedFile;
    public byte[] compressedFileByteArray;
    public boolean compressedFilePlaying;
    public AudioFormat audioFormat;
    public boolean compressed;

    public MainWindow() {

        setBounds(500, 300, 450, 165);
        setResizable(false);
        setTitle("Audio Data Buffer");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Font notBold = new Font("Tahoma", Font.PLAIN, 12);

        JMenuBar menuBar = new JMenuBar();
        JMenu optMenu = new JMenu("Настройка");
        optMenu.setFont(notBold);
        JMenu colorMenu = new JMenu("AAA");
        colorMenu.setFont(notBold);
        optMenu.add(colorMenu);
        JMenuItem redItem = new JMenuItem("aaa1");
        redItem.setFont(notBold);
        colorMenu.add(redItem);
        JMenuItem greenItem = new JMenuItem("aaa2");
        greenItem.setFont(notBold);
        colorMenu.add(greenItem);
        JMenuItem blueItem = new JMenuItem("aaa3");
        blueItem.setFont(notBold);
        colorMenu.add(blueItem);
        JMenuItem greyItem = new JMenuItem("aaa4");
        greyItem.setFont(notBold);
        colorMenu.add(greyItem);
        JMenu limitMenu = new JMenu("BBB");
        limitMenu.setFont(notBold);
        optMenu.add(limitMenu);
        JMenuItem _1Item = new JMenuItem("bbb1");
        _1Item.setFont(notBold);
        limitMenu.add(_1Item);
        JMenuItem _2Item = new JMenuItem("bbb2");
        _2Item.setFont(notBold);
        limitMenu.add(_2Item);
        JMenuItem _3Item = new JMenuItem("bbb3");
        _3Item.setFont(notBold);
        limitMenu.add(_3Item);
        JMenuItem _4Item = new JMenuItem("bbb4");
        _4Item.setFont(notBold);
        limitMenu.add(_4Item);
        JMenuItem _5Item = new JMenuItem("bbb5");
        _5Item.setFont(notBold);
        limitMenu.add(_5Item);
        JMenuItem _6Item = new JMenuItem("bbb6");
        _6Item.setFont(notBold);
        limitMenu.add(_6Item);
        JMenuItem _7Item = new JMenuItem("bbb7");
        _7Item.setFont(notBold);
        limitMenu.add(_7Item);
        JMenuItem _8Item = new JMenuItem("bbb8");
        _8Item.setFont(notBold);
        limitMenu.add(_8Item);
        JMenuItem _9Item = new JMenuItem("bbb9");
        _9Item.setFont(notBold);
        limitMenu.add(_9Item);
        JMenu helpMenu = new JMenu("Справка");
        helpMenu.setFont(notBold);
        JMenuItem progMenu = new JMenuItem("О программе");
        progMenu.setFont(notBold);
        helpMenu.add(progMenu);
        menuBar.add(optMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Colors.n1Color);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel n1Panel = new JPanel();
        n1Panel.setBackground(Colors.n1Color);
        n1Panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton openFileButton = new JButton("Открыть");
        setButtonStyle(openFileButton);
        n1Panel.add(openFileButton);
        JLabel openedFileLabel = new JLabel("");
        openedFileLabel.setPreferredSize(new Dimension(176,25));
        openedFileLabel.setFont(notBold);
        n1Panel.add(openedFileLabel);
        JButton playOpenedFileButton = new JButton("Воспроизвести");
        setButtonStyle(playOpenedFileButton);
        openedFilePlaying = false;
        playOpenedFileButton.setEnabled(false);
        n1Panel.add(playOpenedFileButton);
        mainPanel.add(n1Panel);

        JPanel n2Panel = new JPanel();
        n2Panel.setBackground(Colors.n2Color);
        n2Panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel compressorLabel = new JLabel("Сжать по уровню, %");
        compressorLabel.setFont(notBold);
        compressorLabel.setBorder(BorderFactory.createMatteBorder(0,2,0,30, Colors.n2Color));
        n2Panel.add(compressorLabel);
        JTextField compressorInput = new JTextField("10");
        compressorInput.setPreferredSize(new Dimension(40,20));
        compressorInput.setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
        n2Panel.add(compressorInput);
        JLabel emptyLabel0 = new JLabel("");
        emptyLabel0.setBorder(BorderFactory.createEmptyBorder(0,0,0,104));
        n2Panel.add(emptyLabel0);
        JButton compressorButton = new JButton("Компрессор");
        setButtonStyle(compressorButton);
        compressed = false;
        compressorButton.setEnabled(false);
        n2Panel.add(compressorButton);
        mainPanel.add(n2Panel);

        JPanel n3Panel = new JPanel();
        n3Panel.setBackground(Colors.n1Color);
        n3Panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel compressedFileLabel = new JLabel("");
        compressedFileLabel.setPreferredSize(new Dimension(139,25));
        compressedFileLabel.setFont(notBold);
        compressedFileLabel.setBorder(BorderFactory.createMatteBorder(0,2,0,70, Colors.n1Color));
        n3Panel.add(compressedFileLabel);
        JButton playCompressedFileButton = new JButton("Воспроизвести");
        setButtonStyle(playCompressedFileButton);
        compressedFilePlaying = false;
        playCompressedFileButton.setEnabled(false);
        n3Panel.add(playCompressedFileButton);
        JLabel emptyLabel1 = new JLabel("");
        emptyLabel1.setBorder(BorderFactory.createEmptyBorder(0,0,0,32));
        n3Panel.add(emptyLabel1);
        JButton saveFileButton = new JButton("Сохранить");
        setButtonStyle(saveFileButton);
        saveFileButton.setEnabled(false);
        n3Panel.add(saveFileButton);
        mainPanel.add(n3Panel);

        add(mainPanel);

        setVisible(true);

        openFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser fileChooser = new JFileChooser();
                    if (fileChooser.showOpenDialog(openFileButton) == JFileChooser.APPROVE_OPTION) {
                        openedFile = fileChooser.getSelectedFile();
                    }
                    openedFileLabel.setText(openedFile.getName());
                    openedFileByteArray = AudioDataBuffer.fileToByteArray(openedFile);
                    audioFormat = AudioDataBuffer.audioFormat;
                    playOpenedFileButton.setEnabled(true);
                    compressorButton.setEnabled(true);
                    playCompressedFileButton.setEnabled(false);
                    saveFileButton.setEnabled(false);
                    compressed = false;
                }
                catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        playOpenedFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!openedFilePlaying) {
                    PlayAudio.play(openedFileByteArray, audioFormat);
                    openedFilePlaying = true;
                    playOpenedFileButton.setText("Остановить");
                    openFileButton.setEnabled(false);
                    compressorButton.setEnabled(false);
                    playCompressedFileButton.setEnabled(false);
                    saveFileButton.setEnabled(false);
                }
                else {
                    PlayAudio.stop();
                    openedFilePlaying = false;
                    playOpenedFileButton.setText("Воспроизвести");
                    openFileButton.setEnabled(true);
                    compressorButton.setEnabled(true);
                    if (compressed) {
                        playCompressedFileButton.setEnabled(true);
                        saveFileButton.setEnabled(true);
                    }
                }
            }
        });

        compressorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int compressorLvl;
                    try {
                        compressorLvl = Math.abs(Integer.parseInt(compressorInput.getText()));
                        if ((compressorLvl == 0) || (compressorLvl > 99))
                            compressorLvl = 99;
                    }
                    catch (Exception e2) {
                        compressorLvl = 99;
                    }
                    compressedFileByteArray = Compressor.compress(openedFileByteArray, compressorLvl);
                    compressedFileLabel.setText("compressed!");
                    compressed = true;
                    playCompressedFileButton.setEnabled(true);
                    saveFileButton.setEnabled(true);
                }
                catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        playCompressedFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!compressedFilePlaying) {
                    PlayAudio.play(compressedFileByteArray, audioFormat);
                    compressedFilePlaying = true;
                    playCompressedFileButton.setText("Остановить");
                    openFileButton.setEnabled(false);
                    playOpenedFileButton.setEnabled(false);
                    compressorButton.setEnabled(false);
                    saveFileButton.setEnabled(false);
                }
                else {
                    PlayAudio.stop();
                    compressedFilePlaying = false;
                    playCompressedFileButton.setText("Воспроизвести");
                    openFileButton.setEnabled(true);
                    playOpenedFileButton.setEnabled(true);
                    compressorButton.setEnabled(true);
                    saveFileButton.setEnabled(true);
                }
            }
        });

        saveFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser fileChooser = new JFileChooser();
                    if (fileChooser.showSaveDialog(saveFileButton) == JFileChooser.APPROVE_OPTION) {
                        File savedFile = fileChooser.getSelectedFile();
                        AudioDataBuffer.byteArrayToFile(compressedFileByteArray, savedFile.getPath());
                    }
                }
                catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

    }

    public void setButtonStyle(JButton button) {
        button.setPreferredSize(new Dimension(120,25));
        button.setFont(new Font("Tahoma", Font.PLAIN, 12));
        button.setFocusable(false);
    }

}
