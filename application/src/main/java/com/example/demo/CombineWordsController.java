package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * @author KevinSch
 * @since 18/06/2021
 */
@RestController
public class CombineWordsController {

    @Autowired
    private CombineWordsService combineWordsService;

    @RequestMapping(method = RequestMethod.POST, value = "/getWordsFromFile/{wordLength}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String getWordsFromFile(@PathVariable int wordLength, @RequestParam("file") MultipartFile receivedFile) {
        StringBuilder builder = new StringBuilder();
        try {
            // localPath
            //File file = new File("./resources/src/main/resources/" + receivedFile.getOriginalFilename());

            // path for runnable jar
            String filePath = new File("./").getAbsolutePath();
            File file = new File(filePath + "/resources/" + receivedFile.getOriginalFilename());

            if (file.createNewFile()) {
                FileOutputStream fout = new FileOutputStream(file);
                fout.write(receivedFile.getBytes());
                fout.close();

                List<String> output = combineWordsService.combineWordsPresentInFile(file, wordLength);

                output.forEach(s -> builder.append(s).append("\n"));

                if (!Files.deleteIfExists(file.toPath())) {
                    System.out.println(file.toPath() + "Could not be deleted.");
                }

                return builder.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Something went wrong while handeling the file.";
        }

        return builder.length() != 0 ? builder.toString() : "Something went wrong while reading the file.";
    }
}
