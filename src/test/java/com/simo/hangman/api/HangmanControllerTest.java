package com.simo.hangman.api;

import static com.simo.hangman.domain.GameResult.ONGOING;
import static org.hamcrest.Matchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simo.hangman.domain.LetterGuess;
import com.simo.hangman.domain.PublicGameStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class HangmanControllerTest {

  private static final String GAME_PATH = "/game";

  @Autowired private MockMvc mvc;
  @Autowired private ObjectMapper objectMapper;

  @Test
  public void shouldCreateGame() throws Exception {
    mvc.perform(post(GAME_PATH).contentType(APPLICATION_JSON).accept(APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.gameResult", is(ONGOING.toString())))
        .andExpect(jsonPath("$.hits", is(0)))
        .andExpect(jsonPath("$.wrongGuessesCount", is(0)))
        .andExpect(jsonPath("$.guesses", is(empty())));
  }

  @Test
  public void shouldGuessLetter() throws Exception {
    String response =
        mvc.perform(post(GAME_PATH).contentType(APPLICATION_JSON).accept(APPLICATION_JSON))
            .andExpect(status().is2xxSuccessful())
            .andReturn()
            .getResponse()
            .getContentAsString();

    PublicGameStatus gameStatus = objectMapper.readValue(response, PublicGameStatus.class);

    String letter = "a";
    mvc.perform(
            post(GAME_PATH + "/" + gameStatus.getGameId())
                .content(
                    objectMapper.writeValueAsString(LetterGuess.builder().letter(letter).build()))
                .contentType(APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.gameResult", is(ONGOING.toString())))
        .andExpect(jsonPath("$.guesses", hasItem(letter.toUpperCase())));
  }

  @Test
  public void shouldDeleteGame() throws Exception {
    String response =
        mvc.perform(post(GAME_PATH).contentType(APPLICATION_JSON).accept(APPLICATION_JSON))
            .andExpect(status().is2xxSuccessful())
            .andReturn()
            .getResponse()
            .getContentAsString();

    PublicGameStatus gameStatus = objectMapper.readValue(response, PublicGameStatus.class);

    mvc.perform(delete(GAME_PATH + "/" + gameStatus.getGameId()))
        .andExpect(status().is2xxSuccessful());
  }

  @Test
  public void shouldGetGameConfig() throws Exception {
    mvc.perform(get(GAME_PATH + "/available-words").contentType(APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.word_list", not(empty())));
  }
}
