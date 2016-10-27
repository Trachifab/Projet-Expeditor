package fr.eni.expeditor.servlet;

import fr.eni.expeditor.service.LectureFichierCSVBean;
import fr.eni.expeditor.service.XLSToCSVBean;
import org.jboss.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ImportCSVServlet")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,  // 10 MB
        maxFileSize = 1024 * 1024 * 50,        // 50 MB
        maxRequestSize = 1024 * 1024 * 100)    // 100 MB
public class ImportCSVServlet extends AbstractServlet {
    private static final String UPLOAD_DIR = "uploads";
    private static Logger LOGGER = Logger.getLogger(ImportCSVServlet.class.getName());

    private static List<String> fichiersAutorise = Arrays.asList(".csv", ".xls", ".xlsx");

    @EJB
    private LectureFichierCSVBean lectureFichierCSVBean;

    @EJB
    private XLSToCSVBean xlsToCSVBean;

    @Override
    void action(String action, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info(action);
        switch (action) {
            case "import":
                LOGGER.info("Tentative d'import d'un fichier");
                importerFichier(request, response);
                break;
            default:
                LOGGER.info(String.format("L'action '%s' n'est pas reconnue", action));
                break;
        }
    }

    private void importerFichier(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Récupération du chemin absolu de l'application
        String applicationPath = request.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;

        // On vérifie si le répertoire temporaire existe, sinon on le créé.
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            LOGGER.info(String.format("Création du dossier temporaire %s", uploadFilePath));
            //noinspection ResultOfMethodCallIgnored
            fileSaveDir.mkdirs();
        }

        for (Part part : request.getParts()) {
            if ("action".equals(part.getName())) {
                continue;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_hh_mm");

            // On récupère le header du fichier uploadé afin de récupérer le nom du fichier puisqu'on peut pas récupérer le paramètre...
            String headerContentDisposition = part.getHeader("Content-Disposition");
            String headerFileName = headerContentDisposition.substring(headerContentDisposition.indexOf("filename="));
            String fileName = headerFileName.substring(headerFileName.indexOf('=') + 1).replace('\"', ' ').trim();

            // On récupère son extension
            int lastDot = fileName.lastIndexOf('.');
            String fileExtension = fileName.substring(lastDot);
            if (!fichiersAutorise.contains(fileExtension)) {
                LOGGER.info(String.format("L'extension %s n'est pas autorisée.", fileExtension));
            }

            // On prépare le fichier à uploader et on l'upload
            String uploadedFileName = String.format("import_%s%s", sdf.format(new Date()), fileExtension);
            String uploadedFileNameCompletePath = String.format("%s%s%s", uploadFilePath, File.separator, uploadedFileName);
            part.write(uploadedFileNameCompletePath);
            LOGGER.info(String.format("Le fichier %s à bien été importé", uploadedFileName));

            // Changement du format xls en csv
            if (fileExtension.toLowerCase().equals(".xls")) {
                uploadedFileNameCompletePath = xlsToCSVBean.xlsToCsv(uploadedFileNameCompletePath);
            }

            // Enfin, on va lire le fichier et importer les valeurs
            String result = lectureFichierCSVBean.lectureFichierCommandes(uploadedFileNameCompletePath);
            response.sendRedirect(String.format("%s/manager?csvResult=%s", request.getContextPath(), URLEncoder.encode(result, "UTF-8")));

        }
    }

    @Override
    void init(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }



}
