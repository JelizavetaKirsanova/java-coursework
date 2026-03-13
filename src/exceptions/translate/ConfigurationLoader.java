package exceptions.translate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigurationLoader {

    public String readConfiguration(String configFilePath) {
        Path path = Path.of(configFilePath);
        try {


            if (Files.isDirectory(path)) {
                throw new ConfigurationException(
                        new IOException("Path points to a directory")
                );
            } return Files.readString(path);}

            catch (NoSuchFileException e) {
                throw new ConfigurationException(e);
            }
        catch (ConfigurationException e) {
            throw e;}
            catch (IOException e) {
                throw new InstallationException(e);
            }}}










