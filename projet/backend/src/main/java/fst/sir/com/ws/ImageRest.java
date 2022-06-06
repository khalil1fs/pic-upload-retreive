package fst.sir.com.ws;


import fst.sir.com.bean.Image;
import fst.sir.com.service.facade.ImageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@CrossOrigin
@RestController
@RequestMapping("api/image")
public class ImageRest {

    @PostMapping("/")
    public Image save(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        Image image = new Image(file.getOriginalFilename(), file.getContentType(), compressBytes(file.getBytes()));
        return imageService.save(image);
    }

    @GetMapping("/name/{name}")
    public Image findByName(@PathVariable String name) throws IOException {
        Image retrievedImage = imageService.findByName(name);
        Image image = new Image(retrievedImage.getId(), retrievedImage.getName(), retrievedImage.getType(),
                decompressBytes(retrievedImage.getPicByte()));
        return image;
    }

    @GetMapping("/")
    public List<Image> findAll() {
        List<Image> imageSet = imageService.findAll();
        List<Image> result = new ArrayList<>();
        imageSet.forEach(e -> {
            result.add(new Image(e.getId(), e.getName(), e.getType(),
                    decompressBytes(e.getPicByte())));
        });
        return result;
    }

    @DeleteMapping("/id/{id}")
    public void delete(@PathVariable Long id) {
        imageService.delete(id);
    }

    public int save(Image[] images) {
        return imageService.save(images);
    }

    public int update(Image image) {
        return imageService.update(image);
    }


    private ImageService imageService;

    public ImageRest(ImageService imageService) {
        this.imageService = imageService;
    }


    // Compress the image bytes before returning it to the angular application
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    // extract the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException | DataFormatException ioe) {
        }
        return outputStream.toByteArray();
    }


}
