package utils;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Class pertmit to serialize an object in to a xml file and vice versa.
 */
public final class XMLTools {

    /**
     * Don't do anything
     */
    private XMLTools() {
    }

    /**
     * Serialize the object in to the file called fileName
     * @param object object to be serialized
     * @param fileName destination of the serialize object
     * @throws FileNotFoundException throws if the file is not found
     * @throws IOException throws if the file cannot be opened or created
     */
    public static void encodeToFile(Object object, String fileName) throws FileNotFoundException, IOException {
        // opennig the encoder
        XMLEncoder encoder = new XMLEncoder(new FileOutputStream(fileName));
        try {
            // serialization of the object
            encoder.writeObject(object);
            encoder.flush();
        } finally {
            // closing the encoder
            encoder.close();
        }
    }

    /**
     * Deserialization of an object from a given file
     * @param fileName to be deserialize
     * @return the object
     * @throws FileNotFoundException throws if the file is not found
     * @throws IOException throws if the file cannot be opened
     */
    public static Object decodeFromFile(String fileName) throws FileNotFoundException, IOException {
        Object object = null;
        // openning the  decoder
        XMLDecoder decoder = new XMLDecoder(new FileInputStream(fileName));
        try {
            // deserialization of the objet
            object = decoder.readObject();
        } finally {
            // closing the  decoder
            decoder.close();
        }
        return object;
    }
}
