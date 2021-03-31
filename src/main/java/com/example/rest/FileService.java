package com.example.rest;



import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

@Path("/files")
public class FileService {

    private static final String FILE_PATH = "C:\\Users\\Subtain\\Documents\\GitHub\\T03\\pom.xml";

    /**
     *
     * @return
     */
    @GET
    @Path("/txt")
    @Produces("text/plain")
    public File getFile(){
        return new File(FILE_PATH);
    }

    /**
     *
     * @param uploadedInputStream
     */
    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public void uploadFile(@FormDataParam("file") InputStream uploadedInputStream){
        String fileLocation = "C:\\Users\\Subtain\\Documents\\GitHub\\T03\\img\\test.txt";
        try
        {
            int read = 0;
            byte[] bytes = new byte[1024];
            FileOutputStream out = new FileOutputStream(fileLocation);

            while((read = uploadedInputStream.read(bytes)) != -1){
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
