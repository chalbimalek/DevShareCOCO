package com.coco.pibackend.ServiceIMp;

import com.coco.pibackend.Entity.Post;
import com.coco.pibackend.Service.IPostservice;
import com.coco.pibackend.Repo.PostRepository;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


@Service
public class Postservice implements IPostservice {
    @Autowired
    PostRepository postRepository;
    private static final String UPLOADED_FOLDER = "uploads/";

    @Override
    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public List<Post> retrieveAllPost() {
        List<Post> listP = (List<Post>) postRepository.findAll();
        return listP;
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);

    }

    @Override
    public Post retrievePost(Long id) {
        Post post = postRepository.findById(id).get();
        return post;
    }

    @Override
    public Post updatePost(Long id, Post updatedPost) {
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            Post p = postOptional.get();
            p.setTopic(updatedPost.getTopic());
            p.setContenu(updatedPost.getContenu());
            return postRepository.save(p);
        }

        return null;
    }


    @Override
    public Post addFile(MultipartFile multipartFile, Long id) {

        if(multipartFile!= null) {
            Post post = postRepository.findById(id).get();


            try {
                Path uploadPath = Paths.get(UPLOADED_FOLDER).toAbsolutePath().normalize();
                Files.createDirectories(uploadPath);

                String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
                String fileExtension = FilenameUtils.getExtension(fileName);
                UUID randomUUID = UUID.randomUUID();
                fileName = randomUUID + "." + fileExtension;
                Path filePath = uploadPath.resolve(fileName);

                Files.write(filePath, multipartFile.getBytes(), StandardOpenOption.CREATE);

                post.setFileName(fileName);

                return   postRepository.save(post);


            } catch (IOException e) {
                throw new RuntimeException(e);
            }



        }


        return null;

    }

    @Override
    public ResponseEntity<byte[]> retrieveFile(Long id) throws IOException {

        Post post= postRepository.findById(id).get();
        String fileName = post.getFileName();
        File file = new File(UPLOADED_FOLDER + fileName);
        byte[] fileBytes = FileUtils.readFileToByteArray(file);
        if (fileBytes == null) {
            return ResponseEntity.notFound().build();
        }
        String extension = FilenameUtils.getExtension(fileName);
        MediaType mediaType = null;

        switch (extension.toLowerCase()) {
            case "pdf":
                mediaType = MediaType.APPLICATION_PDF;
                break;
            case "doc":
            case "docx":
                mediaType = MediaType.valueOf("application/msword");
                break;
            case "xls":
            case "xlsx":
                mediaType = MediaType.valueOf("application/vnd.ms-excel");
                break;
            case "ppt":
            case "pptx":
                mediaType = MediaType.valueOf("application/vnd.ms-powerpoint");
                break;
            case "jpg":
            case "jpeg":
                mediaType = MediaType.IMAGE_JPEG;
                break;
            case "png":
                mediaType = MediaType.IMAGE_PNG;
                break;
            case "gif":
                mediaType = MediaType.IMAGE_GIF;
                break;
            case "mp4":
                mediaType = MediaType.valueOf("video/mp4");
                break;
            case "txt":
                mediaType = MediaType.TEXT_PLAIN;
                break;
            default:
                mediaType = MediaType.APPLICATION_OCTET_STREAM;
                break;
        }

        return ResponseEntity.ok()
                .contentType(mediaType)
                .body(fileBytes);
    }


    @Override
    public List<Post> SearchPosts(String word) {
        return postRepository.searchByTitle(word);
    }


}
