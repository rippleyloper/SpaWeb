/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


//import com.digitalpersona.onetouch.DPFPDataPurpose;
//import com.digitalpersona.onetouch.DPFPFeatureSet;
//import com.digitalpersona.onetouch.DPFPGlobal;
//import com.digitalpersona.onetouch.DPFPSample;
//import com.digitalpersona.onetouch.DPFPTemplate;
//import com.digitalpersona.onetouch.capture.DPFPCapture;
//import com.digitalpersona.onetouch.capture.event.DPFPDataAdapter;
//import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
//import com.digitalpersona.onetouch.capture.event.DPFPErrorAdapter;
//import com.digitalpersona.onetouch.capture.event.DPFPErrorEvent;
//import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusAdapter;
//import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
//import com.digitalpersona.onetouch.capture.event.DPFPSensorAdapter;
//import com.digitalpersona.onetouch.capture.event.DPFPSensorEvent;
//import com.digitalpersona.onetouch.processing.DPFPEnrollment;
//import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
//import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
//import static com.digitalpersona.onetouch.processing.DPFPTemplateStatus.TEMPLATE_STATUS_FAILED;
//import com.digitalpersona.onetouch.verification.DPFPVerification;
import conexion.Conexion;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.io.InputStream; 
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author Prueba
 * para hacer funcionar este metodo, primer se llama a Iniciar(el area donde escribe,el label donde imprime la imagen)
 *
 * luego start(el area donde imprime)
 * luego estadoHuella(textArea donde imprime), imprime el estao del sensor
 */
public class MetodosHuella extends javax.swing.JDialog {
    //se realiza este metodo para no cambiar la vista o el JFrame en cual se llame
//    public void noModificarVista()
//    {
//        try {          
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Imposible modificar el tema visual", "Lookandfeel inválido.",
//                    JOptionPane.ERROR_MESSAGE);
//        } 
//    }
//    //Se declaran las variables para captura,verificacion,insercion y template de la huella
//    private DPFPCapture lector;
//    private DPFPEnrollment reclutador;
//    private DPFPVerification verificador;
//    private DPFPTemplate template;
//    public static String TEMPLATE_PROPERTY="template";
//    private DPFPFeatureSet featureIncripcion;
//    private DPFPFeatureSet featureVerifiacion;
//
//    public DPFPFeatureSet getFeatureVerifiacion() {
//        return featureVerifiacion;
//    }
//    
//    public DPFPVerification getVerificador() {
//        return verificador;
//    }
//    
//    private void MetodosHuella()
//    {
//        lector=new DPFPGlobal().getCaptureFactory().createCapture();
//        reclutador=new DPFPGlobal().getEnrollmentFactory().createEnrollment();
//        verificador=new DPFPGlobal().getVerificationFactory().createVerification();
//    }
//    
//    public void iniciar(JTextArea area,JLabel label)
//    {
//        MetodosHuella();
//        lector.addDataListener(new DPFPDataAdapter(){
//        @Override public void dataAcquired(final DPFPDataEvent e){
//            SwingUtilities.invokeLater(new Runnable(){
//                public void run(){
//                    enviarTexto("Huella Capturada",area);
//                    procesarCaptura(e.getSample(),area,label);
//                }
//            });
//        }
//        });
//        
//        lector.addReaderStatusListener(new DPFPReaderStatusAdapter(){
//            @Override public void readerConnected(final DPFPReaderStatusEvent e){
//                SwingUtilities.invokeLater(new Runnable(){
//                    public void run(){
//                        enviarTexto("El sensor esta activado",area);
//                    }
//                });
//            } 
//            @Override public void readerDisconnected(final DPFPReaderStatusEvent e){
//                SwingUtilities.invokeLater(new Runnable(){
//                    public void run(){
//                        enviarTexto("El sensor esta desactivado",area);
//                    }
//                });
//            }
//        });
//        
//        lector.addSensorListener(new DPFPSensorAdapter(){
//            @Override public void fingerTouched(final DPFPSensorEvent e){
//                SwingUtilities.invokeLater(new Runnable(){
//                    public void run(){
//                        enviarTexto("Dedo puesto en el sensor",area);
//                    }
//                });
//            }
//            @Override public void fingerGone(final DPFPSensorEvent e){
//                SwingUtilities.invokeLater(new Runnable(){
//                    public void run(){
//                        enviarTexto("Dedo quitado del sensor",area);
//                    }
//                });
//            }
//        });
//        
//        lector.addErrorListener(new DPFPErrorAdapter(){
//            public void errorReader(final DPFPErrorEvent e){
//                SwingUtilities.invokeLater(new Runnable(){
//                    public void run(){
//                        enviarTexto("Error:"+e.getError(),area);
//                    }
//                });
//            }
//                    
//        });
//        
//    }
//    
//    public void procesarCaptura(DPFPSample sample,JTextArea area,JLabel label)
//    {
//        // Procesar la muestra de la huella y crear un conjunto de características con el propósito de inscripción.
//        featureIncripcion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);
//
// // Procesar la muestra de la huella y crear un conjunto de características con el propósito de verificacion.
//        featureVerifiacion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);
//
// // Comprobar la calidad de la muestra de la huella y lo añade a su reclutador si es bueno
//        if (featureIncripcion != null)
//            try{
//                System.out.println("Las Caracteristicas de la Huella han sido creada");
//                reclutador.addFeatures(featureIncripcion);// Agregar las caracteristicas de la huella a la plantilla a crear
//
//     // Dibuja la huella dactilar capturada.
//                Image image=CreatImagenHuella(sample);
//                dibujarHuella(image,label);
//     
//     
//            }catch (DPFPImageQualityException ex) {
//                System.err.println("Error: "+ex.getMessage());
//            }
//
//            finally {
//     
//                EstadoHuella(area);
//                 // Comprueba si la plantilla se ha creado.
//                switch(reclutador.getTemplateStatus())
//                {
//                    case TEMPLATE_STATUS_READY:// informe de éxito y detiene  la captura de huellas
//                    stop(area);
//                    setTemplate(reclutador.getTemplate());//Para finalizar El template
//                    enviarTexto("Plantilla creada",area);
//
//                    break;
//
//                    case TEMPLATE_STATUS_FAILED: // informe de fallas y reiniciar la captura de huellas
//                    vaciarReclutador();
//                    stop(area);
//                    EstadoHuella(area);
//                    setTemplate(null);
//
//                    start(area);
//                    break;
//                }
//	    }
//    }
//    
//    public void enviarTexto(String texto,JTextArea textArea)
//    {
//        textArea.append(texto+"\n");
//    }
//    
//    public DPFPFeatureSet extraerCaracteristicas(DPFPSample sample,DPFPDataPurpose purpose)
//    {
//        try{
//            DPFPFeatureExtraction extrator= DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
//            return extrator.createFeatureSet(sample,purpose);
//        }
//        catch(DPFPImageQualityException ex)
//        {
//            return null;
//        }
//    }
//    
//    public Image CreatImagenHuella(DPFPSample sample){
//        return DPFPGlobal.getSampleConversionFactory().createImage(sample);
//    }
//    
//    public void dibujarHuella(Image image,JLabel label){
//        label.setIcon(new ImageIcon(image.getScaledInstance(label.getWidth(), label.getHeight(), image.SCALE_DEFAULT)));
//        repaint();
//    }
//    
//    public void EstadoHuella(JTextArea area){
//        enviarTexto("Intentos "+reclutador.getFeaturesNeeded()+" restantes",area);
//    }
//    
//    public void start(JTextArea area){
//        lector.startCapture();
//        enviarTexto("Utilizando el lector", area);
//    }
//    
//    public void stop(JTextArea area){
//        lector.stopCapture();
//        enviarTexto("no se esta utilizando el lector", area);
//    }
//
//    public DPFPTemplate getTemplate() {
//        return template;
//    }
//
//    public void setTemplate(DPFPTemplate template) {
//        DPFPTemplate old=this.template;
//        this.template = template;
//        firePropertyChange(TEMPLATE_PROPERTY, old, template);
//    }
//    
//    public void vaciarReclutador()
//    {
//        reclutador.clear();
//    }
//    
//    public DPFPEnrollment getReclutador(){
//        return reclutador;
//    }
    
    
}
