package utils;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public final class XMLTools {

    private XMLTools() {
    }

    /**
     * Serialization of an object in to a file
     * @param object object to serialize
     * @param filename path to the file
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
     * Deserialization of an object from a file
     * @param filename path to the file
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
