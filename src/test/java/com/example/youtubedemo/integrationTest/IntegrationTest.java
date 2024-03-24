package com.example.youtubedemo.integrationTest;

import com.example.youtubedemo.dto.ChannelDto;
import com.example.youtubedemo.dto.CommentDto;
import com.example.youtubedemo.dto.InteractionDto;
import com.example.youtubedemo.dto.VideoDto;
import com.example.youtubedemo.services.ChannelService;
import com.example.youtubedemo.services.CommentService;
import com.example.youtubedemo.services.InteractionService;
import com.example.youtubedemo.services.VideoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VideoService videoService;

    @MockBean
    private ChannelService channelService;

    @MockBean
    private CommentService commentService;

    @MockBean
    private InteractionService interactionService;

    @Test
    public void testChannelWatchingVideoAndLeavingCommentAndInteraction() throws Exception {

        // Create a test channel
        ChannelDto channelDto = new ChannelDto();
        channelDto.setName("Test Channel");

        // Create a test video
        VideoDto videoDto = new VideoDto();
        videoDto.setTitle("Test Video");
        videoDto.setDescription("Description for test video");
        videoDto.setDuration(Duration.ofMinutes(5));
        videoDto.setViews(0L);
        videoDto.setChannelId(1L);

        // Create a test comment
        CommentDto commentDto = new CommentDto();
        commentDto.setText("Great video!");
        commentDto.setVideoId(1L);

        // Create a test interaction
        InteractionDto interactionDto = new InteractionDto();
        interactionDto.setLiked(true);
        interactionDto.setVideoId(1L);

        // Send POST request to create a channel
        mockMvc.perform(post("/api/v1/channel")
                        .contentType("application/json")
                        .content("{\"name\": \"Test Channel\"}"))
                .andExpect(status().isCreated());

        // Send POST request to create a video
        mockMvc.perform(post("/api/v1/video")
                        .contentType("application/json")
                        .content("{\"title\": \"Test Video\", \"description\": \"Description for test video\", \"duration\": \"PT5M\", \"views\": 0, \"channelId\": 1, \"uploadDate\": \"" + LocalDateTime.now() + "\"}"))
                .andExpect(status().isCreated());


        // Send POST request to create a comment
        mockMvc.perform(post("/api/v1/comment")
                        .contentType("application/json")
                        .content("{\"text\": \"Great video!\", \"videoId\": 1}"))
                .andExpect(status().isCreated());

        // Send POST request to create an interaction
        mockMvc.perform(post("/interaction")
                        .contentType("application/json")
                        .content("{\"liked\": true, \"videoId\": 1}"))
                .andExpect(status().isCreated());
    }
}
