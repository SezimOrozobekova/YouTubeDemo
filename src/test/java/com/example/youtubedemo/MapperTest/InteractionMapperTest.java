package com.example.youtubedemo.MapperTest;

import com.example.youtubedemo.Entity.Interaction;
import com.example.youtubedemo.Entity.Video;
import com.example.youtubedemo.dto.InteractionDto;
import com.example.youtubedemo.mappers.InteractionMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InteractionMapperTest {

    @Test
    void testEntityToDto() {
        // Создаем объект Interaction
        Interaction interaction = new Interaction();
        interaction.setId(1L);
        interaction.setLiked(true);

        // Создаем объект Video
        Video video = new Video();
        video.setId(10L);
        interaction.setVideo(video);

        // Преобразуем Interaction в InteractionDto
        InteractionDto interactionDto = InteractionMapper.INSTANCE.entityToDto(interaction);

        // Проверяем, что InteractionDto не равен null
        assertNotNull(interactionDto);

        // Проверяем, что id в InteractionDto соответствует id в Interaction
        assertEquals(interaction.getId(), interactionDto.getId());

        // Проверяем, что videoId в InteractionDto соответствует id в Video
        assertEquals(interaction.getVideo().getId(), interactionDto.getVideoId());

        // Проверяем, что liked в InteractionDto соответствует liked в Interaction
        assertEquals(interaction.isLiked(), interactionDto.isLiked());
    }

    @Test
    void testDtoToEntity() {
        // Создаем объект InteractionDto
        InteractionDto interactionDto = new InteractionDto();
        interactionDto.setId(1L);
        interactionDto.setLiked(true);
        interactionDto.setVideoId(10L);

        // Преобразуем InteractionDto в Interaction
        Interaction interaction = InteractionMapper.INSTANCE.dtoToEntity(interactionDto);

        // Проверяем, что Interaction не равен null
        assertNotNull(interaction);

        // Проверяем, что id в Interaction соответствует id в InteractionDto
        assertEquals(interactionDto.getId(), interaction.getId());

        // Проверяем, что id в Video в Interaction соответствует videoId в InteractionDto
        assertEquals(interactionDto.getVideoId(), interaction.getVideo().getId());

        // Проверяем, что liked в Interaction соответствует liked в InteractionDto
        assertEquals(interactionDto.isLiked(), interaction.isLiked());
    }
}
