package dbgateway.utils.files;

import dbgateway.config.def.DatabaseConf;
import dbgateway.exception.FileManagerException;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class XmlFileManager {

    protected File targetFolder;

    protected static XmlFileManager instance;

    private XmlFileManager(File folder) throws FileManagerException {
        if (folder.exists() && !folder.isDirectory()) {
            throw new FileManagerException(700, "The parameter should be a folder");
        }
        this.targetFolder = folder;
    }
    public static XmlFileManager getInstance(File folder) throws FileManagerException {
        if (instance == null){
            instance = new XmlFileManager(folder);
        }
        return instance;
    }

    public Object getFileContent(Path filePath) throws IOException {
        try {
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
            JAXBContext jaxbContext = JAXBContext.newInstance(DatabaseConf.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return unmarshaller.unmarshal(filePath.toFile());
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeFileContent(DatabaseConf databaseConf, Path filePath) throws IOException {
        try {
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
            JAXBContext jaxbContext = JAXBContext.newInstance(DatabaseConf.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(databaseConf, filePath.toFile());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public boolean pathExist(Path dbConfPath) {
        return Files.exists(dbConfPath);
    }
}
