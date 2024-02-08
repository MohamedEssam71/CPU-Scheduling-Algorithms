import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GUI extends  JFrame{
    public Vector<Process> processes = new Vector<>();
    private LinkedHashMap<Integer,Color> processesColor = new LinkedHashMap<>();

    public GUI(Vector<Process> processes){
        this.processes = processes;
        JFrame jFrame = new JFrame();
        jFrame.setSize(960,600); // 960 750
        jFrame.setLayout(null);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.setTitle("CPU Scheduling Graph");
        int xPanel = 0, yPanel = 0;

        for(Process p : processes){
            // generate random color for each process
            Color color = ColorHandler.convertColor(p.color);
            processesColor.put(p.executionOrder, color); // save color of new process

            JPanel jPanel1 = new JPanel(new BorderLayout()){

                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    // draw horizontal line for each process
                    g.drawLine(65,25,600,25);
                    g.setColor(color);
                    float[] dashingPattern2 = {9f, 5f};
                    // make it dashed and control thickness
                    Stroke stroke2 = new BasicStroke(1, BasicStroke.CAP_BUTT,
                            BasicStroke.JOIN_MITER, 1.0f,
                            dashingPattern2, 0.0f);
                    Graphics2D graphics2D = (Graphics2D)g;
                    graphics2D.setStroke(stroke2);

                    // loop over each process take its start and end from vector and prints it
                    int factor = 12;
                    for(int i = 1; i < p.startEndTime.size(); i+=2){
                        int start = p.startEndTime.get(i-1)*factor;
                        int end = p.startEndTime.get(i)*factor;
                        int width = end - start;
                        //rectangle of each process.
                        g.fillRect(100+start,17,width,15); // should be (end - start)
                        // start string and ending string on each rectangle.
                        g.setColor(Color.black);
                        String startStr = Integer.toString(start/factor);
                        String endStr = Integer.toString(end/factor);
                        graphics2D.drawString(startStr, 100+start, 55);
                        graphics2D.drawString(endStr, 100+start+width, 55);
                        g.setColor(color);

                        // start and ending vertical lines
                        //end line  last parameter is the height of the line.
                        g.drawLine(100+start+width,0,100+start+ width,60);
                        //start line last parameter is the height of the line.
                        g.drawLine(100+start,0,100+start,60);
                    }
                }
            };
            // repeat each process panel according to number of process.
            jPanel1.setBackground(getBackground());
            jPanel1.setBounds(xPanel,yPanel,600,55);
            yPanel+=100;
            // label for each process
            JLabel jLabel1 = new JLabel(p.name);
            jPanel1.add(jLabel1,BorderLayout.WEST);
            jFrame.add(jPanel1);
        }
        processesColor.put(-1,Color.RED); // used as dummy process to handle some errors
        // make a new panel for process descriptions
        JPanel jPanelFrameProcessDetails = new JPanel(new BorderLayout());
        jPanelFrameProcessDetails.setBounds(600,0,350,400);
        jPanelFrameProcessDetails.setBackground(getBackground());
        // title label
        JLabel title = new JLabel("Process Execution Order");
        title.setHorizontalAlignment(JLabel.CENTER);
        Font font = title.getFont();
        title.setFont(new Font(font.getName(), Font.PLAIN, 20));
        // loop on each process with its color and prints it.
        yPanel = 65;
        for(HashMap.Entry<Integer, Color> set : processesColor.entrySet()){
            if(set.getKey()!=-1) {
                JLabel processLabel = new JLabel(generateHtmlContent(set.getValue(), "Execution ID: "
                        + set.getKey()));
                Font fontProcess = processLabel.getFont();
                processLabel.setFont(new Font(fontProcess.getName(), Font.PLAIN, 15));
                processLabel.setBounds(130, yPanel, 200, 40);
                yPanel += 60;
                jPanelFrameProcessDetails.add(processLabel);
            }
            else{
                JLabel processLabel = new JLabel();
                jPanelFrameProcessDetails.add(processLabel);
            }
        }

        jPanelFrameProcessDetails.add(title,BorderLayout.NORTH);

        // add the process description label in the east of all frame.
        jFrame.add(jPanelFrameProcessDetails,BorderLayout.EAST);
    }

    private String generateHtmlContent(Color color, String text) {
        // Ensure valid color values
        int red = Math.min(255, Math.max(0, color.getRed()));
        int green = Math.min(255, Math.max(0, color.getGreen()));
        int blue = Math.min(255, Math.max(0, color.getBlue()));

        String hexColor = String.format("#%02x%02x%02x", red, green, blue);
        return String.format("<html><div style='background-color: %s; width: 50px; height: 20px;'></div> %s</html>", hexColor, text);
    }
}



