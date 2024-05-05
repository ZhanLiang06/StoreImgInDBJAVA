/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import JPAEntity.TestImg;
import com.sun.scenario.effect.ImageData;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.transaction.UserTransaction;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author User
 */
@MultipartConfig
public class storeImgData extends HttpServlet {
    @PersistenceContext EntityManager em;
    @Resource UserTransaction utx;
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check if the request is multipart/form-data
        // Retrieve the uploaded file as a Part
        Part filePart = request.getPart("imageFile");
        String fileName = filePart.getSubmittedFileName();
        if (filePart != null) {
            // Read the file data into a byte array
            byte[] fileData = readFileData(filePart);
            
            // Store the file data in the database using JPA
            storeImageInDatabase(fileData);

            // Send a success response to the client
            response.getWriter().write("File uploaded and stored in the database successfully.");
        } else {
            response.getWriter().write("No file uploaded.");
        }
    }
    
    
    private byte[] readFileData(Part filePart) throws IOException {
        try (InputStream inputStream = filePart.getInputStream()) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            byte[] buffer = new byte[1096];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            return outputStream.toByteArray();
        }
    }
    
    private void storeImageInDatabase(byte[] imageData) {
        // Create a new ImageData entity and set the file data
        TestImg imageDataEntity = new TestImg();
        imageDataEntity.setImgId("00001");
        imageDataEntity.setImgData(imageData);

        // Persist the entity using JPA
        try{
            utx.begin();
            em.persist(imageDataEntity);
            utx.commit();
        }catch(Exception ex){
            
        }
        
    }

    
}

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
   


