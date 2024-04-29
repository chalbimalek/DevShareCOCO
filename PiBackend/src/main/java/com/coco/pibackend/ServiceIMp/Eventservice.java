package com.coco.pibackend.ServiceIMp;

import com.coco.pibackend.Entity.Event;
import com.coco.pibackend.Repo.EventsRepository;
import com.coco.pibackend.Service.IEventservice;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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
public  class Eventservice {/*implements IEventservice {
    private static final String UPLOADED_FOLDER = "uploads/";

    @Autowired
    private EventsRepository eventRepository;

    @Override
    public Event ajouterEvents(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public List<Event> retrieveAllEvent() {
        List<Event> events = (List<Event>) eventRepository.findAll();
        return events;

    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);

    }

    @Override
    public Optional<Event> retrievEventByID(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        return event;

    }

    @Override
    public Event updateEvent(Long id, Event updatedvent) {
        Event E = eventRepository.findById(id).orElse(null);
        if (E != null) {
            E.setNumberParticipant(updatedvent.getNumberParticipant());
            E.setStartDate(updatedvent.getStartDate());
            E.setEndDate(updatedvent.getEndDate());
            E.setLocation_event(updatedvent.getLocation_event());
            E.setCreated(updatedvent.getCreated());
            E.setTopic(updatedvent.getTopic());


            return eventRepository.save(E);
        }

        return null;
    }

    @Override
    public List<Event> searchEventByTopic(String topic) {
        return eventRepository.findByTopic(topic);
    }
    @Override
    public List<Event> searchEventAdvanced(Event event) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Event> example = Example.of(event, matcher);
        return eventRepository.findAll(example);
    }

    public Event addFile(MultipartFile multipartFile, Long id) {

        if(multipartFile!= null) {

            Event event = eventRepository.findById(id).get();


            try {
                Path uploadPath = Paths.get(UPLOADED_FOLDER).toAbsolutePath().normalize();
                Files.createDirectories(uploadPath);

                String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
                String fileExtension = FilenameUtils.getExtension(fileName);
                UUID randomUUID = UUID.randomUUID();
                fileName = randomUUID + "." + fileExtension;
                Path filePath = uploadPath.resolve(fileName);

                Files.write(filePath, multipartFile.getBytes(), StandardOpenOption.CREATE);

                event.setFileName(fileName);

                return   eventRepository.save(event);


            } catch (IOException e) {
                throw new RuntimeException(e);
            }



        }


        return null;

    }

    public ResponseEntity<byte[]> retrieveFile(Long id) throws IOException {

        Event event= eventRepository.findById(id).get();
        String fileName = event.getFileName();
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
    }*/

}






