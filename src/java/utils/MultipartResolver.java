package utils;

import entidades.Genero;
import entidades.Pelicula;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public class MultipartResolver {

    private static Pelicula pelicula = null;

    // ex: paths
    // in windows: C:\\...\\diseno-y-arquitectura-de-software\\web\\resources\\images\\peliculas
    // in linux: /home/.../diseno-y-arquitectura-de-software/web/resources/images/peliculas
    
    private static final String PATH_IMAGE
            = "/home/ciber/NetBeansProjects/diseno-y-arquitectura-de-software/web/resources/images/peliculas";

    /**
     * Resolve the request type: enctype="multipart/form-data"
     * when it comes with some file in request
     * 
     * @param   request from servlets 
     * @return  <code>HashMap<String, Object></code> with this structure:
     *          {"pelicula", Pelicula}
     *          {"file", FileItem} 
     * @throws  Exception 
     */
    public static HashMap<String, Object> resolveForm(HttpServletRequest request) throws Exception {

        HashMap<String, Object> requestResolved = null;

        if (ServletFileUpload.isMultipartContent(request)) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);

            try {
                List<FileItem> items = upload.parseRequest(request);

                pelicula = new Pelicula();
                requestResolved = new HashMap<>();

                for (FileItem item : items) {
                    if (item.isFormField()) {
                        assignFormFieldsToObject(item);
                    } else {
                        if (item.getString().isEmpty()) {
                            pelicula.setImagen(null);
                            requestResolved.put("file", null);
                        } else {
                            pelicula.setImagen(fileNameGenerator(item.getName()));
                            requestResolved.put("file", (FileItem) item);
                        }
                        break;
                    }
                }
                requestResolved.put("pelicula", pelicula);
            } catch (FileUploadException e) {
                throw e;
            }
        }
        return requestResolved;
    }

    /**
     * Save in server the file with the new name provided 
     * 
     * @param   file FileItem to save
     * @param   fileName String name to assign to File
     * @return  <code>boolean</code> value depending if the file was saved on server
     * @throws  Exception 
     */
    public static boolean saveFileInServer(FileItem file, String fileName) throws Exception {
        boolean isFileSaved = false;

        try {
            File path = new File(PATH_IMAGE);

            if (!path.exists()) {
                path.mkdirs();
            }
            File uploadedFile = new File(path + "/" + fileName);
            file.write(uploadedFile);
            isFileSaved = true;
        } catch (Exception e) {
            throw e;
        }
        return isFileSaved;
    }
    
    /**
     * Delete in server the file with the name provided 
     * 
     * @param fileName String name to deleted
     * @return <code>boolean</code> value depending if the file was deleted on server
     */
    public static boolean deleteFileInServer(String fileName) {
        boolean isFileDeleted = false;

        try {
            File path = new File(PATH_IMAGE);

            if (!path.exists()) {
                path.mkdirs();
            }
            File uploadedFile = new File(path + "/" + fileName);
            uploadedFile.delete();
            
            isFileDeleted = true;
        } catch (Exception e) {
            throw e;
        }
        return isFileDeleted;
    }

    /**
     * Assign parameters to Object Pelicula
     * 
     * @param fileItem FileItem to get his FieldName & assign his String to Object Pelicula
     */
    private static void assignFormFieldsToObject(FileItem fileItem) {
        switch (fileItem.getFieldName()) {
            case "id":
                pelicula.setId(Integer.parseInt(fileItem.getString()));
                break;
            case "nombre":
                pelicula.setNombre(fileItem.getString());
                break;
            case "descripcion":
                pelicula.setDescripcion(fileItem.getString());
                break;
            case "genero":
                pelicula.setGenero(new Genero(Integer.parseInt(fileItem.getString())));
                break;
        }
    }

    /**
     * Generates a new name based on the current SimpleDateFormat 
     * and is concatenated with his extention 
     * 
     * @param originalFileName String original name comes in request
     * @return <code>String</code> ex: "2019876545221.jpg"
     */
    private static String fileNameGenerator(String originalFileName) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        return dateFormat.format(date) + "." + FilenameUtils.getExtension(originalFileName);
    }
}
