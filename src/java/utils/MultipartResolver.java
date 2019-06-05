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
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Christian Carrillo Zúñiga
 */
public class MultipartResolver {

    private static Pelicula pelicula = null;

    private static final String PATH_IMAGE
            = "/home/ciber/NetBeansProjects/diseno-y-arquitectura-de-software/web/resources/images/peliculas";

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
                        pelicula.setImagen(fileNameGenerator(item.getName()));
                        requestResolved.put("file", (FileItem) item);
                        break;
                    }
                }
                requestResolved.put("pelicula", pelicula);
            } catch (Exception e) {
                throw e;
            }
        }
        return requestResolved;
    }

    public static boolean saveFileInServer(FileItem file, String fileName)
            throws Exception {
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

    private static void assignFormFieldsToObject(FileItem fileItem) {
        switch (fileItem.getFieldName()) {
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

    private static String fileNameGenerator(String originalFileName) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        // ex: PATH_IMAGE/2019876545221.jpg
        return dateFormat.format(date) + "." + FilenameUtils.getExtension(originalFileName);
    }

}
