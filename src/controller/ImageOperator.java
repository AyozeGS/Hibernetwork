/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Ayoze Gil Sosa
 */
public class ImageOperator {
    
    //Salva una imagen dada la instancia ImageIcon y la ruta especificada
    static protected boolean savePhoto(Icon icon, String path) throws IOException{
        Boolean b = false;
        ImageIcon imgicon = (ImageIcon)icon;
        Image img = imgicon.getImage();
        BufferedImage bi = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TRANSLUCENT);
        Graphics2D g2 = bi.createGraphics();
        g2.drawImage(img, 0, 0, null);
        g2.dispose();
        ImageIO.write(bi, "png", new File(path));
        b = true;
        return b;
    }
    
    //Carga la foto en la ventana de registro
    static protected boolean loadPhoto(JLabel label) {
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
        file.addChoosableFileFilter(filter);
        file.setFileFilter(filter);
        int result = file.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION){
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            label.setIcon(resizeImage(path, label));
            return true;
        }
        else if(result == JFileChooser.CANCEL_OPTION){
            return false;
        }
        return false;
    }   
    
    //Reescala la imagen al contenedor donde se va a mostrar
    static protected Icon resizeImage(String path, JLabel label){
        int width = label.getSize().width;
        int height = label.getSize().height;
        ImageIcon imgicon = new ImageIcon(path);
        Image img = imgicon.getImage().getScaledInstance(
                width, height, Image.SCALE_SMOOTH);
        //mv.getRegisterPanel().setImage(img);
        imgicon = new ImageIcon(img);
        return (Icon)imgicon;
    }
    
    //Reescala la imagen al contenedor donde se va a mostrar
    static protected Icon resizeImage(String path, int width, int height){
        ImageIcon imgicon = new ImageIcon(path);
        Image img = imgicon.getImage().getScaledInstance(
                width, height, Image.SCALE_SMOOTH);
        //mv.getRegisterPanel().setImage(img);
        imgicon = new ImageIcon(img);
        return (Icon)imgicon;
    }
    
    //Carga la imagen en el perfil del usuario
    static protected ImageIcon loadImage(String path){
        ImageIcon imgicon = new ImageIcon(path);
        int width = imgicon.getIconWidth();
        int height = imgicon.getIconHeight();
        Image img = imgicon.getImage().getScaledInstance(
                width, height, Image.SCALE_SMOOTH);
        //mv.getRegisterPanel().setImage(img);
        imgicon = new ImageIcon(img);
        return imgicon;
    }
    
}
