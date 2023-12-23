package com.example.shoping.controller;

import com.example.shoping.dto.ProductDTO;
import com.example.shoping.dto.ProductImageDTO;
import com.example.shoping.dto.UploadResultDTO;
import com.example.shoping.entity.ProductImage;
import com.example.shoping.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Log4j2
@RequestMapping("/upload")
@RequiredArgsConstructor
public class UploadController {
    private final ProductService productService;
    @Value("${org.zerock.upload.path}")
    private String uploadPath;
    @PostMapping("/uploadProduct")
    public ResponseEntity<List<ProductImageDTO>> uploadFile(@RequestParam MultipartFile[] files, ProductDTO productDTO) {
        List<ProductImageDTO> resultDTOList = new ArrayList<>();
        System.out.println("upload product: " + productDTO);
        for (MultipartFile uploadFile : files) {
            if (uploadFile.getContentType().startsWith("image") == false) {
                log.warn("this file is not image type");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String originalName = uploadFile.getOriginalFilename();
            String fileName = originalName.substring(originalName.indexOf("\\")+1);
            String folderPath = makeDir();
            String uuid = UUID.randomUUID().toString();


            String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;

            Path savePath = Paths.get(saveName);
            System.out.println("---------------------------------------------");
            System.out.println("originalName: " + originalName);
            System.out.println("fileName: " + fileName);
            System.out.println("folderPath: " + folderPath);
            System.out.println("uploadPath: " + uploadPath);
            System.out.println("saveName: " + saveName);
            System.out.println("savePath: " + savePath);
            System.out.println("---------------------------------------------");

            try {
                uploadFile.transferTo(savePath); //uploadFile.transferTo(new File(saveName));
                resultDTOList.add(new ProductImageDTO(uuid,fileName,folderPath));
                String thumbnailSaveName = uploadPath + File.separator + folderPath + File.separator
                        + "s_" + uuid + "_" + fileName;
                File thumbnailFile = new File(thumbnailSaveName);
                Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile,100,100);
                System.out.println("savePath: " + savePath.toFile());
                System.out.println("thumbnailFile: " + thumbnailFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        productService.registerProduct(productDTO, resultDTOList);
        return new ResponseEntity<>(resultDTOList, HttpStatus.OK);
    }

    @GetMapping("/display")
    public ResponseEntity<byte[]> getFile(String fileName, String size) {

        ResponseEntity<byte[]> result = null;

        try {
            String srcFileName =  URLDecoder.decode(fileName,"UTF-8");

            log.info("fileName: " + srcFileName);

            File file = new File(uploadPath +File.separator+ srcFileName);

            if(size != null && size.equals("1")){
                file = new File(file.getParent(), file.getName().substring(2));
            }

            log.info("file: " + file);

            HttpHeaders header = new HttpHeaders();

            //MIME타입 처리
            header.add("Content-Type", Files.probeContentType(file.toPath()));
            //파일 데이터 처리
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    public String makeDir(){
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folderPath = str.replace("/", File.separator);
        File file = new File(uploadPath, folderPath);
        System.out.println(file);

        if(!file.exists()){
            file.mkdirs();
            System.out.println("폴더생성");
        }
        return folderPath;
    }
}
