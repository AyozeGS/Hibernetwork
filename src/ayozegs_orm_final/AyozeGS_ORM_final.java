/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ayozegs_orm_final;

import controller.Controller;

/**
 *
 * @author Ayoze Gil Sosa
 */
public class AyozeGS_ORM_final {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Controller controller;
        //Bucle para ejecutar N sesiones paralelas para pruebas
        for(int i=0; i<1; i++){
            controller = new Controller();
        }
    }
    
}
