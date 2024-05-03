package com.coco.pibackend.Controller;

import com.coco.pibackend.Entity.Product;
import com.coco.pibackend.ServiceIMp.CloudService;
import com.coco.pibackend.ServiceIMp.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cloud")
public class CloudinaryController {

    private final CloudService cloudService;
    private final ProductService productService;



}
