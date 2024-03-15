package com.example.youtubedemo.RepositoryTest;

import com.example.youtubedemo.Entity.Channel;
import com.example.youtubedemo.Entity.Comment;
import com.example.youtubedemo.Entity.Interaction;
import com.example.youtubedemo.Entity.Video;
import com.example.youtubedemo.repositories.ChannelRepository;
import com.example.youtubedemo.repositories.CommentRepository;
import com.example.youtubedemo.repositories.InteractionRepository;
import com.example.youtubedemo.repositories.VideoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class RepositoryTests {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private InteractionRepository interactionRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void testSaveAndRetrieveVideo() {
        Channel channel = new Channel();
        channel.setName("Test Channel");
        channel.setAvatar("test_avatar.png");
        channelRepository.save(channel);

        Video video = new Video();
        video.setTitle("Test Video");
        video.setDescription("Test Description");
        video.setUploadDate(LocalDateTime.now());
        video.setDuration(Duration.ofMinutes(5));
        video.setViews(100L);
        video.setChannel(channel);
        videoRepository.save(video);

        List<Video> savedVideos = videoRepository.findAll();
        assertEquals(1, savedVideos.size());
        assertEquals("Test Video", savedVideos.get(0).getTitle());
    }

    @Test
    public void testSaveAndRetrieveChannel() {
        Channel channel = new Channel();
        channel.setName("Test Channel");
        channel.setAvatar("test_avatar.png");
        channelRepository.save(channel);

        List<Channel> savedChannels = channelRepository.findAll();
        assertEquals(1, savedChannels.size());
        assertEquals("Test Channel", savedChannels.get(0).getName());
    }

    @Test
    public void testSaveAndRetrieveInteraction() {
        Channel channel = new Channel();
        channel.setName("Test Channel");
        channel.setAvatar("test_avatar.png");
        channelRepository.save(channel);

        Video video = new Video();
        video.setTitle("Test Video");
        video.setDescription("Test Description");
        video.setUploadDate(LocalDateTime.now());
        video.setDuration(Duration.ofMinutes(5));
        video.setViews(100L);
        video.setChannel(channel);
        videoRepository.save(video);

        Interaction interaction = new Interaction();
        interaction.setLiked(true);
        interaction.setVideo(video);
        interactionRepository.save(interaction);

        List<Interaction> savedInteractions = interactionRepository.findAll();
        assertEquals(1, savedInteractions.size());
        assertEquals(true, savedInteractions.get(0).isLiked());
    }

    @Test
    public void testSaveAndRetrieveComment() {
        Channel channel = new Channel();
        channel.setName("Test Channel");
        channel.setAvatar("test_avatar.png");
        channelRepository.save(channel);

        Video video = new Video();
        video.setTitle("Test Video");
        video.setDescription("Test Description");
        video.setUploadDate(LocalDateTime.now());
        video.setDuration(Duration.ofMinutes(5));
        video.setViews(100L);
        video.setChannel(channel);
        videoRepository.save(video);

        Comment comment = new Comment();
        comment.setText("Test Comment");
        comment.setCreatedAt(LocalDateTime.now());
        comment.setVideo(video);
        commentRepository.save(comment);

        List<Comment> savedComments = commentRepository.findAll();
        assertEquals(1, savedComments.size());
        assertEquals("Test Comment", savedComments.get(0).getText());
    }
}
