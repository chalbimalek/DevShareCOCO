package com.coco.pibackend.ServiceIMp;

import com.coco.pibackend.Entity.Post;
import com.coco.pibackend.Repo.PostRepository;
import com.coco.pibackend.Service.IPostservice;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements IPostservice {
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
/*implements PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final CommentService commentService;
    private final NotificationService notificationService;
    private final Environment environment;
    private final UserRepo  userRepo;


    @Override
    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
    }

    @Override
    public PostResponse getPostResponseById(Long postId) {
        String username = AuthTokenFilter.CURRENT_USER;
        User authUser = userRepo.findByUsername(username).get();
        Post foundPost = getPostById(postId);
        return PostResponse.builder()
                .post(foundPost)
                .likedByAuthUser(foundPost.getLikeList().contains(authUser))
                .build();
    }

    @Override
    public List<PostResponse> getTimelinePostsPaginate(Integer page, Integer size) {
        String username = AuthTokenFilter.CURRENT_USER;
        User authUser = userRepo.findByUsername(username).get();
        List<User> followingList = authUser.getFollowingUsers();
        followingList.add(authUser);
        List<Long> followingListIds = followingList.stream().map(User::getId).toList();
        return postRepository.findPostsByAuthorIdIn(
                        followingListIds,
                        PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "dateCreated")))
                .stream().map(this::postToPostResponse).collect(Collectors.toList());
    }

    @Override
    public List<PostResponse> getPostSharesPaginate(Post sharedPost, Integer page, Integer size) {
        return postRepository.findPostsBySharedPost(
                        sharedPost,
                        PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "dateCreated")))
                .stream().map(this::postToPostResponse).collect(Collectors.toList());
    }

    @Override
    public List<PostResponse> getPostByTagPaginate(Tag tag, Integer page, Integer size) {
        return postRepository.findPostsByPostTags(
                        tag,
                        PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "dateCreated")))
                .stream().map(this::postToPostResponse).collect(Collectors.toList());
    }

    @Override
    public List<PostResponse> getPostsByUserPaginate(User author, Integer page, Integer size) {
        return postRepository.findPostsByAuthor(
                        author,
                        PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "dateCreated")))
                .stream().map(this::postToPostResponse).collect(Collectors.toList());
    }

    @Override
    public Post createNewPost(String content, MultipartFile postPhoto) {
        String username = AuthTokenFilter.CURRENT_USER;
        Optional<User> optionalUser = userRepo.findByUsername(username);
        if (optionalUser.isPresent()) {
            User authUser = optionalUser.get();
            Post newPost = new Post();
            newPost.setContent(content);
            newPost.setAuthor(authUser);
            newPost.setLikeCount(0);
            newPost.setShareCount(0);
            newPost.setCommentCount(0);
            newPost.setIsTypeShare(false);
            newPost.setSharedPost(null);
            newPost.setDateCreated(new Date());
            newPost.setDateLastModified(new Date());
            System.out.println("Contenu du post : " + content);

            if (postPhoto != null && postPhoto.getSize() > 0) {
                System.out.println("Nom du fichier de la photo : " + postPhoto.getOriginalFilename());
                String uploadDir = environment.getProperty("upload.post.images");
                String newPhotoUrl = environment.getProperty("app.root.backend") + File.separator
                        + environment.getProperty("upload.post.images") + File.separator + newPhotoName;
                newPost.setPostPhoto(newPhotoUrl);
                try {
                    fileUploadUtil.saveNewFile(uploadDir, newPhotoName, postPhoto);
                } catch (IOException e) {
                    System.out.println("Erreur lors de la sauvegarde de la photo : " + e.getMessage());
                    throw new RuntimeException("Erreur lors de la sauvegarde de la photo", e);
                }
            }

            return postRepository.save(newPost);
        } else {
            throw new RuntimeException("Utilisateur introuvable pour le nom d'utilisateur : " + username);
        }
    }


    @Override
    public Post updatePost(Long postId, String content, MultipartFile postPhoto, List<TagDto> postTags) {
        Post targetPost = getPostById(postId);
        if (StringUtils.isNotEmpty(content)) {
            targetPost.setContent(content);
        }

        if (postPhoto != null && postPhoto.getSize() > 0) {
            String uploadDir = environment.getProperty("upload.post.images");
            String oldPhotoName = getPhotoNameFromPhotoUrl(targetPost.getPostPhoto());
            String newPhotoName = fileNamingUtil.nameFile(postPhoto);
            String newPhotoUrl = environment.getProperty("app.root.backend") + File.separator
                    + environment.getProperty("upload.post.images") + File.separator + newPhotoName;
            targetPost.setPostPhoto(newPhotoUrl);
            try {
                if (oldPhotoName == null) {
                    fileUploadUtil.saveNewFile(uploadDir, newPhotoName, postPhoto);
                } else {
                    fileUploadUtil.updateFile(uploadDir, oldPhotoName, newPhotoName, postPhoto);
                }
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }

        targetPost.setDateLastModified(new Date());
        return postRepository.save(targetPost);
    }

    @Override
    public void deletePost(Long postId) {
        String username = AuthTokenFilter.CURRENT_USER;
        User authUser = userRepo.findByUsername(username).get();
        Post targetPost = getPostById(postId);

        if (targetPost.getAuthor().equals(authUser)) {
            targetPost.getShareList().forEach(sharingPost -> {
                sharingPost.setSharedPost(null);
                postRepository.save(sharingPost);
            });

            notificationService.deleteNotificationByOwningPost(targetPost);

            postRepository.deleteById(postId);

            if (targetPost.getPostPhoto() != null) {
                String uploadDir = environment.getProperty("upload.post.images");
                String photoName = getPhotoNameFromPhotoUrl(targetPost.getPostPhoto());
                try {
                    fileUploadUtil.deleteFile(uploadDir, photoName);
                } catch (IOException ignored) {}
            }
        } else {
            throw new InvalidOperationException();
        }
    }

    @Override
    public void deletePostPhoto(Long postId) {
        String username = AuthTokenFilter.CURRENT_USER;
        User authUser = userRepo.findByUsername(username).get();
        Post targetPost = getPostById(postId);

        if (targetPost.getAuthor().equals(authUser)) {
            if (targetPost.getPostPhoto() != null) {
                String uploadDir = environment.getProperty("upload.post.images");
                String photoName = getPhotoNameFromPhotoUrl(targetPost.getPostPhoto());
                try {
                    fileUploadUtil.deleteFile(uploadDir, photoName);
                } catch (IOException ignored) {}
            }

            targetPost.setPostPhoto(null);
            postRepository.save(targetPost);
        } else {
            throw new InvalidOperationException();
        }
    }

    @Override
    public void likePost(Long postId) {
        String username = AuthTokenFilter.CURRENT_USER;
        User authUser = userRepo.findByUsername(username).get();        Post targetPost = getPostById(postId);
        if (!targetPost.getLikeList().contains(authUser)) {
            targetPost.setLikeCount(targetPost.getLikeCount() + 1);
            targetPost.getLikeList().add(authUser);
            postRepository.save(targetPost);

            if (!targetPost.getAuthor().equals(authUser)) {
                notificationService.sendNotification(
                        targetPost.getAuthor(),
                        authUser,
                        targetPost,
                        null,
                        NotificationType.POST_LIKE.name()
                );
            }
        } else {
            throw new InvalidOperationException();
        }
    }

    @Override
    public void unlikePost(Long postId) {
        String username = AuthTokenFilter.CURRENT_USER;
        User authUser = userRepo.findByUsername(username).get();
        Post targetPost = getPostById(postId);
        if (targetPost.getLikeList().contains(authUser)) {
            targetPost.setLikeCount(targetPost.getLikeCount()-1);
            targetPost.getLikeList().remove(authUser);
            postRepository.save(targetPost);

            if (!targetPost.getAuthor().equals(authUser)) {
                notificationService.removeNotification(
                        targetPost.getAuthor(),
                        targetPost,
                        NotificationType.POST_LIKE.name()
                );
            }
        } else {
            throw new InvalidOperationException();
        }
    }

    @Override
    public Comment createPostComment(Long postId, String content) {
        if (StringUtils.isEmpty(content)) throw new EmptyCommentException();

        String username = AuthTokenFilter.CURRENT_USER;
        User authUser = userRepo.findByUsername(username).get();
        Post targetPost = getPostById(postId);
        Comment savedComment = commentService.createNewComment(content, targetPost);
        targetPost.setCommentCount(targetPost.getCommentCount()+1);
        postRepository.save(targetPost);

        if (!targetPost.getAuthor().equals(authUser)) {
            notificationService.sendNotification(
                    targetPost.getAuthor(),
                    authUser,
                    targetPost,
                    savedComment,
                    NotificationType.POST_COMMENT.name()
            );
        }

        return savedComment;
    }

    @Override
    public Comment updatePostComment(Long commentId, Long postId, String content) {
        if (StringUtils.isEmpty(content)) throw new EmptyCommentException();

        return commentService.updateComment(commentId, content);
    }

    @Override
    public void deletePostComment(Long commentId, Long postId) {
        Post targetPost = getPostById(postId);
        commentService.deleteComment(commentId);
        targetPost.setCommentCount(targetPost.getCommentCount()-1);
        targetPost.setDateLastModified(new Date());
        postRepository.save(targetPost);
    }

    @Override
    public Post createPostShare(String content, Long postId) {
        String username = AuthTokenFilter.CURRENT_USER;
        User authUser = userRepo.findByUsername(username).get();
        Post targetPost = getPostById(postId);
        if (!targetPost.getIsTypeShare()) {
            Post newPostShare = new Post();
            newPostShare.setContent(content);
            newPostShare.setAuthor(authUser);
            newPostShare.setLikeCount(0);
            newPostShare.setShareCount(null);
            newPostShare.setCommentCount(0);
            newPostShare.setPostPhoto(null);
            newPostShare.setIsTypeShare(true);
            newPostShare.setSharedPost(targetPost);
            newPostShare.setDateCreated(new Date());
            newPostShare.setDateLastModified(new Date());
            Post savedPostShare = postRepository.save(newPostShare);
            targetPost.getShareList().add(savedPostShare);
            targetPost.setShareCount(targetPost.getShareCount()+1);
            postRepository.save(targetPost);

            if (!targetPost.getAuthor().equals(authUser)) {
                notificationService.sendNotification(
                        targetPost.getAuthor(),
                        authUser,
                        targetPost,
                        null,
                        NotificationType.POST_SHARE.name()
                );
            }

            return savedPostShare;
        } else {
            throw new InvalidOperationException();
        }
    }

    @Override
    public Post updatePostShare(String content, Long postShareId) {
        String username = AuthTokenFilter.CURRENT_USER;
        User authUser = userRepo.findByUsername(username).get();
        Post targetPostShare = getPostById(postShareId);
        if (targetPostShare.getAuthor().equals(authUser)) {
            targetPostShare.setContent(content);
            targetPostShare.setDateLastModified(new Date());
            return postRepository.save(targetPostShare);
        } else {
            throw new InvalidOperationException();
        }
    }

    @Override
    public void deletePostShare(Long postShareId) {
        String username = AuthTokenFilter.CURRENT_USER;
        User authUser = userRepo.findByUsername(username).get();
        Post targetPostShare = getPostById(postShareId);
        if (targetPostShare.getAuthor().equals(authUser)) {
            Post sharedPost = targetPostShare.getSharedPost();
            sharedPost.getShareList().remove(targetPostShare);
            sharedPost.setShareCount(sharedPost.getShareCount()-1);
            postRepository.save(sharedPost);
            postRepository.deleteById(postShareId);

            notificationService.deleteNotificationByOwningPost(targetPostShare);
        } else {
            throw new InvalidOperationException();
        }
    }

    private String getPhotoNameFromPhotoUrl(String photoUrl) {
        if (photoUrl != null) {
            String stringToOmit = environment.getProperty("app.root.backend") + File.separator
                    + environment.getProperty("upload.post.images") + File.separator;
            return photoUrl.substring(stringToOmit.length());
        } else {
            return null;
        }
    }

    private PostResponse postToPostResponse(Post post) {
        String username = AuthTokenFilter.CURRENT_USER;
        User authUser = userRepo.findByUsername(username).get();
        return PostResponse.builder()
                .post(post)
                .likedByAuthUser(post.getLikeList().contains(authUser))
                .build();
    }*/

