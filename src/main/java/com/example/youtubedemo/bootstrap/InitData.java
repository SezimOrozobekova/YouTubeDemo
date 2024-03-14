package com.example.youtubedemo.bootstrap;

import com.example.youtubedemo.Entity.Channel;
import com.example.youtubedemo.Entity.Comment;
import com.example.youtubedemo.Entity.Interaction;
import com.example.youtubedemo.Entity.Video;
import com.example.youtubedemo.repositories.ChannelRepository;
import com.example.youtubedemo.repositories.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitData implements CommandLineRunner {

    private final VideoRepository videoRepository;
    //If the Channel entity has a cascade type defined for its videos collection, such as CascadeType.ALL, changes made to the videos collection within a Channel object will be cascaded to the associated Video entities. This means that when you save a Channel object using the channelRepository, any new or modified Video entities within its videos collection will also be saved to the database automatically.

    private final ChannelRepository channelRepository;

    @Override
    public void run(String... args) throws Exception {

        List<Channel> channels = generateSampleChannels();
        channelRepository.saveAll(channels);
    }
    private List<Video> generateSampleVideos(Channel channel) {
        List<Video> videos = new ArrayList<>();

        for (int i = 1; i <= 2; i++) {
            Video video = Video.builder()
                    .title("Video " + i)
                    .description("Description for Video " + i)
                    .uploadDate(LocalDateTime.now().minusDays(i))
                    .duration(Duration.ofMinutes((long) (Math.random() * 60)))
                    .views((long) (Math.random() * 10000))
                    .channel(channel) // Set the channel directly
                    .build();

            List<Comment> comments = generateSampleComments(video);
            video.setComments(comments);

            List<Interaction> interactions = generateSampleInteractions(video);
            video.setInteractions(interactions);

            videos.add(video);
        }
        return videos;
    }
    private List<Channel> generateSampleChannels() {
        List<Channel> channels = new ArrayList<>();

        for (Long i = 1L; i <= 5L; i++) {
            Channel channel = Channel.builder()
                    .name("Channel " + i)
                    .avatar("avatar_" + i + ".png")
                    .build();
            // Build the channel first

            List<Video> videos = generateSampleVideos(channel); // Generate sample videos for this channel
            channel.setVideos(videos); // Set the generated videos to the channel

            channels.add(channel);
        }
        return channels;
    }
    private List<Comment> generateSampleComments(Video video) {
        List<Comment> comments = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            Comment comment = Comment.builder()
                    .text("Comment " + i + " for Video " + video.getTitle())
                    .createdAt(LocalDateTime.now().minusMinutes(i))
                    .video(video)
                    .build();

            comments.add(comment);
        }
        return comments;
    }

    private List<Interaction> generateSampleInteractions(Video video) {
        List<Interaction> interactions = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            Interaction interaction = Interaction.builder()
                    .liked(Math.random() < 0.5) // Randomly set liked or not
                    .video(video)
                    .build();

            interactions.add(interaction);
        }
        return interactions;
    }

}
