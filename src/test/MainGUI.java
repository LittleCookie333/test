package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;

public class MainGUI extends JFrame {
    private JTextField filePathField;
    private JComboBox<String> architectureComboBox;
    private JTextArea resultArea;
    private JLabel imageLabel;

    public MainGUI() {
        setTitle("经典软件体系结构教学软件");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 创建面板和布局
        JPanel mainPanel = new JPanel(new BorderLayout());

        // 上部面板：文件路径和体系结构选择
        JPanel topPanel = new JPanel(new GridLayout(2, 2, 10, 10)); // 增加间距
        JLabel filePathLabel = new JLabel("请输入要处理的文件的地址：");
        filePathField = new JTextField(30);
        topPanel.add(filePathLabel);
        topPanel.add(filePathField);

        JLabel architectureLabel = new JLabel("请选择一种软件体系结构风格：");
        String[] architectures = {"主程序-子程序", "面向对象", "事件系统", "管道-过滤器"};
        architectureComboBox = new JComboBox<>(architectures);
        topPanel.add(architectureLabel);
        topPanel.add(architectureComboBox);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // 中部面板：结果显示区域
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        mainPanel.add(scrollPane, BorderLayout.WEST);
        imageLabel = new JLabel(); // 用于显示图片
        mainPanel.add(imageLabel, BorderLayout.EAST);

        // 下部面板：图片显示区域和按钮
        JPanel bottomPanel = new JPanel(new BorderLayout());

        JButton submitButton = new JButton("提交");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processSelection();
            }
        });
        bottomPanel.add(submitButton, BorderLayout.EAST);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void processSelection() {
        String filePath = filePathField.getText();
        String selectedArchitecture = (String) architectureComboBox.getSelectedItem();

        resultArea.setText("");
        String result="";

        try {
            switch (selectedArchitecture) {
                case "主程序-子程序":
                    result = MainSubroutineStyle.processFile(filePath);
                    resultArea.append("文件处理完成！结果为:\n" + result);
                    resultArea.append("\n");
                    showArchitectureImage("img1.png");
                    resultArea.append("\n");
                    showPrinciple("1");
                    break;
                case "面向对象":
                    result = ObjectOrientedStyle.processFile(filePath);
                    resultArea.append("文件处理完成！结果为:\n" + result);
                    resultArea.append("\n");
                    showArchitectureImage("img2.png");
                    resultArea.append("\n");
                    showPrinciple("2");
                    break;
                case "事件系统":
                    result = EventDrivenStyle.processFile(filePath);
                    resultArea.append("文件处理完成！结果为:\n" + result);
                    resultArea.append("\n");
                    showArchitectureImage("img3.png");
                    resultArea.append("\n");
                    showPrinciple("3");
                    break;
                case "管道-过滤器":
                    result = PipeFilterStyle.processFile(filePath);
                    resultArea.append("文件处理完成！结果为:\n" + result);
                    resultArea.append("\n");
                    showArchitectureImage("img4.png");
                    resultArea.append("\n");
                    showPrinciple("4");
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "无效的选择。", "错误", JOptionPane.ERROR_MESSAGE);
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "文件处理错误：" + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showArchitectureImage(String imageName) {
        URL imageURL = getClass().getResource("/test/imgs/" + imageName);
        if (imageURL != null) {
            try {
                BufferedImage image = ImageIO.read(imageURL);
                Image scaledImage = image.getScaledInstance(450, 550, Image.SCALE_SMOOTH);
                ImageIcon imageIcon = new ImageIcon(scaledImage);
                imageLabel.setIcon(imageIcon);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "无法加载图片：" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "图片资源未找到。", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showPrinciple(String order) {
        switch (order){
            case "1":
                resultArea.append("主程序-子程序软件体系结构风格的基本原理：\n" +
                        "主程序作为控制中心，负责程序的整体流程控制，包括初始化、数据准备、调用子程序、处理子程序返回结果以及最终的清理工作。\n" +
                        "子程序是完成特定功能的独立代码块，每个子程序通常聚焦于解决一个具体的编程问题或执行一项明确的任务。\n" +
                        "主程序通过调用相应的子程序来完成具体任务，扮演着协调和调度的角色，从而实现代码的模块化、分工合作以及复用。\n");
                resultArea.append("\n");
                resultArea.append("关键函数的程序代码为：\n"+
                        "public static String result = \"\";\n" +
                        "    private ArrayList<String> kwicList = new ArrayList<String>();\n" +
                        "    private ArrayList<String> lineTxt = new ArrayList<String>();\n" +
                        "    private BufferedReader inputFile;\n" +
                        "    public static String processFile(String url) {\n" +
                        "        test.MainSubroutineStyle kwic = new test.MainSubroutineStyle();\n" +
                        "        kwic.input(url);\n" +
                        "        kwic.shift();\n" +
                        "        kwic.alphabetizer();\n" +
                        "        kwic.output();\n" +
                        "        return result;\n" +
                        "    }\n\n");
                break;
            case "2":
                resultArea.append("面向对象软件体系结构风格的基本原理：\n" +
                        "将现实世界的实体抽象为对象，每个对象包含数据（属性）和行为（方法）。\n" +
                        "遵循面向对象的四大基本原则：封装、继承、多态和抽象。\n" +
                        "通过类（对象的模板或蓝图）来定义对象的共同属性和行为，子类可以继承父类的属性和方法，并添加或覆盖特定的行为。\n" +
                        "使用多态允许使用相同的接口（方法签名）来表示或处理不同类的对象，具体行为取决于对象的实际类型。\n");
                resultArea.append("\n");
                resultArea.append("关键函数的程序代码为：\n"+
                        "public static String result = \"\";\n" +
                        "    public static String processFile(String url) {\n" +
                        "        Input input = new Input();\n" +
                        "        input.input(url);\n" +
                        "        Shift shift = new Shift(input.getLineTxt());\n" +
                        "        shift.shift();\n" +
                        "        Alphabetizer alphabetizer = new Alphabetizer(shift.getKwicList());\n" +
                        "        alphabetizer.sort();\n" +
                        "        Output output = new Output(alphabetizer.getKwicList());\n" +
                        "        output.output();\n" +
                        "        return result;\n" +
                        "    }\n\n");
                break;
            case "3":
                resultArea.append("事件系统软件体系结构风格的基本原理：\n" +
                        "基于事件的隐式调用风格，构件不直接调用一个过程，而是触发或广播一个或多个事件。\n" +
                        "事件系统包括事件触发者、事件管理者（或称为事件分发器）以及事件处理者。\n" +
                        "当事件发生时，事件管理者负责将事件分发到已注册的事件处理者，事件处理者根据事件类型执行相应的操作。\n");
                resultArea.append("\n");
                resultArea.append("关键函数的程序代码为：\n"+
                        "        //创建主题\n" +
                        "        KWICSubject kwicSubject = new KWICSubject();\n" +
                        "        //创建观察者\n" +
                        "        Input input = new Input(url);\n" +
                        "        Shift shift = new Shift(input.getLineTxt());\n" +
                        "        Alphabetizer alphabetizer = new Alphabetizer(shift.getKwicList());\n" +
                        "        Output output = new Output(alphabetizer.getKwicList());\n" +
                        "        // 将观察者加入主题\n" +
                        "        kwicSubject.addObserver(input);\n" +
                        "        kwicSubject.addObserver(shift);\n" +
                        "        kwicSubject.addObserver(alphabetizer);\n" +
                        "        kwicSubject.addObserver(output);\n" +
                        "        // 逐步调用各个观察者\n" +
                        "        kwicSubject.startKWIC();\n\n");
                break;
            case "4":
                resultArea.append("管道-过滤器软件体系结构风格的基本原理：\n" +
                        "将数据处理流程划分为一系列相互独立且可重用的组件（即过滤器）。\n" +
                        "通过管道（逻辑上的数据通道）将这些组件连接起来，形成一个处理链。\n" +
                        "数据从输入源开始，经过一系列连续的过滤器，每个过滤器执行特定的数据处理任务，最终生成所需的结果。\n" +
                        "过滤器之间仅通过标准化的接口（通常是数据流接口）交互，降低了组件间的依赖性。\n");
                resultArea.append("\n");
                resultArea.append("关键函数的程序代码为：\n"+
                        "public static String result = \"\";\n" +
                        "    public static String processFile(String url) throws IOException {\n" +
                        "        File inFile = new File(url);\n" +
                        "        Pipe pipe1 = new Pipe();\n" +
                        "        Pipe pipe2 = new Pipe();\n" +
                        "        Pipe pipe3 = new Pipe();\n" +
                        "        Input input = new Input(inFile, pipe1);\n" +
                        "        Shift shift = new Shift(pipe1, pipe2);\n" +
                        "        Alphabetizer alphabetizer  = new Alphabetizer(pipe2, pipe3);\n" +
                        "        Output output = new Output(pipe3);\n" +
                        "        input.transform();\n" +
                        "        shift.transform();\n" +
                        "        alphabetizer.transform();\n" +
                        "        output.transform();\n" +
                        "        return result;\n" +
                        "    }\n\n");
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainGUI().setVisible(true);
            }
        });
    }
}