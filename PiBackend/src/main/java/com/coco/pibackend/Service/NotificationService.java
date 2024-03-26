package com.coco.pibackend.Service;



import com.coco.pibackend.Entity.Comment;
import com.coco.pibackend.Entity.Notification;
import com.coco.pibackend.Entity.Post;
import com.coco.pibackend.Entity.User;

import java.util.List;

public interface NotificationService {
    Notification getNotificationById(Long notificationId);
    Notification getNotificationByReceiverAndOwningPostAndType(User receiver, Post owningPost, String type);
    void sendNotification(User receiver, User sender, Post owningPost, Comment owningComment, String type);
    void removeNotification(User receiver, Post owningPost, String type);
    List<Notification> getNotificationsForAuthUserPaginate(Integer page, Integer size);
    void markAllSeen();
    void markAllRead();
    void deleteNotification(User receiver, Post owningPost, String type);
    void deleteNotificationByOwningPost(Post owningPost);
    void deleteNotificationByOwningComment(Comment owningComment);
}
