/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sinensia.api;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class ServicioProductosSingleton {
    
    private ArrayList<Producto> listaProductos;
    
    public void insertar(Producto p){
    listaProductos.add(p);
    } 
    
    public Producto modificar(Producto p){
    p.setNombre(p.getNombre()+ " - Modificado");
    p.setPrecio(p.getPrecio()+" - Modificado");
    return p;
    }
    
    public Producto[] obtenerTodos(){
    return (Producto[]) listaProductos.toArray();
    }
    
    private final static ServicioProductosSingleton instancia=new ServicioProductosSingleton();
    
    
    private ServicioProductosSingleton(){
        this.listaProductos=new ArrayList();
           
    }
    
    public static ServicioProductosSingleton getInstancia(){          
   return instancia;
    }   
}

   
   


