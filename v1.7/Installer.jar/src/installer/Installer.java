package installer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Installer {
    public static void main(String[] args) {
        // Create a new frame
        JFrame frmFileDownloaderAnd = new JFrame("File Downloader and Extractor");
        frmFileDownloaderAnd.setResizable(false);
        frmFileDownloaderAnd.setTitle("File Downloader and Extractor for v1.7");
        frmFileDownloaderAnd.setIconImage(Toolkit.getDefaultToolkit().getImage(Installer.class.getResource("/installer/icon/downloadicon.png")));

        // Set the size of the frame
        frmFileDownloaderAnd.setSize(470, 207);
        frmFileDownloaderAnd.getContentPane().setLayout(new BorderLayout());

        // Specify what happens when the frame is closed
        frmFileDownloaderAnd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create buttons
        JButton downloadButton = new JButton("Download File and Extract File");

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(downloadButton);

        // Add button panel and progress bars to the frame
        frmFileDownloaderAnd.getContentPane().add(buttonPanel, BorderLayout.NORTH);
        
        JButton btnStartTheApplicaton = new JButton("Start The Applicaton");
        btnStartTheApplicaton.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) { try { 
        			// Specify the directory and JAR file name 
        			String jarFilePath = "extracted_files-from-v1.7.JavaGameStuff/JavaGameStuff-1.7/Launcher.jar"; 
        			// Change this to your specific folder and JAR file
        			// Open the JAR file 
        			File jarFile = new File(jarFilePath); 
        			if (jarFile.exists()) { 
        				Runtime.getRuntime().exec("java -jar " + jarFilePath);
        				} 
        			else { 
        				System.out.println("JAR file not found in the specified directory.");
        				} } 
        		catch (IOException ex) { ex.printStackTrace(); } }
        		
        });
        buttonPanel.add(btnStartTheApplicaton);
                
                JPanel ProgressPannel = new JPanel();
                frmFileDownloaderAnd.getContentPane().add(ProgressPannel, BorderLayout.SOUTH);
                
                        // Create progress bars
                        JProgressBar downloadProgressBar = new JProgressBar(0, 100);
                        ProgressPannel.add(downloadProgressBar);
                JProgressBar extractProgressBar = new JProgressBar(0, 100);
                ProgressPannel.add(extractProgressBar);
                        
                        JScrollPane scrollPane = new JScrollPane();
                        frmFileDownloaderAnd.getContentPane().add(scrollPane, BorderLayout.CENTER);
                        
                        JTextPane txtpnInstuctions = new JTextPane();
                        txtpnInstuctions.setFont(new Font("Open Sans Semibold", Font.BOLD, 11));
                        txtpnInstuctions.setEditable(false);
                        scrollPane.setViewportView(txtpnInstuctions);
                        txtpnInstuctions.setText("Instuctions:\r\n1. Click on Download File and Extract File\r\n3. Click on Start the Application\r\nIf You have alredy clicked on Download File and Extract File then just Click on Start The Application\r\n\r\n(Help:\r\n\r\nIf the Buttons don't work (eg: Pong) then go into the folder called extracted_files-from-v1.7.JavaGameStuff then into the folder called JavaGameStuff-1.7 and then open the Launcher.jar and the buttons should work)");

        // Action listener for download button
        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        String fileUrl = "https://github.com/CodePearly/JavaGameStuff/archive/refs/tags/v1.7.zip"; // Change this URL
                        String savePath = "v1.7.JavaGameStuff";

                        HttpURLConnection httpConn = (HttpURLConnection) new URL(fileUrl).openConnection();
                        int responseCode = httpConn.getResponseCode();
                        
                        if (responseCode == HttpURLConnection.HTTP_OK) {
                            int contentLength = httpConn.getContentLength();

                            try (InputStream in = new BufferedInputStream(httpConn.getInputStream());
                                 FileOutputStream out = new FileOutputStream(savePath)) {

                                byte[] buffer = new byte[1024];
                                int bytesRead = -1;
                                long totalBytesRead = 0;

                                while ((bytesRead = in.read(buffer)) != -1) {
                                    out.write(buffer, 0, bytesRead);
                                    totalBytesRead += bytesRead;

                                    int progress = (int) (totalBytesRead * 100 / contentLength);
                                    publish(progress);
                                }

                                JOptionPane.showMessageDialog(frmFileDownloaderAnd, "File Downloaded Successfully!");
                                String zipFilePath = "v1.7.JavaGameStuff"; // Change this path
                                String destDir = "extracted_files-from-v1.7.JavaGameStuff"; // Change this directory

                                File destDirFile = new File(destDir);
                                if (!destDirFile.exists()) {
                                    destDirFile.mkdir();
                                }
                                

                                try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath))) {
                                    ZipEntry entry;
                                    long totalEntriesSize = 0;
                                    long entriesProcessedSize = 0;

                                    // Calculate total size of entries
                                    while ((entry = zipIn.getNextEntry()) != null) {
                                        totalEntriesSize += entry.getSize();
                                    }
                                    zipIn.close();

                                    // Extract entries
                                    try (ZipInputStream zipInAgain = new ZipInputStream(new FileInputStream(zipFilePath))) {
                                        while ((entry = zipInAgain.getNextEntry()) != null) {
                                            String filePath = destDir + File.separator + entry.getName();

                                            if (!entry.isDirectory()) {
                                                try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
                                                    byte[] buffer2 = new byte[1024];
                                                    int bytesRead2;
                                                    while ((bytesRead2 = zipInAgain.read(buffer2)) != -1) {
                                                        bos.write(buffer2, 0, bytesRead2);
                                                        entriesProcessedSize += bytesRead2;

                                                        int progress = (int) (entriesProcessedSize * 100 / totalEntriesSize);
                                                        publish(progress);
                                                    }
                                                }
                                            } else {
                                                new File(filePath).mkdir();
                                            }

                                            zipInAgain.closeEntry();
                                        }

                                        JOptionPane.showMessageDialog(frmFileDownloaderAnd, "File Extracted Successfully!");
                                        

                            } 
                            }
                        } 
                        }
                        httpConn.disconnect();
                        return null;
                    }

                    @Override
                    protected void process(java.util.List<Integer> chunks) {
                        for (int value : chunks) {
                            downloadProgressBar.setValue(value);
                        }
                    }

                    @Override
                    protected void done() {
                        downloadButton.setEnabled(true);
                    }
                };

                worker.execute();
                downloadButton.setEnabled(false);
            }
        });


        // Make the frame visible
        frmFileDownloaderAnd.setVisible(true);
    }
}