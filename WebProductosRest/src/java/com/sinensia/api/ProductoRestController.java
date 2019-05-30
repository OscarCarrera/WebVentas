package com.sinensia.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
//Decoradores en forma de anotación, descriptores de despliegue
@WebServlet(asyncSupported = true, urlPatterns = "/api/productos")
public class ProductoRestController extends HttpServlet {
    
    private ServicioProductosSingleton servProd;
    
    @Override
    public void init() {
        servProd = ServicioProductosSingleton.getInstancia();
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter escritorRespuesta=response.getWriter();
        response.setContentType("application/json;charset=UTF-8");
      
        BufferedReader bufRead=request.getReader();
        
        StringBuilder textoJson=new StringBuilder();
        for (String lineaJson=bufRead.readLine();lineaJson!=null;lineaJson=bufRead.readLine()){
        textoJson.append(lineaJson);
        } 
        bufRead.close();
        escritorRespuesta.println(textoJson.toString().toUpperCase());
        Gson gson=new Gson();
        Producto producto=gson.fromJson(textoJson.toString(),Producto.class);
        
        System.out.println("<<<<<<"+producto.getNombre());
        
        ServicioProductosSingleton sps=ServicioProductosSingleton.getInstancia();
        sps.modificar(producto);
        
        String jsonRespuesta=gson.toJson(producto);
        escritorRespuesta.println(jsonRespuesta);
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        BufferedReader bufRead = request.getReader();
        StringBuilder rJson = new StringBuilder();
        for (String linea = bufRead.readLine();linea != null; linea = bufRead.readLine()){
        rJson.append(linea);
        }
        bufRead.close();
        Gson gson = new Gson();
        Producto nuevoP = gson.fromJson(rJson.toString(), Producto.class);
        servProd.insertar(nuevoP);
        
        PrintWriter escritorRespuesta = response.getWriter();
        response.setContentType("application/json;charset=UTF-8");
        
        String JsonResp = gson.toJson(nuevoP);
        escritorRespuesta.println(JsonResp);
    }
        
    }
    

