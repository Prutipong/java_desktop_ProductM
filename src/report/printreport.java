/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report;

import dbutility.conDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author marchmeepool
 */
public class printreport {
//
//      String reportPath = "/report/reportfile/";
      String message = "ไม่พบข้อมูลที่ต้องการพิมพ์";
   conDB db = new conDB();
    Connection connection = null;
      public JasperReport getCompiledReport(String reportName) throws JRException {
            JasperReport jasperReport = null;
            try {
                  jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResource("/report/" + reportName + ".jasper"));
            } catch (NullPointerException ex) {
                  jasperReport = null;
            }
            return jasperReport;
      }

      public void viewReport(String reportName, Map parameters) {
            try {
                //link database
                  Class.forName(db.cl);
                   connection = DriverManager.getConnection(db.co);
                  JasperReport jasperReport = getCompiledReport(reportName);
                  if (jasperReport == null) {
                        JOptionPane.showMessageDialog(null, "File not Found: " + reportName + ".jasper");
                  } else {
                        JasperPrint jasperPrint = JasperFillManager.
                                fillReport(jasperReport, parameters, connection);

                        int pageSize = jasperPrint.getPages().size();
                        if (pageSize > 0) {
                              JasperViewer viewer = new JasperViewer(jasperPrint, false);
                              JDialog jviewer = new JDialog(new JFrame(), true);
                              jviewer.setTitle("Print Report" + reportName);
                              jviewer.setSize(1024, 768);
                              jviewer.getContentPane().add(viewer.getContentPane());
                              jviewer.setLocationRelativeTo(null);
                              jviewer.setVisible(true);
                        } else {
                              JOptionPane.showMessageDialog(null, "ไม่พบข้อมูล");
                        }
                  }
            } catch (Exception ex) {
                  JOptionPane.showMessageDialog(null, ex + "\n\tviewReport();");
                  ex.printStackTrace();
            }
      }

}
