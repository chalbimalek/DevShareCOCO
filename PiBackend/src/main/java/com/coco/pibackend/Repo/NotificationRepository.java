package com.coco.pibackend.Repo;


import com.coco.pibackend.Entity.Comment;
import com.coco.pibackend.Entity.Notification;
import com.coco.pibackend.Entity.Post;
import com.coco.pibackend.Entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Optional<Notification> findByReceiverAndOwningPostAndType(User receiver, Post owningPost, String type);
    List<Notification> findNotificationsByReceiver(User receiver, Pageable pageable);
    List<Notification> findNotificationsByReceiverAndIsSeenIsFalse(User receiver);
    List<Notification> findNotificationsByReceiverAndIsReadIsFalse(User receiver);
    void deleteNotificationByOwningPost(Post owningPost);
    void deleteNotificationByOwningComment(Comment owningComment);
}
